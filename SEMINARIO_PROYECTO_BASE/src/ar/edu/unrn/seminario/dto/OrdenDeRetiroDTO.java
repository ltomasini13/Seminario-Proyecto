package ar.edu.unrn.seminario.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import ar.edu.unrn.seminario.modelo.PedidoRetiro;
import ar.edu.unrn.seminario.modelo.Recolector;

public class OrdenDeRetiroDTO {
	private Integer id;
	private String estado;
	private LocalDateTime fechaOrden;
	private LocalDateTime fechaPedido;
	private String nombreApeRecolector;

	
	public OrdenDeRetiroDTO(Integer id, String fecha, String estado,String fechaPedido ,String nombreApeRecolector) {
		this.id=id;
		this.fechaOrden = LocalDateTime.parse(fecha);
		this.estado = estado;
		this.fechaPedido=LocalDateTime.parse(fechaPedido);
		this.nombreApeRecolector=nombreApeRecolector;
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
	
	public LocalDateTime obtenerFecha() {
		return fechaOrden;
	}
	
	public void editarFecha(LocalDateTime fecha) {
		this.fechaOrden = fecha;
	}


	public LocalDateTime obtenerFechaPedido() {
		return fechaPedido;
	}


	public void editarFechaPedido(LocalDateTime fechaPedido) {
		this.fechaPedido = fechaPedido;
	}


	public String obtenerNombreApeRecolector() {
		return nombreApeRecolector;
	}


	public void editarNombreApeRecolector(String nombreApeRecolector) {
		this.nombreApeRecolector = nombreApeRecolector;
	}
	
	
	

}
