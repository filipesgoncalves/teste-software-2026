# SEMANA 11 — Testes de integração com Spring Boot

Java 17, Spring Boot 3.3, Spring Data JPA, H2, JUnit 5 e MockMvc. Nenhum mock substitui `ProdutoService` ou `ProdutoRepository`.

```bash
mvn test
```

## Casos de teste

| Caso | Teste | Fluxo exercitado | Verificação |
|---|---|---|---|
| CT01 | `ProdutoServiceIntegrationTest.deveCadastrarERecuperarProduto` | Service → Repository → H2 | ID gerado; registro relido do banco com nome `Teclado` e preço `150.0` |
| CT02 | `ProdutoServiceIntegrationTest.naoDevePersistirProdutoComNomeEmBranco` | Service → Repository → H2 | `IllegalArgumentException`; `count()` inalterado |
| CT03 | `ProdutoServiceIntegrationTest.deveAtualizarPrecoPersistido` | Service → Repository → H2 | Preço relido do banco igual a `95.0` |
| CT04 | `ProdutoControllerIntegrationTest.deveCadastrarProdutoPelaApi` | MockMvc → Controller → Service → Repository → H2 | HTTP 200; `id` numérico, `nome` `Mouse`, `preco` `80.0` |
| CT05 | `ProdutoControllerIntegrationTest.deveBuscarProdutoPelaApi` | MockMvc → Controller → Service → Repository → H2 | ID extraído do POST com `ObjectMapper`; `GET /produtos/{id}` retorna 200 com os mesmos dados |

## Resultado

```text
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Discussão

**`repository.findById()` x objeto retornado pelo serviço.** O objeto retornado pelo serviço só prova que o método devolveu algo; a consulta ao repositório prova que o registro foi gravado. Como o JPA mantém um cache de primeiro nível na mesma transação, os testes chamam `flush()` e `clear()` no `EntityManager` antes da consulta, forçando a leitura no H2.

**`@Transactional` ao fim de cada teste.** O Spring abre uma transação por método de teste e faz rollback ao final, então nenhum teste enxerga dados de outro e a ordem de execução não importa.

**Componentes exercitados no Exercício 2.** Serialização JSON, `DispatcherServlet`, `ProdutoController`, `ProdutoService`, `ProdutoRepository`, Hibernate e H2, todos reais.

**Por que o MockMvc dispensa servidor HTTP.** Ele chama o `DispatcherServlet` diretamente com requisições simuladas, na mesma thread e no mesmo contexto Spring do teste. Sem porta aberta, o rollback do `@Transactional` também vale para os dados gravados pela API.

**Diferença para o teste unitário do controller.** No teste unitário (`@WebMvcTest` com `@MockBean`) o serviço é simulado e só o mapeamento HTTP é verificado. Aqui as regras de negócio e a persistência rodam de verdade, então o teste detecta falhas na integração entre as camadas.
