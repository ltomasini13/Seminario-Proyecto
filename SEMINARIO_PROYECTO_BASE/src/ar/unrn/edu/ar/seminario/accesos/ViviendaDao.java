package ar.unrn.edu.ar.seminario.accesos;
import java.util.List;

import ar.edu.unrn.seminario.modelo.Vivienda;
public interface ViviendaDao {
	void crear(Vivienda vivienda);

	void modificar(Vivienda vivienda);

	List<Vivienda> listarTodas();
}
