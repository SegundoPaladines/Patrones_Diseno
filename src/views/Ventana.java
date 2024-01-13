package views;
import javax.swing.*;

import controllers.ProductController;
import models.Producto;
import views.observer.ConcreteObservable;
import views.observer.ConcreteObserver;

public class Ventana extends javax.swing.JFrame {
    // objetos observadores
    ConcreteObserver otxt_cantidad;
    ConcreteObserver olbl_inventario;
    ConcreteObserver obtn_agregar;
    ConcreteObserver o_table;

    // objeto observado
    ConcreteObservable observable;

    // constructor
    public Ventana() {
        // inicializar los componentes
        initComponents();

        // instanciar los observadores
        otxt_cantidad = new ConcreteObserver(jLabel_cantidad_productos);
        olbl_inventario = new ConcreteObserver(txtF_inventario);
        o_table = new ConcreteObserver(tabla);

        // el boton se debe ser conectado
        obtn_agregar = new ConcreteObserver(btn_agregar);

        // instanciar el observable
        observable = new ConcreteObservable(obtn_agregar);

        // setear el observer de los elementos observadores
        observable.addObserver(otxt_cantidad);
        observable.addObserver(olbl_inventario);
        observable.addObserver(olbl_inventario);
        observable.addObserver(o_table);
    }

    private void initComponents() {
        productController = ProductController.getInstance();

        // instancia de los elementos de la interfaz gráfica
        txtF_inventario = new javax.swing.JTextField();
        jLabel_cantidad_productos = new javax.swing.JLabel();
        btn_agregar = new javax.swing.JButton();

        // la tabla con el modelo
        tabla = new JTable();

        // se crea el scroll y se le coloca la tabla
        JScrollPane scrollPane = new JScrollPane(tabla);

        // nuevos campos y labels para el formulario de agregar items
        JLabel lbl_nombre = new JLabel("Nombre:");
        JLabel lbl_cantidad = new JLabel("Cantidad:");
        JLabel lbl_valor_unitario = new JLabel("Valor Unitario:");
        txtF_nombre = new JTextField();
        txtF_cantidad = new JTextField();
        txtF_valor_unitario = new JTextField();

        // cuando se cierre la ventana
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // el text field
        txtF_inventario.setEditable(false);

        jLabel_cantidad_productos.setText("Muchos");
        jLabel_cantidad_productos.setFont(new java.awt.Font("Segoe UI", 0, 12));

        btn_agregar.setText("Agregar Producto");
        // evento para cuando el boton sea pulsado
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        // el diseño
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel_cantidad_productos)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                )
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtF_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_nombre)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtF_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_cantidad)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtF_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(lbl_valor_unitario)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtF_valor_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_agregar)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel_cantidad_productos)
                    )
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtF_inventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    )
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_nombre)
                        .addComponent(txtF_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_cantidad)
                        .addComponent(txtF_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_valor_unitario)
                        .addComponent(txtF_valor_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_agregar))
                    .addContainerGap(17, Short.MAX_VALUE)
                )
        );

        pack();

    }

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {
        /* VALIDAR CAMPOS TIPO DE DATO Y QUE NO SEAN VACIOS */
        String nombre = txtF_nombre.getText();
        String cantidad = txtF_cantidad.getText();
        String valorUnitario = txtF_valor_unitario.getText();
        Producto p = new Producto(0, nombre, Integer.parseInt(cantidad), Float.parseFloat(valorUnitario));

        observable.notify_All(productController.agregarProducto(p));
    }

    // metodo que abre la ventana
    public void AbrirVentana() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // variables globales
    private javax.swing.JButton btn_agregar;
    private javax.swing.JLabel jLabel_cantidad_productos;
    private javax.swing.JTextField txtF_inventario;
    private JTable tabla;
    private JTextField txtF_nombre;
    private JTextField txtF_cantidad;
    private JTextField txtF_valor_unitario;
    ProductController productController;
}