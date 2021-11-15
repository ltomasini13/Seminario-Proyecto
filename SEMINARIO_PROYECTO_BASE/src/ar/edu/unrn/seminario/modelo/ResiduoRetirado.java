package ar.edu.unrn.seminario.modelo;

import ar.edu.unrn.seminario.exception.NotNullException;

public class ResiduoRetirado extends Residuo{
	private Visita visita;
	
	public ResiduoRetirado(TipoResiduo residuo, double cantKg) throws NotNullException {
		
		super(residuo, cantKg);
		this.visita=visita;
	}

	public Visita obtenerVisita() {
		return visita;
	}

	public void editarVisita(Visita visita) {
		this.visita = visita;
	}
	
	
	
	
}
