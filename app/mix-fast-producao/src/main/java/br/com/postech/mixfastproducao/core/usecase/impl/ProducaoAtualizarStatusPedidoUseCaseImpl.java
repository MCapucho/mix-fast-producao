package br.com.postech.mixfastproducao.core.usecase.impl;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.exception.PedidoConflictException;
import br.com.postech.mixfastproducao.core.gateway.PedidoGateway;
import br.com.postech.mixfastproducao.core.gateway.PedidoRepository;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoAtualizarStatusPedidoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;


@RequiredArgsConstructor
@Service
public class ProducaoAtualizarStatusPedidoUseCaseImpl implements ProducaoAtualizarStatusPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private final PedidoRepository pedidoRepository;

    @Override
    public void preparar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);

        if (Objects.equals(pedido.getStatusPagamento(), "REPROVADO")) {
            throw new PedidoConflictException(String.format("O status do pagamento do pedido %s está reprovado.", codigo));
        } else if (Objects.equals(pedido.getStatusPagamento(), "AGUARDANDO")) {
            throw new PedidoConflictException(String.format("O pedido %s está aguardando o pagamento", codigo));
        }

        pedidoGateway.atualizarStatus(codigo, "preparamento");

        pedido.setStatus("Preparando");
        pedidoRepository.persistirPedido(pedido);
    }

    @Override
    public void entregar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);
        pedidoGateway.atualizarStatus(codigo, "entrega");

        pedido.setStatus("Entregue");
        pedidoRepository.persistirPedido(pedido);
    }

    @Override
    public void finalizar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);
        pedidoGateway.atualizarStatus(codigo, "finalizado");

        pedido.setStatus("Finalizado");
        pedidoRepository.persistirPedido(pedido);
    }

    @Override
    public void cancelar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);
        pedidoGateway.atualizarStatus(codigo, "cancelamento");

        pedido.setStatus("Cancelado");
        pedidoRepository.persistirPedido(pedido);
    }
}
