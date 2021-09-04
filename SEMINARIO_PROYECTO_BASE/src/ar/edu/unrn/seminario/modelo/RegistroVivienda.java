package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;

public class RegistroVivienda {
	private LocalDateTime fechaYhora;
	private Vivienda vivienda;
	
	
	public RegistroVivienda(LocalDateTime fechaYhora, Vivienda vivienda) {
		
		this.fechaYhora = fechaYhora;
		this.vivienda = vivienda;
	}


	public LocalDateTime fechaYhora() {
		return fechaYhora;
	}


	public void editarFechaYhora(LocalDateTime fechaYhora) {
		this.fechaYhora = fechaYhora;
	}


	public Vivienda vivienda() {
		return vivienda;
	}


	public void editarVivienda(Vivienda vivienda) {
		this.vivienda = vivienda;
	}
	
	
	
}
