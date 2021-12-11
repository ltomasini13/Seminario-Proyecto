package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public interface CiudadanoDao {
	void crear(Ciudadano ciudadano) throws AppException, InstanceException ;
	void actualizar(Ciudadano ciudadano) throws InstanceException, AppException;
	Ciudadano buscar(String dni) throws AppException, InstanceException;
	Ciudadano buscarPorVivienda(Integer idVivienda) throws AppException, InstanceException;
	Ciudadano buscar(Usuario usuario);
	
	List<Vivienda> listarMisViviendas(Ciudadano ciudadano) throws AppException, InstanceException;
	List<Ciudadano> listarTodos() throws AppException, InstanceException;

	
	
}
