package br.com.postech.mixfastproducao.dataproviders.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestMensagemErro {

    private String dateTime;
    private int code;
    private String status;
    private List<String> errors;
}
