package ar.edu.unrn.seminario.dto;

public class CanjeDTO {
	private String fecha;
	private double puntosCanjeados;
	private String beneficio;
	private String ciudadano;
	
	
	public CanjeDTO(String fecha, double puntosCanjeados, String beneficio, String ciudadano) {
		
		this.fecha = fecha;
		this.puntosCanjeados = puntosCanjeados;
		this.beneficio = beneficio;
		this.ciudadano = ciudadano;
	}


	public String obtenerFecha() {
		return fecha;
	}


	public void editarFecha(String fecha) {
		this.fecha = fecha;
	}


	public double obtenerPuntosCanjeados() {
		return puntosCanjeados;
	}


	public void editarPuntosCanjeados(double puntosCanjeados) {
		this.puntosCanjeados = puntosCanjeados;
	}


	public String obtenerBeneficio() {
		return beneficio;
	}


	public void editarBeneficio(String beneficio) {
		this.beneficio = beneficio;
	}


	public String obtenerCiudadano() {
		return ciudadano;
	}


	public void editarCiudadano(String ciudadano) {
		this.ciudadano = ciudadano;
	}
	
	 
}
