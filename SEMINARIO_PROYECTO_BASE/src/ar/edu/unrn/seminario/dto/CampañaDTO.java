package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class CampañaDTO {

	private Integer id;
	private String nombreCampaña;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String descripcion;
	private List<BeneficioDTO> listaBeneficios = new ArrayList<BeneficioDTO>();
	
	
	
	public CampañaDTO (Integer id, String nombre, String fechaInicio, String fechaFin, String desc) {
		
		this.id = id;
		this.nombreCampaña = nombre;
		this.fechaInicio = LocalDateTime.parse(fechaInicio);
		this.fechaFin = LocalDateTime.parse(fechaFin);
		this.descripcion = desc;

	}
	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
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
