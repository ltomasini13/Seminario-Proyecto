package ar.edu.unrn.seminario.dto;

public class BeneficioDTO {

	private String nombreBeneficio;
	private int puntos;
	
	public BeneficioDTO (String nombre, int puntos) {
		
		this.nombreBeneficio = nombre;
		this.puntos = puntos;
		
	}

	public String obtenerNombreBeneficio() {
		return nombreBeneficio;
	}

	public void editarNombreBeneficio(String nombreBeneficio) {
		this.nombreBeneficio = nombreBeneficio;
	}

	public int obtenerPuntos() {
		return puntos;
	}

	public void editarPuntos(int puntos) {
		this.puntos = puntos;
	}
}
