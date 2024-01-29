package br.com.postech.mixfastproducao.dataproviders.model.rest.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenApiRequest {

    private String cpf;
}
