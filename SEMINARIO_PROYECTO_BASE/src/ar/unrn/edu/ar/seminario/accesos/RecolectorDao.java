package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Recolector;

public interface RecolectorDao {

	void crear(Recolector recolector)throws DuplicateUniqueKeyException, SintaxisSQLException;
	
	List<Recolector> listarTodos()throws SintaxisSQLException;

	Recolector buscar(Integer id);
	
	void actualizar(Recolector recolector);

	void eliminar(Integer id);

	void eliminar(Recolector recolector);
}

