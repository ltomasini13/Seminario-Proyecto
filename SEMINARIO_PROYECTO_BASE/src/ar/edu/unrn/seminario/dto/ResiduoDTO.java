package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class ResiduoDTO {
	private Integer id;
	private String tipo;
	private int puntaje;
	
	public ResiduoDTO(Integer id, String tipo, int punto) throws NotNullException, DataEmptyException, NumbersException {
		this.id=id;
		this.tipo = tipo;
		this.puntaje = punto;
		
	}
	
	
	public String obtenerTipo() {
		return tipo;
	}

	public void editarTipo(String tipoResiduo) {
		this.tipo = tipoResiduo;
	}

	public int obtenerPunto() {
		return puntaje;
	}

	
	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
	}

	public void editarPunto(int punto) {
		this.puntaje = punto;
	}

}
