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

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class OrdenDeRetiroDAOJDBC implements OrdenDeRetiroDao {

	@Override
	public void crear(OrdenDeRetiro orden) throws AppException, InstanceException {
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
			
		} catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}
		
	}

	@Override
	public List<OrdenDeRetiro> listarTodos() throws AppException, InstanceException  {
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
		}

			catch (SQLException sq){
				System.out.println("Error al procesar consulta");	
				throw new AppException("Hubo un error en la base de datos");
			}
			catch (Exception e) {
				System.out.print("Error en la bd");
				throw new InstanceException();
			}
			finally {
				ConnectionManager.disconnect();
			}
		return ordenes;
	}

	@Override

	public OrdenDeRetiro buscar(Integer idOrden) throws AppException, InstanceException {

		OrdenDeRetiro orden=null;
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from ordenes o join pedidos p on (p.id_pedido=o.id_pedido)"
							+ " join viviendas v on(p.id_vivienda=v.id_vivienda)"
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
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Vivienda vivienda = new Vivienda(ubicacion, new Ciudadano());
				vivienda.editarId(rs.getInt("v.id_vivienda"));
				PedidoRetiro pedidoRetiro = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada,
						rs.getString("p.observacion"), vivienda);
				pedidoRetiro.editarId(rs.getInt("p.id_pedido"));
				
			
				

				orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), pedidoRetiro);
				orden.asignarRecolector(recolector);
				orden.editarEstado(rs.getString("o.estado"));
				orden.editarId(rs.getInt("o.id_orden"));
			}
		

		}catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}
		return orden;
		
	}

	public List<OrdenDeRetiro> buscarPedido(Integer idPedido) throws AppException, InstanceException {
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
				pedido.editarId(rs.getInt("p.id_pedido"));
				OrdenDeRetiro orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), pedido);
				orden.editarId(rs.getInt("o.id_orden"));
				ordenes.add(orden);
			}
		

		} catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}
		return ordenes;
	}
	@Override
	public void actualizar(OrdenDeRetiro orden) throws AppException, InstanceException {
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
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
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
	public OrdenDeRetiro buscarPorVisita(Integer idVisita) throws AppException, InstanceException {
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
		

		}catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}
		return orden;
	}

	@Override
	public OrdenDeRetiro buscarOrdenPorPedido(PedidoRetiro pedido) throws AppException, InstanceException {
		OrdenDeRetiro orden=null;
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM ordenes o JOIN pedidos p on (p.id_pedido=o.id_pedido) WHERE p.id_pedido=?");
			
			statement.setInt(1, pedido.obtenerId());
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				
				PedidoRetiro pedidoDeRetiro = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada,
						rs.getString("p.observacion"), new Vivienda());
				pedidoDeRetiro.editarId(rs.getInt("p.id_pedido"));
				orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), pedidoDeRetiro);
				orden.editarId(rs.getInt("o.id_orden"));
			}
		

		} catch (SQLException sq){
			System.out.println("Error al procesar consulta");	
			throw new AppException("Hubo un error en la base de datos");
		}
		catch (Exception e) {
			System.out.print("Error en la bd");
			throw new InstanceException();
		}
		finally {
			ConnectionManager.disconnect();
		}
		return orden;
	}
	
}
