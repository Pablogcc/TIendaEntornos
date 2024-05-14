/**
 * 
 */
package app;

/**
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {

	private int numeroCliente;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String localidad;
	private String provincia;
	private String pais;
	private String codigoPostal;
	private String telefono;
	private String mail;
	private String observaciones;

	public Cliente(int numeroCliente, String nombre, String apellidos, String direccion, String localidad,
			String provincia, String pais, String codigoPostal, String telefono, String mail, String observaciones) {
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

	public static void obtenerClientesDesdeBD(Connection cn) {
	        String consultaSQL = "SELECT * FROM Cliente";
	        try (PreparedStatement ps = cn.prepareStatement(consultaSQL);
	             ResultSet rs = ps.executeQuery()) {
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
