package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.RegistroVivienda;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class MemoryApi implements IApi {

	private Map<Integer, Rol> roles = new HashMap<>();
	private Set<Usuario> usuarios = new HashSet();
	private Set<RegistroVivienda> viviendas = new HashSet();

	public MemoryApi() {

		// datos iniciales
		this.roles.put(1, new Rol(1, "ADMIN"));
		this.roles.put(2, new Rol(2, "COMUNIDAD"));
		this.roles.put(3, new Rol(3, "GOBIERNO"));
		inicializarUsuarios();
		inicializarViviendas();
	}

	

	private void inicializarUsuarios() {
		registrarUsuario("mcambarieri", "1234", "mcambarieri@unrn.edu.ar", "Mauro", 3);
		registrarUsuario("ldifabio", "1234", "ldifabio@unrn.edu.ar", "Lucas", 2);
		registrarUsuario("admin", "1234", "admin@unrn.edu.ar", "Admin", 1);

	}
	
	
	private void inicializarViviendas() {
		this.registrarVivienda("Pedro Bronzetti", 450, null,(float) 40.4532, (float)60.7645, "Laura", "Tomasini", "39354863");
		this.registrarVivienda("9 de julio", 222, null, (float)43.55, (float)54.6553, "Karen", "Ruiz", "39776583");
		this.registrarVivienda("Hilario Lagos", 433, null, (float)56.7, (float)76.6553, "Ana", "Rangnau", "38645363");
		
	}	

	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {

		Rol role = this.roles.get(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);
		this.usuarios.add(usuario);

	}

	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		List<UsuarioDTO> dtos = new ArrayList<>();
		for (Usuario u : this.usuarios) {
			dtos.add(new UsuarioDTO(u.getUsuario(), u.getContrasena(), u.getNombre(), u.getEmail(),
					u.getRol().getNombre(), u.isActivo(), u.obtenerEstado()));
		}
		return dtos;
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
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles.values()) {
			dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public List<RolDTO> obtenerRolesActivos() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles.values()) {
			if (r.isActivo())
				dtos.add(new RolDTO(r.getCodigo(), r.getNombre()));
		}
		return dtos;
	}

	@Override
	public void guardarRol(Integer codigo, String descripcion, boolean estado) {
		// TODO Auto-generated method stub
		Rol rol = new Rol(codigo, descripcion);
		this.roles.put(codigo, rol);
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
	public void activarUsuario(String usuario) throws StateException {
		for (Usuario u : usuarios) {
			if (u.getUsuario().equals(usuario))
				u.activar();
			//enviar mail
			//..
		}

	}

	@Override
	public void desactivarUsuario(String usuario) throws StateException {
		for (Usuario u : usuarios) {
			if (u.getUsuario().equals(usuario))
				u.desactivar();

		}
	}

	@Override
	public List<ViviendaDTO> obtenerViviendas() {
		List<ViviendaDTO> viviendasDTO=new ArrayList<ViviendaDTO>();
		for(RegistroVivienda viv : this.viviendas) {
			ViviendaDTO vivienda = new ViviendaDTO(viv.obtenerCalleVivienda(), viv.obtenerNroVivienda(), viv.obtenerBarrioVivienda(),
					viv.obtenerLatitudVivienda(), viv.obtenerLongitudVivienda(),viv.obtenerNombreApellidoCiudadano(), viv.obtenerFechaYhora());
			
			viviendasDTO.add(vivienda);
		}
		return viviendasDTO;
	}

	@Override
	public void registrarVivienda(String calle, int numero, String barrio, float latitud, float longitud,
			String nombreCiudadano, String apeCiudadano, String dniCiudadano) {
		
		Ubicacion ubicacion = new Ubicacion(calle,numero, barrio, latitud, longitud);
		Ciudadano ciudadano = new Ciudadano(nombreCiudadano, apeCiudadano, dniCiudadano);
		Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
		RegistroVivienda regVivienda = new RegistroVivienda(LocalDateTime.now(), vivienda); 
		
		viviendas.add(regVivienda);
		
	}

}
