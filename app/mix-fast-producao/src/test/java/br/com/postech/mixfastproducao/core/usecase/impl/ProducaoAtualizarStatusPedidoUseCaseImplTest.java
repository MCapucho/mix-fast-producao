package br.com.postech.mixfastproducao.core.usecase.impl;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.exception.PedidoConflictException;
import br.com.postech.mixfastproducao.core.gateway.PedidoGateway;
import br.com.postech.mixfastproducao.core.gateway.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProducaoAtualizarStatusPedidoUseCaseImplTest {

    private static final String CODIGO = UUID.randomUUID().toString();

    @InjectMocks
    private ProducaoAtualizarStatusPedidoUseCaseImpl producaoAtualizarStatusPedidoUseCaseImpl;
    @Mock
    private PedidoGateway pedidoGateway;
    @Mock
    private PedidoRepository pedidoRepository;

    private Pedido pedido;

    @BeforeEach
    void setUp() {
        pedido = Pedido.builder()
                .codigo(UUID.randomUUID().toString())
                .fila(1)
                .build();
    }

    @Test
    void deveAtualizarStatusPedidoComSucesso_Preparar_StatusPagamentoReprovado() {
        pedido.setStatusPagamento("REPROVADO");

        when(pedidoGateway.buscarPorCodigo(anyString()))
                .thenReturn(pedido);

        Exception exception = Assertions.assertThrows(PedidoConflictException.class, () ->
                producaoAtualizarStatusPedidoUseCaseImpl.preparar(CODIGO));

        String mensagemEsperada = String.format("O status do pagamento do pedido %s está reprovado.", CODIGO);
        String mensagemAtual = exception.getMessage();

        Assertions.assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveAtualizarStatusPedidoComSucesso_Preparar_StatusPagamentoAguardando() {
        pedido.setStatusPagamento("AGUARDANDO");

        when(pedidoGateway.buscarPorCodigo(anyString()))
                .thenReturn(pedido);

        Exception exception = Assertions.assertThrows(PedidoConflictException.class, () ->
                producaoAtualizarStatusPedidoUseCaseImpl.preparar(CODIGO));

        String mensagemEsperada = String.format("O pedido %s está aguardando o pagamento", CODIGO);
        String mensagemAtual = exception.getMessage();

        Assertions.assertTrue(mensagemAtual.contains(mensagemEsperada));
    }

    @Test
    void deveAtualizarStatusPedidoComSucesso_Preparar() {
        when(pedidoGateway.buscarPorCodigo(anyString()))
                .thenReturn(pedido);

        doNothing()
                .when(pedidoGateway).atualizarStatus(anyString(), anyString());

        doNothing()
                .when(pedidoRepository).persistirPedido(any(Pedido.class));

        producaoAtualizarStatusPedidoUseCaseImpl.preparar(CODIGO);

        verify(pedidoGateway, times(1)).buscarPorCodigo(anyString());
        verify(pedidoGateway, times(1)).atualizarStatus(anyString(), anyString());
        verify(pedidoRepository, times(1)).persistirPedido(any(Pedido.class));
    }

    @Test
    void deveAtualizarStatusPedidoComSucesso_Entregar() {
        when(pedidoGateway.buscarPorCodigo(anyString()))
                .thenReturn(pedido);

        doNothing()
                .when(pedidoGateway).atualizarStatus(anyString(), anyString());

        doNothing()
                .when(pedidoRepository).persistirPedido(any(Pedido.class));

        producaoAtualizarStatusPedidoUseCaseImpl.entregar(CODIGO);

        verify(pedidoGateway, times(1)).buscarPorCodigo(anyString());
        verify(pedidoGateway, times(1)).atualizarStatus(anyString(), anyString());
        verify(pedidoRepository, times(1)).persistirPedido(any(Pedido.class));
    }

    @Test
    void deveAtualizarStatusPedidoComSucesso_Finalizar() {
        when(pedidoGateway.buscarPorCodigo(anyString()))
                .thenReturn(pedido);

        doNothing()
                .when(pedidoGateway).atualizarStatus(anyString(), anyString());

        doNothing()
                .when(pedidoRepository).persistirPedido(any(Pedido.class));

        producaoAtualizarStatusPedidoUseCaseImpl.finalizar(CODIGO);

        verify(pedidoGateway, times(1)).buscarPorCodigo(anyString());
        verify(pedidoGateway, times(1)).atualizarStatus(anyString(), anyString());
        verify(pedidoRepository, times(1)).persistirPedido(any(Pedido.class));
    }

    @Test
    void deveAtualizarStatusPedidoComSucesso_Cancelar() {
        when(pedidoGateway.buscarPorCodigo(anyString()))
                .thenReturn(pedido);

        doNothing()
                .when(pedidoGateway).atualizarStatus(anyString(), anyString());

        doNothing()
                .when(pedidoRepository).persistirPedido(any(Pedido.class));

        producaoAtualizarStatusPedidoUseCaseImpl.cancelar(CODIGO);

        verify(pedidoGateway, times(1)).buscarPorCodigo(anyString());
        verify(pedidoGateway, times(1)).atualizarStatus(anyString(), anyString());
        verify(pedidoRepository, times(1)).persistirPedido(any(Pedido.class));
    }
}