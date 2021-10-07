package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Rol;

public class RolDAOJDBC implements RolDao{

	@Override
	public void crear(Rol rol) {
		
	}

	@Override
	public List<Rol> listarTodos() throws SintaxisSQLException {
		List<Rol> roles = new ArrayList<>();
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM roles");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Rol rol=new Rol();
				rol.editarNombre(rs.getString("nombre"));
				rol.editarCodigo(rs.getInt("id_rol"));
				if(rs.getString("estado_rol").equals("ACTIVO")) {
					rol.setActivo(true);
				}
				else {
					rol.setActivo(true);
				}
				roles.add(rol);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
			throw new SintaxisSQLException("No se pudo crear la vivienda por un error en la Base de Datos");
			
		}catch (Exception e) { 
				
		}
		finally {
			ConnectionManager.disconnect();
		}
		return roles;
		
	}
	
	public Rol obtenerRol(Integer codigo) throws SintaxisSQLException {
		Rol rol=new Rol();
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM roles");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				if(rs.getInt("id_rol")==codigo) {
					rol.editarNombre(rs.getString("nombre"));
					rol.editarCodigo(rs.getInt("id_rol"));
					if(rs.getString("estado_rol").equals("ACTIVO")) {
						rol.setActivo(true);
					}
					else {
						rol.setActivo(true);
					}	
				}
				
				
				
			}
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
			throw new SintaxisSQLException("No se pudo crear la vivienda por un error en la Base de Datos");
			
		}catch (Exception e) { 
				
		}
		finally {
			ConnectionManager.disconnect();
		}
		
		return rol;
	}
	

}
