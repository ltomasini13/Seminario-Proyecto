package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public interface UsuarioDao {
	void crear(Usuario usuario);
	
	List<Usuario> listarTodos();
	
	Usuario buscar(String nombreDeUsuario) throws NotNullException, DataEmptyException;


}
