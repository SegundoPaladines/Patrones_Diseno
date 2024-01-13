package controllers;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import models.Producto;

public class ProductController {
    private static ProductController productController;
    private ArrayList<Producto> productos;
    private ProductController(){
        this.productos = new ArrayList<Producto>();
    }

    public static ProductController getInstance (){
        if(productController == null){
            productController = new ProductController();
        }
        return productController;
    }

    public String agregarProducto(Producto producto){
        this.productos.add(producto);
        return "Agregado";
    }

    public DefaultTableModel getTablaProductos(){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        // crear el modelo de la tabla
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("#");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Valor Unitario");

        int contador = 1;
        for (Producto producto : productos) {
            modeloTabla.addRow(new Object[]{contador, producto.nombre, producto.cantidad, "$ "+producto.valor_unitario});    
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
        float valorT = 0;

        for (Producto producto : productos) {
            valorT += producto.valor_unitario*producto.cantidad;
        }

        return "Valor Total Stock: " + valorT;
    }
}
