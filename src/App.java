import database.engines.IDataBase;
import database.factories.DBFactory;
import views.Ventana;

public class App {
    public static void main(String[] args) throws Exception {
        IDataBase db = DBFactory.DataBaseFactory("");
        System.out.println("El motor es: "+db.getName());
        System.out.println("Productos: "+db.getProductos().length);

        Ventana ventana = new Ventana();
        ventana.AbrirVentana();
    }
}
