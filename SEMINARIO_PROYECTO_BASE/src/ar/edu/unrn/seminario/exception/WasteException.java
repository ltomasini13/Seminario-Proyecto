package ar.edu.unrn.seminario.exception;



//Para disparar cuando el residuo que se quiere ingresar no esta declarado en el pedido. 
public class WasteException extends Exception {
	public WasteException () {
		
	}
	public WasteException(String message) {
		super(message);
	}

}
