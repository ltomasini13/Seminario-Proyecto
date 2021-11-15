package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

public class ResiduoRetiradoDTO {
	private Integer id;
	private String tipo;
	private double cantidadKg;
	
	
	
	public ResiduoRetiradoDTO(Integer id, String tipo, double cantidadKg) {

		this.id = id;
		this.tipo = tipo;
		this.cantidadKg = cantidadKg;
	
	}


	public Integer obtenerId() {
		return id;
	}


	public void editarsId(Integer id) {
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResiduoRetiradoDTO other = (ResiduoRetiradoDTO) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}



	
	
	
	
	
	
}
