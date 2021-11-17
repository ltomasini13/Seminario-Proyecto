package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Visita;

public class ResiduosDAOJDBC  implements ResiduosDao{

	@Override
	public List<ResiduoARetirar> listarTodosResiduosARetirar() {
		List<ResiduoARetirar> residuosARetirar = new ArrayList<ResiduoARetirar>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from  residuos_a_retirar rr  join residuos r on (rr.id_tipo_residuo=r.id_residuo)");
		
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

	@Override
	public List<ResiduoRetirado> listarTodosResiduosRetirados() {
		List<ResiduoRetirado> residuosRetirados = new ArrayList<ResiduoRetirado>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select * from residuos_retirados rr join residuos r on (rr.id_tipo_residuo=r.id_residuo)"
							+ "join visitas v on (rr.id_visita=v.id_visita)"
							+ "join ordenes o on (v.id_orden=o.id_orden)");
		
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				TipoResiduo tipoResiduo  = new TipoResiduo(rs.getString("r.tipo"), rs.getInt("r.puntos"));
				ResiduoRetirado residuoRetirado = new ResiduoRetirado(tipoResiduo, rs.getDouble("rr.cantidad_kg"));
				OrdenDeRetiro orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), new PedidoRetiro());
				Visita visita = new Visita(rs.getTimestamp("v.fecha_visita").toLocalDateTime().toString(), rs.getString("v.observacion"), orden);
				residuoRetirado.editarVisita(visita);
				residuosRetirados.add(residuoRetirado);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			
		} catch (Exception e) {
			System.out.println("Error al buscar vivienda");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return residuosRetirados;
	}

	@Override
	public List<ResiduoRetirado> buscarResiduosRetiradosEnTotal(Integer idOrden) { //cuanto de plastico se retiro, cuanto de carton, etc., para esa orden
		List<ResiduoRetirado> residuosRestantes= new ArrayList<ResiduoRetirado>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("select r.tipo, r.puntos, sum(rr.cantidad_kg) as total from residuos_retirados rr"
							+ " join residuos r on (rr.id_tipo_residuo=r.id_residuo)"
							+ " join visitas v on(v.id_visita=rr.id_visita)"
							+ " join ordenes o on (o.id_orden=v.id_orden)"
							+ " where o.id_orden=?"
							+ " group by r.tipo");
		
			statement.setInt(1, idOrden);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				TipoResiduo tipoResiduo  = new TipoResiduo(rs.getString("r.tipo"), rs.getInt("r.puntos"));
				ResiduoRetirado residuoRestante = new ResiduoRetirado(tipoResiduo, rs.getDouble("total"));
				residuosRestantes.add(residuoRestante);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al proc consulta");
			
		} catch (Exception e) {
			System.out.println("Error al buscar los residuos retirados en total");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return residuosRestantes;
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
			System.out.println("Error al buscar residuos");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return residuosARetirar;
	}

	@Override
	public List<ResiduoRetirado> buscarResiduosRetirados(Integer idVisita) {
		List<ResiduoRetirado> residuosRetirados = new ArrayList<ResiduoRetirado>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM residuos_retirados rr JOIN residuos r ON (rr.id_tipo_residuo=r.id_residuo)"
							+ "JOIN visitas v ON (rr.id_visita=v.id_visita)"
							+ "WHERE v.id_visita=?");
			statement.setInt(1, idVisita);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				TipoResiduo tipoResiduo  = new TipoResiduo(rs.getString("r.tipo"), rs.getInt("r.puntos"));
				ResiduoRetirado residuoRetirado = new ResiduoRetirado(tipoResiduo, rs.getDouble("rr.cantidad_kg"));
				Visita visita = new Visita(rs.getTimestamp("v.fecha_visita").toLocalDateTime().toString(), rs.getString("v.observacion"), new OrdenDeRetiro());
				visita.editarId(rs.getInt("v.id_visita"));
				residuoRetirado.editarVisita(visita);
				residuoRetirado.editarId(rs.getInt("rr.id_residuo_retirado"));
				residuosRetirados.add(residuoRetirado);
			}
		

		} catch (SQLException e) {
			
			System.out.println("Error al procesar consulta");
			
		} catch (Exception e) {
			System.out.println("Error al buscar vivienda");
		
		} finally {
			ConnectionManager.disconnect();
			
		}
		return residuosRetirados;
	}
	

}
