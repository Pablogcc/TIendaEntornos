/**
 * 
 */
package baseDeDatos;

/**
 * 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import baseDeDatos.ConexionBD;

public class TestConexion {

	private static String selectTableSQL;
	private static String insertTableSQL;
	private static String updateTableSQL;

	public static void cerrar_conexion(Connection cn, Statement stm, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stm != null) {
				stm.close();
			}
			if (cn != null) {
				cn.close();
			}
			System.out.println("La conexión se ha cerrado con éxito");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void consulta_select() {
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		Statement stm = null;
		ResultSet rs = null;
		selectTableSQL = "SELECT * FROM USUARIO";
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			rs = stm.executeQuery(selectTableSQL);
			while (rs.next()) {
				int idUsuario = rs.getInt(1);
				String usuario = rs.getString(2);
				String clave = rs.getString(3);
				System.out.println(idUsuario + " " + usuario + " " + clave);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cerrar_conexion(cn, stm, rs);
		}
	}

	public static void consulta_insert(String usuario, String clave) {
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		PreparedStatement ps = null;
		insertTableSQL = "INSERT INTO usuario (usuario,clave) VALUES (?,?)";
		try {
			cn = conexion.conectar();
			ps = cn.prepareStatement(insertTableSQL);
			ps.setString(1, usuario);
			ps.setString(2, clave);
			ps.executeUpdate();
			System.out.println("El registro ha sido insertado con exito en la base de datos");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void consulta_update(int id_usuario, String usuario, String clave) {
		ConexionBD conexion = new ConexionBD();
		Connection cn = null;
		Statement stm = null;
		updateTableSQL = "UPDATE usuario set usuario ='" + usuario + "', clave='" + clave + "' where idusuario="
				+ id_usuario;
		try {
			cn = conexion.conectar();
			stm = cn.createStatement();
			int valor = stm.executeUpdate(updateTableSQL);
			if (valor == 1) {
				System.out.println("El usuario ha sido actualizado correctamente");
			} else {
				System.out.println("No se ha actualizado correctamente");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		consulta_select();

	}
}