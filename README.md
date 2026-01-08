# 💰 Gerenciador de Finanças Pessoais (Java + MySQL)

Aplicação de console para controle financeiro pessoal. Este projeto marca a evolução de estudos em Java, saindo da lógica em memória para uma aplicação conectada a um banco de dados real.

## 🧠 Sobre o Desenvolvimento

Este projeto foi desenvolvido em duas etapas distintas de aprendizado:

1.  **Lógica e Regras de Negócio (Autoral):**
    * Desenvolvimento manual de toda a lógica de programação (loops, condicionais, tratamento de exceções).
    * Estruturação da Orientação a Objetos (`Models`, `Service` e `Main`).
    * Implementação das regras de cálculo de saldo e validações.

2.  **Infraestrutura e Banco de Dados (Mentoria/Guiado):**
    * A camada de persistência (`Repository/DAO` e `UTils`) foi implementada com auxílio de mentoria técnica (AI) para introdução ao padrão JDBC e arquitetura Enterprise.
    * Scripts SQL e configuração de drivers foram fornecidos para estudo e integração.

## 🚀 Funcionalidades

- [x] Adicionar Receitas
- [x] Adicionar Despesas
- [x] Cálculo automático de saldo
- [x] Extrato detalhado
- [x] Persistência de dados MySQL (Padrão DAO)

## 🛠️ Tecnologias Utilizadas

- **Java 25** (Core Logic)
- **MySQL 8** (Banco de Dados)
- **JDBC** (Driver de Conexão)
- **IntelliJ IDEA** (IDE)

## 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:
- **App (View):** Menu e interação com usuário (Autoral).
- **Service (Controller):** Lógica de negócio (Autoral).
- **Model:** Objetos Transação e Enum (Autoral).
- **Repository (DAO):** Comandos SQL e acesso a dados (Guiado).
- **Infra:** Fábrica de conexões JDBC (Guiado).

## ⚙️ Como rodar o projeto

### Pré-requisitos
1. Ter o **Java JDK** instalado.
2. Ter o **MySQL Server** rodando.

### Configuração do Banco
Execute o script abaixo no seu cliente MySQL (ex: DBeaver) para criar a estrutura necessária:

```sql
CREATE DATABASE IF NOT EXISTS financas_db;
USE financas_db;

CREATE TABLE IF NOT EXISTS transacoes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    tipo VARCHAR(20) NOT NULL
);
