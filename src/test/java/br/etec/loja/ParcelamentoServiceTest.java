package br.etec.loja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Testes de LACO: zero iteracoes, uma iteracao, varias iteracoes e limite maximo.
 */
class ParcelamentoServiceTest {

    private final ParcelamentoService service = new ParcelamentoService();

    @Test
    @DisplayName("CT-P01: laco com ZERO iteracoes (valor baixo) -> 1 parcela")
    void zeroIteracoes() {
        assertEquals(1, service.calcularMaximoParcelas(40.0));
    }

    @Test
    @DisplayName("CT-P02: laco com ZERO iteracoes na fronteira (99,99) -> 1 parcela")
    void zeroIteracoesFronteira() {
        assertEquals(1, service.calcularMaximoParcelas(99.99));
    }

    @Test
    @DisplayName("CT-P03: laco com UMA iteracao (100) -> 2 parcelas")
    void umaIteracao() {
        assertEquals(2, service.calcularMaximoParcelas(100.0));
    }

    @Test
    @DisplayName("CT-P04: laco com VARIAS iteracoes (150) -> 3 parcelas")
    void variasIteracoes() {
        assertEquals(3, service.calcularMaximoParcelas(150.0));
    }

    @Test
    @DisplayName("CT-P05: laco para pelo LIMITE de 12 parcelas (1000)")
    void limiteMaximo() {
        assertEquals(12, service.calcularMaximoParcelas(1000.0));
    }

    @Test
    @DisplayName("CT-P06: fronteira exata de 12 parcelas (600 / 12 = 50)")
    void fronteiraDoze() {
        assertEquals(12, service.calcularMaximoParcelas(600.0));
    }

    @Test
    @DisplayName("CT-P07: valor zero lanca excecao")
    void valorZero() {
        assertThrows(IllegalArgumentException.class, () -> service.calcularMaximoParcelas(0));
    }

    @Test
    @DisplayName("CT-P08: valor negativo lanca excecao")
    void valorNegativo() {
        assertThrows(IllegalArgumentException.class, () -> service.calcularMaximoParcelas(-10));
    }
}
