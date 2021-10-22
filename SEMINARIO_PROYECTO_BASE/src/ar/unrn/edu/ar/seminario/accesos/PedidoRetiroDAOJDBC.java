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
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class PedidoRetiroDAOJDBC implements PedidoRetiroDao{

	@Override
	public void crear(PedidoRetiro pedido)  {
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO pedidos (fecha_pedido, carga_pesada, observacion, cantidad_kg, id_vivienda)"
							+ "VALUES (?, ?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			String cargaPesada=null;
			if(pedido.isCargaPesada()) {
				cargaPesada="SI";
			}
			else {
				cargaPesada="NO";
			}
			
			statement.setDate(1, pedido.obtenerFecha());
			statement.setString(2, cargaPesada);
			statement.setString(3, pedido.obtenerObservacion());
			statement.setDouble(4, pedido.obtenerCantidad());
			statement.setLong(5, pedido.obtenerVivienda().obtenerId());
			
			
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El pedido se creo correctamente.");
			
			
			
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();
		    Integer idPedido= miResult.getInt(1);
		    miResult.close();
		    
		    PreparedStatement statementResiduos = conn
					.prepareStatement("INSERT INTO residuos_a_retirar (cantidad_kg, id_tipo_residuo, id_pedido)"
							+ "VALUES (?, ?, ?)");
		    
		    
		    for (ResiduoARetirar r : pedido.obetenerResiduosARetirar()) {
		    	statementResiduos.setDouble(1, r.obtenerCantkg());
		    	statementResiduos.setInt(2, r.obtenerResiduo().obtenerId());
		    	statementResiduos.setInt(3, idPedido);
		    }
		}  catch (SQLException e) {
			
			System.out.println("Error al procesar la consulta");
			
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
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
