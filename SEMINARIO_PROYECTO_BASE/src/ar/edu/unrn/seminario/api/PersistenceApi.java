package ar.edu.unrn.seminario.api;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;
import ar.unrn.edu.ar.seminario.accesos.RolDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.RolDao;
import ar.unrn.edu.ar.seminario.accesos.UsuarioDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.UsuarioDao;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDao;

public class PersistenceApi implements IApi {
	private ViviendaDao viviendaDao;
	private RolDao rolDao;
	private UsuarioDao usuarioDao;

	public PersistenceApi() {
		viviendaDao = new ViviendaDAOJDBC();
		rolDao=new RolDAOJDBC();
		usuarioDao=new UsuarioDAOJDBC();
	}


	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol)
			throws DataEmptyException, NotNullException, SintaxisSQLException {
		
		
		Rol role = this.rolDao.obtenerRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);
		
		this.usuarioDao.crear(usuario);
		
	}


	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud,
			String nombreCiudadano, String apeCiudadano, String dniCiudadano)
			throws NotNullException, DataEmptyException, NumbersException, SintaxisSQLException, DuplicateUniqueKeyException {
		
		if(latitud.isEmpty() || longitud.isEmpty() || numero.isEmpty()) {
			throw new DataEmptyException("Faltan completar campos");
		}
		
		if(!numero.matches("[0-9]+")) {
			throw new NumbersException("El valor ingresado para el campo 'Número' no es numérico");
		}
		int nro = Integer.parseInt(numero);
		double lat=0;
		double longi=0;
		try {
			lat= Double.parseDouble(latitud);
			longi= Double.parseDouble(longitud);
		}
		catch(NumberFormatException e) {
			throw new NumbersException("La latitud y/o longitud ingresadas no son correctas");
		}
		
		
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
	public List<RolDTO> obtenerRoles() throws SintaxisSQLException {
		List<RolDTO> rolesDto=new ArrayList<RolDTO>();
		
		for(Rol r : rolDao.listarTodos()) {
			if(r.isActivo()) {
				rolesDto.add(new RolDTO(r.obtenerCodigo(), r.obtenerNombre(), "ACTIVO"));
			}
			else {
				rolesDto.add(new RolDTO(r.obtenerCodigo(),r.obtenerNombre(), "INACTIVO"));
			}
			
		}
		
		return rolesDto;
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
		List<Usuario> usuarios = this.usuarioDao.listarTodos();
		
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		
		for (Usuario u : usuarios) {
			usuariosDTO.add(new UsuarioDTO(u.obtenerUsuario(), u.obtenerContrasena(), u.obtenerNombre(), u.obtenerEmail(), u.obtenerNombreRol(), u.obtenerEstado()));
			
		}
		
		return usuariosDTO;
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
