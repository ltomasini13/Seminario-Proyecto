package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Beneficio;

public interface BeneficioDao {

	void crear(Beneficio beneficio)throws AppException;
	
	List<Beneficio> listarTodos()throws AppException,  DataEmptyException, NotNullException, NumbersException;

	Beneficio buscar(String benefificio);
	
	void actualizar(Beneficio beneficio);

	void eliminar(Integer id);

	void eliminar(Beneficio beneficio);
}
