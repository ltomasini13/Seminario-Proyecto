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
import ar.edu.unrn.seminario.modelo.Campaña;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
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
			
			int cantidad = statement.executeUpdate();
			if (cantidad==1) 
				System.out.println("La campaña se creo correctamente.");
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
			Integer idCampaña = miResult.getInt(1);
		
			camp.editarId(idCampaña);

		    miResult.close(); 
			
		    
		} catch (SQLException e) {
			
			System.out.println("Error al procesar la consulta");
			throw new AppException("No se pudo crear la campaña por un error en la Base de Datos");
			
		} finally {
			ConnectionManager.disconnect();
		}
		
		
	}

	@Override
	public List<Campaña> listarTodos() throws  DateException, NotNullException, DataEmptyException, AppException {
		List<Campaña> campañas = new ArrayList<Campaña>();
		try {

			conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM campañas");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Campaña camp=new Campaña(rs.getString("nombre_campaña"), rs.getTimestamp("fecha_inicio").toLocalDateTime().toString(),
						rs.getTimestamp("fecha_fin").toLocalDateTime().toString(), rs.getString("descripcion"));
				camp.editarId(rs.getInt("id_campaña"));
				camp.editarFechaFin(rs.getTimestamp("fecha_fin").toLocalDateTime());
				campañas.add(camp);
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
		return campañas;
	}

	@Override
	public Campaña buscar(Integer id) {
		
		Campaña campaña = null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from campañas c where c.id_campaña=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				
				
				campaña = new Campaña(rs.getString("c.nombre_campaña"), rs.getTimestamp("c.fecha_inicio").toLocalDateTime().toString(),
							rs.getTimestamp("c.fecha_fin").toLocalDateTime().toString(), rs.getString("c.descripcion"));
				campaña.editarId(rs.getInt("id_campaña"));
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al buscar la campaña");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}

		return campaña;
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

	@Override
	public Campaña buscarCampaña() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizarCatalogo(Campaña camp) throws AppException {
		
		try {

			conn = ConnectionManager.getConnection();
		 
			
			PreparedStatement statement3 = conn
					.prepareStatement("INSERT INTO catalogos (id_beneficio, id_campaña)"
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
