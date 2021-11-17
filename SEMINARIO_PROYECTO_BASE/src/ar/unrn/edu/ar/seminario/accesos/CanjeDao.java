package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Canje;
import ar.edu.unrn.seminario.modelo.Ciudadano;

public interface CanjeDao {

	void crear(Canje canje)throws AppException;
	
	List<Canje> listarTodos() throws DataEmptyException, NotNullException, NumbersException, AppException;

	Canje buscar(Integer id);
	
	void actualizar(Canje canje);

	void eliminar(Integer id);

	void eliminar(Canje canje);
	
	List<Canje> listarMisCanjes(Ciudadano ciudadano);
}
