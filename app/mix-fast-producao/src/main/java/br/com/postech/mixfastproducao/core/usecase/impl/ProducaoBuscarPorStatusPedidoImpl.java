package br.com.postech.mixfastproducao.core.usecase.impl;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.gateway.PedidoRepository;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoBuscarPorStatusPedido;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProducaoBuscarPorStatusPedidoImpl implements ProducaoBuscarPorStatusPedido {

    private final PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> buscarPorStatusPedido() {
        return pedidoRepository.buscarPorStatus();
    }
}
