package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;
import ar.unrn.edu.ar.seminario.accesos.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ar.edu.unrn.seminario.modelo.Vivienda;

public class ViviendaDAOJDBC implements ViviendaDao {

	@Override
	public void crear(Vivienda vivienda) {
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO vivienda(usuario, contrasena, nombre, email, activo,rol) "
							+ "VALUES (?, ?, ?, ?, ?, ?)");

//			statement.setString(1, usuario.getUsuario());
//			statement.setString(2, usuario.getContrasena());
//			statement.setString(3, usuario.getNombre());
//			statement.setString(4, usuario.getEmail());
//			statement.setBoolean(5, usuario.isActivo());
//			statement.setInt(6, usuario.getRol().getCodigo());
			int cantidad = statement.executeUpdate();
			if (cantidad > 0) {
				// System.out.println("Modificando " + cantidad + " registros");
			} else {
				System.out.println("Error al actualizar");
				// TODO: disparar Exception propia
			}

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

	@Override
	public void modificar(Vivienda vivienda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vivienda> listarTodas() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
