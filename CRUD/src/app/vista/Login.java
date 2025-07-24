package app.vista;

import app.controlador.Usuario;
import app.modelo.Conexion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame
{
    private JPanel panel1;
    private JTextField text1;
    private JButton iniciarSesión;
    private JPasswordField pass1;
    private JButton limpiarButton;
    private JComboBox comboBox1;

    private String username = "Juan";
    private String password = "9029";

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    public Login() {
        setTitle("Login");
        setSize(400, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel1);
        setLocationRelativeTo(null);

        iniciarSesión.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conexion conexion = new Conexion();
                String usuario = text1.getText();
                String contrasenia = pass1.getText();

                if (conexion.validar(usuario,contrasenia)){
                    Usuario.setUsuario(usuario);
                    CatalogoProductos c = new CatalogoProductos();
                    JOptionPane.showMessageDialog(null,"Usuario y contaseña ingresados correctamente.");
                    c.setVisible(true);
                    setVisible(false);

                }else {
                    JOptionPane.showMessageDialog(null, "Usuario y contaseña son incorrectos");
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text1.setText(String.valueOf(""));
                pass1.setText(String.valueOf("0"));
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
