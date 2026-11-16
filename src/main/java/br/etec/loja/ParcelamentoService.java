package br.etec.loja;

/**
 * Calcula o numero maximo de parcelas de uma compra.
 * Regra: cada parcela deve ter no minimo R$ 50,00 e o limite e de 12 parcelas.
 *
 * Contem um LACO while com duas condicoes de parada - bom exemplo para
 * testar 0 iteracoes, 1 iteracao, varias iteracoes e o limite maximo.
 */
public class ParcelamentoService {

    public static final double PARCELA_MINIMA = 50.0;
    public static final int MAXIMO_PARCELAS = 12;

    public int calcularMaximoParcelas(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que zero");
        }

        int parcelas = 1;
        while (parcelas < MAXIMO_PARCELAS && valor / (parcelas + 1) >= PARCELA_MINIMA) {
            parcelas++;
        }
        return parcelas;
    }
}
