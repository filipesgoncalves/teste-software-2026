# Teste de Software 2026 — Atividades

Soluções das atividades da disciplina, a partir do repositório [JoaoChoma/teste_software_2026](https://github.com/JoaoChoma/teste_software_2026).

| Semana | Atividade | Pasta |
|---|---|---|
| 10 | TDD nos quatro projetos, com cobertura total | [`semana10`](semana10) |
| 11 | Testes de integração no projeto Spring Boot | [`semana11/exercicios_integracao_springboot`](semana11/exercicios_integracao_springboot) |
| 14 | Teste de usuário final | [`semana14`](semana14) |

## Executar

Requer Java 17 e Maven.

```bash
mvn verify
```

Roda todos os testes das semanas 10 e 11. Na semana 10 o JaCoCo exige 100% de cobertura de linhas e ramos; o relatório fica em `semana10/<projeto>/target/site/jacoco/index.html`.

## SEMANA 10 — TDD

| Projeto | Implementação | Testes | Linhas | Ramos |
|---|---|---|---|---|
| `bubblesort-tdd` | `BubbleSort.ordenar()` | 3 | 100% | 100% |
| `carrinho-compras-tdd` | `Carrinho` | 9 | 100% | 100% |
| `conta-bancaria-tdd-maven` | `depositar()` e `sacar()` | 6 | 100% | 100% |
| `estoque-tdd` | `adicionarEstoque()` e `retirarEstoque()` | 8 | 100% | 100% |

Decisões:

- **Carrinho, requisito 5:** produto com preço negativo lança `IllegalArgumentException` e o carrinho não muda.
- **Carrinho, requisito 6:** desconto de 10% a partir de R$ 500,00, testado nas fronteiras R$ 499,99, R$ 500,00 e R$ 500,01.
- **Carrinho, requisito 4:** remoção pelo nome do produto.
- **Conta bancária e estoque:** valores negativos ou zero são ignorados, sem alterar saldo ou estoque.
- Os testes fornecidos pelo professor foram mantidos; os novos cobrem os requisitos restantes e os ramos que faltavam.

## SEMANA 11 — Testes de integração

CT01 a CT05 implementados sem mocks, com `@SpringBootTest`, `@Transactional`, H2 e MockMvc. Detalhes e respostas da discussão no [README do projeto](semana11/exercicios_integracao_springboot/README.md).

## SEMANA 14 — Teste de usuário final

Protocolo, tarefas, planilha de observação, questionário SUS e consolidação dos critérios de sucesso em [`semana14/README.md`](semana14/README.md).
