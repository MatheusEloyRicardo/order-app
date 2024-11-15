
# Módulo Order

Este é um sistema baseado em **DDD (Domain-Driven Design)** e princípios de **Clean Architecture** para gestão de pedidos. O projeto utiliza o **Spring Boot 3.3.3** com várias dependências integradas, incluindo **JPA**, e **OpenAPI** para documentação das APIs. A aplicação também conta com ferramentas de monitoramento, testes e cobertura de código.

## Bounded Context e Separação de Domínios

Este projeto segue os princípios de **Domain-Driven Design (DDD)**, onde a aplicação é dividida em **bounded contexts**, cada um representando um conjunto coeso de regras de negócio e entidades relacionadas. Cada **domínio** tem seu próprio conjunto de responsabilidades e entidades, separando claramente as áreas da aplicação e facilitando o entendimento e manutenção do código.

### Domínios e Entidades

#### Domínio de Identidade e Acesso
Responsável pela gestão de usuários e perfis no sistema.

- **Entidades**:
    - `tb_pedido`
    - `tb_produto`
    - `tb_pedido_produto`

## Tecnologias Utilizadas

- **Java 21**: Linguagem de programação.
- **Spring Boot 3.3.3**: Framework para o desenvolvimento de aplicações Java.
- **PostgreSQL**: Banco de dados relacional utilizado.
- **Docker Compose**: Usado para orquestrar contêineres e serviços.
- **OpenAPI**: Geração automática de documentação de API.
- **JUnit/Jacoco**: Testes unitários e cobertura de código.
- **Lombok**: Simplificação de código Java com geração automática de getters/setters, etc.

## Requisitos

- **Java 21**
- **Docker** (para utilizar o banco de dados via Docker Compose)
- **PostgreSQL**
- **Gradle** (para gerenciamento de dependências)

## Como Rodar a Aplicação

### Configuração de Banco de Dados

O projeto utiliza **PostgreSQL** como banco de dados. O serviço pode ser iniciado com o Docker Compose:

```bash
docker-compose up -d
```

### Gerando a Aplicação

Você pode gerar a aplicação utilizando o **Gradle**:

```bash
./gradlew build
```

### Executando a Aplicação

Executando a Aplicação via IntelliJ (Recomendado)

Para rodar o projeto localmente em sua IDE IntelliJ, utilize o seguinte comando:

```bash
gradle bootRun --args='--spring.profiles.active=local' -Pprofile=local
```

### Esse comando irá executar o processo completo, incluindo:

- **Subida dos serviços necessários (banco de dados, etc.);**:
- **Compilação dos códigos gerados via OpenAPI;**:
- **Execução das migrations do Flyway para construção do banco de dados;**:
- **Subida da aplicação;**:
- **Geração de relatório de cobertura de código automaticamente após os testes.**:

Para rodar a aplicação, em outra IDE:

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```

Isso irá rodar a aplicação utilizando o perfil **local**, que pode ser configurado no arquivo `application-local.yml`.

### Rodando Migrations com Flyway

O projeto utiliza **Flyway** para controle de versões do banco de dados. As migrations serão executadas automaticamente quando a aplicação for iniciada, mas você pode rodá-las manualmente com o seguinte comando:

```bash
./gradlew :sdj.data:runFlywayMigrations
```

### Testes e Cobertura de Código

Os testes podem ser executados com o comando:

```bash
./gradlew test
```

A cobertura de código é gerada automaticamente após os testes e pode ser visualizada acessando o relatório em HTML:

```bash
./build/jacocoHtml/index.html
```

### Geração de Documentação com OpenAPI

A documentação das APIs é gerada automaticamente via **OpenAPI**. Para gerar a documentação, use o seguinte comando:

```bash
./gradlew openApiGenerate
```

## Dependências Principais

- **PostgreSQL**: Conector JDBC para o banco de dados.
- **Spring Boot Starter Data JPA**: Integração com o JPA para persistência de dados.
- **Mockito**: Framework para testes unitários.
- **Jacoco**: Ferramenta para geração de relatórios de cobertura de testes.

## Estrutura de Perfis

O projeto utiliza diferentes perfis para gerenciar configurações específicas para cada ambiente:

- **application.yml**: Configurações gerais.
- **application-local.yml**: Configurações para o ambiente local.
---