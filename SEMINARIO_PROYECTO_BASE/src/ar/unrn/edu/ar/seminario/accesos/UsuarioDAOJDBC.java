package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.sun.media.jfxmedia.events.NewFrameEvent;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class UsuarioDAOJDBC implements UsuarioDao{

	@Override
	public void crear(Usuario usuario) {
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO usuarios (usuario, contrasena, nombre, email, id_rol)"
							+ "VALUES (?, ?, ?, ?, ?)");
			
			statement.setString(1, usuario.obtenerUsuario());
			statement.setString(2, usuario.obtenerContrasena());
			statement.setString(3, usuario.obtenerNombre());
			statement.setString(4, usuario.obtenerEmail());
			statement.setInt(5, usuario.obtenerCodigoRol());
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El ciudadano se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	
		    }
				   
		}catch (Exception e) {
			
		
		} finally {
			ConnectionManager.disconnect();
		}
		
	}

	@Override
	public List<Usuario> listarTodos() {
		List<Usuario> usuarios=new ArrayList<Usuario>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from usuarios u join roles r on (u.id_rol=r.id_rol)");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				
				Rol rol = new Rol(rs.getInt("id_rol"), rs.getString("r.nombre"));
				
				String estadoRol = rs.getString("r.estado");
				if(estadoRol.equals("ACTIVO")) {
					rol.setActivo(true);
				}
				else {
					rol.setActivo(false);
				}
				
				Usuario usuario = new Usuario(rs.getString("u.usuario"),rs.getString("u.contrasena"), rs.getString("u.nombre"),
						rs.getString("email"), rol);
				
				usuarios.add(usuario);
				
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al listar viviendas");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}
		return usuarios;
		
	}

	@Override
	public Usuario buscar(String nombreDeUsuario) {
		Usuario usuario = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"SELECT u.usuario,  u.contrasena, u.nombre, u.email, r.id_rol FROM usuarios u JOIN roles r ON (u_id_usuario = r.id_rol) " + " WHERE u.usuario = ?");

			statement.setString(1, nombreDeUsuario);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Rol rol = new Rol(rs.getInt("id_rol"), rs.getString("r.nombre"));
				usuario = new Usuario(rs.getString("u.usuario"), rs.getString("u.contrasena"), rs.getString("u.nombre"),
						rs.getString("u.email"), rol);
			}

		} catch (SQLException e) {
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
			// throw new AppException(e, e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			// TODO: disparar Exception propia
			// throw new AppException(e, e.getCause().getMessage(), e.getMessage());
		} finally {
			ConnectionManager.disconnect();
		}

		return usuario;
	}
	
	
	

}
