package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.modelo.Campaña;

public interface CampañaDao {

	void crear(Campaña camp)throws AppException, InstanceException;
	
	List<Campaña> listarTodos()throws AppException, InstanceException;

	Campaña buscar(Integer id) throws AppException, InstanceException;
	
	Campaña buscarCampaña();
	
	void actualizar(Campaña camp);

	void eliminar(Integer id);

	void eliminar(Campaña camp);
	
	void actualizarCatalogo(Campaña camp) throws AppException, InstanceException;
}
