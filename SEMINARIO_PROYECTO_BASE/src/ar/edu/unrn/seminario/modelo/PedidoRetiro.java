package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;

public class PedidoRetiro {
<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
	private LocalDateTime fechaEmision;
	private LocalDateTime fechaCumplimiento;
	private boolean cargaPesada;
	private String observacion;
	private Vivienda vivienda;
	
<<<<<<< Updated upstream
	public PedidoRetiro(LocalDateTime fechaEmision, boolean cargaPesada, String observacion, Vivienda vivienda) {

		this.fechaEmision = fechaEmision;
		this.cargaPesada = cargaPesada;
		this.vivienda = vivienda;
		this.observacion = observacion;
		
		if(fechaEmision == null || vivienda == null) {
			
		}
	}

=======
	public PedidoRetiro(LocalDateTime fecEmision, boolean cargaPesada, String observacion, Vivienda vivienda) {
		
		this.fechaEmision = fecEmision;
		this.cargaPesada = cargaPesada;
		this.observacion = observacion;
		this.vivienda = vivienda;
		
	}
	
>>>>>>> Stashed changes
	public LocalDateTime obtenerFechaEmision() {
		return fechaEmision;
	}

<<<<<<< Updated upstream
	public void editarFecEmision(LocalDateTime fecha) {
		this.fechaEmision = fecha;
=======
	public void cambiarFechaEmision(LocalDateTime fEmision) {
		this.fechaEmision = fEmision;
>>>>>>> Stashed changes
	}

	public LocalDateTime obtenerFechaCmplimiento() {
		return fechaCumplimiento;
	}

<<<<<<< Updated upstream
	public void editarFechaCumplimiento(LocalDateTime fecha) {
		this.fechaCumplimiento = fecha;
=======
	public void cambiarFechaCumplimiento(LocalDateTime fCumplimiento) {
		this.fechaCumplimiento = fCumplimiento;
>>>>>>> Stashed changes
	}

	public boolean isCargaPesada() {
		return cargaPesada;
	}

<<<<<<< Updated upstream
	public void editarCargaPesada(boolean cargaPesada) {
		this.cargaPesada = cargaPesada;
	}
	
	public String obtenerObservacion() {
		return observacion;
	}

	public void editarObservacion(String observacion) {
		this.observacion = observacion;
	}
	
=======
	public void cambiarCargaPesada(boolean cargaPesada) {
		this.cargaPesada = cargaPesada;
	}

>>>>>>> Stashed changes
	public Vivienda obtenerVivienda() {
		return vivienda;
	}

<<<<<<< Updated upstream
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
=======
	public void obtenerVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}

	public String obtenerObservacion() {
		return observacion;
	}

	public void cambiarObservacion(String observacion) {
		this.observacion = observacion;
>>>>>>> Stashed changes
	}
	
}
