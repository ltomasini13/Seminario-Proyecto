package ar.edu.unrn.seminario.modelo;

public class Ubicacion {
      private String calle;
      private int numero;
      private String barrio;
      private int latitud;
      private int longitud;
      
	public Ubicacion(String calle, int numero, String barrio, int latitud, int longitud) {
		this.calle = calle;
		this.numero = numero;
		this.barrio = barrio;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public String calle() {
		return calle;
	}

	public void editarCalle(String calle) {
		this.calle = calle;
	}

	public int numero() {
		return numero;
	}

	public void editarNumero(int numero) {
		this.numero = numero;
	}

	public String barrio() {
		return barrio;
	}

	public void editarBarrio(String barrio) {
		this.barrio = barrio;
	}

	public int latitud() {
		return latitud;
	}

	public void editarLatitud(int latitud) {
		this.latitud = latitud;
	}

	public int longitud() {
		return longitud;
	}

	public void editarLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	
	

      
}
