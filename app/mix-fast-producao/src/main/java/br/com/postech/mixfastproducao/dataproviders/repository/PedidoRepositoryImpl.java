package br.com.postech.mixfastproducao.dataproviders.repository;

import br.com.postech.mixfastproducao.core.entity.Pedido;
import br.com.postech.mixfastproducao.core.gateway.PedidoRepository;
import br.com.postech.mixfastproducao.dataproviders.model.database.PedidoDB;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    Region region = Region.US_EAST_1;
    DynamoDbClient ddb = DynamoDbClient.builder()
            .region(region)
            .build();

    DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
            .dynamoDbClient(ddb)
            .build();


    private DynamoDbTable<PedidoDB> TABELA_PEDIDO =
            enhancedClient.table("mixfastproducao", TableSchema.fromBean(PedidoDB.class));

    @Override
    public void persistirPedido(Pedido pedido) {
        PedidoDB pedidoDB = new PedidoDB();
        pedidoDB.setCodigo_pedido(pedido.getCodigo());
        pedidoDB.setFila(pedido.getFila().toString());
        pedidoDB.setStatus_pedido(pedido.getStatus());

        TABELA_PEDIDO.putItem(pedidoDB);
    }

    @Override
    public List<Pedido> buscarPorStatus() {
        Iterator<PedidoDB> pedidos = TABELA_PEDIDO.scan().items().iterator();
        List<Pedido> listaPedidos = new ArrayList<>();

        while (pedidos.hasNext()) {
            PedidoDB pedidoDB = pedidos.next();
            Pedido pedido = new Pedido();
            pedido.setCodigo(pedidoDB.getCodigo_pedido());
            pedido.setFila(Integer.valueOf(pedidoDB.getFila()));
            pedido.setStatus(pedidoDB.getStatus_pedido());
            listaPedidos.add(pedido);
        }

        return listaPedidos;
    }
}
