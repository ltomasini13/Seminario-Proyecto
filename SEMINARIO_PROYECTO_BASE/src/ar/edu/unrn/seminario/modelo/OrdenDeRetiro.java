package ar.edu.unrn.seminario.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ar.edu.unrn.seminario.exception.StateException;

public class OrdenDeRetiro {

	private final static String PENDIENTE = "PENDIENTE";
	private final static String EN_EJECUCION = "EN_EJECUCION";
	private final static String CONCRETADA = "CONCRETADA";
	private final static String CANCELADA = "CANCELADA";
	
	private Integer id;
	private String estado;
	private LocalDateTime fechaOrden;
	private PedidoRetiro pedido;
	private Recolector recolector;
	

	public OrdenDeRetiro(String fecha, PedidoRetiro pedido) {

		this.fechaOrden = LocalDateTime.parse(fecha);
		this.estado = PENDIENTE;
		this.pedido = pedido;
		
	}

	public OrdenDeRetiro() {
	
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
	
	public void editarEstado(String estado) {
		this.estado = estado;
	}
	
	public boolean estaCancelada() {
		if (estado.equals(CANCELADA)) {
			return true;
		}
		return false;
	}
	
	public boolean estaEnEjecucion() {
		if (estado.equals(EN_EJECUCION)) {
			return true;
		}
		return false;
	}
	
	public boolean estaPendiente() {
		if (estado.equals(PENDIENTE)) {
			return true;
		}
		return false;
	}
	
	public boolean estaConcretada() {
		if (estado.equals(CONCRETADA)) {
			return true;
		}
		return false;
	}
	
	public void ejecutarOrden() throws StateException {
		if(estaEnEjecucion()) {
			throw new StateException("La orden ya está ejecutandose");
		}
		
		if(estaConcretada()) {
			throw new StateException("No se puede ejecutar la orden porque ya finalizó");
		}
		
		
		if(estaCancelada()) {
			throw new StateException("No se puede ejecutar la orden porque está cancelada");
		}
		
		this.estado=EN_EJECUCION;
	}
	
	
	public void cancelarOrden() throws StateException {
		
		if(estaEnEjecucion()) {
			throw new StateException("No se puede cancelar la orden porque está en ejecución") ;
		}

		if(estaCancelada()) {
			throw new StateException("La orden ya está cancelada");
		}
		if(estaConcretada()) {
			throw new StateException("No se puede cancelar la orden porque ya finalizó");
		}

		
		this.estado=CANCELADA;
	}
	
	public void finalizarOrden() throws StateException {
		
		if(estaConcretada()) {
			throw new StateException("La orden ya está finalizada");
		}

		if(estaCancelada()) {
			throw new StateException("No se puede finalizar la orden porque fue cancelada");
		}
		
		if(estaPendiente()) {
			throw new StateException("No se puede finalizar la orden porque todavia no comenzó su ejecución");
		}
		
		this.estado=CONCRETADA;
	}
	
	
	
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

	public void asignarRecolector(Recolector recolector) {
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