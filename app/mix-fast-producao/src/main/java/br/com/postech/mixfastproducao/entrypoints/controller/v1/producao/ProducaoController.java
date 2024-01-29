package br.com.postech.mixfastproducao.entrypoints.controller.v1.producao;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoAtualizarStatusPedidoUseCase;
import br.com.postech.mixfastproducao.core.usecase.interfaces.ProducaoBuscarPorStatusPedidoUseCase;
import br.com.postech.mixfastproducao.entrypoints.http.PedidoHttp;
import br.com.postech.mixfastproducao.entrypoints.http.mapper.PedidoHttpMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping(value = "/v1/producoes")
public class ProducaoController {

    private final PedidoHttpMapper pedidoHttpMapper;
    private final ProducaoBuscarPorStatusPedidoUseCase producaoBuscarPorStatusPedidoUseCase;
    private final ProducaoAtualizarStatusPedidoUseCase producaoAtualizarStatusPedidoUseCase;

    @GetMapping("/pedidos/status")
    public ResponseEntity<List<PedidoHttp>> buscarPorStatus() {
        List<Pedido> listaPedidos = producaoBuscarPorStatusPedidoUseCase.buscarPorStatusPedido();
        List<PedidoHttp> listaPedidosHttp = new ArrayList<>();

        listaPedidos.forEach(result -> {
            PedidoHttp pedidoHttp = pedidoHttpMapper.entityToHttp(result);
            listaPedidosHttp.add(pedidoHttp);
        });

        log.info("Lista de pedidos preenchida com sucesso");
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidosHttp);
    }

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
