package br.com.postech.mixfastproducao.core.gateway;

import br.com.postech.mixfastproducao.core.entity.Pedido;

public interface PedidoGateway {

    Pedido buscarPorCodigo(String codigo);

    void atualizarStatus(String codigo, String status);
}
