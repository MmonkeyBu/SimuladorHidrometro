// No seu ficheiro Main.java

package simuladorhidrometro;

public class Main {
    public static void main(String[] args) {
        // Usamos o SwingUtilities.invokeLater para garantir que a interface gráfica
        // seja criada e manipulada na thread correta. É a prática mais segura em Swing.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // 1. CRIAÇÃO: Cria todas as peças do nosso sistema MVC.
                Hidrometro modelo = new Hidrometro();
                ConfiguracaoView viewConfig = new ConfiguracaoView();
                HidrometroView viewPrincipal = new HidrometroView();

                // 2. LIGAÇÃO: Cria o Controller, "apresentando" todas as peças a ele.
                HidrometroController controller = new HidrometroController(modelo, viewConfig, viewPrincipal);
                
                // 3. INICIAÇÃO: Manda o controller "ligar os fios" e começar a ouvir os botões.
                // É ESTE MÉTODO QUE FAZ OS BOTÕES FUNCIONAREM!
                controller.iniciar();

                // 4. PONTO DE PARTIDA: Exibe APENAS a primeira tela.
                // O controller se encarregará de fechar esta e abrir a próxima.
                viewConfig.exibir();
            }
        });
    }
}