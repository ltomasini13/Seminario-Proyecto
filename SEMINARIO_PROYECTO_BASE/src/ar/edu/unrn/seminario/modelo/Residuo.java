package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.NotNullException;

public class Residuo {
	private Integer id;
	private TipoResiduo tipo;
	private double cantkg;
	
	public Residuo(TipoResiduo residuo, double cantKg) throws NotNullException {
		this.tipo = residuo;
		this.cantkg = cantKg;
		
		if(residuo == null) {
			throw new NotNullException("El tipo de residuo no puede ser nulo.");
		}
	}
	
	
	
	public Integer obtenerId() {
		return id;
	}



	public void editarId(Integer id) {
		this.id = id;
	}



	public double totalPuntosRetiro(){
		double totalkg = 0;
		totalkg = cantkg * tipo.obtenerPunto();
		return totalkg;
	}
	
	public TipoResiduo obtenerResiduo() {
		return tipo;
	}

	public void editarResiduo(TipoResiduo residuo) {
		this.tipo = residuo;
	}

	public double obtenerCantkg() {
		return cantkg;
	}

	public void editarCantkg(double cantKg) {
		this.cantkg = cantKg;
	}
	
	public String obtenerTipoResiduo() {
		return this.tipo.obtenerTipo();
	}

	public int obtenerPuntosTipoResiduo() {
		return this.tipo.obtenerPunto();
	}
	
	public TipoResiduo obtenerTipo() {
		return this.tipo;
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
		
		Residuo other = (Residuo) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}



	
	
}
