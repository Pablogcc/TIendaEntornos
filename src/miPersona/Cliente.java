/**
 * 
 */
package miPersona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 
 */
public class Cliente {

	private int numeroCliente;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String localidad;
	private String provincia;
	private String pais;
	private int codigoPostal;
	private int telefono;
	private String mail;
	private String observaciones;

	public Cliente(int numeroCliente, String nombre, String apellidos, String direccion, String localidad,
			String provincia, String pais, int codigoPostal, int telefono, String mail, String observaciones) {
		this.numeroCliente = numeroCliente;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
		this.codigoPostal = codigoPostal;
		this.telefono = telefono;
		this.mail = mail;
		this.observaciones = observaciones;
	}

	// Getters y Setters
	public int getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(int numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(int codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public static void obtenerClientesDesdeBD(Connection cn) {
		String consultaSQL = "SELECT * FROM Cliente";
		try (PreparedStatement ps = cn.prepareStatement(consultaSQL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int numeroCliente = rs.getInt("numeroCliente");
				String nombre = rs.getString("nombre");
				// Resto de atributos...
				System.out.println("Cliente: " + numeroCliente + ", Nombre: " + nombre);
				// Imprimir el resto de atributos...
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Cliente [numeroCliente=" + numeroCliente + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", direccion=" + direccion + ", localidad=" + localidad + ", provincia=" + provincia + ", pais="
				+ pais + ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + ", mail=" + mail
				+ ", observaciones=" + observaciones + "]";
	}
}