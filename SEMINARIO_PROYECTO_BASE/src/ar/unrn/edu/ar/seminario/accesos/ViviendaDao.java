package ar.unrn.edu.ar.seminario.accesos;
import java.util.List;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Vivienda;
public interface ViviendaDao {
	void crear(Vivienda vivienda) throws SintaxisSQLException, DuplicateUniqueKeyException;

	Vivienda buscar(Integer idVivienda);
	void modificar(Vivienda vivienda);

	List<Vivienda> listarTodas();
}
