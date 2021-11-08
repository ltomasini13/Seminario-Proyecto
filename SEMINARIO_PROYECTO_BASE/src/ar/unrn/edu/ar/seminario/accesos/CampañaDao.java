package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.modelo.Campaña;

public interface CampañaDao {

	void crear(Campaña camp)throws AppException;
	
	List<Campaña> listarTodos()throws AppException,  DataEmptyException, NotNullException;

	Campaña buscar(Integer id);
	
	void actualizar(Campaña camp);

	void eliminar(Integer id);

	void eliminar(Campaña camp);
}
