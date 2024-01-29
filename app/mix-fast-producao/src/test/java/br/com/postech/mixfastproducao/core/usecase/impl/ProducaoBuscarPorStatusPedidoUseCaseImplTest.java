package br.com.postech.mixfastproducao.core.usecase.impl;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.exception.PedidoListEmptyException;
import br.com.postech.mixfastproducao.core.gateway.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProducaoBuscarPorStatusPedidoUseCaseImplTest {

    @InjectMocks
    private ProducaoBuscarPorStatusPedidoUseCaseImpl producaoBuscarPorStatusPedidoUseCaseImpl;
    @Mock
    private PedidoRepository pedidoRepository;

    private Pedido pedidoResponse;

    @BeforeEach
    void setUp() {
        pedidoResponse = Pedido.builder()
                .codigo(UUID.randomUUID().toString())
                .build();
    }

    @Test
    void deveBuscarTodasCategoriaComSucesso() {
        when(pedidoRepository.buscarPorStatus())
                .thenReturn(List.of(pedidoResponse));

        List<Pedido> listaPedidos = producaoBuscarPorStatusPedidoUseCaseImpl.buscarPorStatusPedido();

        Assertions.assertNotNull(listaPedidos);
        Assertions.assertEquals(1, listaPedidos.size());
    }

    @Test
    void naoDeveBuscarTodasCategoria_Erro() {
        Exception exception = Assertions.assertThrows(PedidoListEmptyException.class, () ->
                producaoBuscarPorStatusPedidoUseCaseImpl.buscarPorStatusPedido());

        String mensagemEsperada = "Lista de pedidos em branco";
        String mensagemAtual = exception.getMessage();

        Assertions.assertTrue(mensagemAtual.contains(mensagemEsperada));
    }
}