package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Rol;

public class RolDAOJDBC implements RolDao{

	@Override
	public void crear(Rol rol) {
		
	}

	@Override
	public List<Rol> listarTodos() throws AppException, InstanceException  {
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
				if(rs.getString("estado").equals("ACTIVO")) {
					rol.setActivo(true);
				}
				else {
					rol.setActivo(true);
				}
				roles.add(rol);
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
		return roles;
		
	}
	
	public Rol obtenerRol(Integer codigo) throws AppException, InstanceException {
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
					if(rs.getString("estado").equals("ACTIVO")) {
						rol.setActivo(true);
					}
					else {
						rol.setActivo(true);
					}	
				}
				
				
				
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
		
		return rol;
	}
	

}
