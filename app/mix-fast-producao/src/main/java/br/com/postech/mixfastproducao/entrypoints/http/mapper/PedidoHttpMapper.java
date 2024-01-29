package br.com.postech.mixfastproducao.entrypoints.http.mapper;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.entrypoints.http.PedidoHttp;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;

@Mapper(componentModel="spring", imports = Collection.class)
public interface PedidoHttpMapper {

    @Mapping(target = "codigoPedido", source = "pedido.codigo")
    @Mapping(target = "statusPedido", source = "pedido.status")
    PedidoHttp entityToHttp(Pedido pedido);
}
