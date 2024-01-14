package controllers;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import database.engines.IDataBase;
import database.factories.DBFactory;
import models.Producto;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductController {
    private static ProductController productController;
    private ArrayList<Producto> productos;

    private IDataBase postgres;
    private IDataBase mySql;

    private ProductController(){
        this.productos = new ArrayList<Producto>();
        this.postgres = DBFactory.DataBaseFactory("PostgreSQL");
        this.mySql = DBFactory.DataBaseFactory("MySql");
        getProductos();
    }

    public static ProductController getInstance (){
        if(productController == null){
            productController = new ProductController();
        }
        return productController;
    }

    private void getProductos() {
        productos = new ArrayList<Producto>();
        productos.addAll(postgres.getProductos());
        productos.addAll(mySql.getProductos());
    }

    public String agregarProducto(Producto producto){
        if(producto != null){
            if(producto.valor_unitario > 100000){
                String res = postgres.addProducto(producto);
                if(res == "success"){
                    this.getProductos();
                }
            }else{
                String res = mySql.addProducto(producto);
                if(res == "success"){
                    this.getProductos();
                }
            }
        }

        return "Agregado";
    }

    public String deleteProducto(int pk, String db){
        if(pk > 0){
            if(db == "MySql"){
                String res = mySql.deleteProducto(pk);
                if(res == "success"){
                    this.getProductos();
                }
            }
            if(db == "PostgreSQL"){
                String res = postgres.deleteProducto(pk);
                if(res == "success"){
                    this.getProductos();
                }
            }
        }

        return "Eliminado";
    }

    public DefaultTableModel getTablaProductos(){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        // crear el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("#");
        modeloTabla.addColumn("pk");
        modeloTabla.addColumn("DataBase");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Valor Unitario");

        int contador = 1;
        for (Producto producto : productos) {
            NumberFormat cop = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
            String res = cop.format(producto.valor_unitario);

            modeloTabla.addRow(new Object[]{contador, producto.pk, producto.bd , producto.nombre, producto.cantidad, res});    
            contador++;
        }

        return modeloTabla;
    }
    
    public String getTotalProductos(){
        int cantidad = 0;

        for (Producto producto : productos) {
            cantidad += producto.cantidad;
        }

        return "Productos totales: " + cantidad;
    }

    public String getValorTotalInventario(){
        double valorT = 0;

        for (Producto producto : productos) {
            valorT += producto.valor_unitario*producto.cantidad;
        }

        NumberFormat cop = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        String res = cop.format(valorT);

        return "Valor Total Stock: " + res;
    }

    public Producto getProducto(int pk, String database){
        if(database.equalsIgnoreCase("Mysql")){
            return mySql.getProducto(pk);
        }
        if(database.equalsIgnoreCase("PostgreSQL")){
            return postgres.getProducto(pk);
        }

        return null;
    }

    public String editarProducto(int pk, String database, String nombre, int cantidad, float valorUnitario) {
        String res = "DB error";
        if(database.equalsIgnoreCase("Mysql")){
            res = mySql.editProducto(pk, nombre, cantidad, valorUnitario);
        }
        if(database.equalsIgnoreCase("PostgreSQL")){
            res = postgres.editProducto(pk, nombre, cantidad, valorUnitario);
        }

        if(res == "success"){
            this.getProductos();
        }

        return "Editado ";
    }

    public void clearConections(){
        mySql.disconnect();
        postgres.disconnect();
    }
}
