package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.modelo.Campa�a;

public interface Campa�aDao {

	void crear(Campa�a camp)throws AppException;
	
	List<Campa�a> listarTodos()throws DateException, NotNullException, DataEmptyException, AppException;

	Campa�a buscar(Integer id);
	
	Campa�a buscarCampa�a();
	
	void actualizar(Campa�a camp);

	void eliminar(Integer id);

	void eliminar(Campa�a camp);
	
	void actualizarCatalogo(Campa�a camp) throws AppException;
}
