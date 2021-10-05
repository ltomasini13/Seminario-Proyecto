package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;

public class Rol {
	private Integer codigo;
	private String nombre;
	private boolean activo;

	public Rol() {

	}

	public Rol(Integer codigo, String nombre) throws NotNullException, DataEmptyException{
		if (codigo==null || nombre==null) {
			throw new NotNullException("Alguno de los datos es nulo");
		}
		
		if(nombre.isEmpty()) {
			throw new DataEmptyException("Uno o mas campos incompletos");
		}
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.activo=true;
	}
	
	
	public Rol(String nombre, boolean activo) throws NotNullException, DataEmptyException {
		if (nombre==null) {
			throw new NotNullException("Alguno de los datos es nulo");
		}
		
		if(nombre.isEmpty()) {
			throw new DataEmptyException("Uno o mas campos incompletos");
		}
	}

	
	public Integer obtenerCodigo() {
		return codigo;
	}

	public void editarCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public void editarNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void activar() {
		this.activo = true;
	}

	public void desactivar() {
		this.activo = false;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Rol [codigo=" + codigo + ", nombre=" + nombre + ", activo=" + activo + "]";
	}
	

}
