package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.NotNullException;

public class Vivienda {
	private Long id;
	private Ubicacion ubicacion;
	private Ciudadano ciudadano;
	
	
	public Vivienda(Ubicacion ubicacion, Ciudadano ciudadano) throws NotNullException {
		this.ubicacion = ubicacion;
		this.ciudadano = ciudadano;
		
		if (ubicacion == null || ciudadano == null) {
			throw new NotNullException("Ubicacion y ciudadano no pueden ingresar nulos");
		}
	}
	
	public Long obtenerId() {
		return id;
	}
	
	public void editarId(Long id) {
		this.id=id;
	}
	
	public Ubicacion obtenerUbicacion() {
		return ubicacion;
	}
	public void editarUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	public Ciudadano obtenerCiudadano() {
		return ciudadano;
	}
	public void editarCiudadano(Ciudadano ciudadano) {
		this.ciudadano = ciudadano;
	}
	
	
	public String obtenerUbicacionCalle() {
		return ubicacion.obetenerCalle();
	}
	
	public int obtenerUbicacionNro() {
		return ubicacion.obtenerNumero();
	}
	
	public String obtenerUbicacionBarrio() {
		return ubicacion.obtenerBarrio();
	}
	
	public double obtenerUbicacionLatitud() {
		return ubicacion.obtenerLatitud();
	}
	
	public double obtenerUbicacionLongitud() {
		return ubicacion.obtenerLongitud();
	}
	
	public String obtenerNombreCiudadano() {
		return ciudadano.obtenerNombre();
	}
	
	public String obtenerApellidoCiudadano() {
		return ciudadano.obtenerApellido();
	}
	
	public String obtenerDniCiudadano() {
		return ciudadano.obtenerDni();
	}
	
}
