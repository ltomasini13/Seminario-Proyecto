package ar.edu.unrn.seminario.exception;

//Para disparar cuando se valida si un campo es num�rico
public class NumbersException extends Exception{

	public NumbersException () {
		
	}
	
	
	public NumbersException (String message) {
		super(message);
	}
}
