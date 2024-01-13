package database.engines;

import java.util.ArrayList;

import models.Producto;

public interface IDataBase {
    public String getName();
    public ArrayList<Producto> getProductos();
    public String addProducto(Producto producto);
}
