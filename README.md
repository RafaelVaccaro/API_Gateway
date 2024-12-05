# Sistema de Microserviços com Spring Boot

Este projeto utiliza uma arquitetura de microserviços para gerenciar usuários, produtos e pedidos, com um API Gateway para centralizar o acesso aos serviços.

## Arquitetura do Projeto

O projeto é composto por quatro serviços principais:

1. **Users-Service**
2. **Products-Service**
3. **Orders-Service**
4. **API-Gateway**

Cada serviço é independente e gerencia uma parte específica do sistema.

---

## **Users-Service**
**Descrição:** Gerencia os dados dos usuários do sistema.

**Principais Funcionalidades:**
- Registro de novos usuários.
- Atualização dos dados cadastrais dos usuários.
- Consulta de informações de usuários.
- Deleção ou desativação de contas.

**Endpoints:**
- **POST** `/users` - Registrar um novo usuário.
- **GET** `/users/{id}` - Consultar um usuário específico.
- **PUT** `/users/{id}` - Atualizar dados de um usuário.
- **DELETE** `/users/{id}` - Desativar/Deletar um usuário.

**Banco de Dados:**
- **Tabela Users**:
  - `id`: Identificador único.
  - `name`: Nome do usuário.
  - `email`: Email do usuário.
  - `password`: Senha do usuário.
  - `status`: Status da conta (ativo/inativo).

---

## **Products-Service**
**Descrição:** Gerencia os produtos disponíveis para venda ou uso.

**Principais Funcionalidades:**
- Cadastro de novos produtos.
- Atualização de informações dos produtos (preço, estoque, etc.).
- Listagem e consulta de produtos.
- Controle de estoque.

**Endpoints:**
- **POST** `/products` - Adicionar um novo produto.
- **GET** `/products` - Listar todos os produtos.
- **GET** `/products/{id}` - Consultar detalhes de um produto.
- **PUT** `/products/{id}` - Atualizar informações de um produto.
- **DELETE** `/products/{id}` - Remover um produto.

**Banco de Dados:**
- **Tabela Products**:
  - `id`: Identificador único.
  - `name`: Nome do produto.
  - `description`: Descrição do produto.
  - `price`: Preço do produto.
  - `stock`: Quantidade em estoque.
  - `category`: Categoria do produto.

---

## **Orders-Service**
**Descrição:** Gerencia os pedidos feitos pelos usuários.

**Principais Funcionalidades:**
- Criação de novos pedidos.
- Atualização do status do pedido.
- Listagem de pedidos de um usuário.
- Histórico de pedidos.

**Endpoints:**
- **POST** `/orders` - Criar um novo pedido.
- **GET** `/orders` - Listar todos os pedidos.
- **GET** `/orders/{id}` - Consultar detalhes de um pedido.
- **PUT** `/orders/{id}` - Atualizar o status de um pedido.
- **DELETE** `/orders/{id}` - Cancelar um pedido.

**Banco de Dados:**
- **Tabela Orders**:
  - `id`: Identificador único do pedido.
  - `user_id`: ID do usuário que fez o pedido.
  - `total_price`: Valor total do pedido.
  - `status`: Status do pedido (em andamento, concluído, cancelado).
  - `created_at`: Data de criação do pedido.
- **Tabela Order_Items**:
  - `id`: Identificador único do item.
  - `order_id`: ID do pedido associado.
  - `product_id`: ID do produto associado.
  - `quantity`: Quantidade do produto.
  - `price`: Preço total do item.

---

## **API-Gateway**
**Descrição:** Centraliza o acesso e redireciona as requisições para os microserviços apropriados.

**Principais Funcionalidades:**
- Roteamento de requisições para os serviços (`users-service`, `products-service`, `orders-service`).
- Gerenciamento de autenticação e autorização.
- Políticas de segurança (CORS, throttling, etc.).

**Configurações de Roteamento:**
- `/users/**` -> Encaminha para o `users-service`.
- `/products/**` -> Encaminha para o `products-service`.
- `/orders/**` -> Encaminha para o `orders-service`.

---

## Como Rodar o Projeto

### **Pré-requisitos**
- **Docker**: Certifique-se de que o Docker e o Docker Compose estão instalados.
- **Java**: Versão 22 ou superior.
- **Gradle**: Configurado globalmente no sistema.

### **Passos:**
1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio
