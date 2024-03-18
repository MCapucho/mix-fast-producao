package br.com.postech.mixfastproducao.entrypoints.sqs;

import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoAtualizarStatusPedidoUseCase;
import br.com.postech.mixfastproducao.entrypoints.sqs.dto.PedidoRequest;
import com.google.gson.Gson;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ConsumerPedidoPagamentoAprovado {

    private final Gson gson;
    private final ProducaoAtualizarStatusPedidoUseCase producaoAtualizarStatusPedidoUseCase;

    @SneakyThrows
    @JmsListener(destination = "${aws.queue.name.aprovado}")
    public void consumerNotificacaoPedido(TextMessage textMessage) {
        PedidoRequest pedidoRequest = gson.fromJson(textMessage.getText(), PedidoRequest.class);
        producaoAtualizarStatusPedidoUseCase.preparar(pedidoRequest.getCodigoPedido());
    }
}