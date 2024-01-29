package br.com.postech.mixfastproducao.core.usecase.impl;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.gateway.PedidoGateway;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoAtualizarStatusPedidoUseCase;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ProducaoAtualizarStatusPedidoUseCaseImpl implements ProducaoAtualizarStatusPedidoUseCase {

    private final PedidoGateway pedidoGateway;
    private AmazonDynamoDB amazonDynamoDB;

    @Override
    public void preparar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);
        pedidoGateway.atualizarStatus(codigo, "preparamento");

        pedido.setStatus("Preparando");
        inserirDados(pedido);
    }

    @Override
    public void entregar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);
        pedidoGateway.atualizarStatus(codigo, "entrega");

        pedido.setStatus("Entregue");
        inserirDados(pedido);
    }

    @Override
    public void finalizar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);
        pedidoGateway.atualizarStatus(codigo, "finalizado");

        pedido.setStatus("Finalizado");
        inserirDados(pedido);
    }

    @Override
    public void cancelar(String codigo) {
        Pedido pedido = pedidoGateway.buscarPorCodigo(codigo);
        pedidoGateway.atualizarStatus(codigo, "cancelamento");

        pedido.setStatus("Cancelado");
        inserirDados(pedido);
    }

    private void inserirDados(Pedido pedido) {
        Map<String, AttributeValue> attributeValues = new HashMap<>();
        attributeValues.put("codigo_pedido", new AttributeValue().withS(pedido.getCodigo()));
        attributeValues.put("fila", new AttributeValue().withS(pedido.getFila().toString()));
        attributeValues.put("status_pedido", new AttributeValue().withS(pedido.getStatus()));

        PutItemRequest putItemRequest = new PutItemRequest()
                .withTableName("mixfastproducao")
                .withItem(attributeValues);

        this.amazonDynamoDB.putItem(putItemRequest);
    }
}
