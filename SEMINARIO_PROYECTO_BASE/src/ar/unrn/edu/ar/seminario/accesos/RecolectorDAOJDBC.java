package ar.unrn.edu.ar.seminario.accesos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.Statement;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;

public class RecolectorDAOJDBC implements RecolectorDao {

	@Override
	public void crear(Recolector recolector) throws AppException, InstanceException {
		
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO recolectores (nombre, apellido, dni, email)"
							+ "VALUES (?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, recolector.obtenerNombre());
			statement.setString(2, recolector.obtenerApellido());
			statement.setString(3, recolector.obtenerDni());
			statement.setString(4, recolector.obtenerEmail());
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El recolector se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("El recolector con dni: "+recolector.obtenerDni()+" ya existe");
		    }
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
		    miResult.close();
		    
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
	}

	@Override
	public List<Recolector> listarTodos() throws SintaxisSQLException, AppException, InstanceException {
		
		List<Recolector> recolectores = new ArrayList<Recolector>();

		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn.prepareStatement("select * from recolectores ");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
	
				Recolector recolector = new Recolector(rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"), rs.getString("email"));
				recolector.editarId(rs.getInt("id_recolector"));
				recolectores.add(recolector);
				
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
		
		return recolectores;
	}

	@Override
	public Recolector buscar(Integer id) throws AppException, InstanceException {
		Recolector recolector = null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from recolectores r where r.id_recolector=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				
				recolector = new Recolector(rs.getString("r.nombre"), rs.getString("r.apellido"), rs.getString("r.dni"),
						rs.getString("r.email"));
				recolector.editarId(rs.getInt("id_recolector"));
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

		return recolector;
	}

	@Override
	public void actualizar(Recolector recolector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Recolector recolector) {
		// TODO Auto-generated method stub
		
	}
}
