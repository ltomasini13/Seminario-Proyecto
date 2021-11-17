package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class CiudadanoDTO {
	private Integer id;
	private String nombre;
	private String apellido;
	private String dni;
	private String nombreDeUsuario;
	
	
	public CiudadanoDTO (Integer id, String nombre, String apellido, String dni) {
		this.id=id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	} 	
 
	public String obtenerNombreDeUsuario() {
		return nombreDeUsuario;
	}

	public void editarNombreDeUsuario(String nombreDeUsuario) {
		this.nombreDeUsuario = nombreDeUsuario;
	}

	public String obtenerNombre() {
		return nombre;
	}
	
	
	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
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
