package app.controlador;

public class Usuario
{
    private static String usuario;

    private static String username = "Seris";
    private static String password = "9029";

    public static String getUsuario() {

        return Usuario.usuario;
    }

    public static void setUsuario(String usuario2) {
       usuario = usuario2;
    }

}
