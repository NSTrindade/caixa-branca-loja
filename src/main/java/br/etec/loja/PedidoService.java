package br.etec.loja;

/**
 * Orquestra o fechamento de um pedido: subtotal -> desconto -> total -> parcelas.
 */
public class PedidoService {

    private final DescontoService descontoService = new DescontoService();
    private final ParcelamentoService parcelamentoService = new ParcelamentoService();

    /** Resultado do fechamento do pedido. */
    public record Resumo(double subtotal, double desconto, double total, int parcelas) { }

    public Resumo fechar(Carrinho carrinho, TipoCliente tipo) {
        if (carrinho == null || carrinho.estaVazio()) {
            throw new IllegalStateException("Carrinho vazio");
        }

        double subtotal = carrinho.calcularSubtotal();
        if (subtotal == 0) {
            throw new IllegalStateException("Nenhum item disponivel em estoque");
        }

        double desconto = descontoService.calcularDesconto(tipo, subtotal);
        double total = subtotal - desconto;
        int parcelas = parcelamentoService.calcularMaximoParcelas(total);
        return new Resumo(subtotal, desconto, total, parcelas);
    }
}
