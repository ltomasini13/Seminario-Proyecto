package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

public class CatalogoDTO {

	private String nombreCampaña;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String descripcion;
	
	public CatalogoDTO (String nombre, String fechaInicio, String fechaFin, String desc) {
		
		this.nombreCampaña = nombre;
		this.fechaInicio = LocalDateTime.parse(fechaInicio);
		this.fechaFin = LocalDateTime.parse(fechaFin);
		this.descripcion = desc;

	}

	public String obtenerNombreCampaña() {
		return nombreCampaña;
	}

	public void editarNombreCampaña(String nombreCampaña) {
		this.nombreCampaña = nombreCampaña;
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
