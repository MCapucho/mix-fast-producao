package br.com.postech.mixfastproducao.entrypoints.controller.v1.producao;

import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoAtualizarStatusPedidoUseCase;
import br.com.postech.mixfastproducao.entrypoints.handler.RestExceptionHandler;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@ExtendWith(MockitoExtension.class)
class ProducaoControllerTest {

    public static final String CODIGO = UUID.randomUUID().toString();

    @InjectMocks
    private ProducaoController producaoController;
    @Mock
    private ProducaoAtualizarStatusPedidoUseCase producaoAtualizarStatusPedidoUseCase;

    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(producaoController)
                .setControllerAdvice(new RestExceptionHandler())
                .build();

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