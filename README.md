# Desafio Itaú Backend

API REST desenvolvida em Java com Spring Boot para processamento de transações financeiras e cálculo de estatísticas em tempo real.

---

## Sobre o Projeto

Esta aplicação permite o registro de transações contendo valor e data/hora, aplicando validações de negócio, e fornece estatísticas baseadas nas transações realizadas nos **últimos 60 segundos**.

As estatísticas retornadas incluem:

- `count` → quantidade de transações
- `sum` → soma total dos valores
- `avg` → média das transações
- `min` → menor valor
- `max` → maior valor

O armazenamento é feito **em memória**, com suporte a concorrência, garantindo simplicidade e desempenho.

---

## Funcionalidades

- Registro de transações
- Validação de dados (valor e data/hora)
- Remoção automática de transações fora da janela de 60 segundos
- Cálculo de estatísticas em tempo real
- Exclusão de todas as transações
- Testes automatizados
- Documentação interativa com Swagger

---

## 🛠Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Validation
- Lombok
- Springdoc OpenAPI (Swagger)
- JUnit / MockMvc

---

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/meuprojeto/desafioitau/
│   │   ├── Config/
│   │   ├── Docs/
│   │   ├── Estatisticas/
│   │   ├── Transações/
│   │   └── DesafioItauApplication.java
│   └── resources/
│       └── application.yml
│
└── test/
    └── java/br/com/meuprojeto/desafioitau/
        ├── EstatisticaControllerTest.java
        └── TransacaoControllerTest.java
```

---

## Endpoints

### Criar Transação

**POST** `/transacao`

```json
{
  "valor": 100.0,
  "dataHora": "2026-04-21T20:00:00-03:00"
}
```

### Obter Estatísticas

**GET** `/estatistica`

```json
{
  "count": 2,
  "sum": 30.0,
  "avg": 15.0,
  "min": 10.0,
  "max": 20.0
}
```

### Deletar Transações

**DELETE** `/transacao`

---

## Regras de Negócio

- O corpo da requisição não pode ser nulo
- O valor deve ser maior ou igual a zero
- A data/hora deve estar no passado (não pode ser futura)
- Apenas transações dos últimos 60 segundos são consideradas nas estatísticas

---

## Testes

O projeto possui testes automatizados para:

- Validação de transações
- Cálculo correto das estatísticas
- Cenário sem transações (estatísticas zeradas)
- Comportamento dos endpoints

---

## Documentação da API

Disponível via Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Como Executar

### Pré-requisitos

- Java 17
- Maven

### Executando o projeto

```bash
./mvnw spring-boot:run
```

Ou no Windows:

```cmd
mvnw.cmd spring-boot:run
```

---

## Objetivo

Este projeto foi desenvolvido como solução para um desafio técnico, com foco em:

- Boas práticas de desenvolvimento
- Arquitetura em camadas (Controller, Service, Repository)
- Código limpo e organizado
- Testes automatizados
- Simplicidade e eficiência

---

## Considerações Finais

A aplicação foi projetada para ser simples, performática e aderente às regras do desafio, utilizando armazenamento em memória e operações eficientes para cálculo de estatísticas em tempo real.