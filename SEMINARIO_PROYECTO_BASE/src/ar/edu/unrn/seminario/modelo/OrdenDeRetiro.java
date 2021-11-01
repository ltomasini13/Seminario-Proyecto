package ar.edu.unrn.seminario.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ar.edu.unrn.seminario.exception.StateException;

public class OrdenDeRetiro {

	private final static String PENDIENTE = "PENDIENTE";
	private final static String EN_EJECUCION = "EN_EJECUCION";
	private final static String CONCRETADO = "CONCRETADO";
	private final static String CANCELADO = "CANCELADO";
	
	private Integer id;
	private String estado;
	private LocalDateTime fechaOrden;
	private PedidoRetiro pedido;
	private Recolector recolector;
	
	public OrdenDeRetiro(String fecha, PedidoRetiro pedido, Recolector recolector) {
		
		this.fechaOrden = LocalDateTime.parse(fecha);
		this.estado = "PENDIENTE";
		this.pedido = pedido;
		this.recolector = recolector;
		
	}

	public Integer obtenerId() {
		return id;
	}
	
	public void editarId(Integer id) {
		this.id = id;
	}
	
	public String obtenerEstado() {
		return estado;
	}
	
	public void ejecutarOrden() throws StateException {
		if(this.estado.equals("EN EJECUCION")) {
			throw new StateException("La orden ya está ejecutandose");
		}
		
		if(this.estado.equals("FINALIZADA")) {
			throw new StateException("No se puede ejecutar la orden porque ya finalizó");
		}
		
		
		if(this.estado.equals("CANCELADA")) {
			throw new StateException("No se puede ejecutar la orden porque está cancelada");
		}
		
		this.estado="EN EJECUCION";
	}
	
	
	public void cancelarOrden() throws StateException {

		if(this.estado.equals("CANCELADA")) {
			throw new StateException("La orden ya está cancelada");
		}
		if(this.estado.equals("FINALIZADA")) {
			throw new StateException("No se puede cancelar la orden porque ya finalizó");
		}

		
		this.estado="CANCELADA";
	}
	
	public void finalizarOrden() throws StateException {
		
		if(this.estado.equals("FINALIZADA")) {
			throw new StateException("La orden ya está finalizada");
		}

		if(this.estado.equals("CANCELADA")) {
			throw new StateException("No se puede finalizar la orden porque fue cancelada");
		}
		
		if(this.estado.equals("PENDIENTE")) {
			throw new StateException("No se puede finalizar la orden porque todavia no comenzó su ejecución");
		}
		
		this.estado="FINALIZADA";
	}
	
	

	public void editarEstado(String estado) {
		this.estado = estado;
	}
//	
	public LocalDateTime obtenerFecha() {
		return fechaOrden;
	}
	
	public void editarFecha(LocalDateTime fecha) {
		this.fechaOrden = fecha;
	}
	
	public PedidoRetiro obtenerPedido() {
		return pedido;
	}
	
	public void editarPedido(PedidoRetiro pedido) {
		this.pedido = pedido;
	}

	public Recolector obtenerRecolector() {
		return recolector;
	}

	public void retirarRecolector(Recolector recolector) {
		this.recolector = recolector;
	}

	public boolean tieneRecolector() {
		if (this.recolector != null){
			return true;
		}
		else 
			return false;
	}
}