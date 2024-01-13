package models;

public class Producto implements Cloneable{
    public int pk;
    public String nombre;
    public int cantidad;
    public float valor_unitario;
    public String bd;

    public Producto(int pk, String nombre, int cantidad, float valor_unitario){
        this.pk = pk;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.valor_unitario = valor_unitario;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    @Override
    public String toString(){
        return " {'Nombre': "+this.nombre+", 'Cantidad': "+this.cantidad+",'valor':'"+this.valor_unitario+"}";
    }
}
