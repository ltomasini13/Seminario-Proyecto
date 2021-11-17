package ar.edu.unrn.seminario.exception;
//se usa para disparar cuando alguna fecha de fin/cumplimiento es nula
public class EndingDate extends Exception {
	public EndingDate () {
		
	}
	public EndingDate (String message) {
		super(message);
	}

}
