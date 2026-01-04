# API de Validação de Senha (Java + Spring Boot)

## Visão Geral

Esta aplicação expõe uma API REST para validar se uma senha atende a critérios de segurança pré-definidos. O foco da solução é clean code, SOLID, baixo acoplamento, testabilidade, segurança e extensibilidade.

A API não armazena nem loga senhas em texto puro.

## Requisitos de Validação

Uma senha é considerada válida quando:

* Possui **9 ou mais caracteres**

* Contém **ao menos 1 dígito** (0-9)

* Contém **ao menos 1 letra minúscula** (a-z)

* Contém **ao menos 1 letra maiúscula** (A-Z)

* Contém **ao menos 1 caractere especial**: !@#$%^&*()-+

* **Não possui caracteres repetidos**

* **Não contém espaços em branco**

## Design da API

### Endpoint
```
POST /api/password/validate
```

### Request
```
{
    "password": "AbTp9!fok"
}
```

### Response
```
{
    "valid": true
}
```

### HTTP Status

* 200 OK → requisição válida

* 400 Bad Request → payload inválido