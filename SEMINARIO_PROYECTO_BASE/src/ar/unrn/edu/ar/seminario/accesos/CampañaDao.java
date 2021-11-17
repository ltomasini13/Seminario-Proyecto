package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.modelo.Campaña;

public interface CampañaDao {

	void crear(Campaña camp)throws AppException;
	
	List<Campaña> listarTodos()throws DateException, NotNullException, DataEmptyException, AppException;

	Campaña buscar(Integer id);
	
	Campaña buscarCampaña();
	
	void actualizar(Campaña camp);

	void eliminar(Integer id);

	void eliminar(Campaña camp);
	
	void actualizarCatalogo(Campaña camp) throws AppException;
}
