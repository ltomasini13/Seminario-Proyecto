package ar.edu.unrn.seminario.api;

import java.util.List;

import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.StateException;

public interface IApi {

	void registrarUsuario(String username, String password, String email, String nombre, Integer rol);
	
	void registrarVivienda(String calle, int numero, String barrio, float latitud, float longitud, String nombreCiudadano, String apeCiudadano,
			String dniCiudadano);

	UsuarioDTO obtenerUsuario(String username);

	void eliminarUsuario(String username);

	List<RolDTO> obtenerRoles();

	List<RolDTO> obtenerRolesActivos();

	void guardarRol(Integer codigo, String descripción, boolean estado); // crear el objeto de dominio “Rol”

	RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

	void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

	void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

	List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

	void activarUsuario(String username) throws StateException; // recuperar el objeto Usuario, implementar el comportamiento de estado.

	void desactivarUsuario(String username) throws StateException ; // recuperar el objeto Usuario, implementar el comportamiento de estado.
	
	List<ViviendaDTO> obtenerViviendas(); //recupera todas las viviendas registradas
}
