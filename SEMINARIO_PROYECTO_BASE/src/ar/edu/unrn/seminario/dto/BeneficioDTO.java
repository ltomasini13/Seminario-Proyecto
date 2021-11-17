package ar.edu.unrn.seminario.dto;

public class BeneficioDTO {

	private Integer id;
	private String nombreBeneficio;
	private int puntos;
	
	public BeneficioDTO (Integer id, String nombre, int puntos) {
		
		this.id = id;
		this.nombreBeneficio = nombre;
		this.puntos = puntos;
		
	}

	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
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
