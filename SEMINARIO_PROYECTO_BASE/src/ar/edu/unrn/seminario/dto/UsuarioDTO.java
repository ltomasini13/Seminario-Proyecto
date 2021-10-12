package ar.edu.unrn.seminario.dto;

public class UsuarioDTO {
	private String username;
	private String password;
	private String nombre;
	private String email;
	private String rol;
	private String estado;

	public UsuarioDTO(String username, String password, String nombre, String email, String rol,
			String estado) {
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.email = email;
		this.rol = rol;
		this.estado = estado;
	}

	public UsuarioDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public boolean isActivo() {
		if(this.estado.equals("ACTIVO")) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setActivo(boolean activo) {
		if(activo==true) {
			estado="ACTIvo";
		}
		else {
			estado="INACTIVO";
		}
		
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
