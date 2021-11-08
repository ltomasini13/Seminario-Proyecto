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
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Beneficio;
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
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El beneficio se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("El beneficio de tipo: "+beneficio.obtenerNombreBeneficio()+" ya existe");
		    }
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();

		    miResult.close(); 
		    
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				System.out.println("Error al procesar la consulta");
				throw new AppException("No se pudo crear el beneficio por un error en la Base de Datos");
			}
			
		
		} catch (DuplicateUniqueKeyException e) {
			System.out.println(e.getMessage());;
			
		} finally {
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
				throw new AppException("No se pudo crear el beneficio por un error en la Base de Datos");
			}
			System.out.println("Error al procesar la consulta");
			
		}
		finally {
			ConnectionManager.disconnect();
		}
		return beneficios;
	}

	@Override
	public Beneficio buscar(String benefificio) {
		// TODO Auto-generated method stub
		return null;
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

}
