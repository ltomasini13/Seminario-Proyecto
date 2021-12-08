package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;

public interface PedidoRetiroDao {
	
	void crear(PedidoRetiro pedido) throws AppException, InstanceException;
	void actualizar(PedidoRetiro pedido) throws AppException, InstanceException;
	List<PedidoRetiro> buscar(Integer idVivienda) throws AppException, InstanceException;
	List<PedidoRetiro> buscarPorUsuario(Integer idUsuario) throws AppException, InstanceException;
	List<PedidoRetiro> listarTodos() throws AppException, InstanceException;
	List<ResiduoARetirar> buscarResiduosARetirar(Integer idPedido) throws AppException, InstanceException;
	PedidoRetiro buscarPedido(Integer idPedido) throws AppException, InstanceException;
	PedidoRetiro buscarPorOrden(Integer idOrden) throws AppException, InstanceException;
}
