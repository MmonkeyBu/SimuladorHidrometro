package simuladorhidrometro;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorController {

    private final GerenciadorView view;
    private final List<HidrometroController> simuladoresAtivos;
    private final int maxSimuladores = 5;
    private int proximoId = 1;

    public GerenciadorController() {
        this.view = new GerenciadorView();
        this.simuladoresAtivos = new ArrayList<>();
    }

    public void iniciar() {
        view.getBotaoLancarSimulador().addActionListener(e -> lancarNovaSimulacao());
        atualizarContador();
        view.exibir();
    }

    private void lancarNovaSimulacao() {
        if (simuladoresAtivos.size() < maxSimuladores) {
            // Cada nova simulação é uma nova instância do trio MVC
            Hidrometro modelo = new Hidrometro();
            ConfiguracaoView viewConfig = new ConfiguracaoView(proximoId);
            HidrometroView viewPrincipal = new HidrometroView(proximoId);

            // O controller da simulação é criado e gerenciado por este controller principal
            HidrometroController controllerSimulador = new HidrometroController(modelo, viewConfig, viewPrincipal);

            // Adiciona um listener para ser notificado quando a janela da simulação fechar
            viewPrincipal.getJanela().addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    notificarSimuladorFechado(controllerSimulador);
                }
            });

            simuladoresAtivos.add(controllerSimulador);
            controllerSimulador.iniciar(); // Inicia a lógica interna do simulador individual
            viewConfig.exibir();

            proximoId++;
            atualizarContador();
        } else {
            view.mostrarAlertaMaximoAtingido();
        }
    }

    // Método chamado quando uma janela de simulação é fechada
    public void notificarSimuladorFechado(HidrometroController controller) {
        simuladoresAtivos.remove(controller);
        atualizarContador();
    }

    private void atualizarContador() {
        view.atualizarContador(simuladoresAtivos.size(), maxSimuladores);
        view.getBotaoLancarSimulador().setEnabled(simuladoresAtivos.size() < maxSimuladores);
    }
}