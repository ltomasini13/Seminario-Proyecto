package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Recolector;

public class OrdenDeRetiroDao {

void crear(Orden)throws SintaxisSQLException;
	
	List<Recolector> listarTodos()throws SintaxisSQLException;

	Recolector buscar(String nombreDeUsuario);
	
	void actualizar(Recolector recolector);

	void eliminar(Integer id);

	void eliminar(Recolector recolector);
}
