package app.vista;

import app.controlador.Servicio;
import app.controlador.Usuario;
import app.modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;


public class CatalogoProductos extends JFrame
{
    private JPanel panel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton nuevoButton;
    private JButton mostrarButton;
    private JButton actualizarButton;
    private JButton eliminarButton;
    private JTable table1;
    private JButton reporteButton;
    private JButton cerrarSesionButton;
    private JLabel cartel1;
    private DefaultTableModel model;
    private String clave;
    private Servicio controlador = new Servicio();

    private Object[] columns={"id","codigo","Producto","Precio"};
    private Object[] row=new Object[4];
    private Map<Integer, Producto> mapa= null;
    public CatalogoProductos() {
        setTitle("Catalogo de Productos");
        setSize(720,480);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);
        //cargarTablaMaterias();
        cartel1.setText("Bienvenido " + Usuario.getUsuario());
        obtenerRegistroTabla();
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = table1.getSelectedRow();
                clave = model.getValueAt(i,0).toString();
                textField1.setText(model.getValueAt(i,2).toString());
                textField2.setText(model.getValueAt(i,1).toString());
                textField3.setText(model.getValueAt(i,3).toString());
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerRegistroTabla();
            }
        });
        nuevoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String codigo = textField2.getText();
                String nombre = textField1.getText();
                double precio = Double.parseDouble((textField3.getText()));
                controlador.insertar(new Producto(codigo,nombre, precio));
                obtenerRegistroTabla();
            }
        });
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(clave);
                String codigo = textField2.getText();
                String nombre = textField1.getText();
                String precioStr = textField3.getText().replace(",",".");
                double precio = Double.parseDouble(precioStr);
                controlador.actualizar(new Producto(id, codigo, nombre, precio));
                obtenerRegistroTabla();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(clave);
                controlador.eliminar(id);
                obtenerRegistroTabla();
            }
        });
        reporteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    table1.print();
                } catch (Exception e2) {
                    System.out.println(e2.getMessage());
                }
            }
        });
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                l.setVisible(true);
                setVisible(false);
            }
        });
    }

    private void cargarTablaMaterias()
    {
        String[] columnas = {"ID","Código","Nombre","Precio"};

        Object[][] datos = {
                {"1", "C009", "Impresora HP Simplex", "130.0"},
                {"2", "A002", "Disco Duro 4TB", "120.0"}
        };
        DefaultTableModel modelo = new DefaultTableModel(datos, columnas);
        table1.setModel(modelo);
    }

    /*public void  obtenerRegistroTabla(){
        model = new DefaultTableModel(){
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int filas, int columnas)
            {return false;}
        };
        while (model.getRowCount()>0){model.removeRow(0);}

        mapa = controlador.seleccionarTodo();
        for(Map.Entry<Integer, Producto> entry:mapa.entrySet()) {
            row[0] = entry.getKey();
            row[1] = entry.getValue().getCodigo();
            row[2] = entry.getValue().getNombre();
            row[3]=String.format("%.2f",entry.getValue().getPrecio());
            model.addRow(row);
        }

        model.setColumnIdentifiers(columns);
        table1.setModel(model);

        table1.getColumnModel().getColumn(0).setPreferredWidth(50);
        table1.getColumnModel().getColumn(1).setPreferredWidth(50);
        table1.getColumnModel().getColumn(2).setPreferredWidth(180);
        table1.getColumnModel().getColumn(3).setPreferredWidth(50);

        limpiarCampos();
    }*/

    public void obtenerRegistroTabla() {
        model = new DefaultTableModel () {
            private static final long serialversionUID = 1L;
            @Override
            public boolean isCellEditable(int fila, int colummnas)
            {return false;}
        };
        model.setColumnIdentifiers(columns);
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        mapa = controlador.seleccionarTodo();
        for (Map.Entry<Integer, Producto> entry : mapa.entrySet()) {
            row[0] = entry.getKey();
            row[1] = entry.getValue().getCodigo();
            row[2] = entry.getValue().getNombre();
            row[3]=String.format("%.2f",entry.getValue().getPrecio());
            model.addRow(row);
        }

        // BLOQUEAR LA EDICION
        table1.setModel(model);
        limpiarCampos();

        table1.getColumnModel().getColumn(0).setPreferredWidth(50);
        table1.getColumnModel().getColumn(1).setPreferredWidth(50);
        table1.getColumnModel().getColumn(2).setPreferredWidth(180);
        table1.getColumnModel().getColumn(3).setPreferredWidth(50);
    }

    public void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
    }

    public  void setCartel1(){
        cartel1.setText("Bienvenido " + Usuario.getUsuario());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CatalogoProductos().setVisible(true);
        });
    }

}
