package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class CiudadanoDTO {

	private String nombre;
	private String apellido;
	private String dni;
	
	public CiudadanoDTO (String nombre, String apellido, String dni) throws NotNullException, DataEmptyException, NumbersException {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
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
}
