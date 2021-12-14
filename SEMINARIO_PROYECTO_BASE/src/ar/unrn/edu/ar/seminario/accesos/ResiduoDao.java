package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.TipoResiduo;

public interface ResiduoDao {

	void crear(TipoResiduo residuo) throws AppException, InstanceException;
	
	List<TipoResiduo> listarTodos() throws AppException, InstanceException;

	TipoResiduo buscar(String tipoResiduo) throws AppException, InstanceException;
	
	void actualizar(TipoResiduo residuo);

	void eliminar(Integer id);

	void eliminar(TipoResiduo residuo);
}
