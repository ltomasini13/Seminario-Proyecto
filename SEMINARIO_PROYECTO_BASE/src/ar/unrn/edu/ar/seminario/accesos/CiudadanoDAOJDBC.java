package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class CiudadanoDAOJDBC implements CiudadanoDao{

	@Override
	public void crear(Ciudadano ciudadano) throws SintaxisSQLException {
		
		
		Connection conn = ConnectionManager.getConnection();
		
		try {
		PreparedStatement statement = conn
				.prepareStatement("INSERT INTO usuarios (usuario, contrasena, nombre, email, id_rol, estado)"
						+ "VALUES (?, ?, ?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
		
		statement.setString(1, ciudadano.obtenerNombreDeUsuario());
		statement.setString(2,ciudadano.obtenerContrasenaUsuario());
		statement.setString(3, ciudadano.obtenerNombre());
		statement.setString(4, ciudadano.obtenerEmailUsuario());
		statement.setInt(5, ciudadano.obtenerUsuario().obtenerCodigoRol());
		statement.setString(6, ciudadano.obtenerUsuario().obtenerEstado());
	
		int cantidad = statement.executeUpdate();
		if (cantidad==1) {
				System.out.println("El usuario se creo correctamente.");
		}
		
	
		ResultSet miResult = statement.getGeneratedKeys();
		miResult.next();
	    int idUsuarioss=miResult.getInt(1);
	    miResult.close();
	    
	    PreparedStatement statement2 = conn
				.prepareStatement("INSERT INTO ciudadanos (nombre, apellido, dni, id_usuario) VALUES (?, ?, ?, ?)");
	    
	    statement2.setString(1, ciudadano.obtenerNombre());
	    statement2.setString(2, ciudadano.obtenerApellido());
	    statement2.setString(3, ciudadano.obtenerDni());
	    statement2.setInt(4, idUsuarioss);
	    
		
	    int cantidad2 = statement2.executeUpdate();
	  	 
	    if(cantidad2==1) {
			    	System.out.println("El ciudadano se creo correctamente");
		}
	  
	    
	    
		}
		catch (SQLException sq){
			throw new SintaxisSQLException("Hubo un error con la base de datos");
			
		}
	}

	@Override
	public Ciudadano buscar(String dni) {
		Ciudadano ciu=null;
		Connection conn = ConnectionManager.getConnection();
		
		try {
		PreparedStatement statement = conn
				.prepareStatement("SELECT * FROM ciudadanos c join usuarios u join roles r "
						+ "WHERE c.id_usuario = u.id_usuario && u.id_rol=r.id_rol && c.dni =?");
		
		statement.setString(1, dni);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			Rol rol= new Rol(rs.getInt("r.id_rol"),rs.getString("r.nombre"));
			 if(rs.getString("r.estado").equals("ACTIVO")) {
					rol.activar();
			 }
			 else {
					rol.desactivar();
			 }
			 
			Usuario usu = new Usuario(rs.getString("u.usuario"), rs.getString("u.contrasena"), rs.getString("u.nombre"), rs.getString("u.email"), rol);
			ciu=new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"), rs.getString("c.dni"), usu);
		}
		
	  
	    
	    
		}
		catch (SQLException sq){
			System.out.println("Error al procesar consulta");			
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
		}
		finally {
			ConnectionManager.disconnect();
		}
		
		return ciu;
	}

	@Override
	public List<Vivienda> listarMisViviendas(Ciudadano ciudadano) {
		List<Vivienda> viviendas = new ArrayList<Vivienda>();
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement("SELECT * FROM viviendas v where v.id_ciudadano=?");

			statement.setInt(1, ciudadano.obtenerId());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
				vivienda.editarId(rs.getInt("v.id_vivienda"));
				viviendas.add(vivienda);
			}

		} catch (SQLException e) {
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
			// throw new AppException(e, e.getSQLState(), e.getMessage());
			
		} catch (Exception e) {
			//hacer algo
		
		} finally {
			ConnectionManager.disconnect();
		}

		return viviendas;
	}

	@Override
	public Ciudadano buscar(Usuario usuario) {
		Ciudadano ciu=null;
		Connection conn = ConnectionManager.getConnection();
		
		try {
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM ciudadanos c where c.id_usuario=?");
			
			statement.setInt(1, usuario.obtenerId());
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				ciu = new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"),rs.getString("c.dni"),  usuario);
				ciu.editarId(rs.getInt("c.id_ciudadano"));
			}
	 
		}
		catch (SQLException sq){
			System.out.println("Error al procesar consulta");			
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
		}
		finally {
			ConnectionManager.disconnect();
		}
		
		return ciu;
	}

	

}
