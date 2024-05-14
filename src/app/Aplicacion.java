package app;

import leer.Leer;
import menu.Menu;
import misArticulos.Producto;
import misArticulos.Vproductos;
import java.sql.Connection;
import baseDeDatos.ConexionBD;

public class Aplicacion {

    public static void main(String[] args) {
        // Aquí inicia el mensaje inicial de bienvenida
        Menu.Mensaje_Inicial();

        // Establecer conexión con la base de datos
        ConexionBD conexion = new ConexionBD();
        Connection cn = conexion.conectar();
        if (cn == null) {
            System.out.println("Error al establecer conexión con la base de datos.");
            return;
        }

        boolean continuar = true;
        Producto[] productos = new Producto[3];
        productos[0] = new Producto("A dos metros de ti", 10, 500, true);
        productos[1] = new Producto("Elden", 50, 200, true);
        productos[2] = new Producto("Una foto", 8, 1000, true);
        
        Vproductos visualizador = new Vproductos();
        
        do {
            // Aquí te sale las opciones del menú
            Menu.Opciones_Menu();

            switch (Leer.datoInt()) {
                case 1:
                    
                    //Vproductos visualizador = new Vproductos();
                	visualizador.mostrarProductosDesdeBD(cn);
                    break;
                case 2:
                    // Comprar artículos
                	Vproductos.comprarArticulos(cn);
                    break;
                case 3:
                    // Otras opciones
                    break;
                default:
                    // Se sale del programa
                    continuar = false;
            }

        } while (continuar);

         //Cerrar conexión con la base de datos
        try {
            if (cn != null) {
                cn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Aquí aparece con el mensaje final
        Menu.Mensaje_Fin();

    }

}