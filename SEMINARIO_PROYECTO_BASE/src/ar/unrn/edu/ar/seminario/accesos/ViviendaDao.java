package ar.unrn.edu.ar.seminario.accesos;
import java.util.List;

import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.edu.unrn.seminario.modelo.Vivienda;
public interface ViviendaDao {
	void crear(Vivienda vivienda);

	Vivienda buscar(Integer idVivienda);
	
	Vivienda buscar(Vivienda vivienda);
	
	List<Vivienda> listarTodas();

	void crearParaRecic(Vivienda vivienda);

	void actualizarYcrear(Vivienda vivienda);

	void actualizar(Vivienda vivienda);
}
