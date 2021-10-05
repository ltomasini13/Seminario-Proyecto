package ar.edu.unrn.seminario.dto;

public class RolDTO {

	private Integer codigo;
	private String nombre;
	private String estado;

	public RolDTO(Integer codigo, String nombre) {
		
		this.codigo = codigo;
		this.nombre = nombre;
	}
	
	public RolDTO(String nombre, String estado) {
		this.nombre=nombre;
		this.estado=estado;
	}

	public RolDTO(Integer codigo, String nombre, String estado) {
		
		this.codigo = codigo;
		this.nombre = nombre;
		this.estado = estado;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String isActivo() {
		return estado;
	}

	public void setActivo(String estado) {
		this.estado = estado;
	}

}
