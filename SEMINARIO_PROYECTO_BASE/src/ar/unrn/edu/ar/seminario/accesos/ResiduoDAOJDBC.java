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
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.TipoResiduo;

public class ResiduoDAOJDBC implements ResiduoDao{

	@Override
	public void crear(TipoResiduo residuo) throws DuplicateUniqueKeyException, SintaxisSQLException {
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO residuos (tipo, puntos)"
							+ "VALUES (?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, residuo.obtenerTipo());
			statement.setInt(2, residuo.obtenerPunto());
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El residuo se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("El residuo de tipo: "+residuo.obtenerTipo()+" ya existe");
		    }
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();

		    miResult.close(); 
		    
		    
		} catch (DuplicateUniqueKeyException e) {
			throw new DuplicateUniqueKeyException(e.getMessage());
			
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
			throw new SintaxisSQLException("No se pudo crear el residuo por un error en la Base de Datos");
			
		
		} finally {
			ConnectionManager.disconnect();
		}
		
	
	}

	@Override
	public List<TipoResiduo> listarTodos() {
		List<TipoResiduo> residuos = new ArrayList<>();
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM residuos");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				TipoResiduo residuo=new TipoResiduo(rs.getString("tipo"), rs.getInt("puntos"));
				residuo.editarId(rs.getInt("id_residuo"));
				residuos.add(residuo);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
			
		}catch (Exception e) { 
				
		}
		finally {
			ConnectionManager.disconnect();
		}
		return residuos;
	}

	@Override
	public TipoResiduo buscar(String tipoResiduo) {
			TipoResiduo residuo = null;
			try {

				Connection conn = ConnectionManager.getConnection();
				PreparedStatement statement = conn
						.prepareStatement("SELECT * FROM residuos r WHERE r.tipo=?");
				statement.setString(1, tipoResiduo);
				
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					residuo=new TipoResiduo(rs.getString("r.tipo"), rs.getInt("r.puntos"));
					residuo.editarId(rs.getInt("r.id_residuo"));
				
				}
				
			} catch (SQLException e) {
				System.out.println("Error al procesar la consulta");
				
			}catch (Exception e) { 
					
			}
			finally {
				ConnectionManager.disconnect();
			}
			return residuo;
	}

	@Override
	public void actualizar(TipoResiduo residuo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(TipoResiduo residuo) {
		// TODO Auto-generated method stub
		
	}

}
