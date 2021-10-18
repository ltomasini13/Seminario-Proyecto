package ar.edu.unrn.seminario.modelo;
 
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;

import ar.edu.unrn.seminario.exception.NotNullException;

public class PedidoRetiro {

	private Long id;
	private LocalDateTime fechaEmision;
	private LocalDateTime fechaCumplimiento;
	private boolean cargaPesada;
	private String observacion;
	private Vivienda vivienda;
	private ArrayList<ResiduoARetirar> residuosARetirar = new ArrayList<ResiduoARetirar>();
	
	public PedidoRetiro(String fechaEmision, boolean cargaPesada, String observacion, Vivienda vivienda) throws NotNullException {

		this.fechaEmision = LocalDateTime.parse(fechaEmision);
		this.cargaPesada = cargaPesada;
		this.observacion = observacion;
		this.vivienda = vivienda;
		
		
		if(fechaEmision == null || vivienda == null) {
			throw new NotNullException("Alguno de los datos son nulos");
		}
	}
	
	public Long obtenerId() {
		return id;
	}
	
	public void editarId(Long id) {
		this.id=id;
	}
	
	public LocalDateTime obtenerFechaEmision() {
		return fechaEmision;
	}

	public void editarFechaEmision(LocalDateTime fecha) {
		this.fechaEmision = fecha;
	}

	public Date obtenerFecha() {
		ZoneId defaultZoneId = ZoneId.systemDefault ();
		Date fecha = (Date) Date.from(this.fechaEmision.atZone(defaultZoneId).toInstant());
		return fecha;
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
	
	public Vivienda obtenerVivienda() {
		return vivienda;
	}

	public String obtenerObservacion() {
		return observacion;
	}
	
	public void editarObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public double obtenerCantidad() {
		double cantidad = 0;
		for(ResiduoARetirar r : this.residuosARetirar) {
		//	cantidad = cantidad
		}
		return cantidad;
	}
}
