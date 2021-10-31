package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class OrdenDeRetiroDAOJDBC implements OrdenDeRetiroDao {

	@Override
	public void crear(OrdenDeRetiro orden) throws SintaxisSQLException {
try {

			
			Connection conn = ConnectionManager.getConnection();
			ResultSet miResult;
			
			if(orden.tieneRecolector()) {
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO ordenes (fecha_orden, estado, id_pedido, id_recolector)"
								+ "VALUES (?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
	
				statement.setTimestamp(1, Timestamp.valueOf(orden.obtenerFecha()));
				statement.setString(2, orden.obtenerEstado());
				statement.setInt(3, orden.obtenerPedido().obtenerId());
				statement.setInt(4, orden.obtenerRecolector().obtenerId());
			
				int cantidad = statement.executeUpdate();
					if (cantidad==1) 
						System.out.println("La orden se creo correctamente");
			
				miResult = statement.getGeneratedKeys();
				miResult.next();
				orden.editarId(miResult.getInt(1));
			}
			else {
				PreparedStatement statement = conn
					.prepareStatement("INSERT INTO ordenes (fecha_orden, estado, id_pedido, id_recolector)"
							+ "VALUES (?, ?, ?, ?)");
				
				statement.setTimestamp(1, Timestamp.valueOf(orden.obtenerFecha()));
				statement.setString(2, orden.obtenerEstado());
				statement.setInt(3, orden.obtenerPedido().obtenerId());
				
				int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("La orden se creo correctamente");
		
				miResult = statement.getGeneratedKeys();
				miResult.next();
				orden.editarId(miResult.getInt(1));
			}
			
			miResult.close();
		}  catch (SQLException e) {
			
			System.out.println("Error al procesar la consulta");
			
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		} finally {
			ConnectionManager.disconnect();
		}
		
	}

	@Override
	public List<OrdenDeRetiro> listarTodos() throws SintaxisSQLException {
		List<OrdenDeRetiro> ordenes=new ArrayList<OrdenDeRetiro>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from ordenes o join pedidos p on (p.id_pedido=o.id_pedido)"
							+ "join recolectores r on (o.id_recolector=r.id_recolector) ");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				
				Recolector recolector = new Recolector(rs.getString("r.nombre"), rs.getString("r.apellido"), rs.getString("r.dni"), rs.getString("r.email"));
				recolector.editarId(rs.getInt("r.id_recolector"));
				Ciudadano ciudadano = new Ciudadano(rs.getString("nombre"), rs.getString("apellido"), rs.getString("dni"), null);
				Ubicacion ubicacion = new Ubicacion(rs.getString("calle"), rs.getInt("numero"), rs.getString("barrio"), rs.getDouble("latitud"), rs.getDouble("longitud"));
				Vivienda viv= new Vivienda(ubicacion, ciudadano);
				PedidoRetiro pedido = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(),cargaPesada, rs.getString("p.observacion"), viv);
				pedido.editarFechaCumplimiento(rs.getTimestamp("p.fecha_cumplimiento"));
				OrdenDeRetiro orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), rs.getString("o.estado"), pedido, recolector);
				
				orden.editarId(rs.getInt("o.id_orden"));
				ordenes.add(orden);
				
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
		return ordenes;
	}

	@Override
	public OrdenDeRetiro buscar(Integer id) {
		OrdenDeRetiro orden=null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from ordenes o join pedidos p on(p.id_pedido=o.id_pedido)"
							+ " join recolectores r on (o.id_recolector=r.id_recolector) where o.id_orden=?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				PedidoRetiro pedidoRetiro = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada,
						rs.getString("p.observacion"), null);
				Recolector recolector = new Recolector(rs.getString("r.nombre"), rs.getString("r.apellido"), rs.getString("r.dni"),
						rs.getString("r.email"));
				orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), rs.getString("o.estado"), pedidoRetiro, recolector);
				orden.editarId(rs.getInt("o.id_orden"));
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
		return orden;
		
	}

	@Override
	public void actualizar(OrdenDeRetiro orden) {
		Connection conn = ConnectionManager.getConnection();
		
		try {
		PreparedStatement statement = conn
				.prepareStatement("UPDATE ordenes" + 
						"SET fecha_orden=?, estado=?, id_pedido=?, id_recolector=?" + 
						"WHERE id_orden=?");
		
		statement.setTimestamp(1,  Timestamp.valueOf(orden.obtenerFecha()));
		statement.setString(2, orden.obtenerEstado());
		statement.setInt(3, orden.obtenerPedido().obtenerId());
		statement.setInt(3, orden.obtenerRecolector().obtenerId());
		
		
	
		int cantidad = statement.executeUpdate();
		if (cantidad==1) {
				System.out.println("La orden se modificó correctamente");
		}
	
	    
	
		}
		catch (SQLException sq){
			//throw new SintaxisSQLException("Hubo un error con la base de datos");
			System.out.println("Error al procesar consulta");
			
		}
		catch(Exception e) {
			System.out.println("Error en la base de datos");
		}
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(OrdenDeRetiro orden) {
		// TODO Auto-generated method stub
		
	}

}
