package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.dto.CampañaDTO;
import ar.edu.unrn.seminario.dto.CiudadanoDTO;
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
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.exception.WasteException;
import ar.edu.unrn.seminario.exception.ZeroNegativeNumberException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.RegistroVivienda;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Ubicacion;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class MemoryApi implements IApi {

	private Map<Integer, Rol> roles = new HashMap<>();
	private Set<Usuario> usuarios = new HashSet<Usuario>();
	private Set<RegistroVivienda> viviendas = new HashSet<RegistroVivienda>();

	public MemoryApi() throws DataEmptyException, NotNullException, NumbersException {

		// datos iniciales
		this.roles.put(1, new Rol(1, "ADMIN"));
		this.roles.put(2, new Rol(2, "COMUNIDAD"));
		this.roles.put(3, new Rol(3, "GOBIERNO"));
		inicializarUsuarios();
		inicializarViviendas();
	}

	private void inicializarUsuarios() throws DataEmptyException, NotNullException {
		registrarUsuario("mcambarieri", "1234", "mcambarieri@unrn.edu.ar", "Mauro", 3);
		registrarUsuario("ldifabio", "1234", "ldifabio@unrn.edu.ar", "Lucas", 2);
		registrarUsuario("admin", "1234", "admin@unrn.edu.ar", "Admin", 1);

	}

	private void inicializarViviendas() throws NotNullException, DataEmptyException, NumbersException {
		this.registrarVivienda("Pedro Bronzetti", "450", null,"40.4532", "60.7645", "Laura", "Tomasini", "39887863");
		this.registrarVivienda("9 de julio", "222", null, "43.55", "54.6553", "Karen", "Ruiz", "39776583");
		this.registrarVivienda("Hilario Lagos", "433", null, "56.7", "76.6553", "Ana", "Rangnau", "38645363");
		
	}	
	@Override
	public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) throws DataEmptyException, NotNullException {

		Rol role = this.roles.get(rol);
		Usuario usuario = new Usuario(username, password, nombre, email, role);
		this.usuarios.add(usuario);
		
	}

	@Override
	public List<UsuarioDTO> obtenerUsuarios() {
		List<UsuarioDTO> dtos = new ArrayList<>();
		for (Usuario u : this.usuarios) {
			dtos.add(new UsuarioDTO(u.obtenerUsuario(), u.obtenerContrasena(), u.obtenerNombre(), u.obtenerEmail(),
					u.obtenerRol().obtenerNombre(),  u.obtenerEstado()));
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
			dtos.add(new RolDTO(r.obtenerCodigo(), r.obtenerNombre()));
		}
		return dtos;
	}

	@Override
	public List<RolDTO> obtenerRolesActivos() {
		List<RolDTO> dtos = new ArrayList<>();
		for (Rol r : this.roles.values()) {
			if (r.isActivo())
				dtos.add(new RolDTO(r.obtenerCodigo(), r.obtenerNombre()));
		}
		return dtos;
	}

	@Override
	public void guardarRol(Integer codigo, String descripcion, boolean estado) throws NotNullException, DataEmptyException {
		// TODO Auto-generated method stub
		Rol rol = new Rol(codigo, descripcion, estado);
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
			if (u.obtenerUsuario().equals(usuario))
				u.activar();
			//enviar mail
			//..
		}

	}

	@Override
	public void desactivarUsuario(String usuario) throws StateException {
		for (Usuario u : usuarios) {
			if (u.obtenerUsuario().equals(usuario))
				u.desactivar();

		}
	}

//	@Override
//	public List<ViviendaDTO> obtenerViviendas() {
//		List<ViviendaDTO> viviendasDTO=new ArrayList<ViviendaDTO>();
//		for(RegistroVivienda viv : this.viviendas) {
//			ViviendaDTO vivienda = new ViviendaDTO(viv.obtenerCalleVivienda(), viv.obtenerNroVivienda(), viv.obtenerBarrioVivienda(),
//					viv.obtenerLatitudVivienda(), viv.obtenerLongitudVivienda(),viv.obtenerNombreApellidoCiudadano(), viv.obtenerFechaYhora());
//			
//			viviendasDTO.add(vivienda);
//		}
//		return viviendasDTO;
//	}

	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud,
			String nombreCiudadano, String apeCiudadano, String dniCiudadano) throws NotNullException, DataEmptyException, NumbersException {
		
		int nro = Integer.parseInt(numero);
		double lat= Double.parseDouble(latitud);
		double longi= Double.parseDouble(longitud);
		
		Ubicacion ubicacion = new Ubicacion(calle,nro, barrio, lat, longi);
		Ciudadano ciudadano = new Ciudadano(nombreCiudadano, apeCiudadano, dniCiudadano, null);
		Vivienda vivienda = new Vivienda(ubicacion, ciudadano);
		RegistroVivienda regVivienda = new RegistroVivienda(LocalDateTime.now(), vivienda); 
		
		viviendas.add(regVivienda);
		
	}

