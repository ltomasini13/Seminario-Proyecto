package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.Beneficio;
import ar.edu.unrn.seminario.modelo.Campaña;

public interface BeneficioDao {

	void crear(Beneficio beneficio)throws AppException;
	
	List<Beneficio> listarTodos()throws AppException,  DataEmptyException, NotNullException, NumbersException;

	Beneficio buscar(Integer id);
	
	void actualizar(Beneficio beneficio);

	void eliminar(Integer id);

	void eliminar(Beneficio beneficio);
	
	List<Beneficio> ListarCatalogo(Campaña camp) throws NotNullException, DataEmptyException, DateException, NumbersException, AppException;
<<<<<<< Updated upstream
<<<<<<< Updated upstream

	List<Beneficio> buscarNombreBeneficio(String nombre) throws AppException, DataEmptyException, NotNullException, NumbersException;
=======
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
}
