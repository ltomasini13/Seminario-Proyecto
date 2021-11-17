package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.ZeroNegativeNumberException;

public class ResiduoARetirar extends Residuo{

	public ResiduoARetirar(TipoResiduo residuo, double cantKg) throws NotNullException, ZeroNegativeNumberException {
		super(residuo, cantKg);
		if(cantKg <=0) {
			throw new ZeroNegativeNumberException("El valor que se ingresa para la cantidad de kg debe ser > 0");
		}
		
	}
	
	
	
}
