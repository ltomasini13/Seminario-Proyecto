package ar.edu.unrn.seminario.api;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unrn.seminario.dto.PedidoRetiroDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.AuthenticationException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.Usuario;

public interface IApi {

	void registrarUsuario(String username, String password, String email, String nombre, Integer rol) throws DataEmptyException, NotNullException, SintaxisSQLException;
	void registrarCiudadano(String username, String password, String email, String nombre, Integer rol, String apellido, String dni) throws SintaxisSQLException, DataEmptyException, NotNullException, NumbersException, AuthenticationException;
	
	UsuarioDTO loguearUsuario(String username, String contrasena) throws SintaxisSQLException, AuthenticationException, NotNullException, DataEmptyException;
	
	void registrarVivienda(String calle, String numero, String barrio, String latitud, String longitud, String nombreCiudadano, String apeCiudadano,
			String dniCiudadano)throws NotNullException, DataEmptyException, NumbersException, SintaxisSQLException, DuplicateUniqueKeyException;

	void registrarResiduo(String tipo, String numero) throws NumbersException, NotNullException, DataEmptyException, DuplicateUniqueKeyException, SintaxisSQLException;
	
	UsuarioDTO obtenerUsuario(String username) throws SintaxisSQLException, NotNullException, DataEmptyException;

	void eliminarUsuario(String username);

	List<RolDTO> obtenerRoles() throws SintaxisSQLException;

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripci�n, boolean estado) throws NotNullException, DataEmptyException; // crear el objeto de dominio �Rol�

	RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

	void activarUsuario(String username) throws StateException; // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username) throws StateException ; // recuperar el objeto Usuario, implementar el comportamiento de estado.
	
	List<ViviendaDTO> obtenerViviendas(); //recupera todas las viviendas registradas
	List<ViviendaDTO> obtenerViviendas(Usuario usuario); //recupera todas las viviendas que tiene registradas ese usuario

	void registrarPedidoRetiro(String fechaEmision, String cargaPesada, String observacion, String calle, String numero, 
			String barrio, String latitud, String longitud,  String nombre, String apellido);
	
	List<PedidoRetiroDTO> obtenerPedidos();
	List<PedidoRetiroDTO> obtenerPedidos(Usuario usuario);
	
}
