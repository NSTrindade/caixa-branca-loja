package br.etec.loja;

/**
 * Regras de desconto por tipo de cliente e valor da compra.
 *
 * <pre>
 *  VIP      : compra >= 500 -> 20%   | compra < 500 -> 10%
 *  REGULAR  : compra >= 500 -> 10%   | 200 <= compra < 500 -> 5%  | compra < 200 -> 0%
 *  VISITANTE: sem desconto
 * </pre>
 *
 * Este metodo possui varias DECISOES aninhadas (if / else if / else),
 * por isso e o melhor exemplo para cobertura de decisoes (branches).
 */
public class DescontoService {

    public static final double LIMITE_ALTO = 500.0;
    public static final double LIMITE_MEDIO = 200.0;

    /** Retorna o PERCENTUAL de desconto (0.0 a 1.0). */
    public double calcularPercentual(TipoCliente tipo, double valorCompra) {
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo de cliente obrigatorio");
        }
        if (valorCompra < 0) {
            throw new IllegalArgumentException("Valor da compra nao pode ser negativo");
        }

        double percentual;
        if (tipo == TipoCliente.VIP) {
            if (valorCompra >= LIMITE_ALTO) {
                percentual = 0.20;
            } else {
                percentual = 0.10;
            }
        } else if (tipo == TipoCliente.REGULAR) {
            if (valorCompra >= LIMITE_ALTO) {
                percentual = 0.10;
            } else if (valorCompra >= LIMITE_MEDIO) {
                percentual = 0.05;
            } else {
                percentual = 0.0;
            }
        } else {
            percentual = 0.0; // VISITANTE
        }
        return percentual;
    }

    /** Retorna o VALOR do desconto em reais, arredondado para centavos. */
    public double calcularDesconto(TipoCliente tipo, double valorCompra) {
        double valor = valorCompra * calcularPercentual(tipo, valorCompra);
        return Math.round(valor * 100.0) / 100.0;
    }
}
