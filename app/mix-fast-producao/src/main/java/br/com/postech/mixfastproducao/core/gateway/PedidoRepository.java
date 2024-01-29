package br.com.postech.mixfastproducao.core.gateway;

import br.com.postech.mixfastproducao.core.entity.Pedido;

public interface PedidoRepository {

    void persistirPedido(Pedido pedido);
}
