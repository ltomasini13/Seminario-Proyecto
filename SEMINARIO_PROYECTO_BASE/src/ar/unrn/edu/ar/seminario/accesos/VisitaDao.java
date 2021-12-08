package ar.unrn.edu.ar.seminario.accesos;

import java.util.List;

import ar.edu.unrn.seminario.exception.AppException;
import ar.edu.unrn.seminario.exception.InstanceException;
import ar.edu.unrn.seminario.modelo.Ciudadano;
import ar.edu.unrn.seminario.modelo.OrdenDeRetiro;
import ar.edu.unrn.seminario.modelo.Visita;


public interface VisitaDao {
void crear(Visita visita) throws AppException, InstanceException;
	
	Visita buscar(Integer idVisita);

	List<Visita> listarTodas() throws AppException, InstanceException;

	List<Visita> listarTodas(OrdenDeRetiro orden) throws AppException, InstanceException;

}
