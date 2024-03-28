## Mix Fast Produção v.1.0.0

### Sobre

Sistema de autoatendimento de fast food, é composto por uma série de dispositivos e interfaces que permitem aos clientes
selecionar e fazer pedidos sem precisar interagir com um atendente.

### Como executar

No projeto existe um arquivo docker-compose.yml, para executar é necessário abrir o terminal na
pasta raiz do projeto e executar o comando:
- docker-compose up -d

### Endpoints

Para ter acesso aos endpoints do sistema é possível utilizar o
[Swagger-UI](http://localhost:9082/swagger-ui/index.html)
- Para utilizar o swagger, executar a aplicação

### Tecnologias

- Java (version 17)
- Spring Boot (version 3.1.0)

### Passos para utilização do sistema - Fluxo de pedido

#### 1) Pedido
-> Obs: Todos os pedidos são emitidos com o status "RECEBIDO"

- Endpoints para atualizar os status do pedido
- http://localhost:9082/v1/pedidos/{codigo-do-pedido}/preparamento
- http://localhost:9082/v1/pedidos/{codigo-do-pedido}/entrega
- http://localhost:9082/v1/pedidos/{codigo-do-pedido}/finalizado

#### Opcional (Somente quando o status for *RECEBIDO*)
* Cancelamento
    - http://localhost:9082/v1/pedidos/{codigo-do-pedido}/cancelamento