package ar.unrn.edu.ar.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Beneficio;
import ar.edu.unrn.seminario.modelo.Canje;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;

public class CanjeDAOJDBC implements CanjeDao{

	private Connection conn;
	
	@Override
	public void crear(Canje canje) throws AppException {
		try {

			conn = ConnectionManager.getConnection();
		
			
			PreparedStatement statement = conn
					.prepareStatement("INSERT INTO canjes (fecha_canje, puntos_canjeados, id_beneficio, id_ciudadano)"
							+ "VALUES (?, ?)",  Statement.RETURN_GENERATED_KEYS);
			
			statement.setTimestamp(1, Timestamp.valueOf(canje.obtenerFechaCanje()));
			statement.setDouble(2, canje.obtenerPuntaje());
			statement.setInt(3, canje.obtenerBeneficio().obtenerId());
			statement.setInt(4, canje.obtenerCiudadano().obtenerId());
			
			
			int cantidad = statement.executeUpdate();
			if (cantidad==1) 
				System.out.println("El canje se creo correctamente.");
			
			ResultSet miResult = statement.getGeneratedKeys();
			miResult.next();

		    miResult.close(); 
		    
		} catch (SQLException e1) {
			System.out.println("Error al procesar la consulta");
			throw new AppException("No se pudo crear el canje por un error en la Base de Datos");
		} finally {
			ConnectionManager.disconnect();
		}
		
		
	}

	@Override
	public List<Canje> listarTodos() throws DataEmptyException, NotNullException, NumbersException, AppException {
		List<Canje> canjes = new ArrayList<Canje>();
		try {

			conn = ConnectionManager.getConnection();
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM canjes c join beneficios b on (c.id_beneficio=b.id_beneficio) "
							+ "join ciudadanos ciu on (c.id_ciudadano=ciu.id_ciudadano)");
			
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				Beneficio beneficio=new Beneficio(rs.getString("b.nombre_beneficio"), rs.getInt("b.puntos"));
				Usuario usu =null;
				if(rs.getString("u.id_usuario")!=null) {	
					Rol rol= new Rol(rs.getInt("r.id_rol"),rs.getString("r.nombre"));
					 if(rs.getString("r.estado").equals("ACTIVO")) {
							rol.activar();
					 }
					 else {
							rol.desactivar();
					 }
					 usu = new Usuario(rs.getString("u.usuario"), rs.getString("u.contrasena"), rs.getString("u.nombre"), rs.getString("u.email"), rol);
					 usu.editarId(rs.getInt("u.id_usuario"));
				}
				Ciudadano ciu=new Ciudadano(rs.getString("ciu.nombre"), rs.getString("ciu.apellido"), rs.getString("ciu.dni"), usu);
				Canje canje = new Canje(rs.getTimestamp("c.fecha_canje").toLocalDateTime().toString(), rs.getDouble("c.puntos_canjeados"), beneficio, ciu);
				canjes.add(canje);
			}
			
		} catch (SQLException e) {
				System.out.println("Error al procesar la consulta");
				throw new AppException("No se pudo listar los canjes por un error en la Base de Datos");
			
		}
		finally {
			ConnectionManager.disconnect();
		}
		return canjes;
	}

	@Override
	public Canje buscar(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actualizar(Canje canje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void eliminar(Canje canje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Canje> listarMisCanjes(Ciudadano ciudadano) {
		// TODO Auto-generated method stub
		return null;
	}

}
