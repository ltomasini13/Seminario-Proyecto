package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ar.edu.unrn.seminario.dto.CiudadanoDTO;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.ResiduoRetiradoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.VisitaDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.CollectorException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.exception.WasteException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.ResiduoRestante;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Visita;
import ar.edu.unrn.seminario.modelo.Vivienda;
import ar.unrn.edu.ar.seminario.accesos.CiudadanoDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.CiudadanoDao;
import ar.unrn.edu.ar.seminario.accesos.OrdenDeRetiroDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.OrdenDeRetiroDao;
import ar.unrn.edu.ar.seminario.accesos.PedidoRetiroDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.PedidoRetiroDao;
import ar.unrn.edu.ar.seminario.accesos.RecolectorDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.RecolectorDao;
import ar.unrn.edu.ar.seminario.accesos.ResiduoDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.ResiduoDao;
import ar.unrn.edu.ar.seminario.accesos.ResiduosDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.ResiduosDao;
import ar.unrn.edu.ar.seminario.accesos.RolDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.RolDao;
import ar.unrn.edu.ar.seminario.accesos.UsuarioDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.UsuarioDao;
import ar.unrn.edu.ar.seminario.accesos.VisitaDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.VisitaDao;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDao;

public class PersistenceApi implements IApi {
	private ViviendaDao viviendaDao;
	private RolDao rolDao;
	private UsuarioDao usuarioDao;
	private CiudadanoDao ciudadanoDao;
	private OrdenDeRetiroDao ordenDao;
	private VisitaDao visitaDao;
	private Sesion sesion;
	private ResiduoDao residuoDao; //hay que cambiarla por TipoDao
	private PedidoRetiroDao pedidoDao;
	private RecolectorDao recolectorDao;
	private ResiduosDao residuosDao;   //dao para trabajar con los residuos a retirar y residuos retirados

