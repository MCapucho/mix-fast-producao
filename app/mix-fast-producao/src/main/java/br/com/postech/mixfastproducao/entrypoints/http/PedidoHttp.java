package br.com.postech.mixfastproducao.entrypoints.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoHttp {

    @JsonProperty("codigo_pedido")
    private String codigoPedido;
    private Integer fila;
    @JsonProperty("status_pedido")
    private String statusPedido;
}
