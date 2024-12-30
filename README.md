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

**Endpoints:**
- **POST** `/user` - Registra um novo usuário.
  **Exemplo de body:**
  ```json
  {
    "name": "Rafael",
    "email": "rafavaccaro@gmail.com"
  }
- **GET** `/user` - Lista usuários registrados.
- **GET** `/user/{id}` - Valída a existência de um usuário pelo ID.
- **DELETE** `/user/{id}` - Deleta um usuário.

**Banco de Dados:**
- **Tabela Users**:
  - `id`: Identificador único.
  - `name`: Nome do usuário.
  - `email`: Email do usuário.

---

## **Products-Service**
**Descrição:** Gerencia os dados dos produtos do sistema.

**Endpoints:**
- **POST** `/product` - Registra um novo produto.
  **Exemplo de body:**
  ```json
  {
    "name" : "Smartphone",
    "description" : "XYZ's smartphone",
    "price" : 5000,
    "stock" : 100
  }
- **GET** `/product` - Lista todos os produtos.
- **GET** `/product/{id}` - Valída a existência de um produto pelo ID.
- **GET** `/product/price/{id}` - Obtem o preço de um produto pelo ID.
- **DELETE** `/product/{id}` - Deleta um produto.

**Banco de Dados:**
- **Tabela Products**:
  - `id`: Identificador único.
  - `name`: Nome do produto.
  - `description`: Descrição do produto.
  - `price`: Preço do produto.
  - `stock`: Quantidade em estoque.

---

## **Orders-Service**
**Descrição:** Gerencia os dados dos pedidos do sistema.

**Endpoints:**
- **POST** `/order` - Registra um novo pedido e seus respectivos itens de pedido.
  **Exemplo de body:**
  ```json
  {
    "userId": 1,
    "orderItems": [
      {
        "productId": 1,
        "quantity": 50
      }
    ]
  }
- **GET** `/order` - Lista todos os pedidos.
- **GET** `/order/produtosDePedido/{id}` - Lista todos produtos de um pedido pelo ID.
- **GET** `/order/pedidosDeUsuario/{id}` - Lista todos pedidos de um usuário pelo ID.
- **DELETE** `/order/{id}` - Deleta um pedido.

**Banco de Dados:**
- **Tabela Orders**:
  - `id`: Identificador único do pedido.
  - `user_id`: ID do usuário que fez o pedido.
  - `total_price`: Valor total do pedido.
  - `orderItems`: Lista de itens do pedido.
  - `created_at`: Data de criação do pedido.
- **Tabela Order_Items**:
  - `id`: Identificador único do item.
  - `order_id`: ID do pedido associado.
  - `product_id`: ID do produto associado.
  - `quantity`: Quantidade do produto.

---

## **API-Gateway**
**Descrição:** Centraliza o acesso e redireciona as requisições para os microserviços apropriados.

**Configurações de Roteamento:**
- `/user/**` -> Encaminha para o `users-service`.
- `/product/**` -> Encaminha para o `products-service`.
- `/order/**` -> Encaminha para o `orders-service`.

---

## Como Rodar o Projeto

### **Pré-requisitos**
- **Docker**: Certifique-se de que o Docker está instalados.
- **Java**: Versão 17 ou superior.
- **Gradle**: Configurado globalmente no sistema.

### **Passos:**
1. Clone este repositório:
   ```bash
   git clone https://github.com/RafaelVaccaro/API_Gateway.git
   cd API_Gateway
