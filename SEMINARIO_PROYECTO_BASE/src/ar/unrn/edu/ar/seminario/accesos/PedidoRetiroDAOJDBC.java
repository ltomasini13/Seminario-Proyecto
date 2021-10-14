package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class PedidoRetiroDAOJDBC implements PedidoRetiroDao{

	@Override
	public void crear(PedidoRetiro pedido) throws DuplicateUniqueKeyException, SintaxisSQLException {
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO pedidos (fecha_pedido, carga_pesada, observacion, cantidad_kg, id_vivienda)"
							+ "VALUES (?, ?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			
			statement.setDate(1, pedido.obtenerFecha());
			statement.setBoolean(2, pedido.isCargaPesada());
			statement.setString(3, pedido.obtenerObservacion());
			statement.setDouble(4, pedido.obtenerCantidad());
			statement.setLong(5, pedido.obtenerVivienda().obtenerId());
			
			try {
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El ciudadano se creo correctamente.");
			}
			catch(MySQLIntegrityConstraintViolationException e){
		    	throw new DuplicateUniqueKeyException("El pedido ya existe");
		    }
			
			
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
		    
		    miResult.close();
		    
		    
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
	public List<PedidoRetiro> listarTodos() {
		List<PedidoRetiro> pedidos=new ArrayList<PedidoRetiro>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from pedidos p join viviendas v on (p.id_vivienda=v.id_vivienda");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				Ciudadano ciudadano = new Ciudadano(rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"), null);
				Ubicacion ubicacion = new Ubicacion(rs.getString("calle"), rs.getInt("numero"), rs.getString("barrio"), rs.getDouble("latitud"), rs.getDouble("longitud"));
				Vivienda viv= new Vivienda(ubicacion, ciudadano);
				PedidoRetiro pedido = new PedidoRetiro(rs.getString("fecha_pedido"),rs.getBoolean("carga_pesada"), rs.getString("observacion"), viv);
				
				pedidos.add(pedido);
				
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al listar viviendas");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}
		return pedidos;
	}

}
