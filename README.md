# 💰 Gerenciador de Finanças Pessoais (Java + MySQL)

Aplicação de console para controle financeiro pessoal, desenvolvida para praticar lógica de programação, Orientação a Objetos e integração com Banco de Dados (JDBC).

## 🚀 Funcionalidades

- [x] Adicionar Receitas
- [x] Adicionar Despesas
- [x] Cálculo automático de saldo
- [x] Extrato detalhado vindo do banco de dados
- [x] Persistência de dados MySQL (Padrão DAO)

## 🛠️ Tecnologias Utilizadas

- **Java 25** (Lógica e Regras de Negócio)
- **MySQL 8** (Banco de Dados Relacional)
- **JDBC** (Java Database Connectivity)
- **DBeaver** (Cliente SQL para testes)
- **IntelliJ IDEA** (IDE)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas para desacoplamento:
- **App (View):** Interação com o usuário via console.
- **Service (Controller):** Regras de negócio e validações.
- **Model:** Representação dos objetos (Transação, Enum).
- **Repository (DAO):** Camada de acesso a dados (SQL puro).
- **Infra:** Gerenciamento de conexões (Factory Pattern).

## ⚙️ Como rodar o projeto

### Pré-requisitos
1. Ter o **Java JDK** instalado.
2. Ter o **MySQL Server** rodando.

### Configuração do Banco
Execute o script abaixo no seu cliente MySQL (ex: DBeaver) para criar a estrutura:

```sql
CREATE DATABASE IF NOT EXISTS financas_db;
USE financas_db;

CREATE TABLE IF NOT EXISTS transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);
