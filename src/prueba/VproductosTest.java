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
		// Aquí se inicializa la conexión a la base de datos y la instancia de
		// Vproductos
		ConexionBD conexion = new ConexionBD();
		cn = conexion.conectar();
		visualizador = new Vproductos();
	}

	@Test
	public void testMostrarProductosDesdeBD() {
		// Inserta productos en la base de datos para probar
		insertarProductoPrueba("Producto1", 10.0, 5, true);
		insertarProductoPrueba("Producto2", 20.0, 3, true);

		// Captura la salida estándar
		java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
		System.setOut(new java.io.PrintStream(outContent));

		// Llama al método para mostrar productos desde la base de datos
		visualizador.mostrarProductosDesdeBD(cn);

		// Verifica que los productos insertados se muestran correctamente
		String expectedOutput = "Productos Disponibles: \n" + "Nombre: Producto1, Precio: $10.0, Stock: 5\n"
				+ "Nombre: Producto2, Precio: $20.0, Stock: 3\n";

		assertTrue(outContent.toString().contains(expectedOutput));
	}

	private void insertarProductoPrueba(String nombre, double precioUnitario, int cantidadStock,
			boolean disponibilidad) {
		// Inserta un producto en la base de datos para las pruebas
		String sql = "INSERT INTO Producto (nombre, precioUnitario, cantidadStock, disponibilidad) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setString(1, nombre);
			ps.setDouble(2, precioUnitario);
			ps.setInt(3, cantidadStock);
			ps.setBoolean(4, disponibilidad);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
