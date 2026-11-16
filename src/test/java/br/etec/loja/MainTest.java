package br.etec.loja;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    @DisplayName("CT-M01: demonstracao imprime resumo do pedido")
    void imprimeResumo() {
        PrintStream original = System.out;
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));
        try {
            Main.main(new String[0]);
        } finally {
            System.setOut(original);
        }
        String texto = saida.toString();
        assertTrue(texto.contains("Subtotal"));
        assertTrue(texto.contains("Parcelas"));
    }
}
