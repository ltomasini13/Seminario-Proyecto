package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.edu.unrn.seminario.exception.NotNullException;

public class PedidoRetiro {

	private LocalDateTime fechaEmision;
	private LocalDateTime fechaCumplimiento;
	private boolean cargaPesada;
	private String observacion;
	private Vivienda vivienda;
	private ArrayList<Residuo> listaResiduos = new ArrayList<Residuo>();
	
	public PedidoRetiro(LocalDateTime fechaEmision, boolean cargaPesada, String observacion, Vivienda vivienda) throws NotNullException {

		this.fechaEmision = fechaEmision;
		this.cargaPesada = cargaPesada;
		this.observacion = observacion;
		this.vivienda = vivienda;
		
		
		if(fechaEmision == null || vivienda == null) {
			throw new NotNullException("Alguno de los datos son nulos");
		}
	}
	
	public LocalDateTime obtenerFechaEmision() {
		return fechaEmision;
	}

	public void editarFechaEmision(LocalDateTime fecha) {
		this.fechaEmision = fecha;
	}

	public LocalDateTime obtenerFechaCumplimiento() {
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
	
	public void obtenerVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}

	public String obtenerObservacion() {
		return observacion;
	}
	
}