	public PersistenceApi() {
		viviendaDao = new ViviendaDAOJDBC();
		rolDao=new RolDAOJDBC();
		usuarioDao=new UsuarioDAOJDBC();
		ciudadanoDao= new CiudadanoDAOJDBC();
		residuoDao= new ResiduoDAOJDBC();
		pedidoDao= new PedidoRetiroDAOJDBC();
		recolectorDao= new RecolectorDAOJDBC();
		ordenDao = new OrdenDeRetiroDAOJDBC();
		visitaDao = new VisitaDAOJDBC();
		residuosDao= new ResiduosDAOJDBC();//dao para trabajar con los residuos a retirar y residuos retirados
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
			throw new NumbersException("El valor ingresado para el campo 'N�mero' no es num�rico");
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
			throw new NumbersException("El valor ingresado para el campo 'N�mero' no es num�rico");
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
	public void guardarRol(Integer codigo, String descripci�n, boolean estado)
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
						v.obtenerUbicacionLatitud(), v.obtenerUbicacionLongitud(), v.obtenerNombreCiudadano()+" "+v.obtenerApellidoCiudadano()));
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
						v.obtenerUbicacionLatitud(), v.obtenerUbicacionLongitud(), v.obtenerNombreCiudadano()+v.obtenerApellidoCiudadano()));
			}
		}
		
		return viviendasDTO;
	}

	@Override
	public ViviendaDTO obtenerVivienda(Integer idVivienda) {
		Vivienda viv = this.viviendaDao.buscar(idVivienda);
		return (new ViviendaDTO(viv.obtenerId(), viv.obtenerUbicacionCalle(), viv.obtenerUbicacionNro(), viv.obtenerUbicacionBarrio(),
				viv.obtenerUbicacionLatitud(), viv.obtenerUbicacionLongitud(), viv.obtenerNombreCiudadano()+" "+viv.obtenerApellidoCiudadano()));
	}
	
	@Override
	public void loguearUsuario(String username, String contrasena) throws SintaxisSQLException, AuthenticationException, NotNullException, DataEmptyException {
		if(username==null || contrasena==null) {
			throw new NotNullException("Los campos de usuario o contrase�a son nulos");
		}
		
		if(username.isEmpty() || contrasena.isEmpty()) {
			throw new DataEmptyException ("Los campos de usuario o contrase�a son vacios");
		}
		
		Usuario usuario = this.usuarioDao.buscar(username);
		
		if(usuario==null) {
			throw new AuthenticationException("Usuario y/o contrase�a");
		}
		else {
			if(!contrasena.equals(usuario.obtenerContrasena())) {
				throw new AuthenticationException("Usuario y/o contrase�a");
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
			throw new NumbersException("El valor ingresado para el campo 'Puntos' no es num�rico");
		}
		int nro = Integer.parseInt(numero);
		
		TipoResiduo residuo = new TipoResiduo(tipo, nro);
		
		residuoDao.crear(residuo);
		
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
				throw new EmptyListException("Aun no realiz� ningun pedido");
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
	public List<ResiduoARetirarDTO> obtenerResiduosARetirar(Integer idPedido) {
		List<ResiduoARetirarDTO> residuosARetirarDTO=new ArrayList<ResiduoARetirarDTO>();
		
		for(ResiduoARetirar res : this.pedidoDao.buscarResiduosARetirar(idPedido)) {
			residuosARetirarDTO.add(new ResiduoARetirarDTO(res.obtenerId(), res.obtenerTipoResiduo(), res.obtenerCantkg()));
		}
		
		return residuosARetirarDTO;
		
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
	public void pedidoPendiente(Integer id_vivienda) throws CreationValidationException {
		
		List<PedidoRetiro> pedidos = pedidoDao.buscar(id_vivienda);
		if(!pedidos.isEmpty()) {
			for(PedidoRetiro p : pedidos) {
				if(p.obtenerFechaCumplimiento()==null) {
					throw new CreationValidationException("La vivienda tiene un pedido que todav�a no concluy�");
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
			recolectoresDTO.add(new RecolectorDTO(r.obtenerId(), r.obtenerNombre(), r.obtenerApellido(), r.obtenerDni(), r.obtenerEmail()));
		}
		
		return recolectoresDTO;
	}
	
	@Override
	public void generarOrden(Integer idPedido) throws SintaxisSQLException, CreationValidationException {
		
		List<OrdenDeRetiro> ordenes = ordenDao.buscarPedido(idPedido);
		if(!ordenes.isEmpty()) {
			throw new CreationValidationException("El pedido ya tiene asignado una orden."); 
		}
		else {
			PedidoRetiro pedido = pedidoDao.buscarPedido(idPedido);
			OrdenDeRetiro orden = new OrdenDeRetiro(LocalDateTime.now().toString(), pedido);
			ordenDao.crear(orden);
		}
		
	}
	
	@Override
	public void ejecutarOrden(Integer idOrden) throws StateException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		orden.ejecutarOrden();
		this.ordenDao.actualizar(orden);
	}
	
	@Override
	public void cancelarOrden(Integer idOrden) throws StateException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		orden.cancelarOrden();
		this.ordenDao.actualizar(orden);
		
	}


	@Override
	public void finalizarOrden(Integer idOrden) throws StateException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		orden.finalizarOrden();
		this.ordenDao.actualizar(orden);
		
	}

	@Override
	public List<OrdenDeRetiroDTO> obtenerOrdenes() throws SintaxisSQLException {
		List<OrdenDeRetiroDTO> listaOrdenes = new ArrayList<OrdenDeRetiroDTO>();
		
		for(OrdenDeRetiro o : this.ordenDao.listarTodos()) {
			if(o.obtenerRecolector()!=null) {
				listaOrdenes.add(new OrdenDeRetiroDTO(o.obtenerId(), o.obtenerFecha().toString(), o.obtenerEstado(),
						o.obtenerPedido().obtenerFechaEmision().toString(), o.obtenerRecolector().obtenerNombre()+" "+o.obtenerRecolector().obtenerApellido()));
			}
			else{
				listaOrdenes.add(new OrdenDeRetiroDTO(o.obtenerId(), o.obtenerFecha().toString(), o.obtenerEstado(),
						o.obtenerPedido().obtenerFechaEmision().toString(), null));
			}
			
		}
		return listaOrdenes;
	}


	@Override
	public void asignarRecolector(Integer idOrden, Integer idRecolector) {
		
		Recolector recolector  = this.recolectorDao.buscar(idRecolector);
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		orden.asignarRecolector(recolector);
		this.ordenDao.actualizar(orden);
		
	}


	@Override
	public void cambiarDue�o(Integer idVivienda, String nombreCiudadano, String apeCiudadano, String dniCiudadano) throws NotNullException, DataEmptyException, NumbersException {
		Ciudadano ciudadano = new Ciudadano(nombreCiudadano, apeCiudadano, dniCiudadano, null);
		Vivienda vivienda = this.viviendaDao.buscar(idVivienda);
		
		vivienda.editarCiudadano(ciudadano);
		viviendaDao.actualizarYcrear(vivienda);
		
	}

	
	@Override
	public void cambiarDue�o(Integer idVivienda, String dni) {
		Ciudadano ciudadano = this.ciudadanoDao.buscar(dni);
		Vivienda vivienda = this.viviendaDao.buscar(idVivienda);
		
		vivienda.editarCiudadano(ciudadano);
		viviendaDao.actualizar(vivienda);
	}

	@Override
	public List<CiudadanoDTO> obtenerCiudadanos() {
		List<CiudadanoDTO> ciudadanosDTO = new ArrayList<CiudadanoDTO>();
		
		for (Ciudadano ciu  :  this.ciudadanoDao.listarTodos()) {
			ciudadanosDTO.add(new CiudadanoDTO(ciu.obtenerId(),ciu.obtenerNombre(), ciu.obtenerApellido(), ciu.obtenerDni()));
		}
		
		return ciudadanosDTO;
	}


	@Override
	public void agregarVisita(Integer idOrden, String observacion, List<ResiduoRetiradoDTO> residuosretiradosDTO) throws NotNullException, StateException, WasteException, CollectorException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		
		if(!orden.tieneRecolector()) {    //Valida que la orden tenga un recolector asignado
			throw new CollectorException("La orden no tiene ning�n recolector asignado");
		}
		
		List<ResiduoRetirado> residuosRetirados = new ArrayList<ResiduoRetirado>();
		List<ResiduoRestante> residuosRestantes = this.calcularResiduosRestantes(orden);   //Almacena en una lista los residuos que faltan para completar el pedido
		
		for(ResiduoRetiradoDTO res : residuosretiradosDTO) {
			TipoResiduo tipoRes = this.residuoDao.buscar(res.obtenerTipo());
			residuosRetirados.add(new ResiduoRetirado(tipoRes, res.obtenerCantidadKg()));
		}
		
		
		if(!estanTodosResiduosDeclarados(residuosRetirados, orden.obtenerPedido().obtenerId())) {   //valida que los residuos retirados que se quieren agregar
																									//al sistema est�n declarados en el pedido
			throw new WasteException("Alguno de los residuos que se quiere ingresar\n no esta declarado en el pedido");
		}
		
		
		if(orden.estaPendiente()) {   //si la orden estaba pendiente se cambia de estado
			orden.ejecutarOrden();
		}
		
	
	
		List<ResiduoRestante> nuevosResiduosRestantes = this.calcularResiduosNuevosRestantes(residuosRetirados, residuosRestantes); //Dispara una excepcion WasteExcepcion si las cantidades declaradas en el pedido
																																	//no coinciden con las cantidades que se quiere ingresar
		if(!quedanResiduosRestantes(nuevosResiduosRestantes)) {    //si no quedan residuos restantes para poder completar el pedido
																   //la orden se cambia de estado
			orden.finalizarOrden();
		}
		
		Visita visita = new Visita(LocalDateTime.now().toString(), observacion, orden);
		visita.editarResiduosRetirados(residuosRetirados);
		visitaDao.crear(visita);
	}


	
	private boolean quedanResiduosRestantes(List<ResiduoRestante> residuosRestantes) {
		for(ResiduoRestante resRestante : residuosRestantes) {
			if(resRestante.obtenerCantkg()>0) {
				return true;
			}
		}
		return false;
	}
	
	private boolean estanTodosResiduosDeclarados(List<ResiduoRetirado> residuosRetirados, Integer idPedido) { //se fija que esten declarados esos residuos pasados por parametro para el pedido idPedido
		List<ResiduoARetirar> residuosARetirar = this.residuosDao.buscarResiduosARetirar(idPedido);
		
		for(ResiduoRetirado resRetirado : residuosRetirados) {
			if(!residuosARetirar.contains(resRetirado)) {
				return false;
			}
		}
		
		return true;
	}


	private List<ResiduoRestante> calcularResiduosNuevosRestantes(List<ResiduoRetirado> residuosRetirados, List<ResiduoRestante> residuosRestantes) throws WasteException {
		List<ResiduoRestante> nuevosResiduosRestantes = new ArrayList<>();
		
		for(ResiduoRetirado  resRetirado: residuosRetirados) {
			for (ResiduoRestante resRestante : residuosRestantes) {
				if(resRetirado.obtenerTipoResiduo().equals(resRestante.obtenerTipoResiduo())) {
					if(resRestante.obtenerCantkg()-resRetirado.obtenerCantkg()<0) {
						throw new WasteException("Las cantidades no coinciden con lo declarado en el pedido");
					}
					try {
						nuevosResiduosRestantes.add(new ResiduoRestante(resRetirado.obtenerTipo(), resRestante.obtenerCantkg()-resRetirado.obtenerCantkg()));
					} catch (NotNullException e) {
						//TRATAR
					}
				}
			}
		}
		
		return nuevosResiduosRestantes;
		
	}
	
	
	
	private List<ResiduoRestante> calcularResiduosRestantes(OrdenDeRetiro orden){ 	//devuelve los residuos restantes de cada tipo
		List<ResiduoRetirado>  residuosRetiradosEnTotal = this.residuosDao.buscarResiduosRetiradosEnTotal(orden.obtenerId());
		List<ResiduoARetirar>residuosARetirar=this.residuosDao.buscarResiduosARetirar(orden.obtenerPedido().obtenerId());
		
		List<ResiduoRestante> residuosRestantes=new ArrayList<ResiduoRestante>();
		
		for(ResiduoARetirar resRetirar : residuosARetirar) {
			for(ResiduoRetirado resRetirado : residuosRetiradosEnTotal) {
				if(resRetirar.obtenerTipoResiduo().equals(resRetirado.obtenerTipoResiduo())) {
					try {
						TipoResiduo tipoRes=new TipoResiduo(resRetirar.obtenerTipoResiduo(), resRetirar.obtenerPuntosTipoResiduo());
						residuosRestantes.add(new ResiduoRestante(tipoRes, resRetirar.obtenerCantkg()-resRetirado.obtenerCantkg()));
					} catch (NotNullException | DataEmptyException | NumbersException e) {
						//que hago aca?
					}
					break;
				}
			}
			
		}
		return residuosRestantes;
	}


	@Override
	public List<VisitaDTO> obtenerVisitas() throws EmptyListException {
		List<Visita> visitas = this.visitaDao.listarTodas();
		List<VisitaDTO> visitasDTO = new ArrayList<>();

		if(visitas.isEmpty()) {
			throw new EmptyListException("Aun no hay ninguna visita en el sistema");
		}
		
		for (Visita visita : visitas) {
			visitasDTO.add(new VisitaDTO(visita.obtenerId(), visita.obtenerFecha(), visita.obtenerObservacion(), visita.obtenerOrden().obtenerFecha()));
		}
		
		return visitasDTO;
	}


	@Override
	public OrdenDeRetiroDTO obtenerOrden(Integer idVisita) {
		OrdenDeRetiro orden = this.ordenDao.buscarPorVisita(idVisita);
		OrdenDeRetiroDTO ordenDTO = new OrdenDeRetiroDTO(orden.obtenerId(), orden.obtenerFecha().toString(), orden.obtenerEstado(), orden.obtenerPedido().obtenerFechaEmision().toString(),
				orden.obtenerRecolector().obtenerNombre()+" "+ orden.obtenerRecolector().obtenerApellido());
		return (ordenDTO);
	}

	
	
	
}
