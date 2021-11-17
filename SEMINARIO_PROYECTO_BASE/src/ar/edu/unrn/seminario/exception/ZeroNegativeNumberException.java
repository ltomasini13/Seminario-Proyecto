package ar.edu.unrn.seminario.exception;
//Para disparar cuando un campo es cero o menor que cero
public class ZeroNegativeNumberException extends Exception  {
	
		public ZeroNegativeNumberException () {
			
		}
		public ZeroNegativeNumberException(String message) {
			super(message);
		}

	
}
