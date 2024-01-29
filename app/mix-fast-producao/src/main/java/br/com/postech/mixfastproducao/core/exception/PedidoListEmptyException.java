package br.com.postech.mixfastproducao.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PedidoListEmptyException extends RuntimeException {

    public PedidoListEmptyException(String message) {
        super(message);
    }
}
