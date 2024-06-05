/**
 * 
 */
package misArticulos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import miPersona.Vcliente;

public class Vproductos {

	public void mostrarProductosDesdeBD(Connection conexion) {
		System.out.println("Productos Disponibles: \n");

		String sql = "SELECT nombre, precioUnitario, cantidadStock FROM Producto WHERE disponibilidad = true";

		// Aquí hacemos el TreeMap para guardar los productos de la base de datos en un
		// TreeMap:
		TreeMap<String, Producto> productbd = new TreeMap<>();

		try (PreparedStatement statement = conexion.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				double precioUnitario = resultSet.getDouble("precioUnitario");
				int cantidadStock = resultSet.getInt("cantidadStock");

				productbd.put(nombre, new Producto(cantidadStock, nombre, precioUnitario, cantidadStock, true));

			}
		} catch (SQLException e) {
			System.out.println("Error al recuperar los productos desde la base de datos: " + e.getMessage());
		}

		for (Map.Entry<String, Producto> entry : productbd.entrySet()) {
			Producto producto = entry.getValue();
			System.out.println("Nombre: " + producto.getNombre() + ", Precio: $" + producto.getPrecioUnitario()
					+ ", Stock: " + producto.getCantidadStock());
		}

	}

	public void mostrarProductos(Producto[] productos) {
		System.out.println("Productos Disponibles: \n");
		for (Producto producto : productos) {
			System.out.println("ID_producto: " + producto.getId() + "Nombre: " + producto.getNombre() + ", Precio: $"
					+ producto.getPrecioUnitario() + ", Stock: " + producto.getCantidadStock() + "\n");
		}
	}

	public static void comprarArticulos(Connection cn, boolean clienteExistente) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("===== COMPRAR EL ARTÍCULO =====");
		System.out.println("Ingrese el nombre del artículo:");
		String nombre = scanner.next();

		System.out.println("Ingrese la cantidad:");
		int cantidad = scanner.nextInt();

		// Solicitar los datos del cliente
		if (!clienteExistente) {
			System.out.println("Ingrese el número de cliente:");
			int numeroCliente = scanner.nextInt();
			System.out.println("Ingrese el nombre del cliente:");
			String nombreCliente = scanner.next();
			System.out.println("Ingrese los apellidos del cliente:");
			String apellidos = scanner.next();
			System.out.println("Ingrese la dirección del cliente:");
			String direccion = scanner.next();
			System.out.println("Ingrese la localidad del cliente:");
			String localidad = scanner.next();
			System.out.println("Ingrese la provincia del cliente:");
			String provincia = scanner.next();
			System.out.println("Ingrese el país del cliente:");
			String pais = scanner.next();
			System.out.println("Ingrese el código postal del cliente:");
			int codigoPostal = scanner.nextInt();
			System.out.println("Ingrese el teléfono del cliente:");
			int telefono = scanner.nextInt();
			System.out.println("Ingrese el correo electrónico del cliente:");
			String correo = scanner.next();
			System.out.println("Ingrese observaciones sobre el cliente:");
			String observaciones = scanner.next();

			// Generar el ticket y guardar la compra en la base de datos
			generarTicket(cn, nombre, cantidad, numeroCliente);
			// Guardar los datos del cliente en la base de datos
			Vcliente.guardarCliente(cn, numeroCliente, nombreCliente, apellidos, direccion, localidad, provincia, pais,
					codigoPostal, telefono, correo, observaciones);
		}
	}

	private static void generarTicket(Connection cn, String nombre, int cantidad, int numeroCliente) {
		String nombreArchivo = "C:\\Users\\pablo\\desktop\\ticketEntornos.txt";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo, true))) {
			writer.write("===== TICKET DE COMPRA =====\n");
			writer.write("Artículo: " + nombre + "\n");
			writer.write("Cantidad: " + cantidad + "\n");
			writer.write("Número de cliente: " + numeroCliente + "\n");
			writer.newLine();

			System.out.println("Ticket de compra generado correctamente. Se ha guardado en " + nombreArchivo);
		} catch (IOException e) {
			System.err.println("Error al generar el ticket: " + e.getMessage());
		}
	}

	private static void guardarTicket(Producto producto, int cantidad, double total) {
		String url = "jdbc:mysql://localhost:9999/bd_ejemplo";
		String usuario = "root";
		String clave = "dada";

		try (Connection conexion = DriverManager.getConnection(url, usuario, clave)) {
			String sql = "INSERT INTO Ticket (producto, cantidad, total) VALUES (?, ?, ?)";
			try (PreparedStatement declaracion = conexion.prepareStatement(sql)) {
				declaracion.setString(1, producto.getNombre());
				declaracion.setInt(2, cantidad);
				declaracion.setDouble(3, total);
				declaracion.executeUpdate();
			}
		} catch (SQLException e) {
			System.err.println("Error al guardar el ticket en la base de datos: " + e.getMessage());
		}
	}
}