package br.com.postech.mixfastproducao.dataproviders.gateway.api.pedido;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    Retryer retryer() {
        return new Retryer.Default(100L, 100L, 5);
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return new PedidoErrorDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
