/**
 * 
 */
package misArticulos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import misArticulos.Producto;

/**
 * 
 */
public class Cine extends Producto {
	private String director;
	private String actor;

	public Cine(int id, String nombre, double precioUnitario, int cantidadStock, boolean disponibilidad, String director) {
		super(id, nombre, precioUnitario, cantidadStock, disponibilidad);
		this.director = director;
	}

	public Cine() {

	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public static void obtenerPeliculasDesdeBD(Connection cn) {
		String consultaSQL = "SELECT * FROM Cine";
		try (PreparedStatement ps = cn.prepareStatement(consultaSQL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				// Resto de atributos...
				System.out.println("ID: " + id + ", Nombre: " + nombre);
				// Imprimir el resto de atributos...
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
