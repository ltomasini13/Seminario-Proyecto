package ar.edu.unrn.seminario.modelo;
 
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import java.sql.Date;
import java.sql.Timestamp;

import ar.edu.unrn.seminario.exception.EmptyListException;
import ar.edu.unrn.seminario.exception.NotNullException;

public class PedidoRetiro {

	private Integer id;
	private LocalDateTime fechaEmision;
	private LocalDateTime fechaCumplimiento;
	private boolean cargaPesada;
	private String observacion;
	private Vivienda vivienda;
	private List<ResiduoARetirar> residuosARetirar = new ArrayList<ResiduoARetirar>();
	
	public PedidoRetiro(String fechaEmision, boolean cargaPesada, String observacion, Vivienda vivienda, List<ResiduoARetirar> residuosARetirar) throws NotNullException, EmptyListException {
		if(fechaEmision == null || vivienda == null) {
			throw new NotNullException("Alguno de los datos son nulos");
		}
		
		
		if(residuosARetirar.isEmpty()) {
			throw new EmptyListException("Se debe agregar al menos un residuo para retirar");
		}
		
		this.fechaEmision = LocalDateTime.parse(fechaEmision);
		this.cargaPesada = cargaPesada;
		this.observacion = observacion;
		this.vivienda = vivienda;
		this.residuosARetirar=residuosARetirar;
		
		
	}
	
	public PedidoRetiro(String fechaEmision, boolean cargaPesada, String observacion, Vivienda vivienda) throws NotNullException {
		if(fechaEmision == null || vivienda == null) {
			throw new NotNullException("Alguno de los datos son nulos");
		}
		
		this.fechaEmision = LocalDateTime.parse(fechaEmision);
		this.cargaPesada = cargaPesada;
		this.observacion = observacion;
		this.vivienda = vivienda;
		this.residuosARetirar=residuosARetirar;
	}

	public PedidoRetiro() {
		// TODO Auto-generated constructor stub
	}

	public Integer obtenerId() {
		return id;
	}
	
	public void editarId(Integer id) {
		this.id=id;
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
	
	public void editarFechaCumplimiento(Timestamp fecha) {
		if(fecha!=null) {
			this.fechaCumplimiento = fecha.toLocalDateTime();
		}
		
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
	
	

	public void setFechaCumplimiento(String fechaCumplimiento) {
		this.fechaCumplimiento = LocalDateTime.parse(fechaCumplimiento);
	}

	public double obtenerCantidad() {
		double cantidad = 0;
		for(ResiduoARetirar r : this.residuosARetirar) {
		//	cantidad = cantidad
		}
		return cantidad;
	}
	
	
	public List<ResiduoARetirar> obetenerResiduosARetirar(){
		return this.residuosARetirar;
	}
}
