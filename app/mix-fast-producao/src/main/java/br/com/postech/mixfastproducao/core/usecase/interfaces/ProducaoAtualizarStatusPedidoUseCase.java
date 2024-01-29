package br.com.postech.mixfastproducao.core.usecase.interfaces;

public interface ProducaoAtualizarStatusPedidoUseCase {

    void preparar(String codigo);
    void entregar(String codigo);
    void finalizar(String codigo);
    void cancelar(String codigo);
}
