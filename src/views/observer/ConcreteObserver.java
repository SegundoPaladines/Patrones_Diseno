package views.observer;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import controllers.ProductController;

import javax.swing.JComponent;

public class ConcreteObserver implements IObserver{
    JComponent component;
    ProductController productController;

    public ConcreteObserver(JComponent c) {
        this.component=c;
        productController = ProductController.getInstance();
    }
    
    @Override
    public void notify_(String msg){
        if(component instanceof JTextField txtF){
            txtF.setText(productController.getValorTotalInventario());
        }
        if(component instanceof JLabel lbl){
            lbl.setText(productController.getTotalProductos());
        }
        if(component instanceof JTable table){
            DefaultTableModel defaultTableModel = (DefaultTableModel) productController.getTablaProductos();
            if (defaultTableModel instanceof DefaultTableModel) {
                table.setModel(defaultTableModel);
            }
        }
    }
}
