package ar.edu.unrn.seminario.api;

import java.util.List;


import ar.edu.unrn.seminario.dto.CiudadanoDTO;

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
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
<<<<<<< Updated upstream
<<<<<<< Updated upstream
import ar.edu.unrn.seminario.exception.WasteException;
import ar.edu.unrn.seminario.exception.ZeroNegativeNumberException;
import ar.edu.unrn.seminario.exception.CreationValidationException;
import ar.edu.unrn.seminario.modelo.ResiduoRestante;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Visita;

=======
import ar.edu.unrn.seminario.exception.UnfinishedException;
>>>>>>> Stashed changes
=======
import ar.edu.unrn.seminario.exception.UnfinishedException;
>>>>>>> Stashed changes

public interface IApi {

	void registrarUsuario(String username, String password, String email, String nombre, Integer rol) throws DataEmptyException, NotNullException, SintaxisSQLException;
	void registrarCiudadano(String username, String password, String email, String nombre, Integer rol, String apellido, String dni) throws SintaxisSQLException, DataEmptyException, NotNullException, NumbersException, AuthenticationException;
	
	void loguearUsuario(String username, String contrasena) throws SintaxisSQLException, AuthenticationException, NotNullException, DataEmptyException;
	
	void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud, String nombreCiudadano, String apeCiudadano,
			String dniCiudadano)throws NotNullException, DataEmptyException, NumbersException, SintaxisSQLException, AuthenticationException;


	void registrarResiduo(String tipo, String numero) throws NumbersException, NotNullException, DataEmptyException, DuplicateUniqueKeyException, SintaxisSQLException;
	
	
	boolean esUsuarioAdmin();
	boolean esUsuarioReciclador();
	UsuarioDTO obtenerUsuario(String username) throws SintaxisSQLException, NotNullException, DataEmptyException;
	void eliminarUsuario(String username);

	List<RolDTO> obtenerRoles() throws SintaxisSQLException;

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripción, boolean estado) throws NotNullException, DataEmptyException; // crear el objeto de dominio “Rol”

	RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

	void activarUsuario(String username) throws StateException; // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username) throws StateException ; // recuperar el objeto Usuario, implementar el comportamiento de estado.
	

	List<ViviendaDTO> obtenerViviendas() throws EmptyListException; //recupera todas las viviendas registradas
	ViviendaDTO obtenerVivienda(Integer idVivienda);
	ViviendaDTO obtenerViviendaDelPedido(Integer idPedido);
	List<PedidoRetiroDTO> obtenerPedidos() throws EmptyListException;
	PedidoRetiroDTO obtenerPedidoDeLaOrden(Integer idOrden);

	List<ResiduoDTO> obtenerResiduos();
	void cerrarSesion(); 				
	
	void generarPedido(Integer id_vivienda, boolean cargaPesada, String observacion, List<ResiduoARetirarDTO> residuosARetirar) throws NotNullException, ZeroNegativeNumberException, EmptyListException;
	void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud) throws DataEmptyException, NumbersException, NotNullException, AuthenticationException;

	void pedidoPendiente(Integer id_vivienda) throws CreationValidationException; //Dispara un error si hay algun pedido que no concluyó para la vievienda pasada por parámetro

	void registrarRecolector(String nombre, String apellido, String dni, String email) throws  NotNullException, DataEmptyException, DuplicateUniqueKeyException, SintaxisSQLException, NumbersException;
	
	List<RecolectorDTO> obtenerRecolectores() throws SintaxisSQLException ;
	
	List<ResiduoARetirarDTO> obtenerResiduosARetirar(Integer idPedido);
	List<ResiduoRetiradoDTO> obtenerResiduosRetirados(Integer idVisita);
	List<ResiduoRestanteDTO> obtenerResiduosRestantes(Integer idPedido);
	List<OrdenDeRetiroDTO> obtenerOrdenes() throws SintaxisSQLException;
	void generarOrden(Integer id_pedido)throws SintaxisSQLException, CreationValidationException ;
	void cancelarOrden(Integer idOrden) throws StateException;
	void concretarOrden(Integer idOrden) throws StateException, SintaxisSQLException;
	
	void asignarRecolector(Integer idOrden, Integer idRecolector);
	void cambiarDueño(Integer idVivienda, String dni);
	void cambiarDueño(Integer idVivienda, String nombreCiudadano, String apeCiudadano, String dniCiudadano) throws NotNullException, DataEmptyException, NumbersException;
	List<CiudadanoDTO> obtenerCiudadanos(); 
	CiudadanoDTO obtenerCiudadanoDeLaVivienda(Integer idVivienda);
	
	void agregarVisita(Integer idOrden, String observacion, List<ResiduoRetiradoDTO> residuosretiradosDTO) throws NotNullException, CreationValidationException, StateException, WasteException, CollectorException, SintaxisSQLException;
	List<VisitaDTO> obtenerVisitas() throws EmptyListException;
	List<VisitaDTO> obtenerVisitasDeLaOrden(Integer idOrden) throws EmptyListException;
	OrdenDeRetiroDTO obtenerOrden(Integer idVisita);
	void registrarBeneficio(String nombre, String puntos) throws DataEmptyException, NotNullException, NumbersException, AppException;

	List<BeneficioDTO> obtenerBeneficios()throws AppException, DataEmptyException, NotNullException, NumbersException;
	
