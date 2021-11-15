package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public interface CiudadanoDao {
	void crear(Ciudadano ciudadano) throws SintaxisSQLException;
	
	Ciudadano buscar(String dni);
	
	Ciudadano buscar(Usuario usuario);
	List<Vivienda> listarMisViviendas(Ciudadano ciudadano);
	List<Ciudadano> listarTodos();

	
	
}
