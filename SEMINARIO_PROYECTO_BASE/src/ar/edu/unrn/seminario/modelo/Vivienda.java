package ar.edu.unrn.seminario.modelo;

public class Vivienda {
	private Ubicacion ubicacion;
	private Ciudadano ciudadano;
	
	
	public Vivienda(Ubicacion ubicacion, Ciudadano ciudadano) {
		this.ubicacion = ubicacion;
		this.ciudadano = ciudadano;
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
		return ubicacion.obeterBarrio();
	}
	
	public float obtenerUbicacionLatitud() {
		return ubicacion.obtenerLatitud();
	}
	
	public float obtenerUbicacionLongitud() {
		return ubicacion.obtenerLongitud();
	}
	
	
	public String obtenerNombreApellidoCiudadano() {
		return ciudadano.nombre()+" "+ciudadano.apellido();
	}
	
	
}
