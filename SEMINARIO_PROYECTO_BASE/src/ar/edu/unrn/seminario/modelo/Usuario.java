package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.StateException;

public class Usuario {
	private String usuario;
	private String contrasena;
	private String nombre;
	private String email;
	private Rol rol;
	private boolean activo;

	public Usuario(String usuario, String contrasena, String nombre, String email, Rol rol) throws DataEmptyException, NotNullException {

		if(usuario.isEmpty() || contrasena.isEmpty()){
			throw new DataEmptyException("Usuario y/o contraseña incompletos");
		}
		
		if(rol==null || usuario==null || contrasena==null) {
			throw new  NotNullException("Algunos de los datos es nulo");
		}
		
		
		
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombre = nombre;
		this.email = email;
		this.rol = rol;
		this.activo=true;
	}

	public String obtenerUsuario() {
		return usuario;
	}

	public void editarUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String obtenerContrasena() {
		return contrasena;
	}

	public void editarContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String obtenerNombre() {
		return nombre;
	}

	public void editarNombre(String nombre) {
		this.nombre = nombre;
	}

	public String obtenerEmail() {
		return email;
	}

	public void editarEmail(String email) {
		this.email = email;
	}

	public Rol obtenerRol() {
		return rol;
	}
	
	public Integer obtenerCodigoRol() {
		return this.rol.obtenerCodigo();
	}

	public void editarRol(Rol rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		return activo;
	}

	public String obtenerEstado() {
		return isActivo() ? "ACTIVO" : "INACTIVO";
	}

	public void activar() throws StateException {
		if (!isActivo())
			this.activo = true;
		else
			throw new StateException("El usuario ya esta activo");

	}

	public void desactivar() throws StateException {
		if (isActivo())
			this.activo = false;
		else
			throw new StateException("El usuario ya esta desactivado");
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Usuario other = (Usuario) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

}
