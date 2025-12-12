# 🔔 NotifyMe - Microsserviço de Notificações Assíncronas

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3-green)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-Messaging-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue)
![Docker](https://img.shields.io/badge/Docker-Compose-2496ED)

> **NotifyMe** é uma API desenhada para lidar com o envio de notificações de forma resiliente, desacoplada e escalável, utilizando o padrão de arquitetura orientada a eventos.

---

## 🚀 Sobre o Projeto

Em sistemas tradicionais, o envio de e-mails ou SMS muitas vezes é feito de forma **síncrona**, bloqueando a experiência do usuário e criando pontos de falha (se o serviço de e-mail cair, a aplicação cai).

O **NotifyMe** resolve isso implementando uma arquitetura **Producer-Consumer** com filas:
1. A API recebe o pedido e responde imediatamente ao usuário (Baixa Latência).
2. O pedido é salvo e enfileirado no RabbitMQ.
3. Um Worker em background processa o envio real, garantindo retentativas (Retries) e tolerância a falhas.

### 🎯 Objetivos de Aprendizado
- Implementação de Microsserviços com **Spring Boot**.
- Comunicação assíncrona com **RabbitMQ**.
- Containerização de banco e broker com **Docker Compose**.
- Padrões de Resiliência e tratamento de erros.

---

## 🏗️ Arquitetura e Fluxo

```mermaid
sequenceDiagram
    participant User as Client (Postman)
    participant API as NotifyMe API
    participant DB as PostgreSQL
    participant Broker as RabbitMQ
    participant Worker as NotifyMe Consumer
    
    User->>API: POST /notifications
    API->>DB: Salva Notificação (Status: PENDING)
    API->>Broker: Publica Mensagem (Exchange)
    API-->>User: 202 Accepted (Retorno Imediato)
    
    Note right of API: Fluxo Assíncrono Abaixo
    
    Broker->>Worker: Consome Mensagem
    Worker->>Worker: Simula Envio de E-mail
    alt Sucesso
        Worker->>DB: Atualiza Status: SENT
    else Erro
        Worker->>DB: Atualiza Status: ERROR
    end
