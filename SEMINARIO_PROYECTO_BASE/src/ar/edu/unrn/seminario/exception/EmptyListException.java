package ar.edu.unrn.seminario.exception;

public class EmptyListException extends Exception {
	public EmptyListException () {
			
		}
	
	
	public EmptyListException (String message) {
		super(message);
	}
}
