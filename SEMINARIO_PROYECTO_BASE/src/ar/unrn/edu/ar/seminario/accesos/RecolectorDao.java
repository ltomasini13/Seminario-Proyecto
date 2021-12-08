package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Recolector;

public interface RecolectorDao {

	void crear(Recolector recolector) throws AppException, InstanceException;
	
	List<Recolector> listarTodos()throws SintaxisSQLException, AppException, InstanceException;

	Recolector buscar(Integer id) throws AppException, InstanceException;
	
	void actualizar(Recolector recolector);

	void eliminar(Integer id);

	void eliminar(Recolector recolector);
}

