package ar.unrn.edu.ar.seminario.accesos;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Ciudadano;

public interface CiudadanoDao {
	void crear(Ciudadano ciudadano) throws SintaxisSQLException;
	
	Ciudadano buscar(String dni) throws NotNullException, DataEmptyException, NumbersException, SintaxisSQLException;
	
}
