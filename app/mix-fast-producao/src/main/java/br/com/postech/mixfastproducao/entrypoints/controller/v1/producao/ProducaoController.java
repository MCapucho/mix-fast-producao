package br.com.postech.mixfastproducao.entrypoints.controller.v1.producao;

import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoAtualizarStatusPedidoUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping(value = "/v1/producoes")
public class ProducaoController {

    private final ProducaoAtualizarStatusPedidoUseCase producaoAtualizarStatusPedidoUseCase;

    @PutMapping("/pedido/{codigo}/preparamento")
    public ResponseEntity<Void> preparar(@PathVariable("codigo") String codigo) {
        producaoAtualizarStatusPedidoUseCase.preparar(codigo);
        log.info("Pedido atualizado em preparamento com sucesso");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/pedido/{codigo}/entrega")
    public ResponseEntity<Void> entregar(@PathVariable("codigo") String codigo) {
        producaoAtualizarStatusPedidoUseCase.entregar(codigo);
        log.info("Pedido atualizado em entregue com sucesso");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/pedido/{codigo}/finalizado")
    public ResponseEntity<Void> finalizar(@PathVariable("codigo") String codigo) {
        producaoAtualizarStatusPedidoUseCase.finalizar(codigo);
        log.info("Pedido atualizado em finalizado com sucesso");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/pedido/{codigo}/cancelamento")
    public ResponseEntity<Void> cancelar(@PathVariable("codigo") String codigo) {
        producaoAtualizarStatusPedidoUseCase.cancelar(codigo);
        log.info("Pedido atualizado em cancelado com sucesso");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
