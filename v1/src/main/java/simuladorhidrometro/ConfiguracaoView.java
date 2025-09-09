package simuladorhidrometro;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField; 

public class ConfiguracaoView {
    private JFrame janela;
    private JTextField campoVazao;
    private JButton botaoIniciar;
    
    public ConfiguracaoView(){
        janela = new JFrame("Configuração do Hidrômetro");
        janela.setSize(300, 150);
        janela.setLayout(new FlowLayout());
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel rotuloVazao = new JLabel("Vazão (m³/h):");
        campoVazao = new JTextField(10);
        
        botaoIniciar = new JButton("Iniciar Simulação");
        
        janela.add(rotuloVazao);
        janela.add(campoVazao);
        janela.add(botaoIniciar);
        
        janela.setVisible(true);
    }

    public void exibir(){
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.pack();
    }

    public double getVazaoInicial(){
        try{
            return Double.parseDouble(campoVazao.getText());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida para vazão. Usando valor padrão 0.0.");
            return 0.0;
        }
    }

    public JButton getBotaoIniciar(){
        return botaoIniciar;
    }

    public void fechar(){
        janela.dispose();
    }
}

