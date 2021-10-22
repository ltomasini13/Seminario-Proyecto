package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;

public class ResiduoARetirarDTO {
	private Integer id;
	private String tipoResiduo;
	private double cantidad ;
	
	public ResiduoARetirarDTO(Integer id, String tipoResiduo, double cantidad) {
		this.id = id;
		this.tipoResiduo = tipoResiduo;
		this.cantidad=cantidad;
		
	}

	public Integer obetenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
	}

	public String obetenerTipoResiduo() {
		return tipoResiduo;
	}

	public void editarTipoResiduo(String tipoResiduo) {
		this.tipoResiduo = tipoResiduo;
	}

	public double obetenerCantidad() {
		return cantidad;
	}

	public void editarCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipoResiduo == null) ? 0 : tipoResiduo.hashCode());
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
		ResiduoARetirarDTO other = (ResiduoARetirarDTO) obj;
		if (tipoResiduo == null) {
			if (other.tipoResiduo != null)
				return false;
		} else if (!tipoResiduo.equals(other.tipoResiduo))
			return false;
		return true;
	}
	
	
	
	
	
}
