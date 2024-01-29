package br.com.postech.mixfastproducao.dataproviders.gateway.api.pedido;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class PedidoConfigTest {

    @InjectMocks
    private PedidoConfig pedidoConfig;

    @Test
    void retryer() {
        Retryer retryer = pedidoConfig.retryer();
        assertNotNull(retryer);
    }

    @Test
    void errorDecoder() {
        ErrorDecoder errorDecoder = pedidoConfig.errorDecoder();
        assertNotNull(errorDecoder);
    }

    @Test
    void feignLoggerLevel() {
        Logger.Level logger = pedidoConfig.feignLoggerLevel();
        assertNotNull(logger);
    }
}