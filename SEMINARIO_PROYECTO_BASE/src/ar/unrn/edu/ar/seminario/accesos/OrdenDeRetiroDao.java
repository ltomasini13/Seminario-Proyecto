package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.PedidoRetiro;

public interface OrdenDeRetiroDao {

	void crear(OrdenDeRetiro orden) throws AppException, InstanceException;
	
	List<OrdenDeRetiro> listarTodos() throws AppException, InstanceException;

	OrdenDeRetiro buscar(Integer id) throws AppException, InstanceException;
	OrdenDeRetiro buscarPorVisita(Integer idVisita) throws AppException, InstanceException;
	OrdenDeRetiro buscarOrdenPorPedido(PedidoRetiro pedido) throws AppException, InstanceException;
	void actualizar(OrdenDeRetiro orden) throws AppException, InstanceException;

	void eliminar(Integer id);

	void eliminar(OrdenDeRetiro orden);
	
	List<OrdenDeRetiro> buscarPedido(Integer idPedido) throws AppException, InstanceException;
}
