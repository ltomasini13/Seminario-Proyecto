package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Beneficio;
import ar.edu.unrn.seminario.modelo.Campaña;

public interface BeneficioDao {

	void crear(Beneficio beneficio)throws AppException, InstanceException;
	
	List<Beneficio> listarTodos()throws AppException, InstanceException;

	Beneficio buscar(Integer id) throws AppException, InstanceException;
	
	void actualizar(Beneficio beneficio);

	void eliminar(Integer id) throws AppException, InstanceException;

	void eliminar(Beneficio beneficio);
	
	List<Beneficio> ListarCatalogo(Campaña camp) throws AppException, InstanceException;

	List<Beneficio> buscarNombreBeneficio(String nombre) throws AppException, InstanceException;

	Beneficio buscarBeneficio(String nombre) throws AppException, InstanceException;

	
}
