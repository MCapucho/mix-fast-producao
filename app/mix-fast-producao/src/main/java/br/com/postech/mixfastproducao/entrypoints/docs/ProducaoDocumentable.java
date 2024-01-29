package br.com.postech.mixfastproducao.entrypoints.docs;

import br.com.postech.mixfastproducao.entrypoints.handler.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Producoes")
public interface ProducaoDocumentable {

    @Operation(summary = "Atualizar status do pedido em preparação por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido atualizado em preparamento com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status do pedido não pode ser alterado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado com o código informado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "409", description = "Erro na comunicação com o banco de dados",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") })})
    ResponseEntity<Void> preparar(@Parameter(name = "codigo", description = "Código do Pedido",
            example = "77b36beb-68cd-4939-9911-fe92a79cff99") String codigo);

    @Operation(summary = "Atualizar status do pedido para entregue por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido atualizado para entregue com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status do pedido não pode ser alterado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado com o código informado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "409", description = "Erro na comunicação com o banco de dados",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") })})
    ResponseEntity<Void> entregar(@Parameter(name = "codigo", description = "Código do Pedido",
            example = "77b36beb-68cd-4939-9911-fe92a79cff99") String codigo);

    @Operation(summary = "Atualizar status do pedido para finalizado por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido atualizado para finalizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status do pedido não pode ser alterado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado com o código informado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "409", description = "Erro na comunicação com o banco de dados",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") })})
    ResponseEntity<Void> finalizar(@Parameter(name = "codigo", description = "Código do Pedido",
            example = "77b36beb-68cd-4939-9911-fe92a79cff99") String codigo);

    @Operation(summary = "Atualizar status do pedido para cancelado por código")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pedido atualizado para cancelado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Status do pedido não pode ser alterado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado com o código informado",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") }),
            @ApiResponse(responseCode = "409", description = "Erro na comunicação com o banco de dados",
                    content = { @Content(schema = @Schema(implementation = ErrorResponse.class),
                            mediaType = "application/json") })})
    ResponseEntity<Void> cancelar(@Parameter(name = "codigo", description = "Código do Pedido",
            example = "77b36beb-68cd-4939-9911-fe92a79cff99") String codigo);
}
