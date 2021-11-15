package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;
import java.util.List;

import ar.edu.unrn.seminario.exception.NotNullException;

public class Visita {
	private Integer id;
	private LocalDateTime fecha;
	private String observacion;
	private OrdenDeRetiro orden;
	private List<ResiduoRetirado> residuosRetirados;
	
	
	
	public Visita(String fecha,String observacion, OrdenDeRetiro orden) throws NotNullException {
		if(fecha==null || orden==null) {
			throw new NotNullException("Alguno de los parametros es nulo");
		}
		this.fecha = LocalDateTime.parse(fecha);
		this.observacion = observacion;
		this.orden = orden;
		
	}
	
	
	public Integer obtenerId() {
		return id;
	}
	public void editarId(Integer id) {
		this.id = id;
	}
	public LocalDateTime obtenerFecha() {
		return fecha;
	}
	public void editarFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}
	public List<ResiduoRetirado> obtenerResiduosRetirados() {
		return residuosRetirados;
	}
	public void editarResiduosRetirados(List<ResiduoRetirado> residuosRetirados) {
		this.residuosRetirados = residuosRetirados;
	}
	public String obtenerObservacion() {
		return observacion;
	}
	public void editarObservacion(String observacion) {
		this.observacion = observacion;
	}
	public OrdenDeRetiro obtenerOrden() {
		return orden;
	}
	public void editarOrden(OrdenDeRetiro orden) {
		this.orden = orden;
	}
	
	
	
}
