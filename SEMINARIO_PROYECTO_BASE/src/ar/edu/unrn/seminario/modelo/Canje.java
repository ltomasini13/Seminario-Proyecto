package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;

import ar.edu.unrn.seminario.exception.NotNullException;

public class Canje {

	private Integer id;
	private LocalDateTime fechaCanje;
	private double puntaje;
	private Ciudadano ciudadano;
	private Beneficio beneficio;
	
	public Canje (String fecha, double puntaje, Beneficio beneficio, Ciudadano ciudadano) throws NotNullException {
		
		this.fechaCanje = LocalDateTime.parse(fecha);
		this.puntaje = puntaje;
		this.beneficio = beneficio;
		this.ciudadano = ciudadano;
		
		if(ciudadano == null || beneficio == null) {
			throw new NotNullException("Ciudadano y/o beneficio son nulos");
		}
	}

	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
	}

	public LocalDateTime obtenerFechaCanje() {
		return fechaCanje;
	}

	public void editarFechaCanje(LocalDateTime fechaCanje) {
		this.fechaCanje = fechaCanje;
	}

	public double obtenerPuntaje() {
		return puntaje;
	}

	public void editarPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public Ciudadano obtenerCiudadano() {
		return ciudadano;
	}

	public void editarCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}

	public Beneficio obtenerBeneficio() {
		return beneficio;
	}

	public void editarBeneficio(Beneficio beneficio) {
		this.beneficio = beneficio;
	}
}
