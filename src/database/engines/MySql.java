package database.engines;
import java.util.ArrayList;

import models.Producto;

public class MySql implements IDataBase{
    private static MySql connection;
    private MySql(){}

    public static MySql getInstance(){       
        
        if(connection==null){
            connection=new MySql();
        }
        return connection;
    }

    @Override
    public String getName(){
        return "MySql";
    }

    @Override
    public ArrayList<Producto> getProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();

        return productos;
    }
    @Override
    public String addProducto(Producto producto){
        try {

            return "success";
            
        } catch (Exception e) {
            return "error";
        }
    }
}
