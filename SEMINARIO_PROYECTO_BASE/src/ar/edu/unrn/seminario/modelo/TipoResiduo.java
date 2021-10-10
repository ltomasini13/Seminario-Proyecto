package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class TipoResiduo {

	private String tipo;
	private int punto;
	
	public TipoResiduo(String tipo, int punto) throws NotNullException, DataEmptyException, NumbersException {
		
		this.tipo = tipo;
		this.punto = punto;
		
		if(tipo == null) {
			throw new NotNullException("El tipo de residuo que se ingreso es nulo.");
		}
		
		if(tipo.isEmpty()) {
			throw new DataEmptyException("El tipo de residuo que se ingreso es vacío.");
		}
		
		if(punto<=0) {
			throw new NumbersException("El valor que se ingreso para el campo punto no es válido.");
		}
	}
	
	public String obtenerTipo() {
		return tipo;
	}

	public void editarTipo(String tipo_residuo) {
		this.tipo = tipo_residuo;
	}

	public int obtenerPunto() {
		return punto;
	}

	public void editarPunto(int punto) {
		this.punto = punto;
	}
}
