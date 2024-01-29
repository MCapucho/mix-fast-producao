package br.com.postech.mixfastproducao.dataproviders.gateway.api.token;

import br.com.postech.mixfastproducao.dataproviders.model.rest.token.TokenApiRequest;
import br.com.postech.mixfastproducao.dataproviders.model.rest.token.TokenApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenGatewayImpl {

    @Value("${feign.client.config.token.client}")
    private String clientId;

    private final ITokenClient tokenClient;

    public String gerarToken() {
        TokenApiRequest tokenApiRequest = TokenApiRequest.builder()
                .cpf(clientId)
                .build();

        TokenApiResponse tokenApiResponse = tokenClient.gerarToken(tokenApiRequest);

        return "Bearer " + tokenApiResponse.getIdToken();
    }
}
