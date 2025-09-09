package simuladorhidrometro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HidrometroView {
    private JFrame janela;
    private Image imagemFundo;
    private JButton botaoAumentar;
    private JButton botaoDiminuir;
    private JButton botaoResetar;
    private PainelHidrometro painelPrincipal;

    private double consumoAtual = 0.0;

    public HidrometroView() {
        carregarImagemDeFundo();
        inicializarComponentes();
    }

    private void carregarImagemDeFundo() {
        try {
            URL caminhoDaImagem = getClass().getResource("/hidrometrov1.png");
            if (caminhoDaImagem != null) {
                this.imagemFundo = new ImageIcon(caminhoDaImagem).getImage();
            } else {
                System.err.println("Erro: Não foi possível encontrar a imagem 'hidrometro_fundo.png' na pasta resources.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarComponentes() {
        janela = new JFrame("Simulador de Hidrómetro");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        painelPrincipal = new PainelHidrometro();

        botaoAumentar = new JButton("Aumentar Vazão (+0.1 L/s)");
        botaoDiminuir = new JButton("Diminuir Vazão (-0.1 L/s)");
        botaoResetar = new JButton("Resetar Consumo");
        JPanel painelDeBotoes = new JPanel();
        painelDeBotoes.add(botaoDiminuir);
        painelDeBotoes.add(botaoResetar);
        painelDeBotoes.add(botaoAumentar);

        janela.add(painelPrincipal, BorderLayout.CENTER);
        janela.add(painelDeBotoes, BorderLayout.SOUTH);

        janela.pack();
    }

    public void exibir() {
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

    public void atualizarDisplay() {
        painelPrincipal.repaint();
    }
    

    public void setConsumo(double novoConsumo) {
        this.consumoAtual = novoConsumo;
    }

    public JButton getBotaoAumentar() { return botaoAumentar; }
    public JButton getBotaoDiminuir() { return botaoDiminuir; }
    public JButton getBotaoResetar() { return botaoResetar; }

    private class PainelHidrometro extends JPanel {
        public PainelHidrometro() {
            this.setPreferredSize(new java.awt.Dimension(500, 350));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagemFundo != null) {
                g.drawImage(imagemFundo, 0, 0, this.getWidth(), this.getHeight(), this);
            }

            g.setFont(new Font("Monospaced", Font.BOLD, 20));
            g.setColor(Color.BLACK);

            String textoConsumo = String.format("%08.2f", consumoAtual);

            g.drawString(textoConsumo, 215, 155);
        }
    }
}