# Diagrama Flowchart — Simulador de Hidrômetro

Abaixo seguem dois formatos de diagrama para o projeto: PlantUML e Mermaid.

## Mermaid

```mermaid
flowchart TD
  Start([Inicia aplicação - Main]) --> CreateModel["Criar: Hidrometro (modelo)"]
  CreateModel --> CreateConfig["Criar: ConfiguracaoView"]
  CreateConfig --> CreateView["Criar: HidrometroView"]
  CreateView --> CreateController["Criar: HidrometroController e registrar listeners"]
  CreateController --> ShowConfig["Exibir ConfiguracaoView"]

  ShowConfig -->|Clique: Iniciar Simulação| ReadFlow["Ler vazão inicial"]
  ReadFlow --> SetFlow["Setar vazão no modelo"]
  SetFlow --> CloseConfig["Fechar ConfiguracaoView"]
  CloseConfig --> ShowMain["Exibir HidrometroView"]
  ShowMain --> StartTimer["Iniciar Timer (1s)"]

  StartTimer --> TimerLoop{"Timer ativo?"}
  TimerLoop -->|Sim| UpdateModel["modelo.atualizarConsumo()"]
  UpdateModel --> UpdateView["Controller atualiza HidrometroView"]
  UpdateView --> TimerActions{Ações do Usuário}

  TimerActions -->|Aumentar| Increase["ajustarVazao(+0.1)"]
  TimerActions -->|Diminuir| Decrease["ajustarVazao(-0.1)"]
  TimerActions -->|Resetar| Reset["resetar() e atualizar view"]
  TimerActions --> TimerLoop

  TimerLoop -->|Não| End([Fim])
```

## Como visualizar
- Para visualizar o `diagrama_flowchart.puml`, use uma ferramenta PlantUML (VS Code PlantUML extension).
- Para visualizar `diagrama_flowchart.md`, abra no VS Code com suporte a Mermaid (p.ex. Markdown Preview Enhanced).
