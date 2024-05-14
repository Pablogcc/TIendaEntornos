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

public class Pedido {

	private int ordenDePedido;
	private int codigoCliente;
	private String producto;
	private int cantidad;

	// Constructor
	public Pedido(int ordenDePedido, int codigoCliente, String producto, int cantidad) {
		this.ordenDePedido = ordenDePedido;
		this.codigoCliente = codigoCliente;
		this.producto = producto;
		this.cantidad = cantidad;
	}

	// Getters y Setters
	public int getOrdenDePedido() {
		return ordenDePedido;
	}

	public void setOrdenDePedido(int ordenDePedido) {
		this.ordenDePedido = ordenDePedido;
	}

	public int getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(int codigoCliente) {
		this.codigoCliente = codigoCliente;
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

	public static void obtenerPedidosDesdeBD(Connection cn) {
		String consultaSQL = "SELECT * FROM Pedido";
		try (PreparedStatement ps = cn.prepareStatement(consultaSQL); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				int ordendepedido = rs.getInt("ordendepedido");
				int codigocliente = rs.getInt("codigocliente");
				String producto = rs.getString("producto");
				int cantidad = rs.getInt("cantidad");
				System.out.println("Pedido: " + ordendepedido + ", Cliente: " + codigocliente + ", Producto: "
						+ producto + ", Cantidad: " + cantidad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Método toString para imprimir la información del pedido
	@Override
	public String toString() {
		return "Pedido [ordenDePedido=" + ordenDePedido + ", codigoCliente=" + codigoCliente + ", producto=" + producto
				+ ", cantidad=" + cantidad + "]";
	}
}
