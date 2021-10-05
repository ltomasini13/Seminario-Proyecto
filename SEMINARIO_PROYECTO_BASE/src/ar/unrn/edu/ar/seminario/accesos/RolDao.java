package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Rol;

public interface RolDao {
	void crear(Rol rol);
	
	List<Rol> listarTodos() throws SintaxisSQLException;
	
	
	Rol obtenerRol(Integer codigo) throws SintaxisSQLException;
}
