package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;

import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.modelo.ResiduoARetirar;

public class PedidoRetiroDTO {
	private Integer id;
	private LocalDateTime fechaEmision;
	private LocalDateTime fechaCumplimiento;
	private boolean cargaPesada;
	private String observacion;
	private String calle;
    private int numero;
    private String barrio;
    private double latitud;
    private double longitud;
    private String nombre;
	private String apellido;
	private String dni;
	private ArrayList<ResiduoARetirar> listaResiduos = new ArrayList<ResiduoARetirar>();
	
	public PedidoRetiroDTO(Integer id, LocalDateTime fechaEmision, LocalDateTime fechaCumplimiento, boolean cargaPesada, String observacion, String calle, int numero, 
			String barrio, double latitud, double longitud,  String nombre, String apellido) throws NotNullException {

		this.id=id;
		this.fechaEmision = fechaEmision;
		this.fechaCumplimiento=fechaCumplimiento;
		this.cargaPesada = cargaPesada;
		this.observacion = observacion;

		this.calle = calle;
		this.numero = numero;
		this.barrio = barrio;
		this.latitud = latitud;
		this.longitud = longitud;
		this.nombre = nombre;
		this.apellido = apellido;
	
	}
	
	public LocalDateTime obtenerFechaEmision() {
		return fechaEmision;
	}

	public void editarFechaEmision(LocalDateTime fecha) {
		this.fechaEmision = fecha;
	}

	public boolean isCargaPesada() {
		return cargaPesada;
	}
	
	public void editarCargaPesada(boolean cargaPesada) {
		this.cargaPesada = cargaPesada;
	}

	public String obtenerObservacion() {
		return observacion;
	}
	
	public void editarObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
	public String obtenerCalle() {
		return calle;
	}

	public void editarCalle(String calle) {
		this.calle = calle;
	}

	public int obtenerNumero() {
		return numero;
	}

	public void editarNumero(int numero) {
		this.numero = numero;
	}

	public Integer obtenerId() {
		return this.id;
	}
	public void editarId(Integer id) {
		this.id = id;
	}
	public LocalDateTime obtenerFechaCumplimiento() {
		return fechaCumplimiento;
	}

	public void seditarFechaCumplimiento(LocalDateTime fechaCumplimiento) {
		this.fechaCumplimiento = fechaCumplimiento;
	}

	public String obtenerBarrio() {
		return barrio;
	}

	public void editarBarrio(String barrio) {
		this.barrio = barrio;
	}

	public double obtenerLatitud() {
		return latitud;
	}

	public void editarLatitud(int latitud) {
		this.latitud = latitud;
	}

	public double obtenerLongitud() {
		return longitud;
	}

	public void editarLongitud(int longitud) {
		this.longitud = longitud;
	}
	
	public String obtenerNomyApe() {
		return nombre+""+apellido;
	}
	
	public String obtenerNombre() {
		return nombre;
	}
	public void editarNombre(String nombre) {
		this.nombre = nombre;
	}
	public String obtenerApellido() {
		return apellido;
	}
	public void editarApellido(String apellido) {
		this.apellido = apellido;
	}
	public String obtenerDni() {
		return dni;
	}
	public void editarDni(String dni) {
		this.dni = dni;
	}
	
}
