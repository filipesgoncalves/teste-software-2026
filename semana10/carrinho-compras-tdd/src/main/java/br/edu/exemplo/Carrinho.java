package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final double VALOR_MINIMO_DESCONTO = 500.0;
    private static final double PERCENTUAL_DESCONTO = 0.10;

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        produtos.add(produto);
    }

    public void remover(String nome) {
        produtos.removeIf(produto -> produto.getNome().equals(nome));
    }

    public double getTotal() {
        double subtotal = produtos.stream().mapToDouble(Produto::getPreco).sum();
        return subtotal >= VALOR_MINIMO_DESCONTO ? subtotal * (1 - PERCENTUAL_DESCONTO) : subtotal;
    }
}
