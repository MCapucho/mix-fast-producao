package br.com.postech.mixfastproducao.entrypoints.controller.v1.producao;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoAtualizarStatusPedidoUseCase;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoBuscarPorStatusPedidoUseCase;
import br.com.postech.mixfastproducao.entrypoints.handler.RestExceptionHandler;
import br.com.postech.mixfastproducao.entrypoints.http.PedidoHttp;
import br.com.postech.mixfastproducao.entrypoints.http.mapper.PedidoHttpMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(MockitoExtension.class)
class ProducaoControllerTest {

    public static final String CODIGO = UUID.randomUUID().toString();

    @InjectMocks
    private ProducaoController producaoController;
    @Mock
    private PedidoHttpMapper pedidoHttpMapper;
    @Mock
    private ProducaoBuscarPorStatusPedidoUseCase producaoBuscarPorStatusPedidoUseCase;
    @Mock
    private ProducaoAtualizarStatusPedidoUseCase producaoAtualizarStatusPedidoUseCase;

    private JacksonTester<PedidoHttp> jacksonTester;
    private MockMvc mvc;
    private Pedido pedido;
    private PedidoHttp pedidoHttp;

    @BeforeEach
    void setUp() {
        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders.standaloneSetup(producaoController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();

        pedido = Pedido.builder()
                .codigo(UUID.randomUUID().toString())
                .fila(1)
                .status("Recebido")
                .build();

        pedidoHttp = PedidoHttp.builder()
                .codigoPedido(UUID.randomUUID().toString())
                .fila(1)
                .statusPedido("Recebido")
                .build();
    }

    @SneakyThrows
    @Test
    void buscarPorStatus() {
        when(producaoBuscarPorStatusPedidoUseCase.buscarPorStatusPedido())
                .thenReturn(List.of(pedido));

        when(pedidoHttpMapper.entityToHttp(any(Pedido.class)))
                .thenReturn(pedidoHttp);

        MockHttpServletResponse response =
                mvc.perform(
                        get("/v1/producoes/pedidos/status")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @SneakyThrows
    @Test
    void preparar() {
        doNothing().when(producaoAtualizarStatusPedidoUseCase)
                .preparar(anyString());

        MockHttpServletResponse response =
                mvc.perform(
                        put("/v1/producoes/pedido/{codigo}/preparamento", CODIGO)
                ).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());
    }

    @SneakyThrows
    @Test
    void entregar() {
        doNothing().when(producaoAtualizarStatusPedidoUseCase)
                .entregar(anyString());

        MockHttpServletResponse response =
                mvc.perform(
                        put("/v1/producoes/pedido/{codigo}/entrega", CODIGO)
                ).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());
    }

    @SneakyThrows
    @Test
    void finalizar() {
        doNothing().when(producaoAtualizarStatusPedidoUseCase)
                .finalizar(anyString());

        MockHttpServletResponse response =
                mvc.perform(
                        put("/v1/producoes/pedido/{codigo}/finalizado", CODIGO)
                ).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());
    }

    @SneakyThrows
    @Test
    void cancelar() {
        doNothing().when(producaoAtualizarStatusPedidoUseCase)
                .cancelar(anyString());

        MockHttpServletResponse response =
                mvc.perform(
                        put("/v1/producoes/pedido/{codigo}/cancelamento", CODIGO)
                ).andReturn().getResponse();

        assertEquals(response.getStatus(), HttpStatus.NO_CONTENT.value());
    }
}