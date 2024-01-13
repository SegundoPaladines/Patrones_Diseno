package database.engines;

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
     public Producto [] getProductos(){
        Producto productos [] = {};

        return productos;
     }
}
