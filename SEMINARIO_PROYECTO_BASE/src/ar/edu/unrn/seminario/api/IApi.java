package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
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
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Usuario;

public interface IApi {

	void registrarUsuario(String username, String password, String email, String nombre, Integer rol) throws DataEmptyException, NotNullException, SintaxisSQLException;
	void registrarCiudadano(String username, String password, String email, String nombre, Integer rol, String apellido, String dni) throws SintaxisSQLException, DataEmptyException, NotNullException, NumbersException, AuthenticationException;
	
	void loguearUsuario(String username, String contrasena) throws SintaxisSQLException, AuthenticationException, NotNullException, DataEmptyException;
	
	void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud, String nombreCiudadano, String apeCiudadano,
			String dniCiudadano)throws NotNullException, DataEmptyException, NumbersException, SintaxisSQLException, DuplicateUniqueKeyException;
<<<<<<< HEAD
=======

	void registrarResiduo(String tipo, String numero) throws NumbersException, NotNullException, DataEmptyException, DuplicateUniqueKeyException, SintaxisSQLException;
>>>>>>> 8b000275cd62562d603c30581e4c5c1694e0bb84
	
	
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
	
<<<<<<< HEAD
	List<ViviendaDTO> obtenerViviendas() throws EmptyListException; //recupera todas las viviendas registradas
	
=======
	List<ViviendaDTO> obtenerViviendas(); //recupera todas las viviendas registradas
	List<ViviendaDTO> obtenerViviendas(Usuario usuario); //recupera todas las viviendas que tiene registradas ese usuario

	void registrarPedidoRetiro(String fechaEmision, String cargaPesada, String observacion);
	
	List<PedidoRetiroDTO> obtenerPedidos();
	List<PedidoRetiroDTO> obtenerPedidos(Usuario usuario);
	
	List<ResiduoDTO> obtenerResiduos()throws SintaxisSQLException, NotNullException, DataEmptyException, NumbersException;
>>>>>>> 8b000275cd62562d603c30581e4c5c1694e0bb84
	
}
