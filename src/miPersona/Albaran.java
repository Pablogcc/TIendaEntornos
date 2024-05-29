/**
 * 
 */
package miPersona;

import java.util.Date;

/**
 * 
 */
public class Albaran {
	private int codigo;
	private int numeroAlbaran;
	private Date fechaAlbaran;
	private String producto;
	private int cantidad;

	// Constructor
	public Albaran(int codigo, int numeroAlbaran, Date fechaAlbaran, String producto, int cantidad) {
		this.codigo = codigo;
		this.numeroAlbaran = numeroAlbaran;
		this.fechaAlbaran = fechaAlbaran;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	// Getters y Setters
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNumeroAlbaran() {
		return numeroAlbaran;
	}

	public void setNumeroAlbaran(int numeroAlbaran) {
		this.numeroAlbaran = numeroAlbaran;
	}

	public Date getFechaAlbaran() {
		return fechaAlbaran;
	}

	public void setFechaAlbaran(Date fechaAlbaran) {
		this.fechaAlbaran = fechaAlbaran;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	// Método toString para imprimir la información del albarán
	@Override
	public String toString() {
		return "Albaran [codigo=" + codigo + ", numeroAlbaran=" + numeroAlbaran + ", fechaAlbaran=" + fechaAlbaran
				+ ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}
}
