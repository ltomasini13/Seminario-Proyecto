package ar.edu.unrn.seminario.api;

import java.util.List;
import java.util.ResourceBundle;

import ar.edu.unrn.seminario.dto.CiudadanoDTO;

import ar.edu.unrn.seminario.dto.BeneficioDTO;
import ar.edu.unrn.seminario.dto.CampañaDTO;
import ar.edu.unrn.seminario.dto.CanjeDTO;
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
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.modelo.ResiduoRestante;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Visita;

public interface IApi {
	void asignarIdioma( ResourceBundle labels);
	ResourceBundle obtenerIdioma();
	void registrarUsuario(String username, String password, String email, String nombre, Integer rol) throws DataEmptyException, NotNullException, AppException, InstanceException;
	void registrarCiudadano(String username, String password, String email, String nombre, Integer rol, String apellido, String dni) throws DataEmptyException, NotNullException, NumbersException, AuthenticationException, AppException, InstanceException;
	
	void loguearUsuario(String username, String contrasena) throws  AuthenticationException, NotNullException, DataEmptyException, AppException, InstanceException;
	
	void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud, String nombreCiudadano, String apeCiudadano,
			String dniCiudadano)throws NotNullException, DataEmptyException, NumbersException, AuthenticationException, AppException, InstanceException;


	void registrarResiduo(String tipo, String numero) throws NumbersException, NotNullException, DataEmptyException, DuplicateUniqueKeyException, AppException, InstanceException;
	
	
	boolean esUsuarioAdmin();
	boolean esUsuarioReciclador();
	UsuarioDTO obtenerUsuario(String username) throws NotNullException, DataEmptyException, AppException, InstanceException;
	void eliminarUsuario(String username);

	List<RolDTO> obtenerRoles() throws AppException, InstanceException;

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripción, boolean estado) throws NotNullException, DataEmptyException; // crear el objeto de dominio “Rol”

	RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios() throws AppException, InstanceException; // recuperar todos los usuarios

	void activarUsuario(String username) throws StateException; // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username) throws StateException ; // recuperar el objeto Usuario, implementar el comportamiento de estado.
	

	List<ViviendaDTO> obtenerViviendas() throws EmptyListException, AppException, InstanceException; //recupera todas las viviendas registradas
	ViviendaDTO obtenerVivienda(Integer idVivienda) throws AppException, InstanceException;
	ViviendaDTO obtenerViviendaDelPedido(Integer idPedido) throws AppException, InstanceException;
	List<PedidoRetiroDTO> obtenerPedidos() throws EmptyListException, AppException, InstanceException;
	PedidoRetiroDTO obtenerPedidoDeLaOrden(Integer idOrden) throws AppException, InstanceException;

	List<ResiduoDTO> obtenerResiduos() throws AppException, InstanceException;
	void cerrarSesion(); 				
	
	void generarPedido(Integer id_vivienda, boolean cargaPesada, String observacion, List<ResiduoARetirarDTO> residuosARetirar) throws NotNullException, ZeroNegativeNumberException, EmptyListException, AppException, InstanceException;
	void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud) throws DataEmptyException, NumbersException, NotNullException, AuthenticationException, AppException, InstanceException;

	void pedidoPendiente(Integer id_vivienda) throws CreationValidationException, AppException, InstanceException; //Dispara un error si hay algun pedido que no concluyó para la vievienda pasada por parámetro

	void registrarRecolector(String nombre, String apellido, String dni, String email) throws  NotNullException, DataEmptyException, DuplicateUniqueKeyException, NumbersException, AppException, InstanceException;
	
	List<RecolectorDTO> obtenerRecolectores() throws AppException, InstanceException ;
	
	List<ResiduoARetirarDTO> obtenerResiduosARetirar(Integer idPedido) throws AppException, InstanceException;
	List<ResiduoRetiradoDTO> obtenerResiduosRetirados(Integer idVisita) throws AppException, InstanceException;
	List<ResiduoRestanteDTO> obtenerResiduosRestantes(Integer idPedido) throws AppException, InstanceException;
	List<OrdenDeRetiroDTO> obtenerOrdenes() throws AppException, InstanceException;
	void generarOrden(Integer id_pedido)throws  CreationValidationException, AppException, InstanceException ;
	void cancelarOrden(Integer idOrden) throws StateException, AppException, InstanceException;
	void concretarOrden(Integer idOrden) throws StateException, AppException, InstanceException;
	
	void asignarRecolector(Integer idOrden, Integer idRecolector) throws AppException, InstanceException;
	void cambiarDueño(Integer idVivienda, String dni) throws AppException, InstanceException;
	void cambiarDueño(Integer idVivienda, String nombreCiudadano, String apeCiudadano, String dniCiudadano) throws NotNullException, DataEmptyException, NumbersException, AppException, InstanceException;
	List<CiudadanoDTO> obtenerCiudadanos() throws AppException, InstanceException; 
	CiudadanoDTO obtenerCiudadanoDeLaVivienda(Integer idVivienda) throws AppException, InstanceException;
	
	void agregarVisita(Integer idOrden, String observacion, List<ResiduoRetiradoDTO> residuosretiradosDTO) throws NotNullException, CreationValidationException, StateException, WasteException, CollectorException,AppException, InstanceException;
	List<VisitaDTO> obtenerVisitas() throws EmptyListException, AppException, InstanceException;
	List<VisitaDTO> obtenerVisitasDeLaOrden(Integer idOrden) throws EmptyListException, AppException, InstanceException;
	OrdenDeRetiroDTO obtenerOrden(Integer idVisita) throws AppException, InstanceException;
	void registrarBeneficio(String nombre, String puntos) throws DataEmptyException, NotNullException, NumbersException, AppException, InstanceException;

	List<BeneficioDTO> obtenerBeneficios()throws AppException, DataEmptyException, NotNullException, NumbersException, InstanceException;
	
	void registrarCampaña(String nombre, String descripcion) throws DataEmptyException, NotNullException, AppException, DateException, CreationValidationException, InstanceException;
	
	List<CampañaDTO> obtenerCampañas()throws AppException, DataEmptyException, NotNullException, DateException, InstanceException;
	
	boolean residuoEstaDeclarado(ResiduoRetiradoDTO residuoRetiradoDto, Integer idOrden) throws AppException, InstanceException;
	double calcularResiduoRestanteDelResiduo(ResiduoRetiradoDTO residuoRetiradoDTO, Integer idOrden) throws AppException, InstanceException;
	
	CiudadanoDTO obtenerCiudadanoSesion();

	void realizarCanje(String nombreBeneficio, String dni) throws NumbersException, NotNullException, AppException, DataEmptyException, InstanceException;
	void realizarCanje(String nombreBeneficio) throws AppException, InstanceException, NumbersException, NotNullException;
	List<CanjeDTO> obtenerCanjes() throws AppException, InstanceException, EmptyListException;
	void actualizarPuntaje(double puntaje);
	List<BeneficioDTO> obtenerCatalogo(Integer idCampaña) throws AppException, NotNullException, DataEmptyException, DateException, NumbersException, InstanceException;
	CampañaDTO obtenerCampañaVigente() throws AppException, DateException, NotNullException, DataEmptyException, InstanceException;
	void agregarBeneficio(Integer idCampaña, Integer idBeneficio) throws AppException, CreationValidationException, DataEmptyException, NotNullException, NumbersException, InstanceException;
	


}
