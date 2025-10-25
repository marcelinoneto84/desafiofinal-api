# 📚 Guia Swagger/OpenAPI - Sistema de Vendas Online

## 🎯 O que é Swagger?

**Swagger** (agora **OpenAPI 3.0**) é o padrão da indústria para documentar APIs REST. Ele oferece uma interface web interativa que permite:

- 📖 **Visualizar** todos os endpoints da API
- 🧪 **Testar** endpoints diretamente na interface
- 📋 **Ver schemas** de dados (DTOs, entidades)
- 🔄 **Gerar código** cliente automaticamente
- 📊 **Exportar** especificação para ferramentas externas

## 🚀 Como Acessar

Após iniciar os serviços, acesse as seguintes URLs:

### 🧑‍💼 Cliente Service
- **Swagger UI**: http://localhost:8081/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8081/v3/api-docs

### 📦 Produto Service  
- **Swagger UI**: http://localhost:8082/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8082/v3/api-docs

### 🛒 Pedido Service
- **Swagger UI**: http://localhost:8083/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8083/v3/api-docs

## 🧪 Tutorial de Uso

### 1️⃣ **Iniciando os Serviços**

```bash
# Opção 1: Docker Compose (Recomendado)
docker-compose up -d

# Opção 2: Executar individualmente
# Terminal 1
cd cliente && ./gradlew bootRun

# Terminal 2  
cd produto && ./gradlew bootRun

# Terminal 3
cd pedido && ./gradlew bootRun
```

### 2️⃣ **Explorando a Interface Swagger**

1. **Acesse** http://localhost:8081/swagger-ui.html
2. **Veja a descrição** do microserviço no topo da página
3. **Explore as seções**:
   - **👤 Clientes**: Endpoints de gerenciamento de clientes
   - **Schemas**: Modelos de dados utilizados

### 3️⃣ **Testando um Endpoint**

**Exemplo: Listar Clientes**

1. Clique em **"GET /clientes"**
2. Clique em **"Try it out"**
3. Clique em **"Execute"**
4. Veja a resposta JSON com os dados pré-populados

**Exemplo: Buscar Cliente por ID**

1. Clique em **"GET /clientes/{id}"**
2. Clique em **"Try it out"**
3. Digite **"1"** no campo `id`
4. Clique em **"Execute"**
5. Veja os dados do cliente João Silva

### 4️⃣ **Criando um Novo Cliente**

1. Clique em **"POST /clientes"**
2. Clique em **"Try it out"**
3. Edite o JSON de exemplo:
```json
{
  "nome": "Maria Oliveira",
  "email": "maria.oliveira@email.com",
  "documento": "11122233344",
  "score": 780
}
```
4. Clique em **"Execute"**
5. Veja o cliente criado com ID gerado automaticamente

## 🎨 Recursos Avançados

### 📊 **Visualização de Schemas**

Na seção **"Schemas"** você encontra:

- **Cliente**: Estrutura completa da entidade
- **Campos obrigatórios** e opcionais
- **Tipos de dados** (String, Integer, etc.)
- **Exemplos** de valores

### 🔍 **Filtros e Busca**

- Use a **caixa de busca** no topo para filtrar endpoints
- **Agrupe por tags** para melhor organização
- **Expanda/Recolha** seções conforme necessário

### 📋 **Copiando Comandos cURL**

Após executar qualquer request:

1. Vá até a seção **"Response"**
2. Copie o comando **cURL** gerado
3. Use no terminal ou em scripts

Exemplo:
```bash
curl -X 'GET' \
  'http://localhost:8081/clientes/1' \
  -H 'accept: application/json'
```

## 🌟 Funcionalidades por Microserviço

### 🧑‍💼 **Cliente Service** (8081)

**Endpoints Disponíveis:**
- `GET /clientes` - Lista todos os clientes
- `GET /clientes/{id}` - Busca por ID
- `GET /clientes/nome/{nome}` - Busca por nome
- `GET /clientes/contar` - Conta total de clientes
- `POST /clientes` - Cria novo cliente
- `PUT /clientes/{id}` - Atualiza cliente
- `DELETE /clientes/{id}` - Remove cliente

