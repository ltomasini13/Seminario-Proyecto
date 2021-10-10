package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.modelo.PedidoRetiro;

public interface PedidoRetiroDao {
	
	void crear(PedidoRetiro pedido);
	
	List<PedidoRetiro> listarTodos();
}
