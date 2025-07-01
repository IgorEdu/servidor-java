# API de Lista de Tarefas (To-Do List)

API RESTful desenvolvida em Java com Spring Boot para um sistema de Lista de Tarefas. O projeto permite o cadastro de usuários e o gerenciamento completo (CRUD) de suas tarefas pessoais, com autenticação para garantir que cada usuário só possa acessar e manipular suas próprias informações.

## ✨ Funcionalidades

  - **Cadastro de Usuários:** Endpoint público para criação de novas contas.
  - **Autenticação:** Sistema de autenticação via HTTP Basic Auth para proteger as rotas.
  - **CRUD de Tarefas:**
      - **Criação:** Adicionar novas tarefas.
      - **Leitura:** Listar todas as tarefas do usuário autenticado.
      - **Atualização:** Modificar a descrição e o status (concluída/pendente) de uma tarefa.
      - **Deleção:** Remover tarefas existentes.
  - **Segurança:** Endpoints de tarefas são protegidos, garantindo que um usuário não possa ver ou modificar as tarefas de outro.

## 🛠️ Tecnologias Utilizadas

  - **Backend:**
      - Java 17
      - Spring Boot
      - Spring Web (para APIs REST)
      - Spring Data JPA (para persistência de dados)
      - Spring Security (para autenticação e autorização)
      - Lombok (para redução de código boilerplate)
  - **Banco de Dados:**
      - PostgreSQL
  - **Gerenciamento de Dependências:**
      - Maven

## 🚀 Como Executar o Projeto

Siga os passos abaixo para configurar e rodar a aplicação localmente.

### Pré-requisitos

  - JDK 17 ou superior instalado.
  - Maven 3.6 ou superior instalado.
  - PostgreSQL instalado e em execução.
  - Um cliente de API como [Insomnia](https://insomnia.rest/) ou [Postman](https://www.postman.com/) para testar os endpoints.

### Instalação e Configuração

1.  **Clone o repositório:**

    ```bash
    git clone https://github.com/AD00G-2025/servidor-java.git
    cd todolist-api
    ```

2.  **Crie o banco de dados no PostgreSQL:**
    Conecte-se ao seu servidor PostgreSQL e execute o seguinte comando para criar o banco de dados:

    ```sql
    CREATE DATABASE listatarefas_db;
    ```

3.  **Configure a conexão com o banco de dados:**

      - Abra o arquivo `src/main/resources/application.properties`.
      - Altere as propriedades `spring.datasource.username` e `spring.datasource.password` com suas credenciais do PostgreSQL.

    <!-- end list -->

    ```properties
    # URL de conexão com o PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/listatarefas_db

    # Usuário e senha do banco
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres
    ```

4.  **Execute a aplicação:**

      - Abra um terminal na raiz do projeto e execute o seguinte comando Maven:

    <!-- end list -->

    ```bash
    mvn spring-boot:run
    ```

      - Ou, se estiver usando uma IDE como o Eclipse ou IntelliJ, encontre a classe principal `TodolistApiApplication.java` e execute-a.

A API estará disponível em `http://localhost:8080`.

## API Endpoints

A seguir estão todos os endpoints disponíveis na API.

### Autenticação

Para todos os endpoints que requerem autenticação, utilize **HTTP Basic Auth**, fornecendo o `email` como nome de usuário e a `senha` como senha.

-----

### Módulo de Usuários

#### 1\. Registrar um Novo Usuário

  - **Método:** `POST`

  - **URL:** `/api/usuarios/registrar`

  - **Autenticação:** Pública

  - **Corpo da Requisição (Request Body):**

    ```json
    {
        "nome": "Seu Nome Completo",
        "email": "seuemail@exemplo.com",
        "senha": "suasenha123"
    }
    ```

  - **Resposta de Sucesso (201 Created):**

    ```json
    {
        "id": 1,
        "nome": "Seu Nome Completo",
        "email": "seuemail@exemplo.com",
        "senha": "[senha_criptografada]",
        "tarefas": null
    }
    ```

-----

### Módulo de Tarefas

#### 2\. Listar todas as tarefas do usuário

  - **Método:** `GET`

  - **URL:** `/api/tarefas`

  - **Autenticação:** Requerida (Basic Auth)

  - **Resposta de Sucesso (200 OK):**

    ```json
    [
        {
            "id": 1,
            "descricao": "Estudar Spring Boot",
            "concluida": false
        },
        {
            "id": 2,
            "descricao": "Fazer o README do projeto",
            "concluida": true
        }
    ]
    ```

#### 3\. Criar uma nova tarefa

  - **Método:** `POST`

  - **URL:** `/api/tarefas`

  - **Autenticação:** Requerida (Basic Auth)

  - **Corpo da Requisição (Request Body):**

    ```json
    {
        "descricao": "Comprar leite",
        "concluida": false
    }
    ```

  - **Resposta de Sucesso (201 Created):**

    ```json
    {
        "id": 3,
        "descricao": "Comprar leite",
        "concluida": false
    }
    ```

#### 4\. Atualizar uma tarefa existente

  - **Método:** `PUT`

  - **URL:** `/api/tarefas/{id}`

  - **Autenticação:** Requerida (Basic Auth)

  - **Corpo da Requisição (Request Body):**

    ```json
    {
        "descricao": "Comprar leite e pão",
        "concluida": true
    }
    ```

  - **Resposta de Sucesso (200 OK):**

    ```json
    {
        "id": 3,
        "descricao": "Comprar leite e pão",
        "concluida": true
    }
    ```

#### 5\. Deletar uma tarefa

  - **Método:** `DELETE`

  - **URL:** `/api/tarefas/{id}`

  - **Autenticação:** Requerida (Basic Auth)

  - **Resposta de Sucesso (200 OK):**

      - Corpo da resposta vazio.

-----

## 🧪 Como Testar

Para facilitar os testes, foi criada uma coleção completa para o **Insomnia** que cobre todos os endpoints listados acima, com exemplos de corpo de requisição e configuração de autenticação.

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.md) para mais detalhes.