package br.etec.loja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Testes de integracao simples: subtotal -> desconto -> total -> parcelas. */
class PedidoServiceTest {

    private final PedidoService service = new PedidoService();

    @Test
    @DisplayName("CT-O01: carrinho nulo lanca excecao")
    void carrinhoNulo() {
        assertThrows(IllegalStateException.class, () -> service.fechar(null, TipoCliente.VIP));
    }

    @Test
    @DisplayName("CT-O02: carrinho vazio lanca excecao")
    void carrinhoVazio() {
        assertThrows(IllegalStateException.class,
                () -> service.fechar(new Carrinho(), TipoCliente.VIP));
    }

    @Test
    @DisplayName("CT-O03: todos os itens sem estoque lanca excecao")
    void semEstoque() {
        Carrinho c = new Carrinho();
        c.adicionar(new Item("Monitor", 900.0, 1, 0));
        assertThrows(IllegalStateException.class, () -> service.fechar(c, TipoCliente.VIP));
    }

    @Test
    @DisplayName("CT-O04: VIP com subtotal 380 -> desconto 38, total 342, 6 parcelas")
    void vipCompraBaixa() {
        Carrinho c = new Carrinho();
        c.adicionar(new Item("Teclado", 150.0, 2, 10));
        c.adicionar(new Item("Mouse", 80.0, 1, 5));
        c.adicionar(new Item("Monitor", 900.0, 1, 0)); // ignorado

        PedidoService.Resumo r = service.fechar(c, TipoCliente.VIP);

        assertEquals(380.0, r.subtotal(), 0.001);
        assertEquals(38.0, r.desconto(), 0.001);
        assertEquals(342.0, r.total(), 0.001);
        assertEquals(6, r.parcelas());
    }

    @Test
    @DisplayName("CT-O05: VIP com subtotal 1000 -> desconto 200, total 800, 12 parcelas")
    void vipCompraAlta() {
        Carrinho c = new Carrinho();
        c.adicionar(new Item("Notebook", 1000.0, 1, 3));

        PedidoService.Resumo r = service.fechar(c, TipoCliente.VIP);

        assertEquals(1000.0, r.subtotal(), 0.001);
        assertEquals(200.0, r.desconto(), 0.001);
        assertEquals(800.0, r.total(), 0.001);
        assertEquals(12, r.parcelas());
    }
}
