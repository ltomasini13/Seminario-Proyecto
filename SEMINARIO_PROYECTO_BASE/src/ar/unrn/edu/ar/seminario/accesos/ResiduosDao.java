package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.ResiduoRestante;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;

public interface ResiduosDao {
	List<ResiduoARetirar> listarTodosResiduosARetirar() throws AppException, InstanceException;
	List<ResiduoRetirado> buscarResiduosRetirados(Integer idVisita) throws AppException, InstanceException;
	List<ResiduoRetirado> listarTodosResiduosRetirados() throws AppException, InstanceException;
	List<ResiduoRetirado> buscarResiduosRetiradosEnTotal(Integer idOrden) throws AppException, InstanceException;
	List<ResiduoARetirar> buscarResiduosARetirar(Integer idPedido) throws AppException, InstanceException;
	
}
