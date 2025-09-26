package simuladorhidrometro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GerenciadorView {

    private JFrame janela;
    private JButton botaoLancarSimulador;
    private JLabel labelContador;

    public GerenciadorView() {
        janela = new JFrame("Gerenciador de Simulações de Hidrômetro");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(400, 200);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(new EmptyBorder(20, 20, 20, 20));

        botaoLancarSimulador = new JButton("Inicializar novo Simulador");
        botaoLancarSimulador.setFont(new Font("Arial", Font.BOLD, 14));

        labelContador = new JLabel();
        labelContador.setFont(new Font("Arial", Font.PLAIN, 16));
        labelContador.setHorizontalAlignment(SwingConstants.CENTER);

        painel.add(labelContador, BorderLayout.CENTER);
        painel.add(botaoLancarSimulador, BorderLayout.SOUTH);

        janela.add(painel);
    }

    public void exibir() {
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    public void atualizarContador(int ativos, int maximo) {
        labelContador.setText("Simuladores ativos: " + ativos + " de " + maximo);
    }

    public void mostrarAlertaMaximoAtingido() {
        JOptionPane.showMessageDialog(janela, "O número máximo de 5 simuladores foi atingido.", "Limite Excedido", JOptionPane.WARNING_MESSAGE);
    }

    public JButton getBotaoLancarSimulador() {
        return botaoLancarSimulador;
    }
}