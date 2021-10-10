package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;

public class PedidoRetiro {
	private LocalDateTime fechaEmision;
	private LocalDateTime fechaCumplimiento;
	private boolean cargaPesada;
	private String observacion;
	private Vivienda vivienda;
	
	public PedidoRetiro(LocalDateTime fechaEmision, boolean cargaPesada, String observacion, Vivienda vivienda) {

		this.fechaEmision = fechaEmision;
		this.cargaPesada = cargaPesada;
		this.vivienda = vivienda;
		this.observacion = observacion;
		
		if(fechaEmision == null || vivienda == null) {
			
		}
	}

	public LocalDateTime obtenerFechaEmision() {
		return fechaEmision;
	}

	public void editarFecEmision(LocalDateTime fecha) {
		this.fechaEmision = fecha;
	}

	public LocalDateTime obtenerFechaCmplimiento() {
		return fechaCumplimiento;
	}

	public void editarFechaCumplimiento(LocalDateTime fecha) {
		this.fechaCumplimiento = fecha;
	}

	public boolean isCargaPesada() {
		return cargaPesada;
	}

	public void editarCargaPesada(boolean cargaPesada) {
		this.cargaPesada = cargaPesada;
	}
	
	public String obtenerObservacion() {
		return observacion;
	}

	public void editarObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public Vivienda obtenerVivienda() {
		return vivienda;
	}

	public void editarVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}
	
	public String obtenerNombreCiudadanoVivienda() {
		return vivienda.obtenerNombreCiudadano();
	}
	
	public String obtenerApellidoCiudadanoVivienda() {
		return vivienda.obtenerApellidoCiudadano();
	}
	
	public String obtenerDniCiudadanoVivienda() {
		return vivienda.obtenerDniCiudadano();
	}
	
}
