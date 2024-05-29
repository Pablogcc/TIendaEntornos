/**
 * 
 */
package miPersona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 
 */
public class Vcliente {

	public static String verificarClienteExistente(Connection cn, int numeroCliente) {
		String sql = "SELECT nombre FROM Cliente WHERE numeroCliente = ?";
		try (PreparedStatement ps = cn.prepareStatement(sql)) {
			ps.setInt(1, numeroCliente);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString("nombre");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void guardarCliente(Connection cn, int numeroCliente, String nombreCliente, String apellidos,
			String direccion, String localidad, String provincia, String pais, int codigoPostal, int telefono,
			String correo, String observaciones) {
		// Verificar si el cliente ya existe en la base de datos
		if (clienteExiste(cn, numeroCliente)) {
			// Si el cliente existe, actualizar sus detalles
			actualizarCliente(cn, numeroCliente, nombreCliente, apellidos, direccion, localidad, provincia, pais,
					codigoPostal, telefono, correo, observaciones);
		} else {
			// Si el cliente no existe, insertarlo en la base de datos
			String insertClienteSQL = "INSERT INTO Cliente (numeroCliente, nombre, apellidos, direccion, localidad, "
					+ "provincia, pais, codigoPostal, telefono, mail, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
		String updateClienteSQL = "UPDATE Cliente SET nombre=?, apellidos=?, direccion=?, localidad=?, provincia=?, pais=?, "
				+ "codigoPostal=?, telefono=?, mail=?, observaciones=? WHERE numeroCliente=?";
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

	public static Cliente solicitarDatosCliente() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese los datos del cliente:");
		System.out.print("Número de cliente: ");
		int numeroCliente = scanner.nextInt();
		scanner.nextLine();
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
		scanner.nextLine();
		System.out.print("Correo electrónico: ");
		String mail = scanner.nextLine();
		System.out.print("Observaciones: ");
		String observaciones = scanner.nextLine();

		return new Cliente(numeroCliente, nombre, apellidos, direccion, localidad, provincia, pais, codigoPostal,
				telefono, mail, observaciones);
	}
}