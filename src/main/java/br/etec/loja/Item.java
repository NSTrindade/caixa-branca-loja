package br.etec.loja;

/**
 * Item de um carrinho de compras.
 *
 * @param nome       nome do produto
 * @param preco      preco unitario (>= 0)
 * @param quantidade quantidade desejada pelo cliente (> 0)
 * @param estoque    quantidade disponivel em estoque (>= 0)
 */
public record Item(String nome, double preco, int quantidade, int estoque) {

    public Item {
        if (preco < 0) {
            throw new IllegalArgumentException("Preco nao pode ser negativo");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
    }

    /** Verifica se ha estoque suficiente para atender o pedido do cliente. */
    public boolean temEstoque() {
        return estoque >= quantidade;
    }

    public double subtotal() {
        return preco * quantidade;
    }
}
