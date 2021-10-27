package ar.edu.unrn.seminario.modelo;

import java.time.LocalDate;

public class OrdenDeRetiro {

	private final static String PENDIENTE = "PENDIENTE";
	private final static String EN_EJECUCION = "EN_EJECUCION";
	private final static String CONCRETADO = "CONCRETADO";
	private final static String CANCELADO = "CANCELADO";
	
	private Integer id;
	private String estado;
	private LocalDate fechaOrden;
	private PedidoRetiro pedido;
	private Recolector recolector;
	
	public OrdenDeRetiro(String fecha, String estado, PedidoRetiro pedido, Recolector recolector) {
		
		this.fechaOrden = LocalDate.parse(fecha);
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
	
	public void editarEstado(String estado) {
		this.estado = estado;
	}
	
	public LocalDate obtenerFecha() {
		return fechaOrden;
	}
	
	public void editarFecha(LocalDate fecha) {
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

}