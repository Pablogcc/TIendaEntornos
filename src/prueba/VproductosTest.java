/**
 * 
 */
package prueba;

/**
 * Pablo
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import baseDeDatos.ConexionBD;
import misArticulos.Vproductos;

import org.junit.jupiter.api.BeforeEach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VproductosTest {
    private Connection cn;
    private Vproductos visualizador;

    @BeforeEach
    public void setUp() {
        // Aquí se inicializa la conexión a la base de datos y la instancia de Vproductos
        ConexionBD conexion = new ConexionBD();
        cn = conexion.conectar();
        visualizador = new Vproductos();
    }

    @Test
    public void testVerificarClienteExistente() {
        // Inserta un cliente en la base de datos para probar
        int numeroCliente = 1;
        String nombreCliente = "Juan";
        insertarClientePrueba(numeroCliente, nombreCliente);

        // Verifica que el cliente existente es reconocido
        String resultado = Vproductos.verificarClienteExistente(cn, numeroCliente);
        assertEquals(nombreCliente, resultado);
    }

    private void insertarClientePrueba(int numeroCliente, String nombreCliente) {
        // Inserta un cliente en la base de datos para las pruebas
        String sql = "INSERT INTO Cliente (numeroCliente, nombre) VALUES (?, ?)";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, numeroCliente);
            ps.setString(2, nombreCliente);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}