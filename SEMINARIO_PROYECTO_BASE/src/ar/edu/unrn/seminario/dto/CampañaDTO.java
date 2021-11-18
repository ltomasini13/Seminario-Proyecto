package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Campa�aDTO {

	private Integer id;
	private String nombreCampa�a;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String descripcion;
	private List<BeneficioDTO> listaBeneficios = new ArrayList<BeneficioDTO>();
	
	
	
	public Campa�aDTO (Integer id, String nombre, String fechaInicio, String fechaFin, String desc) {
		
		this.id = id;
		this.nombreCampa�a = nombre;
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
