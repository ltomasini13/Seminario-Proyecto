package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;

public interface OrdenDeRetiroDao {

	void crear(OrdenDeRetiro orden)throws SintaxisSQLException;
	
	List<OrdenDeRetiro> listarTodos()throws SintaxisSQLException;

	OrdenDeRetiro buscar(Integer id);
	
	void actualizar(OrdenDeRetiro orden);

	void eliminar(Integer id);

	void eliminar(OrdenDeRetiro orden);
	
	List<OrdenDeRetiro> buscarPedido(Integer idPedido);
}
