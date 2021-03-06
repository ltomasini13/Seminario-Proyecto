package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.mysql.fabric.xmlrpc.base.Array;

import ar.edu.unrn.seminario.dto.CiudadanoDTO;

import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.dto.Campa?aDTO;
import ar.edu.unrn.seminario.dto.CanjeDTO;
import ar.edu.unrn.seminario.dto.OrdenDeRetiroDTO;
import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.dto.ResiduoARetirarDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.ResiduoRestanteDTO;
import ar.edu.unrn.seminario.dto.ResiduoRetiradoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.VisitaDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.CollectorException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;

import ar.edu.unrn.seminario.exception.WasteException;
import ar.edu.unrn.seminario.exception.ZeroNegativeNumberException;
import ar.edu.unrn.seminario.modelo.Beneficio;
import ar.edu.unrn.seminario.modelo.Campa?a;
import ar.edu.unrn.seminario.modelo.Canje;
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
import ar.unrn.edu.ar.seminario.accesos.BeneficioDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.BeneficioDao;
import ar.unrn.edu.ar.seminario.accesos.Campa?aDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.Campa?aDao;
import ar.unrn.edu.ar.seminario.accesos.CanjeDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.CanjeDao;
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
	private ResiduoDao residuoDao; // hay que cambiarla por TipoDao
	private PedidoRetiroDao pedidoDao;
	private RecolectorDao recolectorDao;
	private ResiduosDao residuosDao; // dao para trabajar con los residuos a retirar y residuos retirados
	private BeneficioDao beneficioDao;
	private Campa?aDao campa?aDao;
	private CanjeDao canjeDao;
	private  ResourceBundle idioma;

	public PersistenceApi() {
		viviendaDao = new ViviendaDAOJDBC();
		rolDao = new RolDAOJDBC();
		usuarioDao = new UsuarioDAOJDBC();
		ciudadanoDao = new CiudadanoDAOJDBC();
		residuoDao = new ResiduoDAOJDBC();
		pedidoDao = new PedidoRetiroDAOJDBC();
		recolectorDao = new RecolectorDAOJDBC();
		ordenDao = new OrdenDeRetiroDAOJDBC();
		visitaDao = new VisitaDAOJDBC();
		residuosDao = new ResiduosDAOJDBC();// dao para trabajar con los residuos a retirar y residuos retirados
		beneficioDao = new BeneficioDAOJDBC();
		campa?aDao = new Campa?aDAOJDBC();
		canjeDao = new CanjeDAOJDBC();
		idioma=null;
	}

	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol)
			throws DataEmptyException, NotNullException, AppException, InstanceException {

		Rol role = this.rolDao.obtenerRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);

		this.usuarioDao.crear(usuario);

	}

	@Override
	public void registrarCiudadano(String username, String password, String email, String nombre, Integer rol,
			String apellido, String dni) throws  DataEmptyException, NotNullException,
			NumbersException, AuthenticationException, AppException, InstanceException {

		if (this.usuarioDao.buscar(username) != null) {
			throw new AuthenticationException("El nombre de usuario '" + username + "' ya esta en uso");
		}

		if (this.ciudadanoDao.buscar(dni) != null) {
			throw new AuthenticationException("Existe un ciudadano registrado con el dni '" + dni + "'");
		}

		Rol rolCiudadano = rolDao.obtenerRol(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, rolCiudadano);
		Ciudadano ciudadano = new Ciudadano(nombre, apellido, dni, usuario);

		ciudadanoDao.crear(ciudadano);

	}

	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud,
			String nombreCiudadano, String apeCiudadano, String dniCiudadano)
			throws NotNullException, DataEmptyException, NumbersException, 
			AuthenticationException, AppException, InstanceException {

		if (latitud.isEmpty() || longitud.isEmpty() || numero.isEmpty()) {
			throw new DataEmptyException("Faltan completar campos");
		}

		if (!numero.matches("[0-9]+")) {
			throw new NumbersException("El valor ingresado para el campo 'N?mero' no es num?rico");
		}
		int nro = Integer.parseInt(numero);
		double lat = 0;
		double longi = 0;
		try {
			lat = Double.parseDouble(latitud);
			longi = Double.parseDouble(longitud);
		} catch (NumberFormatException e) {
			throw new NumbersException("La latitud y/o longitud ingresadas no son correctas");
		}

		Ubicacion ubicacion = new Ubicacion(calle, nro, barrio, lat, longi);
		Ciudadano ciudadano = new Ciudadano(nombreCiudadano, apeCiudadano, dniCiudadano, null);
		Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
		// RegistroVivienda regVivienda = new RegistroVivienda(LocalDateTime.now(),
		// vivienda);
		Vivienda vivAutent = viviendaDao.buscar(vivienda);
		if (vivAutent != null) {
			throw new AuthenticationException("Ya existe una vivienda con esa direccion");
		}

		Ciudadano ciuAutent = ciudadanoDao.buscar(ciudadano.obtenerDni());
		if (ciuAutent != null) {
			throw new AuthenticationException("El ciudadano con dni: " + ciudadano.obtenerDni() + " ya existe");

		}
		viviendaDao.crear(vivienda);

	}

	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud)
			throws DataEmptyException, NumbersException, NotNullException, AuthenticationException, AppException,
			InstanceException {
		if (latitud.isEmpty() || longitud.isEmpty() || numero.isEmpty()) {
			throw new DataEmptyException("Faltan completar campos");
		}

		if (!numero.matches("[0-9]+")) {
			throw new NumbersException("El valor ingresado para el campo 'N?mero' no es num?rico");
		}
		Integer nro = Integer.parseInt(numero);
		double lat = 0;
		double longi = 0;
		try {
			lat = Double.parseDouble(latitud);
			longi = Double.parseDouble(longitud);
		} catch (NumberFormatException e) {
			throw new NumbersException("La latitud y/o longitud ingresadas no son correctas");
		}

		Ubicacion ubicacion = new Ubicacion(calle, nro, barrio, lat, longi);
		Ciudadano ciudadano = ciudadanoDao.buscar(sesion.obtenerUsuario());
		Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
		// RegistroVivienda regVivienda = new RegistroVivienda(LocalDateTime.now(),
		// vivienda);

		Vivienda vivAutent = viviendaDao.buscar(vivienda);
		if (vivAutent != null) {
			throw new AuthenticationException("Ya existe una vivienda con esa direccion");
		}
		viviendaDao.crearParaRecic(vivienda);
	}

	@Override
	public UsuarioDTO obtenerUsuario(String username)
			throws NotNullException, DataEmptyException, AppException, InstanceException {
		Usuario usuario = this.usuarioDao.buscar(username);
		UsuarioDTO usuarioDTO = null;

		if (usuario != null) {
			Rol rol = this.rolDao.obtenerRol(usuario.obtenerCodigoRol());
			usuarioDTO = new UsuarioDTO(usuario.obtenerUsuario(), usuario.obtenerContrasena(), usuario.obtenerNombre(),
					usuario.obtenerEmail(), rol.obtenerNombre(), usuario.obtenerEstado());
		}

		return usuarioDTO;

	}

	@Override
	public void eliminarUsuario(String username) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<RolDTO> obtenerRoles() throws AppException, InstanceException {
		List<RolDTO> rolesDto = new ArrayList<RolDTO>();

		for (Rol r : rolDao.listarTodos()) {
			if (r.isActivo()) {
				rolesDto.add(new RolDTO(r.obtenerCodigo(), r.obtenerNombre(), "ACTIVO"));
			} else {
				rolesDto.add(new RolDTO(r.obtenerCodigo(), r.obtenerNombre(), "INACTIVO"));
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
	public void guardarRol(Integer codigo, String descripci?n, boolean estado)
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
	public List<UsuarioDTO> obtenerUsuarios() throws AppException, InstanceException {
		List<Usuario> usuarios = this.usuarioDao.listarTodos();

		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();

		for (Usuario u : usuarios) {
			usuariosDTO.add(new UsuarioDTO(u.obtenerUsuario(), u.obtenerContrasena(), u.obtenerNombre(),
					u.obtenerEmail(), u.obtenerNombreRol(), u.obtenerEstado()));

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
	public List<ViviendaDTO> obtenerViviendas() throws EmptyListException, AppException, InstanceException {
		List<ViviendaDTO> viviendasDTO = new ArrayList<ViviendaDTO>();
		List<Vivienda> viviendas;
		if (this.esUsuarioAdmin()) {
			viviendas = viviendaDao.listarTodas();
			if (viviendas.isEmpty()) {
				throw new EmptyListException("No hay ninguna vivienda registrada en el sistema");
			}
			for (Vivienda v : viviendas) {
				viviendasDTO.add(new ViviendaDTO(v.obtenerId(), v.obtenerUbicacionCalle(), v.obtenerUbicacionNro(),
						v.obtenerUbicacionBarrio(), v.obtenerUbicacionLatitud(), v.obtenerUbicacionLongitud(),
						v.obtenerNombreCiudadano() + " " + v.obtenerApellidoCiudadano()));
			}
		}

		if (this.esUsuarioReciclador()) {
			Ciudadano ciu = this.ciudadanoDao.buscar(sesion.obtenerUsuario());
			viviendas = this.ciudadanoDao.listarMisViviendas(ciu);

			if (viviendas.isEmpty()) {
				throw new EmptyListException("No tiene ninguna vivienda registrada");
			}

			for (Vivienda v : viviendas) {
				viviendasDTO.add(new ViviendaDTO(v.obtenerId(), v.obtenerUbicacionCalle(), v.obtenerUbicacionNro(),
						v.obtenerUbicacionBarrio(), v.obtenerUbicacionLatitud(), v.obtenerUbicacionLongitud(),
						v.obtenerNombreCiudadano() + v.obtenerApellidoCiudadano()));
			}
		}

		return viviendasDTO;
	}

	@Override
	public ViviendaDTO obtenerVivienda(Integer idVivienda) throws AppException, InstanceException {
		Vivienda viv = this.viviendaDao.buscar(idVivienda);
		return (new ViviendaDTO(viv.obtenerId(), viv.obtenerUbicacionCalle(), viv.obtenerUbicacionNro(),
				viv.obtenerUbicacionBarrio(), viv.obtenerUbicacionLatitud(), viv.obtenerUbicacionLongitud(),
				viv.obtenerNombreCiudadano() + " " + viv.obtenerApellidoCiudadano()));
	}

	@Override
	public ViviendaDTO obtenerViviendaDelPedido(Integer idPedido) throws AppException, InstanceException {
		Vivienda vivienda = this.viviendaDao.buscarPorPedido(idPedido);
		ViviendaDTO viviendaDTO = new ViviendaDTO(vivienda.obtenerId(), vivienda.obtenerUbicacionCalle(),
				vivienda.obtenerUbicacionNro(), vivienda.obtenerUbicacionBarrio(), vivienda.obtenerUbicacionLatitud(),
				vivienda.obtenerUbicacionLongitud(),
				vivienda.obtenerNombreCiudadano() + " " + vivienda.obtenerApellidoCiudadano());

		return viviendaDTO;
	}

	@Override
	public void loguearUsuario(String username, String contrasena) throws AuthenticationException,
			NotNullException, DataEmptyException, AppException, InstanceException {
		if (username == null || contrasena == null) {
			throw new NotNullException("Los campos de usuario o contrase?a son nulos");
		}

		if (username.isEmpty() || contrasena.isEmpty()) {
			throw new DataEmptyException("Los campos de usuario o contrase?a son vacios");
		}

		Usuario usuario = this.usuarioDao.buscar(username);

		if (usuario == null) {
			throw new AuthenticationException("Usuario y/o contrase?a incorrecto");
		} else {
			if (!contrasena.equals(usuario.obtenerContrasena())) {
				throw new AuthenticationException("Usuario y/o contrase?a incorrecto");
			}
		}

		sesion = new Sesion(usuario);
	}

	public boolean esUsuarioAdmin() {
		if (sesion.tipoDeUsuario().equals("ADMIN")) {
			return true;
		} else {
			return false;
		}
	}

	public boolean esUsuarioReciclador() {
		if (sesion.tipoDeUsuario().equals("RECICLADOR")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void registrarResiduo(String tipo, String numero) throws NumbersException, NotNullException,
			DataEmptyException, DuplicateUniqueKeyException, AppException, InstanceException{

		if (!numero.matches("[0-9]+")) {
			throw new NumbersException("El valor ingresado para el campo 'Puntos' no es num?rico");
		}
		int nro = Integer.parseInt(numero);

		TipoResiduo residuo = new TipoResiduo(tipo, nro);

		residuoDao.crear(residuo);

	}

	@Override
	public List<PedidoRetiroDTO> obtenerPedidos() throws EmptyListException, AppException, InstanceException {
		List<PedidoRetiroDTO> pedidosDTO = new ArrayList<PedidoRetiroDTO>();
		List<PedidoRetiro> pedidos = new ArrayList<PedidoRetiro>();
		if (this.esUsuarioAdmin()) {
			pedidos = this.pedidoDao.listarTodos();
			if (pedidos.isEmpty()) {
				throw new EmptyListException("No hay ningun pedido en el sistema");
			}
			for (PedidoRetiro p : pedidos) {

				try {
					pedidosDTO.add(new PedidoRetiroDTO(p.obtenerId(), p.obtenerFechaEmision(),
							p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
							p.obtenerVivienda().obtenerUbicacionCalle(), p.obtenerVivienda().obtenerUbicacionNro(),
							p.obtenerVivienda().obtenerUbicacionBarrio(), p.obtenerVivienda().obtenerUbicacionLatitud(),
							p.obtenerVivienda().obtenerUbicacionLongitud(), p.obtenerNombreCiudadanoVivienda(),
							p.obtenerApellidoCiudadanoVivienda()));
				} catch (NotNullException e) {
					System.out.println("Error en constructor Pedido de retiro DTO");
				}
			}
		}

		if (this.esUsuarioReciclador()) {
			pedidos = this.pedidoDao.buscarPorUsuario(sesion.obtenerUsuario().obtenerId());
			if (pedidos.isEmpty()) {
				throw new EmptyListException("Aun no realiz? ningun pedido");
			}
			for (PedidoRetiro p : pedidos) {
				try {
					pedidosDTO.add(new PedidoRetiroDTO(p.obtenerId(), p.obtenerFechaEmision(),
							p.obtenerFechaCumplimiento(), p.isCargaPesada(), p.obtenerObservacion(),
							p.obtenerVivienda().obtenerUbicacionCalle(), p.obtenerVivienda().obtenerUbicacionNro(),
							p.obtenerVivienda().obtenerUbicacionBarrio(), p.obtenerVivienda().obtenerUbicacionLatitud(),
							p.obtenerVivienda().obtenerUbicacionLongitud(), p.obtenerNombreCiudadanoVivienda(),
							p.obtenerApellidoCiudadanoVivienda()));
				} catch (NotNullException e) {
					System.out.println("Error en constructor Pedido de retiro DTO");
				}
			}
		}

		return pedidosDTO;

	}

	@Override
	public PedidoRetiroDTO obtenerPedidoDeLaOrden(Integer idOrden) throws AppException, InstanceException {
		PedidoRetiro pedido = this.pedidoDao.buscarPorOrden(idOrden);
		PedidoRetiroDTO pedidoDTO = null;

		try {
			pedidoDTO = new PedidoRetiroDTO(pedido.obtenerId(), pedido.obtenerFechaEmision(),
					pedido.obtenerFechaCumplimiento(), pedido.isCargaPesada(), pedido.obtenerObservacion(),
					pedido.obtenerVivienda().obtenerUbicacionCalle(), pedido.obtenerVivienda().obtenerUbicacionNro(),
					pedido.obtenerVivienda().obtenerUbicacionBarrio(),
					pedido.obtenerVivienda().obtenerUbicacionLatitud(),
					pedido.obtenerVivienda().obtenerUbicacionLongitud(), pedido.obtenerNombreCiudadanoVivienda(),
					pedido.obtenerApellidoCiudadanoVivienda());
		} catch (NotNullException e) {
			// ver
		}

		return pedidoDTO;
	}

	@Override
	public List<ResiduoDTO> obtenerResiduos() throws AppException, InstanceException {
		List<ResiduoDTO> residuosDTO = new ArrayList<ResiduoDTO>();

		for (TipoResiduo residuo : residuoDao.listarTodos()) {
			try {
				residuosDTO.add(new ResiduoDTO(residuo.obtenerId(), residuo.obtenerTipo(), residuo.obtenerPunto()));
			} catch (NotNullException | DataEmptyException | NumbersException e) {

			}
		}

		return residuosDTO;
	}

	@Override
	public List<ResiduoARetirarDTO> obtenerResiduosARetirar(Integer idPedido) throws AppException, InstanceException {
		List<ResiduoARetirarDTO> residuosARetirarDTO = new ArrayList<ResiduoARetirarDTO>();

		for (ResiduoARetirar res : this.pedidoDao.buscarResiduosARetirar(idPedido)) {
			residuosARetirarDTO
					.add(new ResiduoARetirarDTO(res.obtenerId(), res.obtenerTipoResiduo(), res.obtenerCantkg()));
		}

		return residuosARetirarDTO;

	}

	@Override
	public List<ResiduoRetiradoDTO> obtenerResiduosRetirados(Integer idVisita) throws AppException, InstanceException {
		List<ResiduoRetiradoDTO> residuosRetiradosDTO = new ArrayList<ResiduoRetiradoDTO>();

		for (ResiduoRetirado res : this.residuosDao.buscarResiduosRetirados(idVisita)) {
			residuosRetiradosDTO
					.add(new ResiduoRetiradoDTO(res.obtenerId(), res.obtenerTipoResiduo(), res.obtenerCantkg()));
		}
		return residuosRetiradosDTO;
	}

	@Override
	public void cerrarSesion() {
		this.sesion = null;
	}

	@Override
	public void generarPedido(Integer id_vivienda, boolean cargaPesada, String observacion,
			List<ResiduoARetirarDTO> residuosARetirarDTO)
			throws NotNullException, ZeroNegativeNumberException, EmptyListException, AppException, InstanceException {

		List<ResiduoARetirar> residuosARetirar = new ArrayList<ResiduoARetirar>();
		Vivienda vivienda = viviendaDao.buscar(id_vivienda);

		for (ResiduoARetirarDTO r : residuosARetirarDTO) {
			TipoResiduo tipoResiduo = residuoDao.buscar(r.obetenerTipoResiduo());

			residuosARetirar.add(new ResiduoARetirar(tipoResiduo, r.obetenerCantidad()));
		}

		PedidoRetiro pedidoRetiro = new PedidoRetiro(LocalDateTime.now().toString(), cargaPesada, observacion, vivienda,
				residuosARetirar);

		pedidoDao.crear(pedidoRetiro);

	}

	@Override
	public void pedidoPendiente(Integer id_vivienda)
			throws CreationValidationException, AppException, InstanceException {

		List<PedidoRetiro> pedidos = pedidoDao.buscar(id_vivienda);
		if (!pedidos.isEmpty()) {
			for (PedidoRetiro p : pedidos) {
				if (p.obtenerFechaCumplimiento() == null) {
					throw new CreationValidationException("La vivienda tiene un pedido que todav?a no concluy?");
				}
			}
		}

	}

	public void registrarRecolector(String nombre, String apellido, String dni, String email)
			throws NotNullException, DataEmptyException, DuplicateUniqueKeyException, 
			NumbersException, AppException, InstanceException {

		Recolector recolector = new Recolector(nombre, apellido, dni, email);

		this.recolectorDao.crear(recolector);

	}

	@Override
	public List<RecolectorDTO> obtenerRecolectores() throws AppException, InstanceException {
		List<RecolectorDTO> recolectoresDTO = new ArrayList<RecolectorDTO>();

		for (Recolector r : recolectorDao.listarTodos()) {
			recolectoresDTO.add(new RecolectorDTO(r.obtenerId(), r.obtenerNombre(), r.obtenerApellido(), r.obtenerDni(),
					r.obtenerEmail()));
		}

		return recolectoresDTO;
	}

	@Override
	public void generarOrden(Integer idPedido)
			throws CreationValidationException, AppException, InstanceException {

		List<OrdenDeRetiro> ordenes = ordenDao.buscarPedido(idPedido);
		if (!ordenes.isEmpty()) {
			throw new CreationValidationException("El pedido ya tiene asignado una orden.");
		} else {
			PedidoRetiro pedido = pedidoDao.buscarPedido(idPedido);
			OrdenDeRetiro orden = new OrdenDeRetiro(LocalDateTime.now().toString(), pedido);
			ordenDao.crear(orden);
		}

	}

	@Override
	public void cancelarOrden(Integer idOrden) throws StateException, AppException, InstanceException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		orden.cancelarOrden();
		this.ordenDao.actualizar(orden);

	}

	@Override
	public void concretarOrden(Integer idOrden)
			throws StateException, AppException, InstanceException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		orden.finalizarOrden();
		this.ordenDao.actualizar(orden);

		Ciudadano ciudadano = this.ciudadanoDao.buscarPorVivienda(orden.obtenerViviendaDelPedido().obtenerId());
		ciudadano.sumarPuntos(calcularPuntaje(orden));
		ciudadanoDao.actualizar(ciudadano);

		PedidoRetiro pedido = this.pedidoDao.buscarPedido(orden.obtenerPedido().obtenerId());
		pedido.editarFechaCumplimiento(LocalDateTime.now());
		pedidoDao.actualizar(pedido);

	}

	@Override
	public List<OrdenDeRetiroDTO> obtenerOrdenes() throws AppException, InstanceException {
		List<OrdenDeRetiroDTO> listaOrdenes = new ArrayList<OrdenDeRetiroDTO>();

		for (OrdenDeRetiro o : this.ordenDao.listarTodos()) {
			if (o.obtenerRecolector() != null) {
				listaOrdenes.add(new OrdenDeRetiroDTO(o.obtenerId(), o.obtenerFecha().toString(), o.obtenerEstado(),
						o.obtenerPedido().obtenerFechaEmision().toString(),
						o.obtenerRecolector().obtenerNombre() + " " + o.obtenerRecolector().obtenerApellido()));
			} else {
				listaOrdenes.add(new OrdenDeRetiroDTO(o.obtenerId(), o.obtenerFecha().toString(), o.obtenerEstado(),
						o.obtenerPedido().obtenerFechaEmision().toString(), null));
			}

		}
		return listaOrdenes;
	}

	@Override
	public void asignarRecolector(Integer idOrden, Integer idRecolector) throws AppException, InstanceException {

		Recolector recolector = this.recolectorDao.buscar(idRecolector);
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		orden.asignarRecolector(recolector);
		this.ordenDao.actualizar(orden);

	}

	@Override
	public void cambiarDue?o(Integer idVivienda, String nombreCiudadano, String apeCiudadano, String dniCiudadano)
			throws NotNullException, DataEmptyException, NumbersException, AppException, InstanceException {
		Ciudadano ciudadano = new Ciudadano(nombreCiudadano, apeCiudadano, dniCiudadano, null);
		Vivienda vivienda = this.viviendaDao.buscar(idVivienda);

		vivienda.editarCiudadano(ciudadano);
		viviendaDao.actualizarYcrear(vivienda);

	}

	@Override
	public void cambiarDue?o(Integer idVivienda, String dni) throws AppException, InstanceException {
		Ciudadano ciudadano = this.ciudadanoDao.buscar(dni);
		Vivienda vivienda = this.viviendaDao.buscar(idVivienda);

		vivienda.editarCiudadano(ciudadano);
		viviendaDao.actualizar(vivienda);
	}

	@Override
	public List<CiudadanoDTO> obtenerCiudadanos() throws AppException, InstanceException {
		List<CiudadanoDTO> ciudadanosDTO = new ArrayList<CiudadanoDTO>();

		for (Ciudadano ciu : this.ciudadanoDao.listarTodos()) {
			CiudadanoDTO ciudadanoDTO=new CiudadanoDTO(ciu.obtenerId(), ciu.obtenerNombre(), ciu.obtenerApellido(),
					ciu.obtenerDni(), ciu.obtenerPuntaje());
			if(ciu.obtenerUsuario()!=null) {
				ciudadanoDTO.editarNombreDeUsuario(ciu.obtenerNombreDeUsuario());
			}
			ciudadanosDTO.add(ciudadanoDTO);
		}

		return ciudadanosDTO;
	}

	@Override
	public CiudadanoDTO obtenerCiudadanoDeLaVivienda(Integer idVivienda) throws AppException, InstanceException {
		Ciudadano ciudadano = this.ciudadanoDao.buscarPorVivienda(idVivienda);
		CiudadanoDTO ciudadanoDTO = new CiudadanoDTO(ciudadano.obtenerId(), ciudadano.obtenerNombre(),
				ciudadano.obtenerApellido(), ciudadano.obtenerDni(), ciudadano.obtenerPuntaje());
		if (ciudadano.obtenerUsuario() != null) {
			ciudadanoDTO.editarNombreDeUsuario(ciudadano.obtenerNombreDeUsuario());
		}
		return ciudadanoDTO;
	}

	@Override
	public void agregarVisita(Integer idOrden, String observacion, List<ResiduoRetiradoDTO> residuosretiradosDTO)
			throws NotNullException, StateException, WasteException, CollectorException, AppException, InstanceException{
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		if (!orden.tieneRecolector()) { // Valida que la orden tenga un recolector asignado
			throw new CollectorException("La orden no tiene ning?n recolector asignado");
		}

		
		if(orden.estaConcretada() ||orden.estaCancelada()) {
			throw new StateException("No se puede agregar una visita porque la orden esta '"+orden.obtenerEstado()+"'");
		}
		List<ResiduoRetirado> residuosRetirados = new ArrayList<ResiduoRetirado>();
		List<ResiduoRestante> residuosRestantes = this.calcularResiduosRestantes(orden); // Almacena en una lista los
																							// residuos que faltan para
																							// completar el pedido

		for (ResiduoRetiradoDTO res : residuosretiradosDTO) {
			TipoResiduo tipoRes = this.residuoDao.buscar(res.obtenerTipo());
			residuosRetirados.add(new ResiduoRetirado(tipoRes, res.obtenerCantidadKg()));
		}

		if (!estanTodosResiduosDeclarados(residuosRetirados, orden.obtenerPedido().obtenerId())) { // valida que los
																									// residuos
																									// retirados que se
																									// quieren agregar
																									// al sistema est?n
																									// declarados en el
																									// pedido
			throw new WasteException("Alguno de los residuos que se quiere ingresar\n no esta declarado en el pedido");
		}

		if (orden.estaPendiente()) { // si la orden estaba pendiente se cambia de estado
			orden.ejecutarOrden();
		}

		List<ResiduoRestante> nuevosResiduosRestantes = this.calcularResiduosNuevosRestantes(residuosRetirados,
				residuosRestantes); // Dispara una excepcion WasteExcepcion si las cantidades declaradas en el
									// pedido
									// no coinciden con las cantidades que se quiere ingresar

		Visita visita = new Visita(LocalDateTime.now().toString(), observacion, orden);
		visita.editarResiduosRetirados(residuosRetirados);

		visitaDao.crear(visita);
		if (!quedanResiduosRestantes(nuevosResiduosRestantes)) { // si no quedan residuos restantes para poder completar
																	// el pedido
			concretarOrden(idOrden);
		}

	}

	private double calcularPuntaje(OrdenDeRetiro orden) throws AppException, InstanceException {
		double puntaje = 0;
		List<ResiduoRetirado> residuosRetiradosEnTotal = this.residuosDao
				.buscarResiduosRetiradosEnTotal(orden.obtenerId());

		for (ResiduoRetirado res : residuosRetiradosEnTotal) {
			puntaje = puntaje + (res.obtenerCantkg() * res.obtenerPuntosTipoResiduo());
		}
		return puntaje;
	}

	private boolean quedanResiduosRestantes(List<ResiduoRestante> residuosRestantes) {
		for (ResiduoRestante resRestante : residuosRestantes) {
			if (resRestante.obtenerCantkg() > 0) {
				return true;
			}
		}
		return false;
	}

	private boolean estanTodosResiduosDeclarados(List<ResiduoRetirado> residuosRetirados, Integer idPedido)
			throws AppException, InstanceException { // se fija que esten declarados esos residuos pasados por parametro
														// para el pedido idPedido
		List<ResiduoARetirar> residuosARetirar = this.residuosDao.buscarResiduosARetirar(idPedido);

		for (ResiduoRetirado resRetirado : residuosRetirados) {
			if (!residuosARetirar.contains(resRetirado)) {
				return false;
			}
		}

		return true;
	}

	private List<ResiduoRestante> calcularResiduosNuevosRestantes(List<ResiduoRetirado> residuosRetirados,
			List<ResiduoRestante> residuosRestantes) throws WasteException {
		List<ResiduoRestante> nuevosResiduosRestantes = new ArrayList<>();
		for (ResiduoRestante resRestante : residuosRestantes) {
			if (residuosRetirados.contains(resRestante)) {
				for (ResiduoRetirado resRetirado : residuosRetirados) {
					if (resRetirado.obtenerTipoResiduo().equals(resRestante.obtenerTipoResiduo())) {
						if (resRestante.obtenerCantkg() - resRetirado.obtenerCantkg() < 0) {
							try {
								nuevosResiduosRestantes.add(new ResiduoRestante(resRetirado.obtenerTipo(), 0));
							} catch (NotNullException e) {
								// TRATAR
							}
						} else {
							try {
								nuevosResiduosRestantes.add(new ResiduoRestante(resRetirado.obtenerTipo(),
										resRestante.obtenerCantkg() - resRetirado.obtenerCantkg()));
							} catch (NotNullException e) {
								// TRATAR
							}
						}

					}
				}
			} else {
				try {
					nuevosResiduosRestantes
							.add(new ResiduoRestante(resRestante.obtenerResiduo(), resRestante.obtenerCantkg()));
				} catch (NotNullException e) {

				}
			}
		}

		return nuevosResiduosRestantes;

	}

	private List<ResiduoRestante> calcularResiduosRestantes(OrdenDeRetiro orden)
			throws AppException, InstanceException { // devuelve los residuos restantes de cada tipo
		System.out.println();
		List<ResiduoRetirado> residuosRetiradosEnTotal = this.residuosDao
				.buscarResiduosRetiradosEnTotal(orden.obtenerId());
		List<ResiduoARetirar> residuosARetirar = this.residuosDao
				.buscarResiduosARetirar(orden.obtenerPedido().obtenerId());
		List<ResiduoRestante> residuosRestantes = new ArrayList<ResiduoRestante>();
		if (!residuosRetiradosEnTotal.isEmpty()) {

			for (ResiduoARetirar resRetirar : residuosARetirar) {
				for (ResiduoRetirado resRetirado : residuosRetiradosEnTotal) {
					if (resRetirar.obtenerTipoResiduo().equals(resRetirado.obtenerTipoResiduo())) {
						try {
							TipoResiduo tipoRes = new TipoResiduo(resRetirar.obtenerTipoResiduo(),
									resRetirar.obtenerPuntosTipoResiduo());
							if (resRetirar.obtenerCantkg() - resRetirado.obtenerCantkg() < 0)
								residuosRestantes.add(new ResiduoRestante(tipoRes, 0));
							else
								residuosRestantes.add(new ResiduoRestante(tipoRes,
										resRetirar.obtenerCantkg() - resRetirado.obtenerCantkg()));
						} catch (NotNullException | DataEmptyException | NumbersException e) {
							//
						}
						break;
					}
				}

			}
		} else {
			for (ResiduoARetirar resRetirar : residuosARetirar) {
				TipoResiduo tipoRes;
				try {
					tipoRes = new TipoResiduo(resRetirar.obtenerTipoResiduo(), resRetirar.obtenerPuntosTipoResiduo());
					residuosRestantes.add(new ResiduoRestante(tipoRes, resRetirar.obtenerCantkg()));
				} catch (NotNullException | DataEmptyException | NumbersException e) {
					// VEEER
				}

			}
		}
		return residuosRestantes;
	}

	public void registrarBeneficio(String nombre, String puntos)
			throws DataEmptyException, NotNullException, NumbersException, AppException, InstanceException {

		if (!puntos.matches("[0-9]+")) {
			throw new NumbersException("El valor ingresado para el campo 'Puntos' no es num?rico");
		}
		int nro = Integer.parseInt(puntos);
		Beneficio beneficio = new Beneficio(nombre, nro);

		beneficioDao.crear(beneficio);

	}

	@Override
	public List<BeneficioDTO> obtenerBeneficios()
			throws AppException, DataEmptyException, NotNullException, NumbersException, InstanceException {
		List<BeneficioDTO> beneficiosDTO = new ArrayList<BeneficioDTO>();

		for (Beneficio b : beneficioDao.listarTodos()) {

			beneficiosDTO.add(new BeneficioDTO(b.obtenerId(), b.obtenerNombreBeneficio(), b.obtenerPuntos()));
		}
		return beneficiosDTO;
	}

	@Override
	public void registrarCampa?a(String nombre, String descripcion) throws DataEmptyException, NotNullException,
			AppException, DateException, CreationValidationException, InstanceException {

		Campa?a camp = new Campa?a(nombre, LocalDateTime.now().toString(), LocalDateTime.now().plusMonths(2).toString(),
				descripcion);

		if (this.obtenerCampa?aVigente() != null) {
			throw new CreationValidationException("No se puede crear campa?a, ya hay una campa?a vigente");
		}
		campa?aDao.crear(camp);
		campa?aDao.buscar(camp.obtenerId());
	}

	@Override
	public List<VisitaDTO> obtenerVisitas() throws EmptyListException, AppException, InstanceException {
		List<Visita> visitas = this.visitaDao.listarTodas();
		List<VisitaDTO> visitasDTO = new ArrayList<>();

		if (visitas.isEmpty()) {
			throw new EmptyListException("Aun no hay ninguna visita en el sistema");
		}

		for (Visita visita : visitas) {
			visitasDTO.add(new VisitaDTO(visita.obtenerId(), visita.obtenerFecha(), visita.obtenerObservacion(),
					visita.obtenerOrden().obtenerFecha()));
		}

		return visitasDTO;

	}

	@Override
	public List<VisitaDTO> obtenerVisitasDeLaOrden(Integer idOrden)
			throws EmptyListException, AppException, InstanceException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		List<Visita> visitas = this.visitaDao.listarTodas(orden);
		List<VisitaDTO> visitasDTO = new ArrayList<>();

		if (visitas.isEmpty()) {
			throw new EmptyListException("Aun no hay ninguna visita para la orden");
		}

		for (Visita visita : visitas) {
			visitasDTO.add(new VisitaDTO(visita.obtenerId(), visita.obtenerFecha(), visita.obtenerObservacion(),
					visita.obtenerOrden().obtenerFecha()));
		}

		return visitasDTO;
	}

	public List<Campa?aDTO> obtenerCampa?as()
			throws AppException, NotNullException, DateException, DataEmptyException, InstanceException {
		List<Campa?aDTO> campa?asDTO = new ArrayList<Campa?aDTO>();

		for (Campa?a camp : campa?aDao.listarTodos()) {
			campa?asDTO.add(
					new Campa?aDTO(camp.obtenerId(), camp.obtenerNombreCampa?a(), camp.obtenerFechaInicio().toString(),
							camp.obtenerFechaFin().toString(), camp.obtenerDescripcion()));
		}
		return campa?asDTO;
	}

	@Override
	//realiza el canje para el usuario admin
	public void realizarCanje(String nombreBeneficio, String dni) throws NumbersException,
			NotNullException, AppException, DataEmptyException, InstanceException {

		// obtener los beneficios dentro del dao
		Beneficio beneficio = beneficioDao.buscarBeneficio(nombreBeneficio);
		Ciudadano ciudadano = ciudadanoDao.buscar(dni);

		if (ciudadano.obtenerPuntaje() < beneficio.obtenerPuntos()) {//valida que el ciudadano tenga los puntos suficientes para poder realizar el canje
			throw new NumbersException("No se puede realizar el canje, el ciudadano no tiene puntos suficientes");
		}

		double puntos = Double.valueOf(beneficio.obtenerPuntos());
		Canje canje = new Canje(LocalDateTime.now().toString(), beneficio.obtenerPuntos(), beneficio, ciudadano);
		canjeDao.crear(canje);
		ciudadano.restarPuntos(puntos);
		ciudadanoDao.actualizar(ciudadano);

	}
	
	
	
	@Override
	
	//realiza el canje para el usuario Reciclador
	public void realizarCanje(String nombreBeneficio) throws AppException, InstanceException, NumbersException, NotNullException {
				
				Beneficio beneficio = beneficioDao.buscarBeneficio(nombreBeneficio);
				Ciudadano ciudadano = this.ciudadanoDao.buscar(sesion.obtenerUsuario());

				if (ciudadano.obtenerPuntaje() < beneficio.obtenerPuntos()) {//valida que el ciudadano tenga los puntos suficientes para poder realizar el canje
					throw new NumbersException("No se puede realizar el canje, no tiene puntos suficientes");
				}
System.out.println();
				double puntos = Double.valueOf(beneficio.obtenerPuntos());
				Canje canje = new Canje(LocalDateTime.now().toString(), beneficio.obtenerPuntos(), beneficio, ciudadano);
				canjeDao.crear(canje);
				ciudadano.restarPuntos(puntos);
				ciudadanoDao.actualizar(ciudadano);
	}
	
	@Override
	public List<CanjeDTO> obtenerCanjes() throws AppException, InstanceException, EmptyListException{
		List<Canje> canjes=null;
		List<CanjeDTO> canjesDTO=new ArrayList<CanjeDTO>();
		
		if(this.esUsuarioAdmin()) {
			canjes = this.canjeDao.listarTodos();
			if(canjes.isEmpty()) {
				throw new EmptyListException("No hay ningun canje en el sistema");
			}
		}
		else {
			if(this.esUsuarioReciclador()) {
				canjes=this.canjeDao.listarMisCanjes(this.ciudadanoDao.buscar(sesion.obtenerUsuario()));
				if(canjes.isEmpty()) {
					throw new EmptyListException("Todavia no ha realizado ningun canje");
				}
				
			}
		}
		
		for(Canje c : canjes ) {
			canjesDTO.add(new CanjeDTO(c.obtenerFechaCanje().toString(), c.obtenerPuntaje(),
					c.obtenerBeneficio().obtenerNombreBeneficio(), c.obtenerCiudadano().obtenerNombre()+" "+c.obtenerCiudadano().obtenerApellido()));
		}
		return canjesDTO;
		
	}
	
	
	@Override
	public OrdenDeRetiroDTO obtenerOrden(Integer idVisita) throws AppException, InstanceException {
		OrdenDeRetiro orden = this.ordenDao.buscarPorVisita(idVisita);
		OrdenDeRetiroDTO ordenDTO = new OrdenDeRetiroDTO(orden.obtenerId(), orden.obtenerFecha().toString(),
				orden.obtenerEstado(), orden.obtenerPedido().obtenerFechaEmision().toString(),
				orden.obtenerRecolector().obtenerNombre() + " " + orden.obtenerRecolector().obtenerApellido());
		return (ordenDTO);
	}

	@Override
	public List<ResiduoRestanteDTO> obtenerResiduosRestantes(Integer idPedido) throws AppException, InstanceException {
		List<ResiduoRestanteDTO> residuosRestantesDTO = new ArrayList<ResiduoRestanteDTO>();
		PedidoRetiro pedido = this.pedidoDao.buscarPedido(idPedido);
		OrdenDeRetiro orden = this.ordenDao.buscarOrdenPorPedido(pedido);
		if(orden==null) {
			List<ResiduoARetirar> residuosARetirar=this.residuosDao.buscarResiduosARetirar(idPedido);
			for(ResiduoARetirar res : residuosARetirar) {
				residuosRestantesDTO
				.add(new ResiduoRestanteDTO(res.obtenerId(), res.obtenerTipoResiduo(), res.obtenerCantkg()));
			}
		}
		else {
			for (ResiduoRestante res : this.calcularResiduosRestantes(orden)) {
				residuosRestantesDTO
						.add(new ResiduoRestanteDTO(res.obtenerId(), res.obtenerTipoResiduo(), res.obtenerCantkg()));
			}
		}
		
		return residuosRestantesDTO;
	}

	@Override
	public double calcularResiduoRestanteDelResiduo(ResiduoRetiradoDTO residuoRetiradoDTO, Integer idOrden)
			throws AppException, InstanceException {
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);
		List<ResiduoRestante> residuosRestantes = calcularResiduosRestantes(orden);
		TipoResiduo tipo = this.residuoDao.buscar(residuoRetiradoDTO.obtenerTipo());
		ResiduoRetirado residuoRetirado = null;
		System.out.println();
		try {
			residuoRetirado = new ResiduoRetirado(tipo, residuoRetiradoDTO.obtenerCantidadKg());
		} catch (NotNullException e) {
			// VERR
		}

		for (ResiduoRestante res : residuosRestantes) {
			if (residuoRetirado.obtenerTipoResiduo().equals(res.obtenerTipoResiduo())) {
				return res.obtenerCantkg() - residuoRetirado.obtenerCantkg();

			}
		}

		return 0;

	}

	public void actualizarPuntaje(double puntaje) {
		// TODO Auto-generated method stu

	}

	@Override
	public void agregarBeneficio(Integer idCampa?a, Integer idBeneficio) throws AppException,
			CreationValidationException, DataEmptyException, NotNullException, NumbersException, InstanceException {

		Campa?a campa?a = campa?aDao.buscar(idCampa?a);

		Beneficio beneficio = beneficioDao.buscar(idBeneficio);

		List<Beneficio> beneficios = beneficioDao.buscarNombreBeneficio(beneficio.obtenerNombreBeneficio());

		if (!beneficios.isEmpty()) {
			throw new CreationValidationException("El cat?logo ya contiene al beneficio");
		}

		campa?a.agregarBeneficioCatalogo(beneficio);
		campa?aDao.actualizarCatalogo(campa?a);

	}

	@Override
	public List<BeneficioDTO> obtenerCatalogo(Integer idCampa?a) throws AppException, NotNullException,
			DataEmptyException, DateException, NumbersException, InstanceException {

		Campa?a camp = campa?aDao.buscar(idCampa?a);

		List<BeneficioDTO> catalogo = new ArrayList<BeneficioDTO>();

		for (Beneficio b : beneficioDao.ListarCatalogo(camp)) {

			catalogo.add(new BeneficioDTO(b.obtenerId(), b.obtenerNombreBeneficio(), b.obtenerPuntos()));
		}
		return catalogo;
	}

	@Override
	public Campa?aDTO obtenerCampa?aVigente()
			throws AppException, DateException, NotNullException, DataEmptyException, InstanceException {
		List<Campa?a> campa?as = campa?aDao.listarTodos();
		Campa?aDTO campa?aDTO = null;
		for (Campa?a camp : campa?as) {
			if (camp.obtenerFechaFin().isAfter(LocalDateTime.now())) {
				campa?aDTO = new Campa?aDTO(camp.obtenerId(), camp.obtenerNombreCampa?a(),
						camp.obtenerFechaInicio().toString(), camp.obtenerFechaFin().toString(),
						camp.obtenerDescripcion());
			}
		}
		return campa?aDTO;
	}

	public boolean residuoEstaDeclarado(ResiduoRetiradoDTO residuoRetiradoDto, Integer idOrden)
			throws AppException, InstanceException {
		List<ResiduoRetirado> residuosRetirados = new ArrayList<ResiduoRetirado>();
		OrdenDeRetiro orden = this.ordenDao.buscar(idOrden);

		TipoResiduo tipo = this.residuoDao.buscar(residuoRetiradoDto.obtenerTipo());
		try {
			residuosRetirados.add(new ResiduoRetirado(tipo, residuoRetiradoDto.obtenerCantidadKg()));
		} catch (NotNullException e) {
			// VERR
		}
		return this.estanTodosResiduosDeclarados(residuosRetirados, orden.obtenerPedido().obtenerId());
	}

	@Override
	public CiudadanoDTO obtenerCiudadanoSesion() {
		Ciudadano ciu = ciudadanoDao.buscar(sesion.obtenerUsuario());
		
		return new CiudadanoDTO(ciu.obtenerId(), ciu.obtenerNombre(),ciu.obtenerApellido(), ciu.obtenerDni(), ciu.obtenerPuntaje());
	}

	@Override
	public void asignarIdioma(ResourceBundle labels) {
		this.idioma=labels;
		
	}

	@Override
	public ResourceBundle obtenerIdioma() {
		return this.idioma;
	}

	@Override
	public boolean sesionIniciada() {
		if(sesion!=null) {
			return true;
		}
		return false;
	}

	@Override
	public void eliminarBeneficio(Integer id) throws AppException, InstanceException {
		this.beneficioDao.eliminar(id);
	}

}
