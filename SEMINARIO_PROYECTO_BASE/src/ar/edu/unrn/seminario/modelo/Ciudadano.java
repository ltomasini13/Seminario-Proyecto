package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class Ciudadano {
	private Integer id;
	private String nombre;
	private String apellido;
	private String dni;
	private Usuario usuario;
	private double puntaje;
	
	public Ciudadano (String nombre, String apellido, String dni, Usuario usuario) throws NotNullException, DataEmptyException, NumbersException {
		
		if(nombre==null || apellido==null || dni==null) {
			throw new NotNullException("Los datos ingresados son nulos");
		}
		if(nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty()) {
			throw new DataEmptyException("Faltan completar campos");
		}
		if(!dni.matches("[0-9]+") || (dni.length()<7||dni.length()>8)) {
			throw new NumbersException("El dni ingresado es incorrecto");
		}
	
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.usuario=usuario;
<<<<<<< Updated upstream
		this.puntaje=0;
=======
		this.puntaje = 0;
>>>>>>> Stashed changes
	} 	
 
	public Ciudadano() {
		// TODO Auto-generated constructor stub
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
	
	public String obtenerNombreDeUsuario() {
		return this.usuario.obtenerUsuario();
	}
	
	public String obtenerContrasenaUsuario() {
		return this.usuario.obtenerContrasena();
	}
	
	public String obtenerEmailUsuario() {
		return this.usuario.obtenerEmail();
	}
	
	public Usuario obtenerUsuario() {
		return this.usuario;
	}

	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
	}   
	
	public double puntaje() {
		return this.puntaje();
	}
	
	
	public double sumarPuntos(double puntos) {
		return this.puntaje + puntos;
	}
	
	public double restarPuntos(double puntos) {
		return this.puntaje - puntos; 
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido == null) ? 0 : apellido.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}
	
	public double obtenerPuntaje() {
		return puntaje;
	}

	public void editarPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}
	
	public void sumarPuntos(double puntaje) {
		this.puntaje= this.puntaje+puntaje;
	}
	
	public void restarPuntos(double puntaje) {
		this.puntaje= this.puntaje-puntaje;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudadano other = (Ciudadano) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	

	
}
