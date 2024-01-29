package br.com.postech.mixfastproducao.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${mix-fast-producao.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("URL do servidor local");

        Contact contact = new Contact();
        contact.setName("Grupo 36 - FIAP");

        Info info = new Info()
                .title("API Mix Fast Producao")
                .version("1.0.0")
                .contact(contact)
                .description("API fast food para controle de pedidos (MIX-FAST)");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
