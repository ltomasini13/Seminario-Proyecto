package ar.edu.unrn.seminario.exception;

public class DuplicateUniqueKeyException extends Exception {
	public DuplicateUniqueKeyException() {
		super();
	}
	public DuplicateUniqueKeyException(String message) {
		super(message);
	}
}
