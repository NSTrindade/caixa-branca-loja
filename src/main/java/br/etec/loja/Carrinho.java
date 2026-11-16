package br.etec.loja;

import java.util.ArrayList;
import java.util.List;

/**
 * Carrinho de compras. Contem uma ESTRUTURA DE REPETICAO (for) com decisao (if)
 * e desvio de fluxo (continue) - ideal para estudar cobertura de caminhos.
 */
public class Carrinho {

    private final List<Item> itens = new ArrayList<>();

    public void adicionar(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item nao pode ser nulo");
        }
        itens.add(item);
    }

    /**
     * Soma apenas os itens com estoque suficiente.
     * Itens sem estoque sao ignorados (continue).
     */
    public double calcularSubtotal() {
        double total = 0;
        for (Item item : itens) {
            if (!item.temEstoque()) {
                continue; // caminho: item sem estoque e ignorado
            }
            total += item.subtotal();
        }
        return total;
    }

    /** Conta quantos itens nao puderam ser atendidos por falta de estoque. */
    public int contarItensSemEstoque() {
        int quantidade = 0;
        for (Item item : itens) {
            if (!item.temEstoque()) {
                quantidade++;
            }
        }
        return quantidade;
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}
