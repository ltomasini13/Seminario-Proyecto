package ar.edu.unrn.seminario.exception;
//para disparar desde el DAO, cuando ocurre un error de tipo Exception
public class InstanceException extends Exception{
	public InstanceException () {
		
	}
	
	public InstanceException (String message) {
		super(message);
	}
}
