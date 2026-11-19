package br.edu.exemplo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CarrinhoTest {

    private static final double DELTA = 0.001;

    @Test
    void carrinhoVazioDeveTerTotalZero() {
        Carrinho carrinho = new Carrinho();

        assertEquals(0, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAdicionarUmProduto() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100));

        assertEquals(100, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveSomarVariosProdutos() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Teclado", 100));
        carrinho.adicionar(new Produto("Mouse", 50));

        assertEquals(150, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveRemoverProduto() {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Produto("Teclado", 100));
        carrinho.adicionar(new Produto("Mouse", 50));

        carrinho.remover("Mouse");

        assertEquals(100, carrinho.getTotal(), DELTA);
    }

    @Test
    void naoDevePermitirProdutoComPrecoNegativo() {
        Carrinho carrinho = new Carrinho();

        assertThrows(IllegalArgumentException.class,
                () -> carrinho.adicionar(new Produto("Teclado", -100)));
        assertEquals(0, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoDe10PorCento() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 600));

        assertEquals(540, carrinho.getTotal(), DELTA);
    }

    @Test
    void naoDeveAplicarDescontoEm499e99() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 499.99));

        assertEquals(499.99, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoEm500() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 500));

        assertEquals(450, carrinho.getTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoEm500e01() {
        Carrinho carrinho = new Carrinho();

        carrinho.adicionar(new Produto("Monitor", 500.01));

        assertEquals(450.009, carrinho.getTotal(), DELTA);
    }
}