//	@Override
//	public UsuarioDTO loguearUsuario(String username, String contrasena) throws SintaxisSQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<ViviendaDTO> obtenerViviendas(Usuario usuario) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void registrarCiudadano(String username, String password, String email, String nombre, Integer rol,
			String apellido, String dni) throws SintaxisSQLException, DataEmptyException, NotNullException,
			NumbersException, AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarResiduo(String tipo, String numero) throws NumbersException  {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void registrarPedidoRetiro(String fechaEmision, String cargaPesada, String observacion) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public List<PedidoRetiroDTO> obtenerPedidos() {
		// TODO Auto-generated method stub
		return null;
	}
//
//	@Override
//	public List<PedidoRetiroDTO> obtenerPedidos(Usuario usuario) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<ResiduoDTO> obtenerResiduos()
//			throws SintaxisSQLException, NotNullException, DataEmptyException, NumbersException {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void loguearUsuario(String username, String contrasena)
			throws SintaxisSQLException, AuthenticationException, NotNullException, DataEmptyException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean esUsuarioAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esUsuarioReciclador() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ViviendaDTO obtenerVivienda(Integer idVivienda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ViviendaDTO obtenerViviendaDelPedido(Integer idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PedidoRetiroDTO obtenerPedidoDeLaOrden(Integer idOrden) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cerrarSesion() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generarPedido(Integer id_vivienda, boolean cargaPesada, String observacion,
			List<ResiduoARetirarDTO> residuosARetirar)
			throws NotNullException, ZeroNegativeNumberException, EmptyListException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud)
			throws DataEmptyException, NumbersException, NotNullException, AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pedidoPendiente(Integer id_vivienda) throws CreationValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarRecolector(String nombre, String apellido, String dni, String email) throws NotNullException,
			DataEmptyException, DuplicateUniqueKeyException, SintaxisSQLException, NumbersException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RecolectorDTO> obtenerRecolectores() throws SintaxisSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResiduoARetirarDTO> obtenerResiduosARetirar(Integer idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResiduoRetiradoDTO> obtenerResiduosRetirados(Integer idVisita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResiduoRestanteDTO> obtenerResiduosRestantes(Integer idPedido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdenDeRetiroDTO> obtenerOrdenes() throws SintaxisSQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void generarOrden(Integer id_pedido) throws SintaxisSQLException, CreationValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelarOrden(Integer idOrden) throws StateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void concretarOrden(Integer idOrden) throws StateException, SintaxisSQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void asignarRecolector(Integer idOrden, Integer idRecolector) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarDueño(Integer idVivienda, String dni) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarDueño(Integer idVivienda, String nombreCiudadano, String apeCiudadano, String dniCiudadano)
			throws NotNullException, DataEmptyException, NumbersException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CiudadanoDTO> obtenerCiudadanos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CiudadanoDTO obtenerCiudadanoDeLaVivienda(Integer idVivienda) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarVisita(Integer idOrden, String observacion, List<ResiduoRetiradoDTO> residuosretiradosDTO)
			throws NotNullException, CreationValidationException, StateException, WasteException, CollectorException,
			SintaxisSQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<VisitaDTO> obtenerVisitas() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VisitaDTO> obtenerVisitasDeLaOrden(Integer idOrden) throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrdenDeRetiroDTO obtenerOrden(Integer idVisita) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarBeneficio(String nombre, String puntos)
			throws DataEmptyException, NotNullException, NumbersException, AppException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BeneficioDTO> obtenerBeneficios()
			throws AppException, DataEmptyException, NotNullException, NumbersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarCampaña(String nombre, String descripcion)
			throws DataEmptyException, NotNullException, AppException, DateException, CreationValidationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CampañaDTO> obtenerCampañas() throws AppException, DataEmptyException, NotNullException, DateException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean residuoEstaDeclarado(ResiduoRetiradoDTO residuoRetiradoDto, Integer idOrden) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double calcularResiduoRestanteDelResiduo(ResiduoRetiradoDTO residuoRetiradoDTO, Integer idOrden) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizarPuntaje(double puntaje) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BeneficioDTO> obtenerCatalogo(Integer idCampaña)
			throws AppException, NotNullException, DataEmptyException, DateException, NumbersException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CampañaDTO obtenerCampañaVigente() throws AppException, DateException, NotNullException, DataEmptyException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void agregarBeneficio(Integer idCampaña, Integer idBeneficio)
			throws AppException, CreationValidationException, DataEmptyException, NotNullException, NumbersException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ViviendaDTO> obtenerViviendas() throws EmptyListException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ResiduoDTO> obtenerResiduos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void realizarCanje(String nombre, String dni)
			throws NumbersException, SintaxisSQLException, NotNullException, AppException, DataEmptyException {
		// TODO Auto-generated method stub
		
	}

}
