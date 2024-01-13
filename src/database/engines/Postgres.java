package database.engines;

import models.Producto;

public class Postgres implements IDataBase{
    private static Postgres connection;
    private Postgres(){}

    public static Postgres getInstance(){
        if(connection == null){
            connection = new Postgres();
        }
        return connection;
    }

    @Override
    public String getName(){
        return "PostgreSQL";
    }

    @Override
    public Producto [] getProductos(){
        Producto productos [] = {};

        return productos;
    }
}
