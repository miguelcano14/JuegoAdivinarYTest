import java.util.Random;

public class JuegoControlador {

    private final JuegoInterfaz vista;
    private int intentosHechos;
    private Random random;

    public JuegoControlador(JuegoInterfaz vista) {
        this.vista = vista;
        this.intentosHechos = 0;
        this.random = new Random();
        vista.setControlador(this);
    }

    // Método para generar un nuevo número aleatorio
    private int generarNuevoNumero() {
        return random.nextInt(100) + 1; // Genera un número entre 1 y 100
    }

    public void verificarAdivinanza(String texto) {
        if (intentosHechos < JuegoInterfaz.MAX_INTENTOS) {
            try {
                int intentoUsuario = Integer.parseInt(texto);

                if (intentoUsuario == vista.getNumeroAlAzar()) {
                    vista.setResultado("¡Has Ganado!");
                    vista.mostrarGif("recursos/Victoria.gif");
                    reiniciarJuego();
                    return;
                } else if (intentoUsuario < vista.getNumeroAlAzar()) {
                    vista.setResultado("Te has quedado corto.");
                } else {
                    vista.setResultado("Te has pasado.");
                }
                intentosHechos++;
                vista.actualizarVidas(intentosHechos);
            } catch (NumberFormatException ex) {
                vista.setResultado("¿Sabes leer?, Ingresa un NÚMERO.");
            }
        } else {
            vista.setResultado("¡Te has muerto jajajaja!, El número correcto era: " + vista.getNumeroAlAzar());
            vista.mostrarGif("recursos/derrota.gif");
            reiniciarJuego();
        }
    }

    // Método para reiniciar el juego
    public void reiniciarJuego() {
        intentosHechos = 0;
        int nuevoNumero = generarNuevoNumero(); // Genera un nuevo número aleatorio
        vista.reiniciarJuego(nuevoNumero); // Pasa el nuevo número a la vista para reiniciar el juego
    }
}
