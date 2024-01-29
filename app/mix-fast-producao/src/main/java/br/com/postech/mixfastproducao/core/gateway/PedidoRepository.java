package br.com.postech.mixfastproducao.core.gateway;

import br.com.postech.mixfastproducao.core.entity.Pedido;

import java.util.List;

public interface PedidoRepository {

    void persistirPedido(Pedido pedido);

    List<Pedido> buscarPorStatus();
}
