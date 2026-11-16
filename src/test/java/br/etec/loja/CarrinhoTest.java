package br.etec.loja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Testes de Carrinho e Item: lacos for, if e continue. */
class CarrinhoTest {

    @Test
    @DisplayName("CT-C01: carrinho vazio -> laco com zero iteracoes, subtotal 0")
    void carrinhoVazio() {
        Carrinho c = new Carrinho();
        assertTrue(c.estaVazio());
        assertEquals(0.0, c.calcularSubtotal(), 0.001);
        assertEquals(0, c.contarItensSemEstoque());
    }

    @Test
    @DisplayName("CT-C02: todos os itens com estoque -> soma tudo")
    void todosComEstoque() {
        Carrinho c = new Carrinho();
        c.adicionar(new Item("Teclado", 150.0, 2, 10)); // 300
        c.adicionar(new Item("Mouse", 80.0, 1, 5));     // 80
        assertFalse(c.estaVazio());
        assertEquals(380.0, c.calcularSubtotal(), 0.001);
        assertEquals(0, c.contarItensSemEstoque());
    }

    @Test
    @DisplayName("CT-C03: item sem estoque e ignorado (caminho do continue)")
    void itemSemEstoqueIgnorado() {
        Carrinho c = new Carrinho();
        c.adicionar(new Item("Teclado", 150.0, 2, 10)); // 300
        c.adicionar(new Item("Monitor", 900.0, 1, 0));  // sem estoque
        assertEquals(300.0, c.calcularSubtotal(), 0.001);
        assertEquals(1, c.contarItensSemEstoque());
    }

    @Test
    @DisplayName("CT-C04: todos sem estoque -> subtotal 0")
    void todosSemEstoque() {
        Carrinho c = new Carrinho();
        c.adicionar(new Item("Monitor", 900.0, 1, 0));
        assertEquals(0.0, c.calcularSubtotal(), 0.001);
        assertEquals(1, c.contarItensSemEstoque());
    }

    @Test
    @DisplayName("CT-C05: adicionar item nulo lanca excecao")
    void adicionarNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Carrinho().adicionar(null));
    }

    // ---------- Item ----------
    @Test
    @DisplayName("CT-I01: estoque exatamente igual a quantidade -> tem estoque")
    void estoqueExato() {
        assertTrue(new Item("Cabo", 10.0, 3, 3).temEstoque());
    }

    @Test
    @DisplayName("CT-I02: estoque menor que a quantidade -> sem estoque")
    void estoqueInsuficiente() {
        assertFalse(new Item("Cabo", 10.0, 3, 2).temEstoque());
    }

    @Test
    @DisplayName("CT-I03: preco negativo lanca excecao")
    void precoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> new Item("X", -1.0, 1, 1));
    }

    @Test
    @DisplayName("CT-I04: quantidade zero lanca excecao")
    void quantidadeZero() {
        assertThrows(IllegalArgumentException.class, () -> new Item("X", 1.0, 0, 1));
    }
}
