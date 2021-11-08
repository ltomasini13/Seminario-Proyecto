package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;

public class Beneficio {

	private Integer id;
	private String nombreBeneficio;
	private int puntos;
	
	public Beneficio (String nombre, int puntos) throws DataEmptyException, NotNullException, NumbersException {
		
		this.nombreBeneficio = nombre;
		this.puntos = puntos;
		
		if(nombre == null) {
			throw new NotNullException("El dato nombre es nulo");
		}
		if(nombre.isEmpty()) {
			throw new DataEmptyException("Nombre de beneficio incompleto y/o vacío");
		}
		if(puntos <= 0) {
			throw new NumbersException("El campo puntos es incorrecto, su valor debe ser mayor a 0");
		}
	}

	public Integer obtenerId() {
		return id;
	}

	public void editarId(Integer id) {
		this.id = id;
	}

	public String obtenerNombreBeneficio() {
		return nombreBeneficio;
	}

	public void editarNombreBeneficio(String nombreBeneficio) {
		this.nombreBeneficio = nombreBeneficio;
	}

	public int obtenerPuntos() {
		return puntos;
	}

	public void editarPuntos(int puntos) {
		this.puntos = puntos;
	}
}
