package br.com.postech.mixfastproducao.dataproviders.gateway.api.token;

import br.com.postech.mixfastproducao.dataproviders.exception.ResourceApiException;
import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class TokenErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (HttpStatus.valueOf(response.status()).is5xxServerError()) {
            return new RetryableException(response.status(),
                    "Erro na comunicação com o servidor",
                    response.request().httpMethod(),
                    new Date().getTime(),
                    response.request());
        } else if (HttpStatus.valueOf(response.status()).is4xxClientError()) {
            return new ResourceApiException("Erro nos dados informados");
        } else {
            return errorDecoder.decode(methodKey, response);
        }
    }
}
