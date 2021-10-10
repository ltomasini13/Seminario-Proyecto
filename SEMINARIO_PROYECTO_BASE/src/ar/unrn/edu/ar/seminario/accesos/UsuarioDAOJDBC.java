package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Usuario;

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
			statement.setInt(5, usuario.obtenerRol().obtenerCodigo());
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El ciudadano se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("El ciudadano con dni: "+vivienda.obtenerDniCiudadano()+" ya existe");
		    }
			
			
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
		    int idCiudadano=miResult.getInt(1);
		    
		    //vivienda.obtenerCiudadano().editarId(Long.valueOf(idCiudadano));
		    miResult.close();
		    
		    PreparedStatement statement2 = conn
					.prepareStatement("INSERT INTO viviendas (id_ciudadano, calle, numero, barrio, latitud, longitud)"
							+ "VALUES (?, ?, ?, ?, ?, ?)");
		    statement2.setInt(1, idCiudadano);
		    statement2.setString(2, vivienda.obtenerUbicacionCalle());
		    statement2.setInt(3, vivienda.obtenerUbicacionNro());
		    statement2.setString(4, vivienda.obtenerUbicacionBarrio());
		    statement2.setDouble(5, vivienda.obtenerUbicacionLatitud());
		    statement2.setDouble(6, vivienda.obtenerUbicacionLongitud());
			
		    try {
		    	int cantidad2 = statement2.executeUpdate();
		    	 if(cantidad2==1) {
				    	System.out.println("La vivienda se creo correctamente");
				  }
		    }
		    catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("La vivienda con dicha direccion ya existe");
		    }
		   
		   
		    
		    
		} catch (DuplicateUniqueKeyException e) {
			throw new DuplicateUniqueKeyException(e.getMessage());
			
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
			throw new SintaxisSQLException("No se pudo crear la vivienda por un error en la Base de Datos");
			
		
		} finally {
			ConnectionManager.disconnect();
		}
		
	}

	@Override
	public List<Usuario> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
