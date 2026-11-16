package br.etec.loja.parcial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.etec.loja.DescontoService;
import br.etec.loja.TipoCliente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * SUITE PARCIAL (de proposito!) - usada na demonstracao do seminario.
 *
 * Todos os testes passam, mas varios caminhos NUNCA sao exercitados:
 *   - VIP com compra < 500
 *   - REGULAR entre 200 e 499,99 e abaixo de 200
 *   - validacoes (tipo nulo / valor negativo)
 * O relatorio do JaCoCo vai mostrar esses ramos em VERMELHO/AMARELO.
 */
class DescontoServiceParcialTest {

    private final DescontoService service = new DescontoService();

    @Test
    @DisplayName("Parcial: VIP com compra alta")
    void vipAlta() {
        assertEquals(0.20, service.calcularPercentual(TipoCliente.VIP, 600.0), 0.0001);
    }

    @Test
    @DisplayName("Parcial: REGULAR com compra alta")
    void regularAlta() {
        assertEquals(0.10, service.calcularPercentual(TipoCliente.REGULAR, 800.0), 0.0001);
    }

    @Test
    @DisplayName("Parcial: VISITANTE")
    void visitante() {
        assertEquals(0.0, service.calcularPercentual(TipoCliente.VISITANTE, 1000.0), 0.0001);
    }
}
