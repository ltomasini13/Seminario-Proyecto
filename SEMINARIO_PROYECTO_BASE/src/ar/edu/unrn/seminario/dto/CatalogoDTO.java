package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

public class CatalogoDTO {

	private String nombreCampa�a;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String descripcion;
	
	public CatalogoDTO (String nombre, String fechaInicio, String fechaFin, String desc) {
		
		this.nombreCampa�a = nombre;
		this.fechaInicio = LocalDateTime.parse(fechaInicio);
		this.fechaFin = LocalDateTime.parse(fechaFin);
		this.descripcion = desc;

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

}
