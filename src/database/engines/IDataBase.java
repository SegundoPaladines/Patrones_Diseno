package database.engines;

import java.util.ArrayList;

import models.Producto;

public interface IDataBase {
    public String getName();
    public ArrayList<Producto> getProductos();
    public String addProducto(Producto producto);
    public String deleteProducto(int pk);
    public Producto getProducto(int pk);
    public String editProducto(int pk, String nombre, int cantidad, float valorUnitario);
    public void  disconnect();
}
