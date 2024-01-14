package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.Producto;
import views.observer.ConcreteObservable;
import controllers.ProductController;

public class ProductoForm extends JFrame{
    private JTextField txtNombre, txtCantidad, txtValorUnitario;
    private String accion;
    private Producto producto;
    private ProductController productController;
    ConcreteObservable observable;

    public ProductoForm(int pk, String database, String accion, ConcreteObservable observable){
        this.productController = ProductController.getInstance();
        this.producto = productController.getProducto(pk, database);
        this.accion = accion;
        this.observable = observable;

        // consfigurar la ventana
        setTitle("Formulario "+this.accion+" Producto "+producto.nombre);
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // creaciion de loos labels con su respectivo texto
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblCantidad = new JLabel("Cantidad:");
        JLabel lblValorUnitario = new JLabel("Valor Unitario:");

        //los tex field que vana a recibir los datos
        txtNombre = new JTextField(20);
        txtCantidad = new JTextField(20);
        txtValorUnitario = new JTextField(20);

        //botpones para guardar o cancelar
        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        // configurar el layout
        setLayout(new GridLayout(4, 2));

        // se añaden los elementos del form
        add(lblNombre);
        add(txtNombre);
        add(lblCantidad);
        add(txtCantidad);
        add(lblValorUnitario);
        add(txtValorUnitario);
        add(btnGuardar);
        add(btnCancelar);

        // configurar la operación de cierre
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // accion del boton guardar
        btnGuardar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // funcion guardar
                guardarDatos();
            }
        });

        //cerrar la ventana al cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // cerrar la ventana
                dispose();
            }
        });

        cargarDatos();
    }

    private void cargarDatos(){
        txtNombre.setText(this.producto.nombre);
        txtCantidad.setText(this.producto.cantidad+"");
        txtValorUnitario.setText(this.producto.valor_unitario+"");
    }

    // para mostrar el formulario
    public void mostrarForm() {
        setVisible(true);
    }

    // guardar los cambios
    private void guardarDatos(){
        /* VALIDAR CAMPOS TIPO DE DATO Y QUE NO SEAN VACIOS */
        String nombre = txtNombre.getText();
        int cantidad = Integer.parseInt(txtCantidad.getText());
        float valorUnitario = Float.parseFloat(txtValorUnitario.getText());

        if(accion.equalsIgnoreCase("Clonar")){
            try {
                Producto clonedP = this.producto.clonarProducto();
                if(clonedP!=null){
                    clonedP.nombre = nombre;
                    clonedP.cantidad = cantidad;
                    clonedP.valor_unitario = valorUnitario;

                    productController.agregarProducto(clonedP);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            
        }

        //se supone que actualiza la tabla
        observable.notify_All(accion);

        dispose();
    }
}

