package br.com.postech.mixfastproducao.entrypoints.sqs.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoRequest {

    private String codigoPedido;
    private String nomeCliente;
    private String emailCliente;
    private BigDecimal valorTotalPedido;
    private String statusPedido;
    private String statusPagamento;
}
