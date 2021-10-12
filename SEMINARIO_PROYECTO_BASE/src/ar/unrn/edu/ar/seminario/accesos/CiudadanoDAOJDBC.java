package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	    int idUsuarios=miResult.getInt(1);
	    miResult.close();
	    
	    PreparedStatement statement2 = conn
				.prepareStatement("INSERT INTO ciudadanos c (c.nombre, c.apellido, c.dni, c.id_usuario) VALUES (?, ?, ?, ?)");
	    
	    statement2.setString(1, ciudadano.obtenerNombre());
	    statement2.setString(2, ciudadano.obtenerApellido());
	    statement2.setString(3, ciudadano.obtenerDni());
	    statement2.setInt(4, idUsuarios);
	    
		
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
	public Ciudadano buscar(String dni) throws NotNullException, DataEmptyException, NumbersException, SintaxisSQLException {
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
			throw new SintaxisSQLException("Hubo un error con la base de datos");
			
		}
		finally {
			ConnectionManager.disconnect();
		}
		
		return ciu;
	}

}
