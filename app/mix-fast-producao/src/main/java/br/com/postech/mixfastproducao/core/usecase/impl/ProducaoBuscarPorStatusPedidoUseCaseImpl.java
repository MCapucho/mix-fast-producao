package br.com.postech.mixfastproducao.core.usecase.impl;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.exception.PedidoListEmptyException;
import br.com.postech.mixfastproducao.core.gateway.PedidoRepository;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoBuscarPorStatusPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProducaoBuscarPorStatusPedidoUseCaseImpl implements ProducaoBuscarPorStatusPedidoUseCase {

    private final PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> buscarPorStatusPedido() {
        List<Pedido> listaPedidos = pedidoRepository.buscarPorStatus();

        if (listaPedidos.isEmpty()) {
            throw new PedidoListEmptyException(String.format("Lista de pedidos em branco"));
        }

        return listaPedidos;
    }
}
