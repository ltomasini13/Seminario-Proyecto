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
import jdk.nashorn.internal.runtime.ECMAException;

public class ViviendaDAOJDBC implements ViviendaDao {

	@Override
	public void crear(Vivienda vivienda) {
		
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO ciudadanos (nombre, apellido, dni)"
							+ "VALUES (?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, vivienda.obtenerNombreCiudadano());
			statement.setString(2, vivienda.obtenerApellidoCiudadano());
			statement.setString(3, vivienda.obtenerDniCiudadano());
			
			
			int cantidad = statement.executeUpdate();
			if (cantidad==1) 
				System.out.println("El ciudadano se creo correctamente.");
			
			
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
			

		    int cantidad2 = statement2.executeUpdate();
		    if(cantidad2==1) {
				System.out.println("La vivienda se creo correctamente");
			}
		  
			
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");

			
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		
		} finally {
			ConnectionManager.disconnect();
		}
		
	}

	
	@Override
	public void crearParaRecic(Vivienda vivienda) {
		
		try {

			Connection conn = ConnectionManager.getConnection();
		    
		    PreparedStatement statement2 = conn
					.prepareStatement("INSERT INTO viviendas (id_ciudadano, calle, numero, barrio, latitud, longitud)"
							+ "VALUES (?, ?, ?, ?, ?, ?)");
		    statement2.setInt(1, vivienda.obtenerCiudadano().obtenerId());
		    statement2.setString(2, vivienda.obtenerUbicacionCalle());
		    statement2.setInt(3, vivienda.obtenerUbicacionNro());
		    statement2.setString(4, vivienda.obtenerUbicacionBarrio());
		    statement2.setDouble(5, vivienda.obtenerUbicacionLatitud());
		    statement2.setDouble(6, vivienda.obtenerUbicacionLongitud());
			
		    
		    int cantidad2 = statement2.executeUpdate();
		    if(cantidad2==1) {
				   System.out.println("La vivienda se creo correctamente");
			 }
		   
		  
		 	
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
		
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		} finally {
			ConnectionManager.disconnect();
		}
		
	}
	
	
	
	
	@Override
	public void actualizarYcrear(Vivienda vivienda) {
		try {

			Connection conn = ConnectionManager.getConnection();

			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO ciudadanos (nombre, apellido, dni)"
							+ "VALUES (?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setString(1, vivienda.obtenerNombreCiudadano());
			statement.setString(2, vivienda.obtenerApellidoCiudadano());
			statement.setString(3, vivienda.obtenerDniCiudadano());
			
			
			int cantidad = statement.executeUpdate();
			if (cantidad==1) 
				System.out.println("El ciudadano se agregó correctamente.");
			
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
		    int idCiudadano=miResult.getInt(1);
		    
		    //vivienda.obtenerCiudadano().editarId(Long.valueOf(idCiudadano));
		    miResult.close();
		    
		    
		    PreparedStatement statement2 = conn
					.prepareStatement("UPDATE viviendas set id_ciudadano=?, calle=?, numero=?, barrio=?, latitud=?, longitud=? where id_vivienda=?");
		    
		    statement2.setInt(1, idCiudadano);
		    statement2.setString(2, vivienda.obtenerUbicacionCalle());
		    statement2.setInt(3, vivienda.obtenerUbicacionNro());
		    statement2.setString(4, vivienda.obtenerUbicacionBarrio());
		    statement2.setDouble(5, vivienda.obtenerUbicacionLatitud());
		    statement2.setDouble(6, vivienda.obtenerUbicacionLongitud());
		    statement2.setInt(7, vivienda.obtenerId());
		    int cantidad2 = statement2.executeUpdate();
		    
		    if(cantidad2==1) {
				   System.out.println("La vivienda se modificó correctamente");
			 }
		   
		  
		 	
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
		
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		} finally {
			ConnectionManager.disconnect();
		}
		
	}
	
	
	@Override
	public void actualizar(Vivienda vivienda) {
		try {

			Connection conn = ConnectionManager.getConnection();
		    
		    PreparedStatement statement2 = conn
					.prepareStatement("UPDATE viviendas set id_ciudadano=?, calle=?, numero=?, barrio=?, latitud=?, longitud=? where id_vivienda=?");
		    
		    statement2.setInt(1, vivienda.obtenerIdCiudadano());
		    statement2.setString(2, vivienda.obtenerUbicacionCalle());
		    statement2.setInt(3, vivienda.obtenerUbicacionNro());
		    statement2.setString(4, vivienda.obtenerUbicacionBarrio());
		    statement2.setDouble(5, vivienda.obtenerUbicacionLatitud());
		    statement2.setDouble(6, vivienda.obtenerUbicacionLongitud());
		    statement2.setInt(7, vivienda.obtenerId());
		    int cantidad2 = statement2.executeUpdate();
		    
		    if(cantidad2==1) {
				   System.out.println("La vivienda se modificó correctamente");
			 }
		   
		  
		 	
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");
		
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		} finally {
			ConnectionManager.disconnect();
		}
		
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
				Ciudadano ciudadano = new Ciudadano(rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"), null);
				Ubicacion ubicacion = new Ubicacion(rs.getString("calle"), rs.getInt("numero"), rs.getString("barrio"), rs.getDouble("latitud"), rs.getDouble("longitud"));
				Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
				vivienda.editarId(rs.getInt("v.id_vivienda"));
				viviendas.add(vivienda);
				
			}
		
		   viviendasOrdenadasPorBarrio= viviendas.stream().sorted((v1, v2) -> 
		   v1.obtenerUbicacionBarrio().compareTo(v2.obtenerUbicacionBarrio())).collect(Collectors.toList());

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			
		} catch (Exception e) {
			System.out.println("Error al listar viviendas");
			
		} finally {
			ConnectionManager.disconnect();
			
		}
		
		return viviendasOrdenadasPorBarrio;

	}
	
	@Override
	public Vivienda buscar(Vivienda vivienda) {
		Vivienda viv =null;
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM viviendas v WHERE v.calle=? and v.numero=?");
		
			statement.setString(1, vivienda.obtenerUbicacionCalle());
			statement.setInt(2, vivienda.obtenerUbicacionNro());
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				viv=new Vivienda(ubicacion, new Ciudadano());
				viv.editarId(rs.getInt("v.id_vivienda"));
			}
		
		  

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
		} catch (Exception e) {
			System.out.println("Error al listar viviendas");
		} finally {
			ConnectionManager.disconnect();
			
		}
		
		return viv;
	}
	

	@Override
	public Vivienda buscar(Integer idVivienda) {
		Vivienda vivienda =null;
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM viviendas v JOIN ciudadanos c ON (v.id_ciudadano=c.id_ciudadano) WHERE v.id_vivienda=?");
		
			statement.setInt(1, idVivienda);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Ciudadano ciudadano = new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"), rs.getString("c.dni"), null);
				ciudadano.editarId(rs.getInt("c.id_ciudadano"));
				vivienda=new Vivienda(ubicacion, ciudadano);
				vivienda.editarId(idVivienda);
			}
		
		  

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al listar viv");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}
		
		return vivienda;
	}


	@Override
	public Vivienda buscarPorPedido(Integer idPedido) {
		Vivienda vivienda =null;
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM pedidos p JOIN viviendas v ON (v.id_vivienda=p.id_vivienda)"
							+ " JOIN ciudadanos c ON (c.id_ciudadano=v.id_ciudadano)"
							+ "WHERE p.id_pedido=?");
		
			statement.setInt(1, idPedido);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Ciudadano ciudadano = new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"), rs.getString("c.dni"), null);
				ciudadano.editarId(rs.getInt("c.id_ciudadano"));
				vivienda=new Vivienda(ubicacion, ciudadano);
				vivienda.editarId(rs.getInt("v.id_vivienda"));
			}
		
		  

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al listar viv");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}
		
		return vivienda;
	}


	
	
	
	
}
	

