package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.Visita;


public interface VisitaDao {
void crear(Visita visita);
	
	Visita buscar(Integer idVisita);

	List<Visita> listarTodas();

	List<Visita> listarTodas(OrdenDeRetiro orden);

}
