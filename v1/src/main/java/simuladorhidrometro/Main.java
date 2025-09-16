// No seu ficheiro Main.java

package simuladorhidrometro;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                Hidrometro modelo = new Hidrometro();
                ConfiguracaoView viewConfig = new ConfiguracaoView();
                HidrometroView viewPrincipal = new HidrometroView();

                HidrometroController controller = new HidrometroController(modelo, viewConfig, viewPrincipal);
                

                controller.iniciar();

                viewConfig.exibir();
            }
        });
    }
}