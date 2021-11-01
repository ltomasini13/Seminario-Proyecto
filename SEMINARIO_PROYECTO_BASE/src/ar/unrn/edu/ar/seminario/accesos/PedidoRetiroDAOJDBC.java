package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class PedidoRetiroDAOJDBC implements PedidoRetiroDao{

	@Override
	public void crear(PedidoRetiro pedido)  {
		
		try {

			
			Connection conn = ConnectionManager.getConnection();
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO pedidos (fecha_pedido, carga_pesada, observacion, id_vivienda)"
							+ "VALUES (?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			//.prepareStatement("INSERT INTO pedidos (fecha_pedido, carga_pesada, observacion, id_vivienda)"
				//	+ "VALUES (?, ?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			String cargaPesada=null;
			if(pedido.isCargaPesada()) {
				cargaPesada="SI";
			}
			else {
				cargaPesada="NO";
			}
	
			statement.setTimestamp(1, Timestamp.valueOf(pedido.obtenerFechaEmision()));
			statement.setString(2, cargaPesada);
			statement.setString(3, pedido.obtenerObservacion());
			statement.setInt(4, pedido.obtenerVivienda().obtenerId());
			
			
			int cantidad = statement.executeUpdate();
				if (cantidad==1) 
					System.out.println("El pedido se creo correctamente");
			
			
			
			
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
		    	statementResiduos.executeUpdate();
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
					.prepareStatement("select * from pedidos p join viviendas v on (p.id_vivienda=v.id_vivienda)"
							+ "join ciudadanos c on (c.id_ciudadano=v.id_ciudadano) ");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				Ciudadano ciudadano = new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"), rs.getString("c.dni"), null);
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"), rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Vivienda viv= new Vivienda(ubicacion, ciudadano);
				PedidoRetiro pedido = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(),cargaPesada, rs.getString("p.observacion"), viv);
				pedido.editarFechaCumplimiento(rs.getTimestamp("p.fecha_cumplimiento"));
				pedido.editarId(rs.getInt("p.id_pedido"));
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

	@Override
	public List<PedidoRetiro> buscar(Integer idVivienda) {
		List<PedidoRetiro> pedidos=new ArrayList<PedidoRetiro>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from pedidos p join viviendas v on (p.id_vivienda=v.id_vivienda) where v.id_vivienda=?");
			
			statement.setInt(1, idVivienda);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
//				
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Vivienda vivienda = new Vivienda(ubicacion, new Ciudadano());
				PedidoRetiro pedido = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada, rs.getString("p.observacion"), vivienda);
				pedido.editarFechaCumplimiento(rs.getTimestamp("p.fecha_cumplimiento"));
				pedido.editarId(rs.getInt("p.id_pedido"));
				pedidos.add(pedido);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			
		} catch (Exception e) {
			System.out.println("Error al buscar vivienda");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return pedidos;
	}

	@Override
	public List<PedidoRetiro> buscarPorUsuario(Integer idUsuario) {
List<PedidoRetiro> pedidos=new ArrayList<PedidoRetiro>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from usuarios u join ciudadanos c on (c.id_usuario=u.id_usuario)"
							+ "join viviendas v on(v.id_ciudadano=c.id_ciudadano)"
							+ "join pedidos p on (p.id_vivienda=v.id_vivienda) where u.id_usuario=? ");
			
			statement.setInt(1, idUsuario);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
//				
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Vivienda vivienda = new Vivienda(ubicacion, new Ciudadano());
				PedidoRetiro pedido = new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada, rs.getString("p.observacion"), vivienda);
				pedido.editarFechaCumplimiento(rs.getTimestamp("p.fecha_cumplimiento"));
				pedido.editarId(rs.getInt("p.id_pedido"));
				pedidos.add(pedido);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			
		} catch (Exception e) {
			System.out.println("Error al buscar vivienda");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return pedidos;
	}

	@Override
	public List<ResiduoARetirar> buscarResiduosARetirar(Integer idPedido) {
		List<ResiduoARetirar> residuosARetirar = new ArrayList<ResiduoARetirar>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from  residuos_a_retirar rr  join residuos r on (rr.id_tipo_residuo=r.id_residuo) where rr.id_pedido=?");
			
			statement.setInt(1, idPedido);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				TipoResiduo tipoRes = new TipoResiduo(rs.getString("r.tipo"), rs.getInt("r.puntos"));
				ResiduoARetirar residuoARetirar = new ResiduoARetirar(tipoRes, rs.getDouble("rr.cantidad_kg"));
				residuoARetirar.editarId(rs.getInt("rr.id_residuo"));
				
				residuosARetirar.add(residuoARetirar);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			
		} catch (Exception e) {
			System.out.println("Error al buscar vivienda");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return residuosARetirar;
	}

	public PedidoRetiro buscarPedido(Integer idPedido) {
		PedidoRetiro pedido =null;
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM viviendas v JOIN ciudadanos c ON (v.id_ciudadano=c.id_ciudadano) WHERE v.id_vivienda=?");
		
			statement.setInt(1, idPedido);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				
				boolean cargaPesada=true;
				if(rs.getString("p.carga_pesada").equals("NO")) {
					cargaPesada=false;
				}
				
				Ubicacion ubicacion = new Ubicacion(rs.getString("v.calle"), rs.getInt("v.numero"), rs.getString("v.barrio"),
						rs.getDouble("v.latitud"), rs.getDouble("v.longitud"));
				Ciudadano ciudadano = new Ciudadano(rs.getString("c.nombre"), rs.getString("c.apellido"), rs.getString("c.dni"), null);
				Vivienda vivienda=new Vivienda(ubicacion, ciudadano);
				pedido=  new PedidoRetiro(rs.getTimestamp("p.fecha_pedido").toLocalDateTime().toString(), cargaPesada, rs.getString("p.observacion"), vivienda);
				pedido.editarFechaCumplimiento(rs.getTimestamp("p.fecha_cumplimiento"));
				pedido.editarId(idPedido);
			}
		}
		catch (SQLException e) {
			System.out.println("Error al procesar consulta");
		// TODO: disparar Exception propia
		} catch (Exception e) {
			System.out.println("Error al listar viviendas");
		// TODO: disparar Exception propia
		} finally {
			ConnectionManager.disconnect();
		}
		return pedido;
	}
}
		