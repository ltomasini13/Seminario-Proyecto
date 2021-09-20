package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.RegistroVivienda;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Vivienda;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDao;

public class PersistenceApi implements IApi {
	private ViviendaDao viviendaDao;
	

	public PersistenceApi() {
		viviendaDao = new ViviendaDAOJDBC();
		
	}


	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol)
			throws DataEmptyException, NotNullException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud,
			String nombreCiudadano, String apeCiudadano, String dniCiudadano)
			throws NotNullException, DataEmptyException, NumbersException {
		
		int nro = Integer.parseInt(numero);
		double lat= Double.parseDouble(latitud);
		double longi= Double.parseDouble(longitud);
		
		Ubicacion ubicacion = new Ubicacion(calle,nro, barrio, lat, longi);
		Ciudadano ciudadano = new Ciudadano(nombreCiudadano, apeCiudadano, dniCiudadano);
		Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
		//RegistroVivienda regVivienda = new RegistroVivienda(LocalDateTime.now(), vivienda); 
		
		viviendaDao.crear(vivienda);
		
	}


	@Override
	public UsuarioDTO obtenerUsuario(String username) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void eliminarUsuario(String username) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<RolDTO> obtenerRoles() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<RolDTO> obtenerRolesActivos() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void guardarRol(Integer codigo, String descripción, boolean estado)
			throws NotNullException, DataEmptyException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public RolDTO obtenerRolPorCodigo(Integer codigo) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void activarRol(Integer codigo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void desactivarRol(Integer codigo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void activarUsuario(String username) throws StateException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void desactivarUsuario(String username) throws StateException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<ViviendaDTO> obtenerViviendas() {
		
		List<ViviendaDTO> viviendasDTO=new ArrayList<ViviendaDTO>();
		
		for(Vivienda v : viviendaDao.listarTodas()) {
			viviendasDTO.add(new ViviendaDTO(v.obtenerUbicacionCalle(), v.obtenerUbicacionNro(), v.obtenerUbicacionBarrio(),
					v.obtenerUbicacionLatitud(), v.obtenerUbicacionLongitud(), v.obtenerNombreCiudadano()+v.obtenerApellidoCiudadano(), null));
		}
		
		return viviendasDTO;
	}
	
	
}
