package ar.edu.unrn.seminario.api;

import ar.edu.unrn.seminario.modelo.Usuario;

public class Sesion {
	private Usuario usuario;
	
	
	public Sesion(Usuario usuario) {
		this.usuario=usuario;
	}
	
	
	public String tipoDeUsuario() {
		return usuario.obtenerNombreRol();
	}
	
	public Usuario obtenerUsuario() {
		return this.usuario;
	}
	
	
}
