package br.etec.loja;

/** Pequena demonstracao de uso (nao entra na analise principal de cobertura do seminario). */
public class Main {

    public static void main(String[] args) {
        Carrinho carrinho = new Carrinho();
        carrinho.adicionar(new Item("Teclado", 150.0, 2, 10));
        carrinho.adicionar(new Item("Mouse", 80.0, 1, 5));
        carrinho.adicionar(new Item("Monitor", 900.0, 1, 0)); // sem estoque

        PedidoService.Resumo r = new PedidoService().fechar(carrinho, TipoCliente.VIP);

        System.out.printf("Subtotal : R$ %.2f%n", r.subtotal());
        System.out.printf("Desconto : R$ %.2f%n", r.desconto());
        System.out.printf("Total    : R$ %.2f%n", r.total());
        System.out.printf("Parcelas : ate %dx%n", r.parcelas());
    }
}
