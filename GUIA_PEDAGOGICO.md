# 📚 Guia Pedagógico - Sistema de Vendas Online

## 🎯 Objetivos de Aprendizagem

Este projeto foi desenvolvido para demonstrar na prática:

### 🏗️ **Arquitetura de Software**
- Microserviços vs Monolito
- Separação de responsabilidades
- Comunicação entre serviços
- Padrões arquiteturais

### 🎨 **Padrões de Projeto**
- MVC (Model-View-Controller)
- Repository Pattern
- Builder Pattern
- Service Locator Pattern
- Dependency Injection

### 🛠️ **Tecnologias Spring**
- Spring Boot
- Spring Data JPA
- Spring Web (REST)
- Configurações e Profiles

## 📋 Checklist de Análise do Código

### ✅ **Organização e Estrutura**

#### **1. Separação de Responsabilidades**
- [ ] Controllers apenas recebem requests e delegam para Services
- [ ] Services contêm a lógica de negócio
- [ ] Repositories abstraem acesso aos dados
- [ ] Models representam entidades do domínio
- [ ] DTOs são usados para transferência de dados

#### **2. Nomenclatura e Convenções**
- [ ] Classes seguem PascalCase (`ClienteController`)
- [ ] Métodos seguem camelCase (`findById`)
- [ ] Packages seguem padrão reverso (`com.empresa.projeto`)
- [ ] Constantes em UPPER_CASE (`STATUS_ATIVO`)

#### **3. Anotações Spring**
- [ ] `@RestController` para endpoints REST
- [ ] `@Service` para lógica de negócio
- [ ] `@Repository` para acesso a dados
- [ ] `@Entity` para entidades JPA
- [ ] `@RequiredArgsConstructor` para injeção de dependência

### ✅ **Padrões de Projeto Implementados**

#### **1. MVC Pattern**
```java
// ✅ CONTROLLER - Camada de Apresentação
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    // Responsável apenas por HTTP
}

// ✅ SERVICE - Camada de Negócio  
@Service
public class ClienteService {
    // Contém regras de negócio
}

// ✅ MODEL - Camada de Domínio
@Entity
public class Cliente {
    // Representa entidade do negócio
}
```

#### **2. Repository Pattern**
```java
// ✅ Abstração do acesso aos dados
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNome(String nome);
    // Spring Data gera implementação automaticamente
}
```

#### **3. Builder Pattern**
```java
// ✅ Construção fluente de objetos
PedidoDetalheDto detalhes = PedidoDetalheDto.builder()
    .id(pedido.getId())
    .cliente(clienteDto)
    .produto(produtoDto)
    .build();
```

#### **4. Service Locator Pattern**
```java
// ✅ Centraliza descoberta de serviços
@Service
public class ExternalServiceLocator {
    public ClienteDto findClienteById(Long id) {
        // Localiza e consome serviço externo
    }
}
```

### ✅ **Boas Práticas de Código**

#### **1. Injeção de Dependência**
```java
// ✅ BOM - Constructor Injection
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository repository;
}

// ❌ EVITAR - Field Injection
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
}
```

#### **2. Tratamento de Erros**
```java
// ✅ BOM - Optional para valores que podem não existir
public Optional<Cliente> findById(Long id) {
    return repository.findById(id);
}

// ✅ BOM - ResponseEntity para controle de HTTP Status
public ResponseEntity<Cliente> findById(@PathVariable Long id) {
    return service.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}
```

#### **3. Validação de Dados**
```java
// ✅ BOM - Validação no Service
public Cliente save(Cliente cliente) {
    if (cliente.getScore() < 0) {
        throw new IllegalArgumentException("Score não pode ser negativo");
    }
    return repository.save(cliente);
}
```

## 🏛️ Análise Arquitetural por Camada

### 📱 **Camada Controller (Apresentação)**

**Responsabilidades:**
- Receber requisições HTTP
- Validar dados de entrada
- Delegar para camada Service
- Retornar responses HTTP apropriados

**Exemplo de Análise:**
```java
@RestController
@RequestMapping("/clientes") // ✅ Endpoint bem definido
@RequiredArgsConstructor     // ✅ Injeção via construtor
public class ClienteController {
    
    private final ClienteService service; // ✅ Depende apenas do Service
    
    @GetMapping("/{id}")  // ✅ Mapeamento REST correto
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)           // ✅ 200 se encontrado
            .orElse(ResponseEntity.notFound().build()); // ✅ 404 se não encontrado
    }
}
```

**Pontos de Verificação:**
- [ ] Não contém lógica de negócio
- [ ] Retorna status HTTP apropriados
- [ ] Usa DTOs quando necessário
- [ ] Valida apenas dados de entrada

### 🏗️ **Camada Service (Negócio)**

**Responsabilidades:**
- Implementar regras de negócio
- Coordenar operações entre repositórios
- Aplicar validações de domínio
- Gerenciar transações

**Exemplo de Análise:**
```java
@Service              // ✅ Anotação correta
@RequiredArgsConstructor // ✅ Injeção de dependência
@Slf4j               // ✅ Logging estruturado
public class PedidoService {
    
    private final PedidoRepository repository;
    private final ExternalServiceLocator serviceLocator; // ✅ Usa Service Locator
    
    public Optional<PedidoDetalheDto> findDetalhesById(Long id) {
        // ✅ Implementa regra de negócio complexa
        Optional<Pedido> pedidoOpt = repository.findById(id);
        
        if (!pedidoOpt.isPresent()) {
            return Optional.empty(); // ✅ Trata caso não encontrado
        }
        
        // ✅ Integra com outros serviços
        ClienteDto cliente = serviceLocator.findClienteById(pedido.getClienteId());
        ProdutoDto produto = serviceLocator.findProdutoById(pedido.getProdutoId());
        
        // ✅ Usa Builder Pattern
        return Optional.of(PedidoDetalheDto.builder()...build());
    }
}
```

