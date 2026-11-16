package br.etec.loja.parcial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.etec.loja.ParcelamentoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SUITE PARCIAL (de proposito!): so testa o caso "feliz" de valor alto.
 * Nao exercita: zero iteracoes, valores intermediarios nem as excecoes.
 */
class ParcelamentoServiceParcialTest {

    @Test
    @DisplayName("Parcial: valor alto chega no limite de 12 parcelas")
    void valorAlto() {
        assertEquals(12, new ParcelamentoService().calcularMaximoParcelas(1000.0));
    }
}
