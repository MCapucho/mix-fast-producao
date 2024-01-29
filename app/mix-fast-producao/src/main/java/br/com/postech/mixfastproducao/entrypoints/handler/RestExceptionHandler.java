package br.com.postech.mixfastproducao.entrypoints.handler;

import br.com.postech.mixfastproducao.dataproviders.exception.ResourceApiException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @ExceptionHandler(ResourceApiException.class)
    public ResponseEntity<ErrorResponse> handleClient(ResourceApiException ex) {
        return handleGeneric(null, ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValid(MethodArgumentNotValidException ex) {
        return handleGeneric(getErrors(ex), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return handleGeneric(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> handleGeneric(List<String> errors, String error, HttpStatus httpStatus) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .dateTime(LocalDateTime.now().format(formatter))
                .status(httpStatus)
                .code(httpStatus.value())
                .errors(error != null ? List.of(error) : errors)
                .build();

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private List<String> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }
}
