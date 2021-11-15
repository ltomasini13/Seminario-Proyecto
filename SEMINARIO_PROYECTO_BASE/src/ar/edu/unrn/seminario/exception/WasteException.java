package ar.edu.unrn.seminario.exception;


//Para disparar cuando las cantidades de residuos restantes para una orden no coinciden con las que se quieren ingresar en una visita.
//Tambi�n cuando el residuo que se quiere ingresar no esta declarado en el pedido. 
public class WasteException extends Exception {
	public WasteException () {
		
	}
	public WasteException(String message) {
		super(message);
	}

}
