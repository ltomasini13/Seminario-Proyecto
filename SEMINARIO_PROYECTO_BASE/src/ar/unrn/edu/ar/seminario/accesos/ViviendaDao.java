package ar.unrn.edu.ar.seminario.accesos;
import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Vivienda;
public interface ViviendaDao {
	void crear(Vivienda vivienda) throws AppException, InstanceException;

	Vivienda buscar(Integer idVivienda) throws AppException, InstanceException;
	
	Vivienda buscar(Vivienda vivienda) throws AppException, InstanceException;
	
	Vivienda buscarPorPedido(Integer idPedido) throws AppException, InstanceException;
	
	List<Vivienda> listarTodas() throws AppException, InstanceException;

	void crearParaRecic(Vivienda vivienda) throws AppException, InstanceException;

	void actualizarYcrear(Vivienda vivienda) throws AppException, InstanceException;

	void actualizar(Vivienda vivienda) throws AppException, InstanceException;
}
