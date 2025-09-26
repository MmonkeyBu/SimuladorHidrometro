package simuladorhidrometro;

public class Main {
    public static void main(String[] args) {
        // A Main agora apenas inicializa o controller principal que gerencia as simulações
        javax.swing.SwingUtilities.invokeLater(() -> {
            GerenciadorController gerenciador = new GerenciadorController();
            gerenciador.iniciar();
        });
    }
}