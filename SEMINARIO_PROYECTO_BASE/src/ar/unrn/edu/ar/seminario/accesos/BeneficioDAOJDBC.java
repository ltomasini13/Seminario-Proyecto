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
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Beneficio;
import ar.edu.unrn.seminario.modelo.Campaña;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.TipoResiduo;

public class BeneficioDAOJDBC implements BeneficioDao {

	private Connection conn;
	
	@Override
	public void crear(Beneficio beneficio) throws AppException {
		
		try {

			conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO beneficios (nombre_beneficio, puntos)"
							+ "VALUES (?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, beneficio.obtenerNombreBeneficio());
			statement.setInt(2, beneficio.obtenerPuntos());
			
			int cantidad = statement.executeUpdate();
			if (cantidad==1) 
				System.out.println("El beneficio se creo correctamente.");
			
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();

		    miResult.close(); 
		    
		} catch (SQLException e1) {
			System.out.println("Error al procesar la consulta");
			throw new AppException("No se pudo crear el beneficio por un error en la Base de Datos");
		}
		finally {
			ConnectionManager.disconnect();
		}
		
	}

	@Override
	public List<Beneficio> listarTodos() throws AppException, DataEmptyException, NotNullException, NumbersException {
		List<Beneficio> beneficios = new ArrayList<Beneficio>();
		try {

			conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM beneficios");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Beneficio beneficio=new Beneficio(rs.getString("nombre_beneficio"), rs.getInt("puntos"));
				beneficio.editarId(rs.getInt("id_beneficio"));
				beneficios.add(beneficio);
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
		return beneficios;
	}

	@Override
	public Beneficio buscar(Integer id) {
		Beneficio beneficio = null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from beneficios b where b.id_beneficio=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				
				beneficio = new Beneficio(rs.getString("b.nombre_beneficio"), rs.getInt("b.puntos"));
				beneficio.editarId(rs.getInt("id_beneficio"));
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al buscar el beneficio");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}

		return beneficio;
	}
	

	@Override
	public void actualizar(Beneficio beneficio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Beneficio beneficio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Beneficio> ListarCatalogo(Campaña camp) throws NotNullException, DataEmptyException, DateException, NumbersException, AppException {
		List<Beneficio> catalogo = new ArrayList<Beneficio>();
		try {

			conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM catalogos c join campañas camp on (c.id_campaña = camp.id_campaña) "
							+ "join beneficios b on (c.id_beneficio=b.id_beneficio) "
							+ "where camp.id_campaña=?" );
			statement.setInt(1, camp.obtenerId());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {

				Beneficio b = new Beneficio(rs.getString("b.nombre_beneficio"), rs.getInt("b.puntos"));
				catalogo.add(b);
			}
			
		} catch (SQLException e1) {
			System.out.println("Error al procesar la consulta");
			throw new AppException("No se pudo listar los catalogos por un error en la Base de Datos");
	
		}
		finally {
			ConnectionManager.disconnect();
		}
		return catalogo;
		
	}

	@Override
	public List<Beneficio> buscarNombreBeneficio(String nombre) throws AppException, DataEmptyException, NotNullException, NumbersException {
		List<Beneficio> catalogos = new ArrayList<Beneficio>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM catalogos c join campañas camp on (c.id_campaña = camp.id_campaña) " + 
							"join beneficios b on (c.id_beneficio=b.id_beneficio) " + 
							"where b.nombre_beneficio=?");
			statement.setString(1, nombre);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				
				Beneficio beneficio = new Beneficio(rs.getString("b.nombre_beneficio"), rs.getInt("b.puntos"));
				catalogos.add(beneficio);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			throw new AppException("No se pudo buscar el beneficio por un error en la base de datos");
		
		} finally {
			ConnectionManager.disconnect();
			
		}

		return catalogos;
	}

	
}
