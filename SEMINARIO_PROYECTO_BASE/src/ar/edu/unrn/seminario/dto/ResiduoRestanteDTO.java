package ar.edu.unrn.seminario.dto;

public class ResiduoRestanteDTO {
	private Integer id;
	private String tipo;
	private double cantidadKg;
	
	
	public ResiduoRestanteDTO(Integer id, String tipo, double cantidadKg) {
		
		this.id = id;
		this.tipo = tipo;
		this.cantidadKg = cantidadKg;
	}


	public Integer obtenerId() {
		return id;
	}


	public void editarId(Integer id) {
		this.id = id;
	}


	public String obtenerTipo() {
		return tipo;
	}


	public void editarTipo(String tipo) {
		this.tipo = tipo;
	}


	public double obtenerCantidadKg() {
		return cantidadKg;
	}


	public void editarCantidadKg(double cantidadKg) {
		this.cantidadKg = cantidadKg;
	}
	
	
	
	
}
