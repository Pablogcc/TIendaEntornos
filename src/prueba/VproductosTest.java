/**
 * 
 */
package prueba;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import misArticulos.Producto;
import misArticulos.Vproductos;


public class VproductosTest {

    private Connection connection;
    private Vproductos vproductos;

    @Before
    public void setUp() {
        try {
            // Establecer la conexión a la base de datos de prueba
            String url = "jdbc:mysql://localhost:3306/test_database";
            String username = "username";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);
            vproductos = new Vproductos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMostrarProductosDesdeBD() {
        // Llamamos al método que queremos probar
        vproductos.mostrarProductosDesdeBD(connection);
        
        // Agrega aserciones según sea necesario para verificar el resultado
    }

    @Test
    public void testMostrarProductos() {
        // Crea algunos productos de prueba y pasa la matriz de productos al método
        Producto[] productos = {
            new Producto(1, "Producto1", 10.0, 5, true),
            new Producto(2, "Producto2", 20.0, 10, true)
        };

        vproductos.mostrarProductos(productos);

        // Agrega aserciones según sea necesario para verificar el resultado
    }

    @Test
    public void testComprarArticulos() {
        // Creamos una instancia de Vproductos
        Vproductos vproductos = new Vproductos();

        // Simulamos la entrada del usuario para la compra de un artículo
        ByteArrayInputStream simulatedInput = new ByteArrayInputStream("Articulo1\n2\n123\nJuan\nPerez\nDireccion\nLocalidad\nProvincia\nPais\n12345\n123456789\ncorreo@example.com\nObservaciones\n".getBytes());
        System.setIn(simulatedInput);

        // Llamamos al método para comprar artículos
        vproductos.comprarArticulos(connection, false);
    }


  

}
