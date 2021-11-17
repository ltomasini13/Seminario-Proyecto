package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;
import ar.edu.unrn.seminario.modelo.ResiduoRestante;
import ar.edu.unrn.seminario.modelo.ResiduoRetirado;

public interface ResiduosDao {
	List<ResiduoARetirar> listarTodosResiduosARetirar();
	List<ResiduoRetirado> buscarResiduosRetirados(Integer idVisita);
	List<ResiduoRetirado> listarTodosResiduosRetirados();
	List<ResiduoRetirado> buscarResiduosRetiradosEnTotal(Integer idOrden);
	List<ResiduoARetirar> buscarResiduosARetirar(Integer idPedido);
	
}
