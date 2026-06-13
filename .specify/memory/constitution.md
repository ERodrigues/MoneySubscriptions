# MoneySubscriptions Constitution

## Core Principles

### I. Qualidade Sustentavel
Todo codigo deve ser simples de entender, manter e evoluir. Mudancas devem ter escopo claro, nomes expressivos, baixo acoplamento e comportamento previsivel. Duplicacao, complexidade acidental e abstracoes prematuras devem ser removidas ou justificadas no plano da feature.

Revisoes devem validar legibilidade, consistencia com os padroes existentes do projeto, tratamento de erros e ausencia de regressao em fluxos ja suportados. Codigo que toca dados financeiros, assinaturas, cobrancas, datas ou valores monetarios exige cuidado extra com precisao, arredondamento, fuso horario e estados limite.

### II. Testes como Contrato de Confianca
Toda feature ou correcao deve vir acompanhada de testes proporcionais ao risco. A regra minima e: logica de dominio com testes unitarios, fluxos entre camadas com testes de integracao, e jornadas criticas do usuario com testes de interface ou ponta a ponta quando aplicavel.

Testes devem cobrir caminhos felizes, erros esperados e casos limite relevantes, especialmente renovacoes, cancelamentos, valores recorrentes, datas de vencimento, moedas, filtros e persistencia. Bugs corrigidos devem ganhar teste de regressao. Nenhuma entrega deve depender apenas de verificacao manual quando o comportamento puder ser automatizado de forma confiavel.

### III. UX Consistente e Orientada a Tarefa
A experiencia deve ser previsivel, acessivel e coerente em todos os fluxos. Componentes, textos, estados vazios, carregamento, erro, confirmacao e navegacao devem seguir os mesmos padroes visuais e comportamentais do produto.

Cada tela deve deixar claro o que o usuario pode fazer, qual o estado atual dos dados e quais acoes sao destrutivas ou irreversiveis. Interfaces para dinheiro, assinaturas e prazos devem priorizar clareza, leitura rapida, formatos locais apropriados e feedback imediato. Mudancas visuais devem considerar responsividade, teclado, leitores de tela, contraste e estados de foco.

### IV. Performance Percebida e Medida
Performance e requisito funcional. Cada feature deve definir ou preservar metas de tempo de resposta, carregamento, renderizacao e uso de recursos adequadas ao seu contexto. Operacoes comuns devem parecer imediatas; operacoes mais longas devem oferecer feedback claro e nao bloquear interacoes desnecessariamente.

Listas, agregacoes, filtros, graficos e sincronizacoes devem evitar trabalho repetido, consultas excessivas, renderizacoes desnecessarias e payloads maiores que o necessario. Qualquer regressao conhecida de performance deve ser documentada com justificativa, impacto e plano de mitigacao.

### V. Evolucao Segura e Observavel
O sistema deve falhar de forma compreensivel e recuperavel. Erros esperados devem ser tratados com mensagens uteis para o usuario e diagnostico suficiente para desenvolvimento. Mudancas em contratos, dados persistidos, integracoes ou comportamento publico devem ser versionadas, migraveis e reversiveis quando possivel.

Features devem favorecer incrementos pequenos, verificaveis e compativeis com o estado atual do produto. Quando uma decisao aumentar complexidade, custo operacional ou risco de regressao, o plano deve registrar a alternativa mais simples considerada e por que ela nao atende.

## Quality Gates

Antes de iniciar implementacao, cada plano deve declarar:

- Como a mudanca preserva ou melhora a qualidade do codigo.
- Codigo deve ter no mínimo 90% de cobertura de testes de domínio
- Quais testes serao criados ou atualizados, e quais riscos eles cobrem.
- Quais padroes de UX existentes serao reutilizados.
- Quais metas ou restricoes de performance se aplicam.
- Quais casos limite financeiros, temporais ou de estado precisam ser tratados.

Antes de considerar uma entrega pronta:

- Testes relevantes devem passar localmente ou a impossibilidade deve ser registrada com motivo concreto.
- Fluxos criticos afetados devem ser verificados em estados de sucesso, erro, carregamento e vazio.
- A interface deve permanecer responsiva, acessivel e consistente nos tamanhos de tela suportados.
- Regressao de performance, aumento de bundle, consulta adicional ou renderizacao custosa deve ser medida ou justificada.
- Documentacao, tipos, contratos e dados de exemplo devem refletir o comportamento entregue quando forem afetados.

## Development Workflow

O trabalho deve seguir ciclos curtos: entender o comportamento esperado, identificar riscos, escrever ou ajustar testes, implementar, verificar e revisar o impacto no produto. A implementacao deve preferir padroes ja existentes no repositorio e introduzir novas dependencias apenas quando reduzirem complexidade real ou risco.

Especificacoes devem descrever o resultado do ponto de vista do usuario e os criterios de aceitacao devem ser testaveis. Planos devem explicitar estrutura de arquivos, estrategia de testes, impactos de UX e performance. Tarefas devem ser pequenas o suficiente para revisao objetiva e agrupadas por valor entregue.

## Governance

Esta constituicao tem prioridade sobre preferencias locais ou atalhos de implementacao. Toda especificacao, plano, tarefa e revisao deve verificar conformidade com estes principios.

Emendas exigem uma justificativa escrita, avaliacao de impacto em templates/specs existentes e atualizacao da versao. Mudancas que adicionam ou removem principios incrementam a versao major. Mudancas que alteram substancialmente gates ou fluxo incrementam a versao minor. Ajustes editoriais incrementam a versao patch.

Violacoes temporarias sao permitidas apenas quando documentadas no plano com motivo, risco, alternativa rejeitada e tarefa de acompanhamento. A ausencia de testes, a inconsistencia de UX ou a regressao de performance nunca deve ser tratada como detalhe invisivel.

**Version**: 1.0.0 | **Ratified**: 2026-06-13 | **Last Amended**: 2026-06-13
