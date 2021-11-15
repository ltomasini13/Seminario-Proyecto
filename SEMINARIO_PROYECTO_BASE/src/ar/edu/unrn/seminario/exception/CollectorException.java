package ar.edu.unrn.seminario.exception;

//Para disparar cuando una orden no tiene asignado un recolector
public class CollectorException extends Exception{
	public CollectorException () {
		
	}
	
	
	public CollectorException (String message) {
		super(message);
	}
}
