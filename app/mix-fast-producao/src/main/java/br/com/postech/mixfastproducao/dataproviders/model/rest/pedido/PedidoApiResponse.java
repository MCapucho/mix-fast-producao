package br.com.postech.mixfastproducao.dataproviders.model.rest.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoApiResponse {

    private String codigo;
    private Integer fila;
    @JsonProperty("status_pedido")
    private String status;
    @JsonProperty("status_pagamento")
    private String statusPagamento;
}
