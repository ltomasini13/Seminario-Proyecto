package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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

public class CiudadanoDAOJDBC implements CiudadanoDao{

	@Override
	public void crear(Ciudadano ciudadano) throws AppException, InstanceException {
		
		
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
			throw new AppException("Hubo un error con la base de datos");
			
		}
		catch (Exception e) {
			System.out.println("Error al buscar la campa?a");
			throw new InstanceException();
		}
	}

	@Override
	public Ciudadano buscar(String dni) throws AppException, InstanceException {
		Ciudadano ciu=null;
		Connection conn = ConnectionManager.getConnection();
		
		try {
		PreparedStatement statement = conn
				.prepareStatement("SELECT * FROM ciudadanos c left join usuarios u on (c.id_usuario=u.id_usuario)" + 
						"left join roles r on(r.id_rol=u.id_rol) where c.dni=?");
		
		statement.setString(1, dni);
		ResultSet rs = statement.executeQuery();
		
		while(rs.next()) {
			Usuario usu =null;
			if(rs.getString("u.id_usuario")!=null) {
				
				Rol rol= new Rol(rs.getInt("r.id_rol"),rs.getString("r.nombre"));
				 if(rs.getString("r.estado").equals("ACTIVO")) {
						rol.activar();
				 }
				 else {
						rol.desactivar();
				 }
				 usu = new Usuario(rs.getString("u.usuario"), rs.getString("u.contrasena"), rs.getString("u.nombre"), rs.getString("u.email"), rol);
				 usu.editarId(rs.getInt("u.id_usuario"));
			}
			ciu=new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"), rs.getString("c.dni"), usu);
			ciu.editarId(rs.getInt("c.id_ciudadano"));
			ciu.editarPuntaje(rs.getDouble("c.puntaje"));
		}
		
	  
	    
	    
		}
		catch (SQLException sq){
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
		
		return ciu;
	}

	@Override
	public Ciudadano buscarPorVivienda(Integer idVivienda) throws AppException, InstanceException {
		Ciudadano ciu=null;
		Connection conn = ConnectionManager.getConnection();
		
		try {
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM viviendas v JOIN ciudadanos c "
							+ "ON (c.id_ciudadano=v.id_ciudadano) "
							+ "LEFT JOIN usuarios u on(u.id_usuario=c.id_usuario) "
							+ "LEFT JOIN roles r on(r.id_rol=u.id_rol)"
							+ "WHERE v.id_vivienda=?");
			
			statement.setInt(1, idVivienda);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Usuario usu =null;
				if(rs.getString("u.id_usuario")!=null) {
					
					Rol rol= new Rol(rs.getInt("r.id_rol"),rs.getString("r.nombre"));
					 if(rs.getString("r.estado").equals("ACTIVO")) {
							rol.activar();
					 }
					 else {
							rol.desactivar();
					 }
					 usu = new Usuario(rs.getString("u.usuario"), rs.getString("u.contrasena"), rs.getString("u.nombre"), rs.getString("u.email"), rol);
					 usu.editarId(rs.getInt("u.id_usuario"));
				}
				ciu=new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"), rs.getString("c.dni"), usu);
				ciu.editarPuntaje(rs.getDouble("c.puntaje"));
				ciu.editarId(rs.getInt("c.id_ciudadano"));
			}
		
		}
		catch (SQLException sq){
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
		
		return ciu;
	}
	
	
	@Override
	public List<Vivienda> listarMisViviendas(Ciudadano ciudadano) throws AppException, InstanceException {
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

		} catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		} finally {
			ConnectionManager.disconnect();
		}

		return viviendas;
	}

	@Override
	public Ciudadano buscar(Usuario usuario) {
		Ciudadano ciu=null;
		
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM ciudadanos c where c.id_usuario=?");
			
			statement.setInt(1, usuario.obtenerId());
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				ciu = new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"),rs.getString("c.dni"),  usuario);
				ciu.editarId(rs.getInt("c.id_ciudadano"));
				ciu.editarPuntaje(rs.getDouble("c.puntaje"));
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
	public List<Ciudadano> listarTodos() throws AppException, InstanceException {

		List<Ciudadano> ciudadanos=new ArrayList<Ciudadano>();
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM ciudadanos c left join "
							+ "usuarios u on (c.id_usuario=u.id_usuario) ");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Usuario usuario=null;
				if(rs.getString("u.usuario")!=null) {
					usuario = new Usuario(rs.getString("u.usuario"), rs.getString("u.contrasena"), rs.getString("u.nombre"), rs.getString("u.email"), new Rol());
				}
				
				Ciudadano ciu= new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"),rs.getString("c.dni"),  usuario);
				ciu.editarId(rs.getInt("c.id_ciudadano"));
				ciu.editarPuntaje(rs.getDouble("c.puntaje"));
				ciudadanos.add(ciu);
			}
	 
		}
		catch (SQLException sq){
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
		
		return ciudadanos;
	}

	@Override
	public void actualizar(Ciudadano ciudadano) throws InstanceException, AppException  {
	
		
		try {
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
				.prepareStatement("UPDATE ciudadanos SET nombre=?, apellido=?, dni=?, id_usuario=?, puntaje=?"
						+ "WHERE id_ciudadano=?");
			statement.setString(1, ciudadano.obtenerNombre());
			statement.setString(2, ciudadano.obtenerApellido());
			statement.setString(3, ciudadano.obtenerDni());
			if(ciudadano.obtenerUsuario()!=null) {
				statement.setInt(4, ciudadano.obtenerUsuario().obtenerId());
			}
			else {
				statement.setNull(4, Types.NULL);
			}
			statement.setDouble(5, ciudadano.obtenerPuntaje());
			statement.setInt(6, ciudadano.obtenerId());
	
	
			int cantidad = statement.executeUpdate();
			if (cantidad==1) {
					System.out.println("El ciudadano se modific? correctamente.");
			}
	
		}
		catch (SQLException sq){
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

	

}
