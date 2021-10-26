package ar.edu.unrn.seminario.modelo;

public class OrdenDeRetiro {

	private final static String PENDIENTE = "PENDIENTE";
	private final static String EN_EJECUCION = "EN_EJECUCION";
	private final static String CONCRETADO = "CONCRETADO";
	private final static String CANCELADO = "CANCELADO";
	
	private Integer id;
	private String estado;
	private PedidoRetiro pedido;
	private Recolector recolector;
	
	public OrdenDeRetiro(String estado, PedidoRetiro pedido, Recolector recolector) {
		
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