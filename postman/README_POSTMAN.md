# 📋 Guia de Uso - Collection Postman Sistema Vendas Online

## 🚀 Como Importar e Usar

### 1. Importar no Postman
1. Abra o Postman Desktop ou Web
2. Clique em **Import** no topo esquerdo
3. Selecione o arquivo: `Sistema_Vendas_Online_API_v2.postman_collection.json`
4. (Opcional) Importe também: `Sistema_Vendas_Online.postman_environment.json`

### 2. Configurar Environment (Opcional)
Se importou o arquivo de environment:
- Selecione "Sistema Vendas Online" no dropdown de environments
- Variáveis disponíveis:
  - `cliente_url`: http://localhost:8081
  - `produto_url`: http://localhost:8082  
  - `pedido_url`: http://localhost:8083

### 3. Iniciar os Serviços
Antes de testar, certifique-se que os serviços estão rodando:

```bash
# Na pasta raiz do projeto
docker-compose up -d

# Ou executar individualmente cada módulo:
# Terminal 1 - Cliente Service
cd cliente && ./gradlew bootRun

# Terminal 2 - Produto Service  
cd produto && ./gradlew bootRun

# Terminal 3 - Pedido Service
cd pedido && ./gradlew bootRun
```

## 📊 Estrutura da Collection

### 🧑‍💼 CLIENTES (Port 8081)
- **GET** `/clientes` - Listar todos
- **GET** `/clientes/{id}` - Buscar por ID
- **GET** `/clientes/nome/{nome}` - Buscar por nome
- **GET** `/clientes/contar` - Contar total
- **POST** `/clientes` - Criar novo
- **PUT** `/clientes/{id}` - Atualizar
- **DELETE** `/clientes/{id}` - Deletar

### 📦 PRODUTOS (Port 8082)
- **GET** `/produtos` - Listar todos
- **GET** `/produtos/{id}` - Buscar por ID
- **POST** `/produtos` - Criar novo
- **PUT** `/produtos/{id}` - Atualizar
- **DELETE** `/produtos/{id}` - Deletar

### 🛒 PEDIDOS (Port 8083)
- **GET** `/pedidos` - Listar todos
- **GET** `/pedidos/{id}` - Buscar por ID
- **GET** `/pedidos/cliente/{clienteId}` - Buscar por cliente
- **POST** `/pedidos` - Criar novo
- **PUT** `/pedidos/{id}` - Atualizar
- **DELETE** `/pedidos/{id}` - Deletar

## 🔧 Exemplos de Uso

### Fluxo Completo de Teste:

1. **Criar um Cliente**
   ```json
   POST http://localhost:8081/clientes
   {
     "nome": "Maria Silva",
     "email": "maria@email.com",
     "documento": "98765432100",
     "score": 750
   }
   ```

2. **Criar um Produto**
   ```json
   POST http://localhost:8082/produtos
   {
     "nome": "Notebook Dell",
     "descricao": "Notebook Dell Inspiron 15",
     "preco": 2499.99,
     "estoque": 10
   }
   ```

3. **Criar um Pedido**
   ```json
   POST http://localhost:8083/pedidos
   {
     "clienteId": 1,
     "produtoId": 1,
     "quantidade": 1,
     "valorTotal": 2499.99,
     "status": "PENDENTE"
   }
   ```

## 🎯 Status de Pedidos Válidos
- `PENDENTE`
- `CONFIRMADO`  
- `ENTREGUE`
- `CANCELADO`

## 📝 Notas Importantes

1. **IDs**: Os IDs são gerados automaticamente pelo banco H2
2. **Portas**: Cada serviço roda em uma porta específica:
   - Cliente: 8081
   - Produto: 8082  
   - Pedido: 8083
3. **Banco**: H2 in-memory - dados são perdidos ao reiniciar
4. **CORS**: Configurado para desenvolvimento local

## 🔍 Troubleshooting

### Erro de Conexão:
- Verifique se os serviços estão rodando: `docker-compose ps`
- Teste diretamente no browser: `http://localhost:8081/clientes`

### Erro 404:
- Confirme que está usando a porta correta para cada endpoint
- Verifique se o endpoint existe no controller

### Erro 500:
- Verifique os logs do serviço: `docker-compose logs [nome-do-servico]`
- Confirme se os dados do JSON estão corretos

## 🚀 Dicas de Produtividade

1. **Use variáveis**: Configure o environment para facilitar mudanças de URL
2. **Organize testes**: Use as pastas da collection para organizar seus testes
3. **Salve responses**: Use o recurso "Save Response" para comparações
4. **Automatize**: Configure testes automatizados usando os scripts do Postman

---

🎉 **Pronto para testar!** Sua API Sistema Vendas Online está documentada e pronta para uso.