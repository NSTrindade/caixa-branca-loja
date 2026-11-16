package br.etec.loja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Suite COMPLETA: cobre todas as decisoes (branches) de DescontoService.
 * Cada teste exercita UM caminho do codigo (nomeado como CT-D01, CT-D02...).
 */
class DescontoServiceTest {

    private final DescontoService service = new DescontoService();

    // ---------- Caminho VIP ----------
    @Test
    @DisplayName("CT-D01: VIP com compra >= 500 recebe 20%")
    void vipCompraAlta() {
        assertEquals(0.20, service.calcularPercentual(TipoCliente.VIP, 600.0), 0.0001);
    }

    @Test
    @DisplayName("CT-D02: VIP com compra < 500 recebe 10%")
    void vipCompraBaixa() {
        assertEquals(0.10, service.calcularPercentual(TipoCliente.VIP, 100.0), 0.0001);
    }

    // ---------- Caminho REGULAR ----------
    @Test
    @DisplayName("CT-D03: REGULAR com compra >= 500 recebe 10%")
    void regularCompraAlta() {
        assertEquals(0.10, service.calcularPercentual(TipoCliente.REGULAR, 800.0), 0.0001);
    }

    @Test
    @DisplayName("CT-D04: REGULAR com compra entre 200 e 499,99 recebe 5%")
    void regularCompraMedia() {
        assertEquals(0.05, service.calcularPercentual(TipoCliente.REGULAR, 300.0), 0.0001);
    }

    @Test
    @DisplayName("CT-D05: REGULAR com compra < 200 nao recebe desconto")
    void regularCompraBaixa() {
        assertEquals(0.0, service.calcularPercentual(TipoCliente.REGULAR, 50.0), 0.0001);
    }

    // ---------- Caminho VISITANTE ----------
    @Test
    @DisplayName("CT-D06: VISITANTE nunca recebe desconto")
    void visitante() {
        assertEquals(0.0, service.calcularPercentual(TipoCliente.VISITANTE, 1000.0), 0.0001);
    }

    // ---------- Valores-limite (fronteiras das decisoes) ----------
    @Test
    @DisplayName("CT-D07: fronteira 500 exato (VIP) -> 20%")
    void fronteira500Vip() {
        assertEquals(0.20, service.calcularPercentual(TipoCliente.VIP, 500.0), 0.0001);
    }

    @Test
    @DisplayName("CT-D08: fronteira 499,99 (VIP) -> 10%")
    void fronteira49999Vip() {
        assertEquals(0.10, service.calcularPercentual(TipoCliente.VIP, 499.99), 0.0001);
    }

    @Test
    @DisplayName("CT-D09: fronteira 200 exato (REGULAR) -> 5%")
    void fronteira200Regular() {
        assertEquals(0.05, service.calcularPercentual(TipoCliente.REGULAR, 200.0), 0.0001);
    }

    @Test
    @DisplayName("CT-D10: fronteira 199,99 (REGULAR) -> 0%")
    void fronteira19999Regular() {
        assertEquals(0.0, service.calcularPercentual(TipoCliente.REGULAR, 199.99), 0.0001);
    }

    // ---------- Validacoes ----------
    @Test
    @DisplayName("CT-D11: tipo de cliente nulo lanca excecao")
    void tipoNulo() {
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularPercentual(null, 100.0));
    }

    @Test
    @DisplayName("CT-D12: valor negativo lanca excecao")
    void valorNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> service.calcularPercentual(TipoCliente.VIP, -1.0));
    }

    // ---------- Valor em reais ----------
    @Test
    @DisplayName("CT-D13: desconto em reais VIP 1000 = 200,00")
    void descontoEmReais() {
        assertEquals(200.00, service.calcularDesconto(TipoCliente.VIP, 1000.0), 0.001);
    }

    @Test
    @DisplayName("CT-D14: desconto em reais e arredondado para centavos")
    void descontoArredondado() {
        // 199,99 * 10% = 19,999 -> 20,00
        assertEquals(20.00, service.calcularDesconto(TipoCliente.VIP, 199.99), 0.001);
    }
}
