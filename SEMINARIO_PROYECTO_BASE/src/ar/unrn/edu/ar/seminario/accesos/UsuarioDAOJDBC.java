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

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class UsuarioDAOJDBC implements UsuarioDao{

	@Override
	public void crear(Usuario usuario) throws AppException, InstanceException {
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

			int cantidad = statement.executeUpdate();
			if (cantidad==1) 
				System.out.println("El ciudadano se creo correctamente.");
			
				   
		}catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}
		
	}

	@Override
	public List<Usuario> listarTodos() throws AppException, InstanceException {
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
		

		}catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}
		return usuarios;
		
	}

	@Override
	public Usuario buscar(String nombreDeUsuario) throws AppException, InstanceException {
		Usuario usuario = null;
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"SELECT u.id_usuario, u.usuario,  u.contrasena, u.nombre, u.email, u.id_rol, (select r.nombre from roles r"
					+ " where r.id_rol=u.id_rol) as nombre_rol FROM usuarios u where u.usuario = ?");

			statement.setString(1, nombreDeUsuario);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				Rol rol = new Rol(rs.getInt("u.id_rol"), rs.getString("nombre_rol"));
				usuario = new Usuario(rs.getString("u.usuario"), rs.getString("u.contrasena"), rs.getString("u.nombre"),
						rs.getString("u.email"), rol);
				usuario.editarId(new Integer(rs.getInt("u.id_usuario")));
				
			}

		} catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}

		return usuario;
	}

	
	
	

}
