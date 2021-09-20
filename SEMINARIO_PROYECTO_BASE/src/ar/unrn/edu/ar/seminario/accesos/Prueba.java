package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Prueba {

	public static void main(String[] args) {
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM ciudadanos");
			
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				System.out.println("Nombre y apellido: "+rs.getString("nombre")+" "+rs.getString("apellido")+"Dni: "+rs.getString("dni"));
			}

//			statement.setString(1, usuario.getUsuario());
//			statement.setString(2, usuario.getContrasena());
//			statement.setString(3, usuario.getNombre());
//			statement.setString(4, usuario.getEmail());
//			statement.setBoolean(5, usuario.isActivo());
//			statement.setInt(6, usuario.getRol().getCodigo());
//			int cantidad = statement.executeUpdate();
//			if (cantidad > 0) {
//				// System.out.println("Modificando " + cantidad + " registros");
//			} else {
//				System.out.println("Error al actualizar");
//				// TODO: disparar Exception propia
//			}

		} catch (SQLException e) {
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al insertar un usuario");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
		}

	}

}
