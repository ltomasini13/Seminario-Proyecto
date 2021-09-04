package ar.edu.unrn.seminario.modelo;

public class Ubicacion {
      private String calle;
      private int numero;
      private String barrio;
      private float latitud;
      private float longitud;
      
	public Ubicacion(String calle, int numero, String barrio, float latitud, float longitud) {
		this.calle = calle;
		this.numero = numero;
		this.barrio = barrio;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public String obetenerCalle() {
		return calle;
	}

	public void editarCalle(String calle) {
		this.calle = calle;
	}

	public int obtenerNumero() {
		return numero;
	}

	public void editarNumero(int numero) {
		this.numero = numero;
	}

	public String obeterBarrio() {
		return barrio;
	}

	public void editarBarrio(String barrio) {
		this.barrio = barrio;
	}

	public float obtenerLatitud() {
		return latitud;
	}

	public void editarLatitud(int latitud) {
		this.latitud = latitud;
	}

	public float obtenerLongitud() {
		return longitud;
	}

	public void editarLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	
	

      
}
