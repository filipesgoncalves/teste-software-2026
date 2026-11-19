# Teste de Software 2026

Atividades da disciplina de Teste de Software, feitas sobre o repositório do professor: [JoaoChoma/teste_software_2026](https://github.com/JoaoChoma/teste_software_2026).

## Estrutura

- `semana10`: TDD nos projetos bubblesort, carrinho de compras, conta bancária e estoque
- `semana11`: testes de integração no projeto Spring Boot
- `semana14`: teste de usuário final

## Como executar

Precisa de Java 17 e Maven.

```bash
mvn verify
```

Esse comando roda os testes das semanas 10 e 11. Na semana 10 o JaCoCo está configurado para exigir 100% de cobertura de linhas e ramos. O relatório fica em `semana10/<projeto>/target/site/jacoco/index.html`.

## Semana 10

| Projeto | Testes | Cobertura |
|---|---|---|
| bubblesort-tdd | 3 | 100% |
| carrinho-compras-tdd | 9 | 100% |
| conta-bancaria-tdd-maven | 6 | 100% |
| estoque-tdd | 8 | 100% |

## Semana 11

Testes CT01 a CT05 com `@SpringBootTest`, H2 e MockMvc. Mais detalhes no [README do projeto](semana11/exercicios_integracao_springboot/README.md).

## Semana 14

Roteiro, planilha de observação e questionário SUS em [semana14](semana14/README.md).
