package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.NotNullException;

public class ResiduoARetirar {

	private TipoResiduo tipo;
	private double cantkg;
	
	public ResiduoARetirar(TipoResiduo residuo, double cantKg) throws NotNullException {
		this.tipo = residuo;
		this.cantkg = cantKg;
		
		if(residuo == null) {
			throw new NotNullException("El tipo de residuo no puede ser nulo.");
		}
		if(cantKg <0) {
			throw new NumberFormatException("El valor que se ingreso para la cantidad de kg debe ser > 0");
		}
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

	public int obtenerPuntosTipoResiduo(int puntos) {
		return this.tipo.obtenerPunto();
	}
}
