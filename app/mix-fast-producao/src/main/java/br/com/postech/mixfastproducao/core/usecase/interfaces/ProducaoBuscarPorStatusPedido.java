package br.com.postech.mixfastproducao.core.usecase.interfaces;

import br.com.postech.mixfastproducao.core.entity.Pedido;

import java.util.List;

public interface ProducaoBuscarPorStatusPedido {

    List<Pedido> buscarPorStatusPedido();
}
