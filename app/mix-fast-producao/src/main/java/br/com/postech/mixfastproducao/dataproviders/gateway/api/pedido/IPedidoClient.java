package br.com.postech.mixfastproducao.dataproviders.gateway.api.pedido;

import br.com.postech.mixfastproducao.dataproviders.model.rest.pedido.PedidoApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "pedido", url = "${feign.client.config.pedido.url}", configuration = PedidoConfig.class)
public interface IPedidoClient {

    @GetMapping("/{codigo}")
    PedidoApiResponse buscarPorCodigo(@PathVariable("codigo") String codigo,
                                      @RequestHeader("Authorization") String token);

    @PutMapping("/{codigo}/{status}")
    PedidoApiResponse atualizarStatus(@PathVariable("codigo") String codigo,
                                      @PathVariable("status") String status,
                                      @RequestHeader("Authorization") String token);
}
