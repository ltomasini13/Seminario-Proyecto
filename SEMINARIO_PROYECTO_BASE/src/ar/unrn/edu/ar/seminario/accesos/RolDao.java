package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Rol;

public interface RolDao {
	void crear(Rol rol);
	
	List<Rol> listarTodos() throws AppException, InstanceException ;
	
	
	Rol obtenerRol(Integer codigo) throws AppException, InstanceException;
}
