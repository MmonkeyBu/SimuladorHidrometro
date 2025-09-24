// No seu ficheiro Main.java

package simuladorhidrometro;

public class Main {
    public static void main(String[] args) {
<<<<<<< HEAD
=======

>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                Hidrometro modelo = new Hidrometro();
                ConfiguracaoView viewConfig = new ConfiguracaoView();
                HidrometroView viewPrincipal = new HidrometroView();

                HidrometroController controller = new HidrometroController(modelo, viewConfig, viewPrincipal);
<<<<<<< HEAD
                

                controller.iniciar();

=======

                controller.iniciar();
>>>>>>> cad04a9ede1b391e2f2c4559ed301a861bfe3c10
                viewConfig.exibir();
            }
        });
    }
}