**Pontos de Verificação:**
- [ ] Contém apenas lógica de negócio
- [ ] Não conhece detalhes de HTTP/REST
- [ ] Coordena múltiplas operações
- [ ] Implementa validações de domínio

### 🗄️ **Camada Repository (Persistência)**

**Responsabilidades:**
- Abstrair acesso aos dados
- Fornecer queries específicas do domínio
- Encapsular detalhes do banco de dados

**Exemplo de Análise:**
```java
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    // ✅ Query Method - Spring Data gera automaticamente
    List<Cliente> findByNome(String nome);
    
    // ✅ Query customizada quando necessário
    @Query("SELECT COUNT(c) FROM Cliente c")
    Long countClientes();
    
    // ✅ Query com parâmetros
    List<Cliente> findByScoreGreaterThan(Integer score);
}
```

**Pontos de Verificação:**
- [ ] Estende JpaRepository corretamente
- [ ] Usa Query Methods quando possível
- [ ] Implementa queries customizadas quando necessário
- [ ] Não contém lógica de negócio

### 📋 **Camada Model (Domínio)**

**Responsabilidades:**
- Representar entidades do negócio
- Definir relacionamentos
- Encapsular dados e comportamentos

**Exemplo de Análise:**
```java
@Entity              // ✅ Mapeamento JPA
@Data                // ✅ Lombok para reduzir boilerplate
@Builder             // ✅ Builder Pattern
@NoArgsConstructor   // ✅ Construtor padrão para JPA
@AllArgsConstructor  // ✅ Construtor completo para Builder
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Chave primária auto-incremento
    private Long id;
    
    private Long clienteId;  // ✅ Referência por ID (microserviços)
    private Long produtoId;  // ✅ Evita JOINs entre serviços
    
    @Enumerated(EnumType.STRING) // ✅ Enum como String no banco
    private StatusPedido status;
    
    // ✅ Enum interno bem definido
    public enum StatusPedido {
        PENDENTE, CONFIRMADO, ENTREGUE, CANCELADO
    }
}
```

**Pontos de Verificação:**
- [ ] Anotações JPA corretas
- [ ] Relacionamentos apropriados para microserviços
- [ ] Usa Lombok adequadamente
- [ ] Enums bem definidos

## 🔄 Fluxo de Dados - Análise Passo a Passo

### **Cenário: Consultar Detalhes do Pedido**

```
1. 🌐 Request HTTP
   GET /pedidos/1/detalhes
   
2. 🎮 PedidoController
   ├─ Recebe requisição
   ├─ Valida parâmetros
   └─ Delega para PedidoService
   
3. 🏗️ PedidoService  
   ├─ Busca pedido no repositório
   ├─ Valida se pedido existe
   ├─ Usa ServiceLocator para buscar cliente
   ├─ Usa ServiceLocator para buscar produto
   ├─ Aplica regras de negócio (cálculos)
   └─ Constrói DTO com Builder Pattern
   
4. 🔍 ExternalServiceLocator
   ├─ Faz chamadas HTTP para outros serviços
   ├─ Implementa fallback em caso de erro
   └─ Retorna DTOs dos serviços externos
   
5. 🗄️ PedidoRepository
   ├─ Abstrai acesso ao banco H2
   └─ Retorna entidade Pedido
   
6. 📊 Response JSON
   └─ DTO serializado com todos os dados integrados
```

## 🎓 Exercícios Práticos Sugeridos

### **Nível Básico**
1. **Adicionar novo campo** ao Cliente (telefone)
2. **Criar endpoint** de busca por documento  
3. **Implementar validação** de CPF no service

### **Nível Intermediário**
4. **Criar novo status** de pedido (EM_TRANSPORTE)
5. **Implementar endpoint** de relatório de vendas por período
6. **Adicionar cache** com @Cacheable

### **Nível Avançado**
7. **Implementar Circuit Breaker** no ServiceLocator
8. **Criar testes unitários** com Mockito
9. **Adicionar autenticação** com JWT

## 🔍 Pontos de Discussão

### **1. Por que Microserviços?**
- **Vantagens:** Escalabilidade independente, tecnologias diferentes, deploy isolado
- **Desvantagens:** Complexidade de comunicação, consistência eventual
- **Quando usar:** Equipes grandes, domínios bem definidos, necessidade de escala

### **2. Por que não usar JOIN entre serviços?**
- Cada serviço tem seu próprio banco
- Relacionamentos por ID evitam acoplamento
- Permite evolução independente dos schemas

### **3. Como tratar falhas de comunicação?**
- Circuit Breaker Pattern
- Timeout e retry policies  
- Fallback responses
- Monitoramento e alertas

### **4. Como garantir consistência?**
- Eventual consistency
- Saga Pattern para transações distribuídas
- Event sourcing
- Compensating actions

## 📚 Referências e Estudos Complementares

### **Livros Recomendados**
- "Microservices Patterns" - Chris Richardson
- "Building Microservices" - Sam Newman  
- "Clean Architecture" - Robert C. Martin
- "Spring Boot in Action" - Craig Walls

### **Documentação Oficial**
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [Docker Compose](https://docs.docker.com/compose/)

### **Próximos Passos**
1. Implementar testes automatizados
2. Adicionar documentação Swagger/OpenAPI
3. Configurar monitoramento com Actuator
4. Deploy em ambiente cloud (AWS/Azure/GCP)
5. Implementar CI/CD pipeline

---

**💡 Dica Final:** Use este projeto como base para explorar outros conceitos como Event-Driven Architecture, CQRS, e observabilidade em microserviços!