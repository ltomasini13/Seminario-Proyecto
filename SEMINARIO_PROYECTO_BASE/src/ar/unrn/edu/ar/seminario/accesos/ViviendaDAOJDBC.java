package ar.unrn.edu.ar.seminario.accesos;


import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class ViviendaDAOJDBC implements ViviendaDao {

	@Override
	public void crear(Vivienda vivienda) throws SintaxisSQLException, DuplicateUniqueKeyException {
		
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO ciudadanos (nombre, apellido, dni)"
							+ "VALUES (?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, vivienda.obtenerNombreCiudadano());
			statement.setString(2, vivienda.obtenerApellidoCiudadano());
			statement.setString(3, vivienda.obtenerDniCiudadano());
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El ciudadano se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("El ciudadano con dni: "+vivienda.obtenerDniCiudadano()+" ya existe");
		    }
			
			
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
		    int idCiudadano=miResult.getInt(1);
		    
		    //vivienda.obtenerCiudadano().editarId(Long.valueOf(idCiudadano));
		    miResult.close();
		    
		    PreparedStatement statement2 = conn
					.prepareStatement("INSERT INTO viviendas (id_ciudadano, calle, numero, barrio, latitud, longitud)"
							+ "VALUES (?, ?, ?, ?, ?, ?)");
		    statement2.setInt(1, idCiudadano);
		    statement2.setString(2, vivienda.obtenerUbicacionCalle());
		    statement2.setInt(3, vivienda.obtenerUbicacionNro());
		    statement2.setString(4, vivienda.obtenerUbicacionBarrio());
		    statement2.setDouble(5, vivienda.obtenerUbicacionLatitud());
		    statement2.setDouble(6, vivienda.obtenerUbicacionLongitud());
			
		    try {
		    	int cantidad2 = statement2.executeUpdate();
		    	 if(cantidad2==1) {
				    	System.out.println("La vivienda se creo correctamente");
				  }
		    }
		    catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("La vivienda con dicha direccion ya existe");
		    }
		   
		   
		    
		    
		} catch (DuplicateUniqueKeyException e) {
			throw new DuplicateUniqueKeyException(e.getMessage());
			
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
			throw new SintaxisSQLException("No se pudo crear la vivienda por un error en la Base de Datos");
			
		
		} finally {
			ConnectionManager.disconnect();
		}
		
	}

	@Override
	public void modificar(Vivienda vivienda) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Vivienda> listarTodas() {

		List<Vivienda> viviendas=new ArrayList<Vivienda>();
		 List<Vivienda> viviendasOrdenadasPorBarrio=new ArrayList<Vivienda>();
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from viviendas v join ciudadanos c on (v.id_ciudadano=c.id_ciudadano)");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
//				System.out.println("Nombre y apellido: "+rs.getString("nombre")+" "+rs.getString("apellido")+"Dni: "+rs.getString("dni"));
				Ciudadano ciudadano = new Ciudadano(rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"));
				Ubicacion ubicacion = new Ubicacion(rs.getString("calle"), rs.getInt("numero"), rs.getString("barrio"), rs.getDouble("latitud"), rs.getDouble("longitud"));
				viviendas.add(new Vivienda(ubicacion, ciudadano));
			}
		
		   viviendasOrdenadasPorBarrio= viviendas.stream().sorted((v1, v2) -> 
		   v1.obtenerUbicacionBarrio().compareTo(v2.obtenerUbicacionBarrio())).collect(Collectors.toList());

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al listar viviendas");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			return viviendasOrdenadasPorBarrio;
		}
		
		

	}
	
	
	
	
}
	

