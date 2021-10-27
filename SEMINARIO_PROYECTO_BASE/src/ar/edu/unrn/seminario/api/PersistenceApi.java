package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.exception.UnfinishedException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;
import ar.unrn.edu.ar.seminario.accesos.CiudadanoDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.CiudadanoDao;
import ar.unrn.edu.ar.seminario.accesos.PedidoRetiroDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.PedidoRetiroDao;
import ar.unrn.edu.ar.seminario.accesos.RecolectorDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.RecolectorDao;
import ar.unrn.edu.ar.seminario.accesos.ResiduoDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.ResiduoDao;
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
	private CiudadanoDao ciudadanoDao;

	private Sesion sesion;

	private ResiduoDao residuoDao;
	private PedidoRetiroDao pedidoDao;
	private RecolectorDao recolectorDao;

	public PersistenceApi() {
		viviendaDao = new ViviendaDAOJDBC();
		rolDao=new RolDAOJDBC();
		usuarioDao=new UsuarioDAOJDBC();
		ciudadanoDao= new CiudadanoDAOJDBC();
		residuoDao= new ResiduoDAOJDBC();
		pedidoDao= new PedidoRetiroDAOJDBC();
		recolectorDao= new RecolectorDAOJDBC();
	}


	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol)
			throws DataEmptyException, NotNullException, SintaxisSQLException {
		
		
		Rol role = this.rolDao.obtenerRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);
		
		this.usuarioDao.crear(usuario);
		
	}

	@Override
	public void registrarCiudadano(String username, String password, String email, String nombre, Integer rol,
			String apellido, String dni) throws SintaxisSQLException, DataEmptyException, NotNullException, NumbersException, AuthenticationException {
		
		if(this.usuarioDao.buscar(username)!=null) {
			throw new AuthenticationException("El nombre de usuario '"+ username + "' ya esta en uso");
		}
		
		if(this.ciudadanoDao.buscar(dni)!=null) {
			throw new AuthenticationException("Existe un ciudadano registrado con el dni '"+dni+"'");
		}
		
		Rol rolCiudadano= rolDao.obtenerRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, rolCiudadano);
		Ciudadano ciudadano =new Ciudadano(nombre, apellido, dni, usuario);

		ciudadanoDao.crear(ciudadano);
		
	}
	
	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud,
			String nombreCiudadano, String apeCiudadano, String dniCiudadano)
			throws NotNullException, DataEmptyException, NumbersException, SintaxisSQLException, AuthenticationException {
		
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
		Ciudadano ciudadano = new Ciudadano(nombreCiudadano, apeCiudadano, dniCiudadano, null);
		Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
		//RegistroVivienda regVivienda = new RegistroVivienda(LocalDateTime.now(), vivienda); 
		Vivienda vivAutent = viviendaDao.buscar(vivienda);
		if(vivAutent!=null) {
			throw new AuthenticationException("Ya existe una vivienda con esa direccion");
		}
		
		Ciudadano ciuAutent = ciudadanoDao.buscar(ciudadano.obtenerDni());
		if(ciuAutent!=null) {
				throw new AuthenticationException("El ciudadano con dni: " +ciudadano.obtenerDni()+" ya existe");
			
		}
		viviendaDao.crear(vivienda);
		
	}
	
	
	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud) throws DataEmptyException, NumbersException, NotNullException, AuthenticationException {
		if(latitud.isEmpty() || longitud.isEmpty() || numero.isEmpty()) {
			throw new DataEmptyException("Faltan completar campos");
		}
		
		if(!numero.matches("[0-9]+")) {
			throw new NumbersException("El valor ingresado para el campo 'Número' no es numérico");
		}
		Integer nro = Integer.parseInt(numero);
		double lat=0;
		double longi=0;
		try {
			lat= Double.parseDouble(latitud);
			longi= Double.parseDouble(longitud);
		}
		catch(NumberFormatException e) {
			throw new NumbersException("La latitud y/o longitud ingresadas no son correctas");
		}
		
		
		Ubicacion ubicacion = new Ubicacion(calle,nro, barrio, lat,longi);
		Ciudadano ciudadano = ciudadanoDao.buscar(sesion.obtenerUsuario());
		Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
		//RegistroVivienda regVivienda = new RegistroVivienda(LocalDateTime.now(), vivienda); 
		
		Vivienda vivAutent = viviendaDao.buscar(vivienda);
		if(vivAutent!=null) {
			throw new AuthenticationException("Ya existe una vivienda con esa direccion");
		}
		viviendaDao.crearParaRecic(vivienda);
	}


	@Override
	public UsuarioDTO obtenerUsuario(String username) throws SintaxisSQLException, NotNullException, DataEmptyException {
		Usuario usuario = this.usuarioDao.buscar(username);
		UsuarioDTO usuarioDTO = null;
		
		if(usuario!=null) {
			Rol rol = this.rolDao.obtenerRol(usuario.obtenerCodigoRol());
			usuarioDTO=new UsuarioDTO(usuario.obtenerUsuario(), usuario.obtenerContrasena(), usuario.obtenerNombre(),
					usuario.obtenerEmail(),rol.obtenerNombre(), usuario.obtenerEstado());
		}
		
		return usuarioDTO;
		
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
	public List<ViviendaDTO> obtenerViviendas() throws EmptyListException  {
		List<ViviendaDTO> viviendasDTO=new ArrayList<ViviendaDTO>();
		List<Vivienda> viviendas;
		if(this.esUsuarioAdmin()) {
			viviendas=viviendaDao.listarTodas();
			if(viviendas.isEmpty()) {
				throw new EmptyListException("No hay ninguna vivienda registrada en el sistema");
			}
			for(Vivienda v : viviendas ) {
				viviendasDTO.add(new ViviendaDTO(v.obtenerId(), v.obtenerUbicacionCalle(), v.obtenerUbicacionNro(), v.obtenerUbicacionBarrio(),
						v.obtenerUbicacionLatitud(), v.obtenerUbicacionLongitud(), v.obtenerNombreCiudadano()+v.obtenerApellidoCiudadano(), null));
			}
		}
		
		
		if(this.esUsuarioReciclador()) {
			Ciudadano ciu=this.ciudadanoDao.buscar(sesion.obtenerUsuario());
			viviendas=this.ciudadanoDao.listarMisViviendas(ciu);
			
			if(viviendas.isEmpty()) {
				throw new EmptyListException("No tiene ninguna vivienda registrada");
			}
			
			for(Vivienda v : viviendas) {
				viviendasDTO.add(new ViviendaDTO(v.obtenerId(), v.obtenerUbicacionCalle(), v.obtenerUbicacionNro(), v.obtenerUbicacionBarrio(), 
						v.obtenerUbicacionLatitud(), v.obtenerUbicacionLongitud(), v.obtenerNombreCiudadano()+v.obtenerApellidoCiudadano(), null));
			}
		}
		
		return viviendasDTO;
	}


	
	@Override
	public void loguearUsuario(String username, String contrasena) throws SintaxisSQLException, AuthenticationException, NotNullException, DataEmptyException {
		if(username==null || contrasena==null) {
			throw new NotNullException("Los campos de usuario o contraseña son nulos");
		}
		
		if(username.isEmpty() || contrasena.isEmpty()) {
			throw new DataEmptyException ("Los campos de usuario o contraseña son vacios");
		}
		
		Usuario usuario = this.usuarioDao.buscar(username);
		
		if(usuario==null) {
			throw new AuthenticationException("Usuario y/o contraseña");
		}
		else {
			if(!contrasena.equals(usuario.obtenerContrasena())) {
				throw new AuthenticationException("Usuario y/o contraseña");
			}
		}
		
		sesion=new Sesion(usuario);
	}
	
	
	public boolean esUsuarioAdmin() {
		if(sesion.tipoDeUsuario().equals("ADMIN")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	public boolean esUsuarioReciclador() {
		if(sesion.tipoDeUsuario().equals("RECICLADOR")) {
			return true;
		}
		else {
			return false;
		}
	}


	


	@Override
	public void registrarResiduo(String tipo, String numero) throws NumbersException, NotNullException, DataEmptyException, DuplicateUniqueKeyException, SintaxisSQLException {
		
		if(!numero.matches("[0-9]+")) {
			throw new NumbersException("El valor ingresado para el campo 'Puntos' no es numérico");
		}
		int nro = Integer.parseInt(numero);
		
		TipoResiduo residuo = new TipoResiduo(tipo, nro);
		
		residuoDao.crear(residuo);
		
	}


	@Override
	public void registrarPedidoRetiro(String fechaEmision, String cargaPesada, String observacion) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<PedidoRetiroDTO> obtenerPedidos() throws EmptyListException {
		List<PedidoRetiroDTO> pedidosDTO=new ArrayList<PedidoRetiroDTO>();
		List<PedidoRetiro> pedidos = new ArrayList<PedidoRetiro>();
		if(this.esUsuarioAdmin()) {
			pedidos= this.pedidoDao.listarTodos();
			if(pedidos.isEmpty()) {
				throw new EmptyListException("No hay ningun pedido en el sistema");
			}
			for(PedidoRetiro p : pedidos) {
				
				try {
					pedidosDTO.add(new PedidoRetiroDTO(p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(), p.obtenerVivienda().obtenerUbicacionCalle(),
							p.obtenerVivienda().obtenerUbicacionNro(), p.obtenerVivienda().obtenerUbicacionBarrio(), p.obtenerVivienda().obtenerUbicacionLatitud(),
							p.obtenerVivienda().obtenerUbicacionLongitud(), p.obtenerNombreCiudadanoVivienda(), p.obtenerApellidoCiudadanoVivienda()));
				} catch (NotNullException e) {
					System.out.println("Error en constructor Pedido de retiro DTO");
				}
			}
		}
		
		if(this.esUsuarioReciclador()) {
			pedidos=this.pedidoDao.buscarPorUsuario(sesion.obtenerUsuario().obtenerId());
			if(pedidos.isEmpty()) {
				throw new EmptyListException("Aun no realizó ningun pedido");
			}
			for(PedidoRetiro p : pedidos) {
				try {
					pedidosDTO.add(new PedidoRetiroDTO(p.obtenerId(), p.obtenerFechaEmision(), p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(), p.obtenerVivienda().obtenerUbicacionCalle(),
							p.obtenerVivienda().obtenerUbicacionNro(), p.obtenerVivienda().obtenerUbicacionBarrio(), p.obtenerVivienda().obtenerUbicacionLatitud(),
							p.obtenerVivienda().obtenerUbicacionLongitud(), p.obtenerNombreCiudadanoVivienda(), p.obtenerApellidoCiudadanoVivienda()));
				} catch (NotNullException e) {
					System.out.println("Error en constructor Pedido de retiro DTO");
				}
			}
		}
		
		return pedidosDTO;
		
	}



	@Override
	public List<ResiduoDTO> obtenerResiduos()  {
		List<ResiduoDTO> residuosDTO=new ArrayList<ResiduoDTO>();
		
		try {
			for(TipoResiduo residuo : residuoDao.listarTodos()) {
				try {
					residuosDTO.add(new ResiduoDTO(residuo.obtenerId(),residuo.obtenerTipo(), residuo.obtenerPunto()));
				} catch (NotNullException | DataEmptyException | NumbersException e) {
					
				}
			}
		} catch (SintaxisSQLException e) {
			
		}
		
		return residuosDTO;
	}


	@Override
	public void cerrarSesion() {
		this.sesion=null;	
	}


	@Override
	public void generarPedido(Integer id_vivienda, boolean cargaPesada, String observacion,
			List<ResiduoARetirarDTO> residuosARetirarDTO) throws NotNullException {
		
		List<ResiduoARetirar> residuosARetirar = new ArrayList<ResiduoARetirar>();
		Vivienda vivienda = viviendaDao.buscar(id_vivienda);
		
		for(ResiduoARetirarDTO r : residuosARetirarDTO) {
			TipoResiduo tipoResiduo = residuoDao.buscar(r.obetenerTipoResiduo());
			
				residuosARetirar.add(new ResiduoARetirar(tipoResiduo, r.obetenerCantidad()));
		}
		
		PedidoRetiro pedidoRetiro = new PedidoRetiro(LocalDateTime.now().toString(), cargaPesada, observacion, vivienda, residuosARetirar);
		
		pedidoDao.crear(pedidoRetiro);
		
	}


	@Override
	public void pedidoPendiente(Integer id_vivienda) throws UnfinishedException {
		
		List<PedidoRetiro> pedidos = pedidoDao.buscar(id_vivienda);
		if(!pedidos.isEmpty()) {
			for(PedidoRetiro p : pedidos) {
				if(p.obtenerFechaCumplimiento()==null) {
					throw new UnfinishedException("La vivienda tiene un pedido que todavía no concluyó");
				}
			}
		}
		
		
	}

	public void registrarRecolector(String nombre, String apellido, String dni, String email)
			throws NotNullException, DataEmptyException, DuplicateUniqueKeyException, SintaxisSQLException, NumbersException {
		
		Recolector recolector = new Recolector(nombre, apellido, dni, email);
		
		this.recolectorDao.crear(recolector);
		
	}


	
	
	@Override
	public List<RecolectorDTO> obtenerRecolectores() throws SintaxisSQLException {
		List<RecolectorDTO> recolectoresDTO=new ArrayList<RecolectorDTO>();
		
		for(Recolector r: recolectorDao.listarTodos()) {
			recolectoresDTO.add(new RecolectorDTO(r.obtenerNombre(), r.obtenerApellido(), r.obtenerDni(), r.obtenerEmail()));
		}
		
		return recolectoresDTO;
	}


	@Override
	public void generarOrden(String estado, Integer id_pedido) {
		// TODO Auto-generated method stub
		
	}	
	
}
