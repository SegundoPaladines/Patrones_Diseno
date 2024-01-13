package database.engines;

import models.Producto;

public interface IDataBase {
    public String getName();
    public Producto [] getProductos();
}
