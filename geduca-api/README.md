# GEDUCA-API


#### Ambiente

- JDK 1.8
- Maven 3.6
- MySQL ou PostgreSQL
- Loombok

#### Spring e MySQL

Classe que inicia o projeto: `GeducaWebApplication`

Arquivo de configuração do Spring-Boot `application.yml`:

Utilizar um dos profiles

- MYSQL: ```application-local-mysql.yml```
- PostgreSQL: ```application-local-postgres.yml```

Alterar nome de usuário, senha e porta caso seja necessário


#### Acessos 

Url: 
```
http://localhost:12333/geduca
```

#### Arquitetura

```
.
└── api
    ├── config (spring security)
    ├── model (Entidades Básicas)
    ├── repository (Repositorios e Implementacao)
    ├── service (Regras de Negocio)
    ├── controller (Rest controllers)
└── Resources (Configurações)
```
  

