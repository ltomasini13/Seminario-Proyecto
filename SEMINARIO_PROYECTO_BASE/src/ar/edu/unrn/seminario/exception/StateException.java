package ar.edu.unrn.seminario.exception;

//Para disparar cuando hay un error relacionado a los estados de la ordenes
public class StateException extends Exception {

	public StateException () {
		
	}
	public StateException(String message) {
		super(message);
	}


}
