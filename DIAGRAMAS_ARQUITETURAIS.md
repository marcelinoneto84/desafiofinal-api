# 📊 Diagramas Arquiteturais Complementares

## 🏗️ Arquitetura Geral do Sistema

```
                    🌐 Internet / Cliente
                            │
            ┌───────────────┼───────────────┐
            │               │               │
    ┌───────▼───────┐ ┌─────▼─────┐ ┌──────▼──────┐
    │   Cliente     │ │  Produto  │ │   Pedido    │
    │   Service     │ │  Service  │ │   Service   │
    │   Port 8081   │ │ Port 8082 │ │  Port 8083  │
    └───────┬───────┘ └─────┬─────┘ └──────┬──────┘
            │               │               │
    ┌───────▼───────┐ ┌─────▼─────┐ ┌──────▼──────┐
    │  H2 Database  │ │H2 Database│ │ H2 Database │
    │   clientes    │ │ produtos  │ │   pedidos   │
    └───────────────┘ └───────────┘ └─────────────┘
```

## 🔄 Fluxo de Comunicação entre Microserviços

```
┌─────────────────────────────────────────────────────────────────┐
│                    Consulta de Detalhes do Pedido              │
└─────────────────────────────────────────────────────────────────┘

1️⃣  Cliente/Postman                  
    │                                 
    │ GET /pedidos/1/detalhes         
    ▼                                 
2️⃣  PedidoController                  
    │                                 
    │ findDetalhesById(1)             
    ▼                                 
3️⃣  PedidoService                     
    │                                 
    ├─► PedidoRepository.findById(1) ──► 📊 H2 Database (pedidos)
    │   ↳ retorna: Pedido entity
    │
    ├─► ServiceLocator.findClienteById(clienteId)
    │   │
    │   └─► HTTP GET localhost:8081/clientes/{id}
    │       ↳ retorna: ClienteDto
    │
    ├─► ServiceLocator.findProdutoById(produtoId)  
    │   │
    │   └─► HTTP GET localhost:8082/produtos/{id}
    │       ↳ retorna: ProdutoDto
    │
    └─► PedidoDetalheDto.builder()
        ├─ dados do pedido
        ├─ dados do cliente (via HTTP)
        ├─ dados do produto (via HTTP)
        ├─ cálculos (valor unitário)
        └─ formatações (descrição status)
        ↓
4️⃣  Retorna JSON completo ao cliente
```

## 🏛️ Padrão MVC por Microserviço

```
┌─────────────────────────────────────────────────────────────────┐
│                      CLIENTE SERVICE (8081)                     │
├─────────────────────────────────────────────────────────────────┤
│  📱 CONTROLLER         🏗️ SERVICE          📋 MODEL             │
│  ┌─────────────────┐  ┌─────────────────┐ ┌─────────────────┐  │
│  │ClienteController│  │ ClienteService  │ │    Cliente      │  │
│  │                 │  │                 │ │                 │  │
│  │ @GetMapping     │──│ findAll()       │─│ @Entity         │  │
│  │ @PostMapping    │  │ save()          │ │ @Id Long id     │  │
│  │ @PutMapping     │  │ findById()      │ │ String nome     │  │
│  │ @DeleteMapping  │  │ deleteById()    │ │ String email    │  │
│  │                 │  │                 │ │ Integer score   │  │
│  └─────────────────┘  └─────────────────┘ └─────────────────┘  │
│           │                     │                    │          │
│           └─────────────────────┼────────────────────┘          │
│                                 │                               │
│  🗄️ REPOSITORY                  │        ⚙️ CONFIG             │
│  ┌─────────────────┐           │       ┌─────────────────┐      │
│  │ClienteRepository│───────────┘       │   DataLoader    │      │
│  │                 │                   │                 │      │
│  │ extends JPA     │                   │ @Component      │      │
│  │ findByNome()    │                   │ CommandLineRunner│      │
│  │ countClientes() │                   │ popula dados    │      │
│  └─────────────────┘                   └─────────────────┘      │
└─────────────────────────────────────────────────────────────────┘
```

## 🎯 Padrões de Projeto Implementados

