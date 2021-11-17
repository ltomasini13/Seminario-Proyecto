package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.modelo.Beneficio;
import ar.edu.unrn.seminario.modelo.Campa�a;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

public class Campa�aDAOJDBC implements Campa�aDao {

	private Connection conn;
	
	@Override
	public void crear(Campa�a camp) throws AppException {
		try {

			conn = ConnectionManager.getConnection();
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO campa�as (nombre_campa�a, fecha_inicio, fecha_fin, descripcion)"
							+ "VALUES (?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, camp.obtenerNombreCampa�a());
			statement.setTimestamp(2, Timestamp.valueOf(camp.obtenerFechaInicio()));
			statement.setTimestamp(3, Timestamp.valueOf(camp.obtenerFechaFin()));
			statement.setString(4, camp.obtenerDescripcion());
			
			int cantidad = statement.executeUpdate();
			if (cantidad==1) 
				System.out.println("La campa�a se creo correctamente.");
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
			Integer idCampa�a = miResult.getInt(1);
		
			camp.editarId(idCampa�a);

		    miResult.close(); 
			
		    
		} catch (SQLException e) {
			
			System.out.println("Error al procesar la consulta");
			throw new AppException("No se pudo crear la campa�a por un error en la Base de Datos");
			
		} finally {
			ConnectionManager.disconnect();
		}
		
		
	}

	@Override
	public List<Campa�a> listarTodos() throws  DateException, NotNullException, DataEmptyException, AppException {
		List<Campa�a> campa�as = new ArrayList<Campa�a>();
		try {

			conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM campa�as");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Campa�a camp=new Campa�a(rs.getString("nombre_campa�a"), rs.getTimestamp("fecha_inicio").toLocalDateTime().toString(),
						rs.getTimestamp("fecha_fin").toLocalDateTime().toString(), rs.getString("descripcion"));
				camp.editarId(rs.getInt("id_campa�a"));
				camp.editarFechaFin(rs.getTimestamp("fecha_fin").toLocalDateTime());
				campa�as.add(camp);
			}
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Error al procesar la consulta");
				throw new AppException("No se pudo listar los beneficios por un error en la Base de Datos");
			}
			System.out.println("Error al procesar la consulta");
			
		}
		finally {
			ConnectionManager.disconnect();
		}
		return campa�as;
	}

	@Override
	public Campa�a buscar(Integer id) {
		
		Campa�a campa�a = null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from campa�as c where c.id_campa�a=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				
				
				campa�a = new Campa�a(rs.getString("c.nombre_campa�a"), rs.getTimestamp("c.fecha_inicio").toLocalDateTime().toString(),
							rs.getTimestamp("c.fecha_fin").toLocalDateTime().toString(), rs.getString("c.descripcion"));
				campa�a.editarId(rs.getInt("id_campa�a"));
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al buscar la campa�a");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}

		return campa�a;
	}

	@Override
	public void actualizar(Campa�a camp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Campa�a camp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Campa�a buscarCampa�a() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarCatalogo(Campa�a camp) throws AppException {
		
		try {

			conn = ConnectionManager.getConnection();
		 
			
			PreparedStatement statement3 = conn
					.prepareStatement("INSERT INTO catalogos (id_beneficio, id_campa�a)"
							+ "VALUES (?, ?)");
			
			statement3.setInt(1, camp.obtenerUltimoBeneficioAgregado().obtenerId());
			statement3.setInt(2, camp.obtenerId());
			
		
			int cantidad3 = statement3.executeUpdate();
			if (cantidad3==1) 
				System.out.println("El beneficio se agrego correctamente.");
		    
	} catch (SQLException e) {
		
		System.out.println("Error al procesar consulta");
		throw new AppException("No se pudo crear el catalogo por un error de la base de datos");
	} finally {
		ConnectionManager.disconnect();
		
	}
	}

}
