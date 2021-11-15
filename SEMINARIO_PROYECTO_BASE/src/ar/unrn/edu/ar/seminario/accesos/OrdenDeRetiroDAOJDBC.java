package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.Type;

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
			
				PreparedStatement statement = conn
						.prepareStatement("INSERT INTO ordenes (fecha_orden, estado, id_pedido)"
								+ "values (?, ?, ?)");
	
				statement.setTimestamp(1, Timestamp.valueOf(orden.obtenerFecha()));
				statement.setString(2, orden.obtenerEstado());
				statement.setInt(3, orden.obtenerPedido().obtenerId());
			
				int cantidad = statement.executeUpdate();
					if (cantidad==1) 
						System.out.println("La orden se creo correctamente");
			
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
					.prepareStatement("select * from ordenes o join pedidos p on (p.id_pedido=o.id_pedido) "
							+ "left join recolectores r on(r.id_recolector=o.id_recolector)");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				Recolector recolector =null;
				if(rs.getString("r.dni")!=null) {
					recolector= new Recolector(rs.getString("r.nombre"), rs.getString("r.apellido"), rs.getString("r.dni"), rs.getString("r.email"));
					recolector.editarId(rs.getInt("r.id_recolector"));
				}
				PedidoRetiro pedido = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(),cargaPesada, rs.getString("p.observacion"), new Vivienda());
				pedido.editarFechaCumplimiento(rs.getTimestamp("p.fecha_cumplimiento"));
				pedido.editarId(rs.getInt("p.id_pedido"));
				OrdenDeRetiro orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), pedido);
				orden.asignarRecolector(recolector);
				orden.editarId(rs.getInt("o.id_orden"));
				orden.editarEstado(rs.getString("o.estado"));
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

	public OrdenDeRetiro buscar(Integer idOrden) {

		OrdenDeRetiro orden=null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from ordenes o join pedidos p on (p.id_pedido=o.id_pedido)"
							+ " left join recolectores r on(r.id_recolector=o.id_recolector) where o.id_orden=?");
			statement.setInt(1, idOrden);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				
				Recolector recolector =null;
				if(rs.getString("r.dni")!=null) {
					recolector= new Recolector(rs.getString("r.nombre"), rs.getString("r.apellido"), rs.getString("r.dni"), rs.getString("r.email"));
					recolector.editarId(rs.getInt("r.id_recolector"));
				}
				
				PedidoRetiro pedidoRetiro = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada,
						rs.getString("p.observacion"), new Vivienda());
				pedidoRetiro.editarId(rs.getInt("p.id_pedido"));
				
			
				

				orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), pedidoRetiro);
				orden.asignarRecolector(recolector);
				orden.editarEstado(rs.getString("o.estado"));
				orden.editarId(rs.getInt("o.id_orden"));
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al buscar ordenn");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}
		return orden;
		
	}

	public List<OrdenDeRetiro> buscarPedido(Integer idPedido) {
		List<OrdenDeRetiro> ordenes=new ArrayList<OrdenDeRetiro>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from ordenes o join pedidos p on (o.id_pedido=p.id_pedido)"
							+ "where p.id_pedido=?");
			
			statement.setInt(1, idPedido);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				
				PedidoRetiro pedido = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada,
						rs.getString("p.observacion"), new Vivienda());
				OrdenDeRetiro orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), pedido);
				orden.editarId(rs.getInt("o.id_pedido"));
				ordenes.add(orden);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			
		} catch (Exception e) {
			System.out.println("Error al buscar vivienda");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return ordenes;
	}
	@Override
	public void actualizar(OrdenDeRetiro orden) {
		Connection conn = ConnectionManager.getConnection();
		
		try {
		PreparedStatement statement = conn
				.prepareStatement("UPDATE ordenes SET fecha_orden=?, estado=?, id_pedido=?, id_recolector=? WHERE id_orden=?");
		
		statement.setTimestamp(1,  Timestamp.valueOf(orden.obtenerFecha()));
		statement.setString(2, orden.obtenerEstado());
		statement.setInt(3, orden.obtenerPedido().obtenerId());
		if(orden.obtenerRecolector()!=null) {
			statement.setInt(4, orden.obtenerRecolector().obtenerId());
		}
		else {
			statement.setNull(4, Types.NULL);
		}
		statement.setInt(5, orden.obtenerId());
		
	
		int cantidad = statement.executeUpdate();
		if (cantidad==1) {
				System.out.println("La orden se modifico correctamente");
		}
	
	    
	
		}
		catch (SQLException sq){
			//throw new SintaxisSQLException("Hubo un error con la base de datos");
			System.out.println("Error al procesar consulta");
			
		}
		catch(Exception e) {
			System.out.println("Error en la base de dato");
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

	@Override
	public OrdenDeRetiro buscarPorVisita(Integer idVisita) {
		OrdenDeRetiro orden=null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from visitas v "
							+ "join ordenes o on (o.id_orden=v.id_orden)"
							+ " join pedidos p on(p.id_pedido=o.id_pedido)"
							+ " join recolectores r on (r.id_recolector=o.id_recolector)"
							+ " where v.id_visita=?");
			statement.setInt(1, idVisita);
			ResultSet rs = statement.executeQuery();
			
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				
				Recolector recolector =null;
				if(rs.getString("r.dni")!=null) {
					recolector= new Recolector(rs.getString("r.nombre"), rs.getString("r.apellido"), rs.getString("r.dni"), rs.getString("r.email"));
					recolector.editarId(rs.getInt("r.id_recolector"));
				}
				
				PedidoRetiro pedidoRetiro = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada,
						rs.getString("p.observacion"), new Vivienda());
				pedidoRetiro.editarId(rs.getInt("p.id_pedido"));
				
			
				

				orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), pedidoRetiro);
				orden.asignarRecolector(recolector);
				orden.editarEstado(rs.getString("o.estado"));
				orden.editarId(rs.getInt("o.id_orden"));
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al buscar ordenn");
			// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
			
		}
		return orden;
	}

}
