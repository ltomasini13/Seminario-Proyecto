package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.modelo.Campaña;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class CampañaDAOJDBC implements CampañaDao {

	private Connection conn;
	
	@Override
	public void crear(Campaña camp) throws AppException {
		try {

			conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO campañas (nombre_campaña, fecha_inicio, fecha_fin, descripcion)"
							+ "VALUES (?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, camp.obtenerNombreCampaña());
			statement.setTimestamp(2, Timestamp.valueOf(camp.obtenerFechaInicio()));
			statement.setTimestamp(3, Timestamp.valueOf(camp.obtenerFechaFin()));
			statement.setString(4, camp.obtenerDescripcion());
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("La campaña se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("El beneficio de tipo: "+camp.obtenerNombreCampaña()+" ya existe");
		    }
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();

		    miResult.close(); 
		    
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Error al procesar la consulta");
				throw new AppException("No se pudo crear la campaña por un error en la Base de Datos");
			}
			
		
		} catch (DuplicateUniqueKeyException e) {
			System.out.println(e.getMessage());;
			
		} finally {
			ConnectionManager.disconnect();
		}
		
		
	}

	@Override
	public List<Campaña> listarTodos() throws AppException, DataEmptyException, NotNullException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Campaña buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Campaña camp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Campaña camp) {
		// TODO Auto-generated method stub
		
	}

}