```
┌─────────────────────────────────────────────────────────────────┐
│                        BUILDER PATTERN                          │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│   PedidoDetalheDto detalhes = PedidoDetalheDto.builder()        │
│       .id(pedido.getId())                     ✨ Fluent API    │
│       .quantidade(pedido.getQuantidade())     ✨ Legibilidade   │  
│       .valorTotal(pedido.getValorTotal())     ✨ Imutabilidade  │
│       .cliente(clienteDto)                    ✨ Flexibilidade  │
│       .produto(produtoDto)                                      │
│       .valorUnitario(calcularValor(...))                       │
│       .build();                              ✅ Objeto Criado  │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                    SERVICE LOCATOR PATTERN                      │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  @Service                                                       │
│  public class ExternalServiceLocator {                          │
│                                                                 │
│    🔍 findClienteById(Long id)                                  │
│      ├─ HTTP GET localhost:8081/clientes/{id}                   │
│      ├─ ✅ Success → return ClienteDto                          │
│      └─ ❌ Error → return FallbackClienteDto                    │
│                                                                 │
│    🔍 findProdutoById(Long id)                                  │
│      ├─ HTTP GET localhost:8082/produtos/{id}                   │
│      ├─ ✅ Success → return ProdutoDto                          │
│      └─ ❌ Error → return FallbackProdutoDto                    │
│                                                                 │
│    ✨ Benefícios:                                               │
│      ├─ Centralização da lógica de descoberta                   │
│      ├─ Implementação de circuit breaker                        │
│      ├─ Facilita testes e mocking                               │
│      └─ Reduz acoplamento entre serviços                        │
│  }                                                              │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────────┐
│                      REPOSITORY PATTERN                         │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  public interface ClienteRepository                             │
│                   extends JpaRepository<Cliente, Long> {        │
│                                                                 │
│    🔍 List<Cliente> findByNome(String nome);                    │
│       └─ Query Method automatica do Spring Data                 │
│                                                                 │
│    🔍 @Query("SELECT COUNT(c) FROM Cliente c")                  │
│       Long countClientes();                                     │
│       └─ Query customizada com JPQL                             │
│                                                                 │
│    ✨ Abstrai complexidade do acesso a dados                    │
│    ✨ Fornece CRUD automático                                   │ 
│    ✨ Permite queries customizadas                              │
│  }                                                              │
└─────────────────────────────────────────────────────────────────┘
```

## 📊 Monitoramento e Observabilidade (Futuro)

```
┌─────────────────────────────────────────────────────────────────┐
│                     OBSERVABILITY STACK                         │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  📊 Metrics           📝 Logs              🔍 Tracing           │
│  ┌─────────────┐      ┌─────────────┐     ┌─────────────┐       │
│  │ Prometheus  │      │ ELK Stack   │     │   Zipkin    │       │
│  │ Micrometer  │      │ Logback     │     │ Sleuth      │       │
│  │ Grafana     │      │ Structured  │     │ Distributed │       │
│  └─────────────┘      └─────────────┘     └─────────────┘       │
│        │                     │                   │              │
│        └─────────────────────┼───────────────────┘              │
│                              │                                  │
│              ┌───────────────▼───────────────┐                  │
│              │     Spring Boot Actuator     │                  │
│              │        Health Checks         │                  │
│              │         /actuator/*          │                  │
│              └─────────────────────────────┘                  │
└─────────────────────────────────────────────────────────────────┘
```

## 🚀 Pipeline de Deploy (Futuro)

```
  📝 Código      🔨 Build       🧪 Test       📦 Package     🚀 Deploy
     │             │             │             │              │
┌────▼────┐   ┌────▼────┐   ┌────▼────┐   ┌────▼────┐   ┌────▼────┐
│  Git    │   │ Gradle  │   │  JUnit  │   │ Docker  │   │ K8s/AWS │
│ GitHub  │──▶│  Build  │──▶│ Postman │──▶│ Images  │──▶│ ECS/GKE │
│ Commit  │   │ Test    │   │TestNG   │   │Registry │   │ Deploy  │
└─────────┘   └─────────┘   └─────────┘   └─────────┘   └─────────┘
     │             │             │             │              │
     └─────────────┼─────────────┼─────────────┼──────────────┘
                   │             │             │
              ✅ Success     ❌ Fail      🔄 Rollback
```