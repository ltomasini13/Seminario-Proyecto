package ar.edu.unrn.seminario.modelo;

public class Vivienda {
	private Ubicacion ubicacion;
	private Ciudadano ciudadano;
	
	
	public Vivienda(Ubicacion ubicacion, Ciudadano ciudadano) {
		this.ubicacion = ubicacion;
		this.ciudadano = ciudadano;
	}
	
	public Ubicacion ubicacion() {
		return ubicacion;
	}
	public void editarUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Ciudadano ciudadano() {
		return ciudadano;
	}
	public void editarCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}
	
	
}