**Dados Pré-populados:**
- 5 clientes com diferentes scores
- Nomes realistas e CPFs válidos
- Scores variando de 680 a 920

### 📦 **Produto Service** (8082)

**Endpoints Disponíveis:**
- `GET /produtos` - Lista todos os produtos
- `GET /produtos/{id}` - Busca por ID
- `POST /produtos` - Cria novo produto
- `PUT /produtos/{id}` - Atualiza produto
- `DELETE /produtos/{id}` - Remove produto

**Dados Pré-populados:**
- 6 produtos de tecnologia
- Preços em BigDecimal (precisão monetária)
- Controle de estoque disponível

### 🛒 **Pedido Service** (8083)

**Endpoints Disponíveis:**
- `GET /pedidos` - Lista todos os pedidos
- `GET /pedidos/{id}` - Busca por ID
- `GET /pedidos/cliente/{clienteId}` - Pedidos por cliente
- `GET /pedidos/{id}/detalhes` - **⭐ Detalhes completos**
- `POST /pedidos` - Cria novo pedido
- `PUT /pedidos/{id}` - Atualiza pedido
- `DELETE /pedidos/{id}` - Remove pedido

**Destaque: Endpoint de Detalhes**
- Integra dados de cliente e produto
- Usa Service Locator Pattern
- Implementa Builder Pattern
- Calcula valor unitário automaticamente

## 🔧 Configuração Técnica

### **Dependências Gradle**

```gradle
dependencies {
    implementation 'org.springdoc:springdoc-openapi-ui:1.7.0'
    // ... outras dependências
}
```

### **Configuração Customizada**

Cada microserviço possui um `SwaggerConfig.java` com:

- **Título personalizado** com emojis
- **Descrição detalhada** das funcionalidades
- **Informações de contato** do desenvolvedor
- **Licença MIT** especificada
- **Versão** do serviço

### **Anotações Utilizadas**

```java
@Tag(name = "👤 Clientes", description = "Endpoints para gerenciamento de clientes")
@Operation(summary = "📋 Listar todos os clientes", 
           description = "Retorna uma lista com todos os clientes cadastrados")
```

## 🎯 Casos de Uso Práticos

### **1. Desenvolvimento Frontend**
- Veja exatamente quais dados cada endpoint retorna
- Teste integração antes de desenvolver o frontend
- Copie exemplos de JSON para mockups

### **2. Testes de API**
- Valide todos os cenários de sucesso
- Teste códigos de erro (404, 400, 500)
- Verifique formatos de dados

### **3. Documentação para Equipe**
- Compartilhe URLs do Swagger com colegas
- Use como especificação oficial da API
- Mantenha documentação sempre atualizada

### **4. Integração com Outras Ferramentas**
- Importe especificação OpenAPI no Postman
- Gere código cliente em múltiplas linguagens
- Use em ferramentas de monitoramento

## 🚨 Troubleshooting

### **Swagger UI não carrega?**
```bash
# Verifique se o serviço está rodando
curl http://localhost:8081/actuator/health

# Verifique se a dependência está no build.gradle
grep -r "springdoc-openapi-ui" build.gradle
```

### **Endpoints não aparecem?**
- Certifique-se que os controllers têm `@RestController`
- Verifique se os métodos são públicos
- Confirme que as anotações `@GetMapping`, `@PostMapping` estão corretas

### **Schemas não aparecem?**
- Verifique se as classes têm anotações JPA (`@Entity`)
- Confirme que estão no mesmo package ou sub-package do `@SpringBootApplication`

## 📚 Recursos Adicionais

### **Links Úteis**
- [Documentação SpringDoc](https://springdoc.org/)
- [OpenAPI Specification](https://swagger.io/specification/)
- [Swagger Editor](https://editor.swagger.io/)

### **Próximos Passos**
1. **Adicionar validações** com Bean Validation (`@Valid`, `@NotNull`)
2. **Implementar autenticação** e documentar security schemes
3. **Adicionar exemplos** mais detalhados com `@Schema(example = "...")`
4. **Configurar diferentes profiles** (dev, test, prod)

---

**💡 Dica:** O Swagger é atualizado automaticamente conforme você modifica o código. Sempre que adicionar novos endpoints ou alterar DTOs, a documentação será regenerada! 🚀