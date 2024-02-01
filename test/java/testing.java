import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class testing {

    @Test
    @DisplayName("Testing generarNumero() en la clase Juego")
    public void testGenerarNumero() {
        Juego juego = new Juego();
        int numeroGenerado = juego.generarNumero();
        assertTrue(numeroGenerado >= 1 && numeroGenerado <= 100, "El número generado debe estar entre 1 y 100");
    }


}
