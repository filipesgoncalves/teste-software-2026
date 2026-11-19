# Semana 11: testes de integração com Spring Boot

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

### Por que buscar com `repository.findById()` em vez de olhar só o objeto retornado pelo serviço?

O objeto que o serviço devolve não garante que algo foi salvo. Buscando no repositório dá para confirmar que o produto está no banco. Antes da busca os testes chamam `flush()` e `clear()` no `EntityManager`, para o JPA ler do H2 e não do cache.

### O que o `@Transactional` faz no fim de cada teste?

Desfaz tudo o que o teste gravou (rollback). Assim um teste não interfere no outro.

### Quais componentes são exercitados no Exercício 2?

Controller, service, repository, JPA e H2, todos reais.

### Por que o MockMvc não precisa de um servidor HTTP?

Ele simula a requisição e chama o Spring MVC direto, dentro do próprio teste, sem abrir porta.

### Qual a diferença para um teste unitário do controller?

No teste unitário o service seria um mock e só o controller seria testado. Aqui todas as camadas rodam juntas, então o teste pega erro na ligação entre elas.
