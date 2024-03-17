package br.com.postech.mixfastproducao.dataproviders.gateway.api.pedido;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.gateway.PedidoGateway;
import br.com.postech.mixfastproducao.dataproviders.gateway.api.token.TokenGatewayImpl;
import br.com.postech.mixfastproducao.dataproviders.model.rest.pedido.PedidoApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PedidoGatewayImpl implements PedidoGateway {

    private final IPedidoClient pedidoClient;
    private final TokenGatewayImpl tokenGatewayImpl;

    @Override
    public Pedido buscarPorCodigo(String codigo) {
        String token = tokenGatewayImpl.gerarToken();
        PedidoApiResponse pedidoApiResponse = pedidoClient.buscarPorCodigo(codigo, token);

        return Pedido.builder()
                .codigo(pedidoApiResponse.getCodigo())
                .fila(pedidoApiResponse.getFila())
                .status(pedidoApiResponse.getStatus())
                .statusPagamento(pedidoApiResponse.getStatusPagamento())
                .build();
    }

    @Override
    public void atualizarStatus(String codigo, String status) {
        String token = tokenGatewayImpl.gerarToken();
        pedidoClient.atualizarStatus(codigo, status, token);
    }
}
