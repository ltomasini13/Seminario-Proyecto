package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;

public class Campaña {

	private Integer id;
	private String nombreCampaña;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	private String descripcion;
	private Catalogo catalogo;
	
	public Campaña (String nombre, String fechaInicio, String fechaFin, String desc, Catalogo catalogo) throws NotNullException, DataEmptyException {
		
		this.nombreCampaña = nombre;
		this.fechaInicio = LocalDateTime.parse(fechaInicio);
		this.fechaFin = LocalDateTime.parse(fechaFin);
		this.descripcion = desc;
		this.catalogo = catalogo;
		
		if(nombre == null || catalogo == null) {
			throw new  NotNullException("Algunos de los datos son nulos");
		}
		if(nombre.isEmpty())
			throw new DataEmptyException("Nombre de campaña incompleto y/o vacío");

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

	public Catalogo obtenerCatalogo() {
		return catalogo;
	}

	public void editarCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}
}
