package app.modelo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class Conexion
{
    private File archivo = null;
    private FileReader fr = null;
    private BufferedReader br = null;

    // METODOS PARA LEER EL ARCHIVO
    public void leerUnaFila(String path)
    {
        try {
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // lectura e impresion
//            String linea = br.readLine();
//            System.out.println(linea);
            String linea;
            while((linea = br.readLine())!=null)
                System.out.println(linea);
        } catch (Exception e) { e.printStackTrace();}

        try {   fr.close();
        } catch (IOException e) {   e.printStackTrace();} // todo Auto-generated catch block
    }
    public void leerArchivo()
    {
        try {
            JFileChooser file = new JFileChooser();
            file.setCurrentDirectory(new File("C:/Users/POO/Desktop/pailland/CRUD/src/app/modelo/"));
            file.showOpenDialog(null);
            File nuevo = file.getSelectedFile();
            fr = new FileReader(nuevo);
            br = new BufferedReader(fr);
            String linea;
            while ((linea = br.readLine()) != null) {   System.out.println(linea);   }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR al tratar de leer el archivo");
            e.printStackTrace();
        } finally {
            try {   fr.close();
            } catch (IOException e) { e.printStackTrace();}
        }
    }

    public boolean validar (String usuario, String password) {
        String rutaArchivo = "C:/Users/POO/Desktop/pailland/CRUD/src/app/modelo/users.txt";
        System.out.println("Abriendo archivo: " + rutaArchivo);
        boolean validacion = false;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    String usuarioArchivo = partes[0].trim();
                    String passwordArchivo = partes[1].trim();

                    if (usuario.equals(usuarioArchivo) && password.equals(passwordArchivo)) {
                        validacion = true;
                    }
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de usuarios:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return validacion;
    }





    public Connection conectar()
    {
        String url = "jdbc:sqlite:" + new File("C:/Users/POO/Desktop/pailland/prod.db").getAbsolutePath();
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Estamos conectados");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.conectar();
    }


}
