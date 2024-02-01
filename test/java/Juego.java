import java.util.Random;

public class Juego {
    private Random random;

    public Juego() {
        random = new Random();
    }

    public int generarNumero() {
        return random.nextInt(100) + 1;
    }
}