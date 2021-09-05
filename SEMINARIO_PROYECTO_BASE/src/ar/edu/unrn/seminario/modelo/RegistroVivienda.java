package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;

public class RegistroVivienda {
	private LocalDateTime fechaYhora;
	private Vivienda vivienda;
	
	
	public RegistroVivienda(LocalDateTime fechaYhora, Vivienda vivienda) {
		
		this.fechaYhora = fechaYhora;
		this.vivienda = vivienda;
	}


	public LocalDateTime obtenerFechaYhora() {
		return fechaYhora;
	}


	public void editarFechaYhora(LocalDateTime fechaYhora) {
		this.fechaYhora = fechaYhora;
	}


	public Vivienda obtenerVivienda() {
		return vivienda;
	}


	public void editarVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}
	
	public String obtenerCalleVivienda() {
		return vivienda.obtenerUbicacionCalle();
	}
	
	public int obtenerNroVivienda() {
		return vivienda.obtenerUbicacionNro();
	}
	
	public String obtenerBarrioVivienda() {
		return vivienda.obtenerUbicacionBarrio();
	}
	
	public float obtenerLatitudVivienda() {
		return vivienda.obtenerUbicacionLatitud();
	}
	
	public float obtenerLongitudVivienda() {
		return vivienda.obtenerUbicacionLongitud();
	}
	
	public String obtenerNombreApellidoCiudadano() {
		return vivienda.obtenerNombreApellidoCiudadano();
	}
	
}
