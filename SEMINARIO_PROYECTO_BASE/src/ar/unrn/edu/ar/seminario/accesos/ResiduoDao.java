package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.TipoResiduo;

public interface ResiduoDao {

	void crear(TipoResiduo residuo)throws DuplicateUniqueKeyException, SintaxisSQLException;
	
	List<TipoResiduo> listarTodos()throws SintaxisSQLException;

	TipoResiduo buscar(String nombreDeUsuario);
	
	void actualizar(TipoResiduo residuo);

	void eliminar(Long id);

	void eliminar(TipoResiduo residuo);
}
