import views.Ventana;
import controllers.ProductController;

public class App {
    public static void main(String[] args) throws Exception {
        Ventana ventana = new Ventana();
        ventana.AbrirVentana();
        ProductController.getInstance().clearConections();
    }
}