<<<<<<< Updated upstream
<<<<<<< Updated upstream
	void registrarCampaña(String nombre, String descripcion) throws DataEmptyException, NotNullException, AppException, DateException, CreationValidationException;
	
	List<CampañaDTO> obtenerCampañas()throws AppException, DataEmptyException, NotNullException, DateException;
	
	boolean residuoEstaDeclarado(ResiduoRetiradoDTO residuoRetiradoDto, Integer idOrden);
	double calcularResiduoRestanteDelResiduo(ResiduoRetiradoDTO residuoRetiradoDTO, Integer idOrden);

	
	void realizarCanje(Integer idBeneficio, String dni) throws NumbersException, SintaxisSQLException, NotNullException, AppException;
	
	void actualizarPuntaje(double puntaje);
	
	List<BeneficioDTO> obtenerCatalogo(Integer idCampaña) throws AppException, NotNullException, DataEmptyException, DateException, NumbersException;
	void agregarBeneficio(Integer idCampaña, Integer idBeneficio) throws AppException, NotNullException, DataEmptyException, DateException, NumbersException, CreationValidationException;
	
	CampañaDTO obtenerCampañaVigente() throws AppException, DateException, NotNullException, DataEmptyException;

=======
	void registrarCampaña(String nombre, String descripcion) throws DataEmptyException, NotNullException, AppException, DateException;
	
	List<CampañaDTO> obtenerCampañas()throws AppException, NotNullException, DateException, DataEmptyException;
	
	
	void realizarCanje(Integer idBeneficio, String dni);
	
	void actualizarPuntaje(double puntaje);
	
	List<BeneficioDTO> obtenerCatalogo(Integer idCampaña) throws AppException, NotNullException, DataEmptyException, DateException, NumbersException;
	void agregarBeneficio(Integer idCampaña, Integer idBeneficio) throws AppException;
>>>>>>> Stashed changes
=======
	void registrarCampaña(String nombre, String descripcion) throws DataEmptyException, NotNullException, AppException, DateException;
	
	List<CampañaDTO> obtenerCampañas()throws AppException, NotNullException, DateException, DataEmptyException;
	
	
	void realizarCanje(Integer idBeneficio, String dni);
	
	void actualizarPuntaje(double puntaje);
	
	List<BeneficioDTO> obtenerCatalogo(Integer idCampaña) throws AppException, NotNullException, DataEmptyException, DateException, NumbersException;
	void agregarBeneficio(Integer idCampaña, Integer idBeneficio) throws AppException;
>>>>>>> Stashed changes
}
