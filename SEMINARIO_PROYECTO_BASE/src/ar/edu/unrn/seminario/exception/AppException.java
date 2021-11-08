package ar.edu.unrn.seminario.exception;

import java.sql.SQLException;

public class AppException extends SQLException{

	public AppException () {
		
	}
	public AppException (String message) {
		super(message);
	}
}
