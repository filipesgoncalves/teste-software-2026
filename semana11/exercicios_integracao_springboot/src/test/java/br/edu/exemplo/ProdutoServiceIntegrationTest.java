package br.edu.exemplo;

import br.edu.exemplo.model.Produto;
import br.edu.exemplo.repository.ProdutoRepository;
import br.edu.exemplo.service.ProdutoService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProdutoServiceIntegrationTest {

    @Autowired
    ProdutoService service;

    @Autowired
    ProdutoRepository repository;

    @Autowired
    EntityManager entityManager;

    @Test
    void deveCadastrarERecuperarProduto() {
        Produto salvo = service.cadastrar("Teclado", 150.0);
        assertNotNull(salvo.getId());

        recarregarDoBanco();
        Produto encontrado = repository.findById(salvo.getId()).orElseThrow();

        assertEquals("Teclado", encontrado.getNome());
        assertEquals(150.0, encontrado.getPreco());
    }

    @Test
    void naoDevePersistirProdutoComNomeEmBranco() {
        long antes = repository.count();

        assertThrows(IllegalArgumentException.class, () -> service.cadastrar(" ", 80.0));

        assertEquals(antes, repository.count());
    }

    @Test
    void deveAtualizarPrecoPersistido() {
        Produto salvo = service.cadastrar("Mouse", 80.0);

        service.atualizarPreco(salvo.getId(), 95.0);
        recarregarDoBanco();

        assertEquals(95.0, repository.findById(salvo.getId()).orElseThrow().getPreco());
    }

    private void recarregarDoBanco() {
        entityManager.flush();
        entityManager.clear();
    }
}
