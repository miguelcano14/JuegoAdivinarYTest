import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class JuegoVista implements JuegoInterfaz {

    private JPanel panelPrincipal;
    private JTextField jugador;
    private JLabel resultado;
    private List<JLabel> vidas;
    private JuegoControlador controlador;
    private int numeroAlAzar;

    public JuegoVista() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(7, 1));

        JLabel etiquetaInstrucciones = new JLabel("Intenta adivinar el número entre el 0 y el 100:");
        jugador = new JTextField(10);
        JButton botonAdivinar = new JButton("Adivinar");
        JButton botonReset = new JButton("Resetear Juego");
        resultado = new JLabel();

        JPanel panelVidas = new JPanel();
        vidas = new ArrayList<>();
        for (int i = 0; i < JuegoInterfaz.MAX_INTENTOS; i++) {
            JLabel corazon = new JLabel(new ImageIcon("recursos/corazon.png"));
            vidas.add(corazon);
            panelVidas.add(corazon);
        }

        botonAdivinar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.verificarAdivinanza(jugador.getText());
            }
        });

        botonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controlador.reiniciarJuego(); // Llama al método reiniciarJuego del controlador
            }
        });

        panelPrincipal.add(etiquetaInstrucciones);
        panelPrincipal.add(jugador);
        panelPrincipal.add(botonAdivinar);
        panelPrincipal.add(botonReset);
        panelPrincipal.add(panelVidas);
        panelPrincipal.add(resultado);
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    @Override
    public void setControlador(JuegoControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public void setResultado(String texto) {
        resultado.setText(texto);
    }

    @Override
    public void actualizarVidas(int intentosHechos) {
        if (intentosHechos <= JuegoInterfaz.MAX_INTENTOS) {
            vidas.get(JuegoInterfaz.MAX_INTENTOS - intentosHechos).setVisible(false);
        }
    }

    @Override
    public void reiniciarJuego(int nuevoNumero) {
        jugador.setEnabled(true);
        resultado.setText("");
        numeroAlAzar = nuevoNumero;
        for (JLabel corazon : vidas) {
            corazon.setVisible(true);
            jugador.setText("");
        }
    }

    @Override
    public void mostrarGif(String rutaGif) {
        ImageIcon gifIcon = new ImageIcon(rutaGif);
        JLabel label = new JLabel(gifIcon);
        JOptionPane.showMessageDialog(null, label);

        controlador.reiniciarJuego(); // Reinicia el juego después de mostrar el gif
    }

    @Override
    public int getNumeroAlAzar() {
        return numeroAlAzar;
    }
}
