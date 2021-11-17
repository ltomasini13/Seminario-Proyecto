package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DateException;
import ar.edu.unrn.seminario.exception.NotNullException;

public class Campa�a {

	private Integer id;
	private String nombreCampa�a;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String descripcion;
	private List<Beneficio> catalogo = new ArrayList<Beneficio>();
	
	public Campa�a (String nombre, String fechaInicio, String fechaFin, String desc) throws NotNullException, DataEmptyException, DateException {
		
		this.nombreCampa�a = nombre;
		this.fechaInicio = LocalDateTime.parse(fechaInicio);
		if(fechaFin != null)
			this.fechaFin = LocalDateTime.parse(fechaFin);
		this.descripcion = desc;
		
		if(nombre == null) {
			throw new  NotNullException("Algunos de los datos son nulos");
		}
		if(nombre.isEmpty()){
			throw new DataEmptyException("Nombre de campa�a incompleto y/o vac�o");
		}
		if(this.fechaFin != null) {
			if( this.fechaInicio.isAfter(this.fechaFin)) 
				throw new DateException("La fecha de inicio no puede superar la fecha final");
		}
	}

	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
	}

	public String obtenerNombreCampa�a() {
		return nombreCampa�a;
	}

	public void editarNombreCampa�a(String nombreCampa�a) {
		this.nombreCampa�a = nombreCampa�a;
	}

	public LocalDateTime obtenerFechaInicio() {
		return fechaInicio;
	}

	public void editarFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime obtenerFechaFin() {
		return fechaFin;
	}

	public void editarFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String obtenerDescripcion() {
		return descripcion;
	}

	public void editarDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Beneficio obtenerUltimoBeneficioAgregado() {
		return catalogo.get(catalogo.size()-1);
	}

	public void agregarBeneficioCatalogo(Beneficio beneficio) {
		this.catalogo.add(beneficio);
	}

	
}
