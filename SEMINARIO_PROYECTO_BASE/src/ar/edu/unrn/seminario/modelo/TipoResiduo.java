package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class TipoResiduo {

	private Integer id;
	private String tipo;
	private int punto;
	
	public TipoResiduo(String tipo, int punto) throws NotNullException, DataEmptyException, NumbersException {
		
		this.tipo = tipo;
		this.punto = punto;
		
		if(tipo == null) {
			throw new NotNullException("El tipo de residuo que se ingreso es nulo.");
		}
		
		if(tipo.isEmpty()) {
			throw new DataEmptyException("El tipo de residuo esta incompleto y/o vacio.");
		}
		
		if(punto<=0) {
			throw new NumbersException("El valor que se ingreso para el campo punto no es válido.");
		}
	}
	
	public TipoResiduo() {
	}
	
	public Integer obtenerId() {
		return id;
	}
	
	public void editarId(Integer id) {
		this.id=id;
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
