package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class Recolector {
	private Integer id;
	private String nombre;
	private String apellido;
	private String dni;
	private String email;
		
	public Recolector(String nombre, String apellido, String dni, String email) throws DataEmptyException, NotNullException, NumbersException {
			
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
			
		if(nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || email.isEmpty()) {
			throw new DataEmptyException("Uno o m�s campos estan incompletos y/o vac�os.");
		}
			
		if(nombre == null || apellido == null || dni == null || email == null) {
			throw new NotNullException("Uno o m�s datos son nulos.");
		}
		
		if(!dni.matches("[0-9]+") || (dni.length()<7||dni.length()>8)) {
			throw new NumbersException("El dni ingresado es incorrecto");
		}
	}

		public Integer obtenerId() {
			return id;
		}

		public void editarId(Integer id) {
			this.id = id;
		}
		
		public String obtenerNombre() {
			return nombre;
		}

		public void editarNombre(String nombre) {
			this.nombre = nombre;
		}

		public String obtenerApellido() {
			return apellido;
		}

		public void editarApellido(String apellido) {
			this.apellido = apellido;
		}

		public String obtenerDni() {
			return dni;
		}

		public void editarDni(String dni) {
			this.dni = dni;
		}

		public String obtenerEmail() {
			return email;
		}

		public void editarEmail(String email) {
			this.email = email;
		}
}
