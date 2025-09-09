package simuladorhidrometro;

// Importamos o Timer do Swing, que é seguro para atualizar interfaces gráficas.
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
        // 1. Configura a ação do botão "Iniciar" da tela de configuração.
        viewConfig.getBotaoIniciar().addActionListener(e -> iniciarSimulacao());

        // 2. Configura as ações dos botões da tela principal.
        viewPrincipal.getBotaoAumentar().addActionListener(e -> ajustarVazao(0.1));
        viewPrincipal.getBotaoDiminuir().addActionListener(e -> ajustarVazao(-0.1));
        viewPrincipal.getBotaoResetar().addActionListener(e -> resetarConsumo());
    }

    private void iniciarSimulacao() {

        double vazaoInicial = viewConfig.getVazaoInicial();
        // Manda o Modelo definir essa vazão
        modelo.setVazao(vazaoInicial);

        // Atualiza a tela principal com os valores iniciais (vazão e consumo)
        atualizarViewPrincipal();

        // Esconde a tela de configuração e exibe a tela principal
        viewConfig.fechar();
        viewPrincipal.exibir();

        // Inicia o "relógio" da simulação
        iniciarTimer();
    }
    
    /**
     * Ação para os botões de aumentar e diminuir a vazão.
     * @param delta A quantidade a ser somada à vazão atual (pode ser positiva ou negativa).
     */
    private void ajustarVazao(double delta) {
        double vazaoAtual = modelo.getVazao();
        modelo.setVazao(vazaoAtual + delta);
    }
    
    /**
     * Ação para o botão de resetar o consumo.
     */
    private void resetarConsumo() {
        modelo.resetar();
        // Após resetar, precisamos de atualizar a view imediatamente.
        atualizarViewPrincipal();
    }
    
    /**
     * Inicia o Timer que vai rodar a simulação a cada segundo.
     */
    private void iniciarTimer() {
        // Cria um Timer que vai disparar um evento a cada 1000 milissegundos (1 segundo).
        // A cada disparo, ele executa a ação de atualizar a simulação.
        timerDaSimulacao = new Timer(1000, e -> passoDaSimulacao());
        timerDaSimulacao.start(); // Inicia o timer
    }

    /**
     * Este método é o coração da simulação. É executado a cada segundo pelo Timer.
     */
    private void passoDaSimulacao() {
        // 1. Manda o Modelo atualizar o seu consumo com base na vazão atual.
        modelo.atualizarConsumo();
        
        // 2. Atualiza a View com os novos dados.
        atualizarViewPrincipal();
    }
    
    /**
     * Pega os dados mais recentes do Modelo e "empurra" para a View.
     */
    private void atualizarViewPrincipal() {
        // Pega o consumo atualizado do modelo
        double consumo = modelo.getConsumo();
        // Manda a View definir esse novo valor
        viewPrincipal.setConsumo(consumo);
        // Manda a View se redesenhar para mostrar o novo valor
        viewPrincipal.atualizarDisplay();
    } 
}
