package ar.unrn.edu.ar.seminario.accesos;

import java.util.ArrayList;
import java.util.List;
import ar.unrn.edu.ar.seminario.accesos.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;

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
			if (cantidad<1) {
				System.out.println("Error al actualizar");
				// TODO: disparar Exception propia
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
			
		    int cantidad2 = statement2.executeUpdate();
		    if(cantidad2<1) {
		    	//disparar excepcion
		    }
		    System.out.println("Se agrego correctamente la vivienda");
		    
		    

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al insertar un usuario");
			// TODO: disparar Exception propia
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
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from viviendas v join ciudadanos c on (v.id_ciudadano=c.id_ciudadano)");
			
			ResultSet rs = statement.executeQuery();
			
			if(rs.next()) {
//				System.out.println("Nombre y apellido: "+rs.getString("nombre")+" "+rs.getString("apellido")+"Dni: "+rs.getString("dni"));
				Ciudadano ciudadano = new Ciudadano(rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"));
				Ubicacion ubicacion = new Ubicacion(rs.getString("calle"), rs.getInt("numero"), rs.getString("barrio"), rs.getDouble("latitud"), rs.getDouble("longitud"));
				viviendas.add(new Vivienda(ubicacion, ciudadano));
			}

		    
		    
		    

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al insertar un usuario");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			return viviendas;
		}
		
		

	}
	
	
}
	

