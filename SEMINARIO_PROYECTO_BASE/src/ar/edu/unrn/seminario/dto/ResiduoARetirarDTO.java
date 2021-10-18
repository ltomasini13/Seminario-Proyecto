package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;

public class ResiduoARetirarDTO {
	private Integer id;
	private String tipoResiduo;
	private float cantidad ;
	
	public ResiduoARetirarDTO(Integer id, String tipoResiduo, String cantidad) {
		this.id = id;
		this.tipoResiduo = tipoResiduo;
		
		if(!cantidad.matches("[0-9]+")) {
			
		}
		
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

	public float obetenerCantidad() {
		return cantidad;
	}

	public void editarCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	
	
}
