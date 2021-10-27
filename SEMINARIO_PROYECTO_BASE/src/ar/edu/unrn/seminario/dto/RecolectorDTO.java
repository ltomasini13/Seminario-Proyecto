package ar.edu.unrn.seminario.dto;

public class RecolectorDTO {

	private String nombre;
	private String apellido;
	private String dni;
	private String email;
	
	public RecolectorDTO(String nombre, String apellido, String dni, String email) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.email = email;
		
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
