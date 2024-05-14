/**
 * 
 */
package misArticulos;

/**
 * 
 */
public class Producto {
	private String nombre;
	private double precioUnitario;
	private int cantidadStock;
	private boolean disponibilidad;

	public Producto(String nombre, double precioUnitario, int cantidadStock, boolean disponibilidad) {
		this.nombre = nombre;
		this.precioUnitario = precioUnitario;
		this.cantidadStock = cantidadStock;
		this.disponibilidad = disponibilidad;
	}

	public Producto() {
	}

	// Getter para el precio

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getCantidadStock() {
		return cantidadStock;
	}

	public void setCantidadStock(int cantidadStock) {
		this.cantidadStock = cantidadStock;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
}
