package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;
import ar.edu.unrn.seminario.modelo.Visita;

public class VisitaDAOJDBC implements VisitaDao{

	@Override
	public void crear(Visita visita) {
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statementInsertVisita = conn
					.prepareStatement("INSERT INTO visitas (fecha_visita, observacion, id_orden)"
							+ "VALUES (?, ?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statementInsertVisita.setTimestamp(1, Timestamp.valueOf(visita.obtenerFecha()));
			statementInsertVisita.setString(2, visita.obtenerObservacion());
			statementInsertVisita.setInt(3, visita.obtenerOrden().obtenerId());
			int cantVisitasInsertadas = statementInsertVisita.executeUpdate();
			if (cantVisitasInsertadas==1) 
				System.out.println("La visita se creo correctamente.");
			
			
			ResultSet miResult = statementInsertVisita.getGeneratedKeys();
			miResult.next();
		    int idVisita=miResult.getInt(1);
		    
		    //vivienda.obtenerCiudadano().editarId(Long.valueOf(idCiudadano));
		    miResult.close();
		    
		    PreparedStatement statementInsertResiduosRetirados = conn
					.prepareStatement("INSERT INTO residuos_retirados (cantidad_kg, id_tipo_residuo, id_visita)"
							+ "VALUES (?, ?, ?)");
		    
		    for(ResiduoRetirado res : visita.obtenerResiduosRetirados()) {
		    	 statementInsertResiduosRetirados.setDouble(1, res.obtenerCantkg());
				 statementInsertResiduosRetirados.setInt(2,res.obtenerResiduo().obtenerId());
				 statementInsertResiduosRetirados.setInt(3, idVisita);
				 
				 int cantResiduosInsertados = statementInsertResiduosRetirados.executeUpdate();
				    if(cantResiduosInsertados==1) {
						System.out.println("Residuo retirado agregado correctamente");
					}
		    }
		   
			

		    PreparedStatement statementEditarOrden = conn
					.prepareStatement("UPDATE ordenes SET estado = ? WHERE id_orden=?");
		    statementEditarOrden.setString(1, visita.obtenerOrden().obtenerEstado());
		    statementEditarOrden.setInt(2, visita.obtenerOrden().obtenerId());
		    
		    int cantidadOrdenesModificadas=  statementEditarOrden.executeUpdate();
		    if (cantidadOrdenesModificadas==1) 
				System.out.println("La orden se modificó correctamente");
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");

			
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		
		} finally {
			ConnectionManager.disconnect();
		}
		
		
	}

	
	
	
	
	@Override
	//Devuelve las visitas que tiene esa orden de retiro pasada por parametros
	public List<Visita> listarTodas(OrdenDeRetiro orden) {   
		List<Visita> visitas = new ArrayList<Visita>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statementSelectVisitas = conn
					.prepareStatement("SELECT * FROM visitas v WHERE v.id_orden=?");
			
			statementSelectVisitas.setInt(1, orden.obtenerId());
			
			ResultSet rs = statementSelectVisitas.executeQuery();
			
			while(rs.next()) {
				visitas.add(new Visita(rs.getTimestamp("v.fecha_visita").toLocalDateTime().toString(), rs.getString("v.observacion"), orden));
			}
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");

			
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		
		} finally {
			ConnectionManager.disconnect();
		}
		return visitas;
	}
	
	@Override
	public Visita buscar(Integer idVisita) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	@Override
	public List<Visita> listarTodas() {
		List<Visita> visitas = new ArrayList<Visita>();
		
		try {

			Connection conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statementSelectVisitas = conn
					.prepareStatement("SELECT * FROM visitas v join ordenes o"
							+ " on (v.id_orden=o.id_orden)");
		
			ResultSet rs = statementSelectVisitas.executeQuery();
			
			while(rs.next()) {
				OrdenDeRetiro orden = new OrdenDeRetiro(rs.getTimestamp("o.fecha_orden").toLocalDateTime().toString(), new PedidoRetiro());
				orden.editarId(rs.getInt("o.id_orden"));
				Visita visita = new Visita(rs.getTimestamp("v.fecha_visita").toLocalDateTime().toString(), rs.getString("v.observacion"), orden);
				visita.editarId(rs.getInt("v.id_visita"));
				visitas.add(visita);
			}
			
		} catch (SQLException e) {
			System.out.println("Error al procesar la consulta");

			
		} catch (Exception e) {
			System.out.println("Error en la base de datos");
		
		} finally {
			ConnectionManager.disconnect();
		}
		return visitas;
	}

}
