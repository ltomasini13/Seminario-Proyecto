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
				Rol rol = new Rol(rs.getString("nombre"));
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

}
