# Money Subscriptions: Sistema de Controle de Assinaturas

Um gerenciador centralizado para acompanhar, analisar e otimizar assinaturas de serviços (streaming, SaaS, ferramentas de desenvolvimento, etc.), desenvolvido para resolver o problema de custos pulverizados e falta de visibilidade financeira.

---

## 📌 Contexto e Justificativa

No cenário atual, o modelo de negócios baseado em assinatura (*subscription-based*) tornou-se o padrão para a grande maioria dos serviços digitais. Com o tempo, o acúmulo de assinaturas (pessoais e profissionais) tende a crescer de forma desordenada. 

Este projeto nasceu de uma necessidade real e diária: a dificuldade de rastrear **onde**, **quando** e **quanto** está sendo alocado em cada serviço. Quando as informações ficam espalhadas em faturas de diferentes cartões, e-mails e contas bancárias, perde-se o controle do orçamento. O **MoneySubscriptions** centraliza esses dados para fornecer uma visão clara, preditiva e consolidada dos seus gastos fixos e recorrentes.

---

## 🚀 Escopo do MVP (Mínimo Produto Viável)

O objetivo principal do MVP é entregar valor imediato com foco na **centralização** e **visualização** dos dados, sem a complexidade inicial de fluxos multiusuário.

### 1. Cadastro de Assinaturas (CRUD)
Permitir o gerenciamento completo das assinaturas com os seguintes campos essenciais:
* **Nome do Serviço:** Ex: Netflix, AWS, Spotify, GitHub Pro.
* **Valor:** Preço cobrado por ciclo.
* **Moeda:** Suporte inicial para BRL (R$) e USD ($), considerando serviços internacionais.
* **Periodicidade:** Mensal ou Anual.
* **Data de Vencimento/Cobrança:** Dia do mês ou data específica do ano para renovação.
* **Categoria:** Ex: Entretenimento, Trabalho, Produtividade, Saúde.
* **Status:** Ativa ou Inativa.

### 2. Relatórios e Visualização (Dashboard Simplificado)
* **Gasto Total Mensal/Anual:** Cálculo consolidado convertido para a moeda principal.
* **Distribuição por Categoria:** Gráfico ou indicador de onde o dinheiro está concentrado.
* **Próximos Vencimentos:** Uma listagem cronológica das assinaturas que serão cobradas nos próximos dias para evitar surpresas no saldo.

---

## 🔮 Roadmap de Evolução (Futuras Funcionalidades)

Após a validação da dinâmica de cadastro e relatórios do MVP, o projeto será expandido com as seguintes *features*:

- [ ] **Autenticação e Controle de Usuários:** Sistema de login seguro (OAuth / JWT) para permitir que múltiplos usuários gerenciem suas próprias assinaturas de forma isolada.
- [ ] **Notificações:** Alertas por E-mail, WhatsApp ou Telegram dias antes de uma assinatura expirar ou ser renovada.
- [ ] **Histórico de Preços:** Rastreamento de reajustes no valor das assinaturas ao longo do tempo.
- [ ] **Conversão Automática de Câmbio:** Integração com API de cotação para atualizar o valor real de assinaturas em USD/EUR baseado no câmbio do dia.
- [ ] **Sugestões de Otimização:** Identificação de serviços pouco utilizados ou alternativas mais baratas.

---

## 🛠️ Tecnologias Sugeridas para a Implementação

* **Frontend:** Next.js
* **Backend:** Java 21 com Spring
* **Banco de Dados:** PostgreSQL para ambiente produtivo e SQLite para ambiente de dev

---

## ⚙️ Como Executar o Projeto (Instruções para o Desenvolvedor)

```bash
# Exemplo genérico
# 1. Clonar o repositório
git clone [https://github.com/ERodrigues/MoneySubscriptions.git](https://github.com/ERodrigues/MoneySubscriptions.git)

# 2. Instalar as dependências
npm install 

# 3. Executar em ambiente de desenvolvimento
npm run dev 