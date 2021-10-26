package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class Ubicacion {
	private Long id;
      private String calle;
      private Integer numero;
      private String barrio;
      private double latitud;
      private double longitud;
      
	public Ubicacion(String calle, Integer numero, String barrio, double latitud, double longitud) throws DataEmptyException, NumbersException, NotNullException {
		
		this.calle = calle;
		this.numero = numero;
		this.barrio = barrio;
		this.latitud = latitud;
		this.longitud = longitud;
		
		if(calle==null) {
			throw new NotNullException("La calle no puede ser nula");
		}
		if(calle.isEmpty()) {
			throw new DataEmptyException("La calle no se completo correctamente");
		}
		if(latitud < 0 || longitud < 0 ) {
			throw new NumbersException("Latitud y/o longitud deben ser valores mayores a 0");	
		}
	}

	public String obetenerCalle() {
		return calle;
	}

	public void editarCalle(String calle) {
		this.calle = calle;
	}

	public Integer obtenerNumero() {
		return numero;
	}

	public void editarNumero(Integer numero) {
		this.numero = numero;
	}

	public String obtenerBarrio() {
		return barrio;
	}

	public void editarBarrio(String barrio) {
		this.barrio = barrio;
	}

	public double obtenerLatitud() {
		return latitud;
	}

	public void editarLatitud(int latitud) {
		this.latitud = latitud;
	}

	public double obtenerLongitud() {
		return longitud;
	}

	public void editarLongitud(int longitud) {
		this.longitud = longitud;
	}  
	
	public Long obtenerId() {
		return id;
	}
}
