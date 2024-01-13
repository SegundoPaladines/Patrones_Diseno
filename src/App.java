import java.util.Scanner;

import database.engines.IDataBase;
import database.factories.DBFactory;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Qué motor de base de datos");
        String sdb = sc.nextLine();
        sc.close();
        IDataBase db = DBFactory.DataBaseFactory(sdb);
        System.out.println("El motor es: "+db.getName());
    }
}
