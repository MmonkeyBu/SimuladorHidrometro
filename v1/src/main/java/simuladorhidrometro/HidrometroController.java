package simuladorhidrometro;
<<<<<<< HEAD
=======

>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10
import javax.swing.Timer;

public class HidrometroController {
    private Hidrometro modelo;
    private ConfiguracaoView viewConfig;
    private HidrometroView viewPrincipal;
    private Timer timerDaSimulacao;

     public HidrometroController(Hidrometro modelo, ConfiguracaoView viewConfig, HidrometroView viewPrincipal) {
        this.modelo = modelo;
        this.viewConfig = viewConfig;
        this.viewPrincipal = viewPrincipal;
    }


    public void iniciar() {
<<<<<<< HEAD

=======
>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10
        viewConfig.getBotaoIniciar().addActionListener(e -> iniciarSimulacao());
        viewPrincipal.getBotaoAumentar().addActionListener(e -> ajustarVazao(0.1));
        viewPrincipal.getBotaoDiminuir().addActionListener(e -> ajustarVazao(-0.1));
        viewPrincipal.getBotaoResetar().addActionListener(e -> resetarConsumo());
    }

    private void iniciarSimulacao() {

        double vazaoInicial = viewConfig.getVazaoInicial();
<<<<<<< HEAD
=======

>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10
        modelo.setVazao(vazaoInicial);
        atualizarViewPrincipal();
        viewConfig.fechar();
        viewPrincipal.exibir();
<<<<<<< HEAD
        iniciarTimer();
    }

=======

        iniciarTimer();
    }
    
>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10
    private void ajustarVazao(double delta) {
        double vazaoAtual = modelo.getVazao();
        modelo.setVazao(vazaoAtual + delta);
    }
<<<<<<< HEAD
    
=======
>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10

    private void resetarConsumo() {
        modelo.resetar();
        atualizarViewPrincipal();
    }
    
<<<<<<< HEAD

    private void iniciarTimer() {
         
        timerDaSimulacao = new Timer(1000, e -> passoDaSimulacao());
        timerDaSimulacao.start(); 
    }
              
    private void passoDaSimulacao() {

        modelo.atualizarConsumo();

        atualizarViewPrincipal();
    }
    
    private void atualizarViewPrincipal() {

        double consumo = modelo.getConsumo();

        viewPrincipal.setConsumo(consumo);

=======

    private void iniciarTimer() {
        timerDaSimulacao = new Timer(1000, e -> passoDaSimulacao());
        timerDaSimulacao.start(); // Inicia o timer
    }

    private void passoDaSimulacao() {
        modelo.atualizarConsumo();
        atualizarViewPrincipal();
    }
           
    private void atualizarViewPrincipal() {
        double consumo = modelo.getConsumo();
        viewPrincipal.setConsumo(consumo);
>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10
        viewPrincipal.atualizarDisplay();
    } 
}
