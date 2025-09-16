package simuladorhidrometro;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
                System.err.println("Erro: Nao foi possível encontrar a imagem 'hidrometrov1.png' na pasta resources.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void inicializarComponentes() {
        janela = new JFrame("Simulador de Hidrometro");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        painelPrincipal = new PainelHidrometro();

        botaoAumentar = new JButton("Aumentar Vazao (+0.1 L/s)");
        botaoDiminuir = new JButton("Diminuir Vazao (-0.1 L/s)");
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
            this.setPreferredSize(new Dimension(600, 450)); 
        }

        private void desenharPonteiro(Graphics2D g2d, double valor, int centroX, int centroY, int raio) {
            double angulo = (valor / 10.0) * (2 * Math.PI) - (Math.PI / 2);
            int pontaX = centroX + (int) (raio * Math.cos(angulo));
            int pontaY = centroY + (int) (raio * Math.sin(angulo));
            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.RED);
            g2d.drawLine(centroX, centroY, pontaX, pontaY);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            if (imagemFundo != null) {
                int painelLargura = this.getWidth();
                int painelAltura = this.getHeight();
                
                int imgOrigLargura = imagemFundo.getWidth(this);
                int imgOrigAltura = imagemFundo.getHeight(this);
                
                if (imgOrigLargura <= 0 || imgOrigAltura <= 0) {
                    System.err.println("Erro: Dimensoes da imagem de fundo sao invalidas (0 ou menos).");
                    return;
                }

                double fatorEscala = Math.min((double) painelLargura / imgOrigLargura, (double) painelAltura / imgOrigAltura);
                
                int imgEscaladaLargura = (int) (imgOrigLargura * fatorEscala);
                int imgEscaladaAltura = (int) (imgOrigAltura * fatorEscala);
                int imgX = (painelLargura - imgEscaladaLargura) / 2;
                int imgY = (painelAltura - imgEscaladaAltura) / 2;

                g2d.drawImage(imagemFundo, imgX, imgY, imgEscaladaLargura, imgEscaladaAltura, this);

                // Desenhar Texto Digital
                g2d.setFont(new Font("Monospaced", Font.BOLD, (int)(45 * fatorEscala)));
                String numeroFormatado = String.format("%08.2f", consumoAtual);
                
                String parteInteira;
                String parteDecimal;

                int pontoIndex = numeroFormatado.indexOf('.');
                if (pontoIndex == -1) {
                    pontoIndex = numeroFormatado.indexOf(',');
                }

                if (pontoIndex != -1) {
                    parteInteira = numeroFormatado.substring(0, pontoIndex);
                    parteDecimal = numeroFormatado.substring(pontoIndex);
                } else {
                    parteInteira = numeroFormatado;
                    parteDecimal = "";
                }
                
                FontMetrics fm = g2d.getFontMetrics();
                int larguraParteInteira = fm.stringWidth(parteInteira);
                int larguraNumeroFormatado = fm.stringWidth(numeroFormatado);
                String unidadeMedida = " m³";
                int larguraUnidade = fm.stringWidth(unidadeMedida);

                int offsetTextoXOriginal = 640; 
                int offsetTextoYOriginal = 380; 

                int xTextoInicio = imgX + (int)(offsetTextoXOriginal * fatorEscala) - ((larguraNumeroFormatado + larguraUnidade) / 2);
                int yTextoBase = imgY + (int)(offsetTextoYOriginal * fatorEscala);
                
                g2d.setColor(Color.BLACK);
                g2d.drawString(parteInteira, xTextoInicio, yTextoBase);
                g2d.setColor(Color.RED);
                g2d.drawString(parteDecimal, xTextoInicio + larguraParteInteira, yTextoBase);
                g2d.setColor(Color.BLACK);
                g2d.drawString(unidadeMedida, xTextoInicio + larguraNumeroFormatado, yTextoBase);

                double valorCentenasLitros = (consumoAtual * 10) % 10;     
                double valorDezenasLitros = (consumoAtual * 100) % 10;    

                int centroX_01_orig = 595; 
                int centroY_01_orig = 583; 
                int raio_01_orig = 30;     

                int centroX_001_orig = 725; 
                int centroY_001_orig = 515; 
                int raio_001_orig = 30;     

                desenharPonteiro(g2d, valorCentenasLitros, 
                                 imgX + (int)(centroX_01_orig * fatorEscala), 
                                 imgY + (int)(centroY_01_orig * fatorEscala), 
                                 (int)(raio_01_orig * fatorEscala));

                desenharPonteiro(g2d, valorDezenasLitros, 
                                 imgX + (int)(centroX_001_orig * fatorEscala), 
                                 imgY + (int)(centroY_001_orig * fatorEscala), 
                                 (int)(raio_001_orig * fatorEscala));
            }
        }
    }
}