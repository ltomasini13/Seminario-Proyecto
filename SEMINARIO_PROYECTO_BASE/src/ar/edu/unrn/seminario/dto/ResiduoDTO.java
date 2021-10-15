package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class ResiduoDTO {

	private String tipo;
	private int punto;
	
	public ResiduoDTO(String tipo, int punto) throws NotNullException, DataEmptyException, NumbersException {
		
		this.tipo = tipo;
		this.punto = punto;
		
	}
	
	public String obtenerTipo() {
		return tipo;
	}

	public void editarTipo(String tipoResiduo) {
		this.tipo = tipoResiduo;
	}

	public int obtenerPunto() {
		return punto;
	}

	public void editarPunto(int punto) {
		this.punto = punto;
	}

}
