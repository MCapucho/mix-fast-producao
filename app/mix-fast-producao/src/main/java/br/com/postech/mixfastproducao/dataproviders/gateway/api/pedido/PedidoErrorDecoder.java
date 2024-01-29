package br.com.postech.mixfastproducao.dataproviders.gateway.api.pedido;

import br.com.postech.mixfastproducao.dataproviders.exception.ResourceApiException;
import br.com.postech.mixfastproducao.dataproviders.model.rest.RestMensagemErro;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class PedidoErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        RestMensagemErro message;

        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            message = mapper.readValue(bodyIs, RestMensagemErro.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }

        if (HttpStatus.valueOf(response.status()).is5xxServerError()) {
            return new RetryableException(response.status(),
                    "Erro na comunicação com o servidor",
                    response.request().httpMethod(),
                    new Date().getTime(),
                    response.request());
        } else if (HttpStatus.valueOf(response.status()).is4xxClientError()) {
            return new ResourceApiException(message.getErrors().get(0) != null ?
                    message.getErrors().get(0) :
                    "Erro nos dados informados");
        } else {
            return errorDecoder.decode(methodKey, response);
        }
    }
}
