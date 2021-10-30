package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;

public interface PedidoRetiroDao {
	
	void crear(PedidoRetiro pedido);
	
	List<PedidoRetiro> buscar(Integer idVivienda);
	List<PedidoRetiro> buscarPorUsuario(Integer idUsuario);
	List<PedidoRetiro> listarTodos();
	List<ResiduoARetirar> buscarResiduosARetirar(Integer idPedido);
}
