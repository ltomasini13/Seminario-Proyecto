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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + punto;
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
		TipoResiduo other = (TipoResiduo) obj;
		if (punto != other.punto)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	
	
	
}
