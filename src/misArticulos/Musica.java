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

public class Musica extends Producto {

	private String artista;
	private String duracion;
	private String selloDiscografico;

	public Musica(String nombre, double precioUnitario, int cantidadStock, boolean disponibilidad, String artista) {
		super(nombre, precioUnitario, cantidadStock, disponibilidad);
		this.artista = artista;
	}

	public Musica() {

	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getSelloDiscografico() {
		return selloDiscografico;
	}

	public void setSelloDiscografico(String selloDiscografico) {
		this.selloDiscografico = selloDiscografico;
	}

	public static void obtenerMusicasDesdeBD(Connection cn) {
		String consultaSQL = "SELECT * FROM Musica";
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
