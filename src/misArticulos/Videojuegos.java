/**
 * 
 */
package misArticulos;

/**
 * 
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Videojuegos extends Producto {
	private String plataforma;
	private String multijugador;
	private String genero;

	public Videojuegos(int id, String nombre, double precioUnitario, int cantidadStock, boolean disponibilidad,
			String plataforma) {
		super(id, nombre, precioUnitario, cantidadStock, disponibilidad);
		this.plataforma = plataforma;
	}

	public Videojuegos() {

	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getMultijugador() {
		return multijugador;
	}

	public void setMultijugador(String multijugador) {
		this.multijugador = multijugador;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public static void obtenerVideojuegosDesdeBD(Connection cn) {
		String consultaSQL = "SELECT * FROM Videojuegos";
		try (PreparedStatement ps = cn.prepareStatement(consultaSQL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");

				System.out.println("ID: " + id + ", Nombre: " + nombre);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
