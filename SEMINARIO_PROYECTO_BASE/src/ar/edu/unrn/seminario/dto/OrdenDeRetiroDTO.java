package ar.edu.unrn.seminario.dto;

import java.time.LocalDate;

import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;

public class OrdenDeRetiroDTO {

	private String estado;
	private LocalDate fechaOrden;
	private PedidoRetiro pedido;
	private Recolector recolector;
	
	public OrdenDeRetiroDTO(String fecha, String estado, PedidoRetiro pedido, Recolector recolector) {
		
		this.fechaOrden = LocalDate.parse(fecha);
		this.estado = "PENDIENTE";
		this.pedido = pedido;
		this.recolector = recolector;
		
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
}
