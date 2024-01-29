package br.com.postech.mixfastproducao.dataproviders.gateway.api.pedido;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.dataproviders.gateway.api.token.TokenGatewayImpl;
import br.com.postech.mixfastproducao.dataproviders.model.rest.pedido.PedidoApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoGatewayImplTest {

    private static final String CODIGO = UUID.randomUUID().toString();

    @InjectMocks
    private PedidoGatewayImpl pedidoGatewayImpl;
    @Mock
    private IPedidoClient pedidoClient;
    @Mock
    private TokenGatewayImpl tokenGateway;

    private PedidoApiResponse pedidoApiResponse;

    @BeforeEach
    void setUp() {
        pedidoApiResponse = PedidoApiResponse.builder()
                .codigo(UUID.randomUUID().toString())
                .fila(1)
                .status("Recebido")
                .build();
    }

    @Test
    void deveBuscarPorCodigoPedidoComSucesso() {
        when(tokenGateway.gerarToken())
                .thenReturn("abc1234");

        when(pedidoClient.buscarPorCodigo(anyString(), anyString()))
                .thenReturn(pedidoApiResponse);

        Pedido pedidoEncontrado = pedidoGatewayImpl.buscarPorCodigo(CODIGO);

        assertNotNull(pedidoEncontrado);
        assertEquals(1, pedidoEncontrado.getFila());
    }

    @Test
    void deveAtualizarStatusBuscPedidoComSucesso() {
        when(tokenGateway.gerarToken())
                .thenReturn("abc1234");

        when(pedidoClient.atualizarStatus(anyString(), anyString(), anyString()))
                .thenReturn(pedidoApiResponse);

        pedidoGatewayImpl.atualizarStatus(CODIGO, "preparamento");

        verify(pedidoClient, times(1)).atualizarStatus(anyString(), anyString(), anyString());
    }
}