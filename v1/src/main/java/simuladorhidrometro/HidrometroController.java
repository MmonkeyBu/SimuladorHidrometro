package simuladorhidrometro;

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

        // Garante que o timer seja parado quando a janela principal da simulação fechar
        viewPrincipal.getJanela().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                if (timerDaSimulacao != null) {
                    timerDaSimulacao.stop();
                }
            }
        });
    }

    public void iniciar() {
        viewConfig.getBotaoIniciar().addActionListener(e -> iniciarSimulacao());
        viewPrincipal.getBotaoAumentar().addActionListener(e -> ajustarVazao(0.1));
        viewPrincipal.getBotaoDiminuir().addActionListener(e -> ajustarVazao(-0.1));
        viewPrincipal.getBotaoResetar().addActionListener(e -> resetarConsumo());
    }

    private void iniciarSimulacao() {
        double vazaoInicial = viewConfig.getVazaoInicial();
        modelo.setVazao(vazaoInicial);
        atualizarViewPrincipal();
        viewConfig.fechar();
        viewPrincipal.exibir();
        iniciarTimer();
    }

    private void ajustarVazao(double delta) {
        double vazaoAtual = modelo.getVazao();
        // Adiciona uma verificação para não permitir vazão negativa
        if (vazaoAtual + delta >= 0) {
            modelo.setVazao(vazaoAtual + delta);
        }
    }

    private void resetarConsumo() {
        modelo.resetar();
        atualizarViewPrincipal();
    }

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
        viewPrincipal.atualizarDisplay();
    }
}