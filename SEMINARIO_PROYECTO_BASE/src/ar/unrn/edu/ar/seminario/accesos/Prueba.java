package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;

import ar.edu.unrn.seminario.exception.EmptyListException;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;

import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.ResiduoRestante;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class Prueba {


	public static void main(String[] args) throws SintaxisSQLException, AuthenticationException, NotNullException, DataEmptyException, EmptyListException, DuplicateUniqueKeyException, NumbersException {


//		try {
//
//			Connection conn = ConnectionManager.getConnection();
//			PreparedStatement statement = conn
//					.prepareStatement("SELECT * FROM ciudadanos");
//			
//			ResultSet rs = statement.executeQuery();
//			if(rs.next()) {
//				System.out.println("Nombre y apellido: "+rs.getString("nombre")+" "+rs.getString("apellido")+"Dni: "+rs.getString("dni"));
//			}
//
////			statement.setString(1, usuario.getUsuario());
////			statement.setString(2, usuario.getContrasena());
////			statement.setString(3, usuario.getNombre());
////			statement.setString(4, usuario.getEmail());
////			statement.setBoolean(5, usuario.isActivo());
////			statement.setInt(6, usuario.getRol().getCodigo());
////			int cantidad = statement.executeUpdate();
////			if (cantidad > 0) {
////				// System.out.println("Modificando " + cantidad + " registros");
////			} else {
////				System.out.println("Error al actualizar");
////				// TODO: disparar Exception propia
////			}
//
//		} catch (SQLException e) {
//			System.out.println("Error al procesar consulta");
//			// TODO: disparar Exception propia
//		} catch (Exception e) {
//			System.out.println("Error al insertar un usuario");
//			// TODO: disparar Exception propia
//		} finally {
//			ConnectionManager.disconnect();
//		}
		
		
		
		PersistenceApi api = new PersistenceApi();
	
		List<ResiduoRetirado > listResRetirados= new ArrayList<>();
		listResRetirados.add(new ResiduoRetirado(new TipoResiduo("PLASTICO", 150), 5));
		listResRetirados.add(new ResiduoRetirado(new TipoResiduo("METAL", 200), 8));
		listResRetirados.add(new ResiduoRetirado(new TipoResiduo("CARTON", 100), 9));
		
		
		List<ResiduoARetirar> listResARetirar = new ArrayList<>();
		listResARetirar.add(new ResiduoARetirar(new TipoResiduo("PLASTICO", 150), 6));
		listResARetirar.add(new ResiduoARetirar(new TipoResiduo("CARTON", 100), 7));
		listResARetirar.add(new ResiduoARetirar(new TipoResiduo("METAL", 200), 6));
		

		for(ResiduoRetirado resRetirado : listResRetirados) {
			if(!listResARetirar.contains(resRetirado)) {
				System.out.println("NO ESTAN TODOS DECLARADOS");
			}
			
		}
	}
	
	
	


}
