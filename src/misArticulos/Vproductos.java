/**
 * 
 */
package misArticulos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import app.Cliente;
import misArticulos.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import misArticulos.Producto;
import java.sql.DriverManager;

public class Vproductos {

	public void mostrarProductosDesdeBD(Connection conexion) {
		System.out.println("Productos Disponibles: \n");

		String sql = "SELECT nombre, precioUnitario, cantidadStock FROM Producto WHERE disponibilidad = true";

		try (PreparedStatement statement = conexion.prepareStatement(sql)) {
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String nombre = resultSet.getString("nombre");
				double precioUnitario = resultSet.getDouble("precioUnitario");
				int cantidadStock = resultSet.getInt("cantidadStock");

				System.out.println(
						"Nombre: " + nombre + ", Precio: $" + precioUnitario + ", Stock: " + cantidadStock + "\n");
				
			}
		} catch (SQLException e) {
			System.out.println("Error al recuperar los productos desde la base de datos: " + e.getMessage());
		}
	}

	public void mostrarProductos(Producto[] productos) {
		System.out.println("Productos Disponibles: \n");
		for (Producto producto : productos) {
			System.out.println("ID_producto: " + producto.getId() + "Nombre: " + producto.getNombre() + ", Precio: $" + producto.getPrecioUnitario()
					+ ", Stock: " + producto.getCantidadStock() + "\n");
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
		if(!clienteExistente) {
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
		guardarCliente(cn, numeroCliente, nombreCliente, apellidos, direccion, localidad, provincia, pais, codigoPostal,
				telefono, correo, observaciones);
		}
	}

	private static void generarTicket(Connection cn, String nombre, int cantidad, int numeroCliente) {
		String nombreArchivo = "C:\\Users\\pablo\\desktop\\ticketEntornos.txt";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
			writer.write("===== TICKET DE COMPRA =====\n");
			writer.write("Artículo: " + nombre + "\n");
			writer.write("Cantidad: " + cantidad + "\n");
			writer.write("Número de cliente: " + numeroCliente + "\n");

			System.out.println("Ticket de compra generado correctamente. Se ha guardado en " + nombreArchivo);
		} catch (IOException e) {
			System.err.println("Error al generar el ticket: " + e.getMessage());
		}
	}

	private static void guardarCliente(Connection cn, int numeroCliente, String nombreCliente, String apellidos,
            String direccion, String localidad, String provincia, String pais, int codigoPostal, int telefono,
            String correo, String observaciones) {
        // Verificar si el cliente ya existe en la base de datos
        if (clienteExiste(cn, numeroCliente)) {
            // Si el cliente existe, actualizar sus detalles
            actualizarCliente(cn, numeroCliente, nombreCliente, apellidos, direccion, localidad, provincia, pais,
                codigoPostal, telefono, correo, observaciones);
        } else {
            // Si el cliente no existe, insertarlo en la base de datos
            String insertClienteSQL = "INSERT INTO Cliente (numeroCliente, nombre, apellidos, direccion, localidad, provincia, pais, codigoPostal, telefono, mail, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = cn.prepareStatement(insertClienteSQL)) {
                ps.setInt(1, numeroCliente);
                ps.setString(2, nombreCliente);
                ps.setString(3, apellidos);
                ps.setString(4, direccion);
                ps.setString(5, localidad);
                ps.setString(6, provincia);
                ps.setString(7, pais);
                ps.setInt(8, codigoPostal);
                ps.setInt(9, telefono);
                ps.setString(10, correo);
                ps.setString(11, observaciones);

                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("Cliente guardado en la base de datos.");
                } else {
                    System.out.println("Error al guardar el cliente en la base de datos.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

private static boolean clienteExiste(Connection cn, int numeroCliente) {
        String sql = "SELECT COUNT(*) FROM Cliente WHERE numeroCliente = ?";
        try (PreparedStatement statement = cn.prepareStatement(sql)) {
            statement.setInt(1, numeroCliente);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static void actualizarCliente(Connection cn, int numeroCliente, String nombreCliente, String apellidos,
            String direccion, String localidad, String provincia, String pais, int codigoPostal, int telefono,
            String correo, String observaciones) {
        String updateClienteSQL = "UPDATE Cliente SET nombre=?, apellidos=?, direccion=?, localidad=?, provincia=?, pais=?, codigoPostal=?, telefono=?, mail=?, observaciones=? WHERE numeroCliente=?";
        try (PreparedStatement ps = cn.prepareStatement(updateClienteSQL)) {
            ps.setString(1, nombreCliente);
            ps.setString(2, apellidos);
            ps.setString(3, direccion);
            ps.setString(4, localidad);
            ps.setString(5, provincia);
            ps.setString(6, pais);
            ps.setInt(7, codigoPostal);
            ps.setInt(8, telefono);
            ps.setString(9, correo);
            ps.setString(10, observaciones);
            ps.setInt(11, numeroCliente);

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente actualizado en la base de datos.");
            } else {
                System.out.println("Error al actualizar el cliente en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	private static Cliente solicitarDatosCliente() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese los datos del cliente:");
		System.out.print("Número de cliente: ");
		int numeroCliente = scanner.nextInt();
		scanner.nextLine(); // Consumir la nueva línea
		System.out.print("Nombre: ");
		String nombre = scanner.nextLine();
		System.out.print("Apellidos: ");
		String apellidos = scanner.nextLine();
		System.out.print("Dirección: ");
		String direccion = scanner.nextLine();
		System.out.print("Localidad: ");
		String localidad = scanner.nextLine();
		System.out.print("Provincia: ");
		String provincia = scanner.nextLine();
		System.out.print("País: ");
		String pais = scanner.nextLine();
		System.out.print("Código Postal: ");
		int codigoPostal = scanner.nextInt();
		System.out.print("Teléfono: ");
		int telefono = scanner.nextInt();
		System.out.print("Correo electrónico: ");
		String mail = scanner.nextLine();
		System.out.print("Observaciones: ");
		String observaciones = scanner.nextLine();

		return new Cliente(numeroCliente, nombre, apellidos, direccion, localidad, provincia, pais, codigoPostal,
				telefono, mail, observaciones);
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