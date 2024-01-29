package br.com.postech.mixfastproducao.core.usecase.interfaces;

import br.com.postech.mixfastproducao.core.entity.Pedido;

import java.util.List;

public interface ProducaoBuscarPorStatusPedidoUseCase {

    List<Pedido> buscarPorStatusPedido();
}
