package br.com.postech.mixfastproducao.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PedidoConflictException extends RuntimeException {

    public PedidoConflictException(String message) {
        super(message);
    }
}
