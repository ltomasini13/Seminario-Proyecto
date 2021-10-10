package ar.edu.unrn.seminario.modelo;

public class Residuo {

<<<<<<< Updated upstream
	private TipoResiduo tipo;
	private double cantkg;
	
	public Residuo(TipoResiduo residuo, float cant_kg) {
		this.tipo = residuo;
		this.cantkg = cant_kg;
	}
	
	public double totalPuntosRetiro(){
		double totalkg = 0;
		totalkg = cantkg * tipo.obtenerPunto();
		return totalkg;
	}
	
	public TipoResiduo obtenerResiduo() {
		return tipo;
	}

	public void editarResiduo(TipoResiduo residuo) {
		this.tipo = residuo;
	}

	public double obtenerCantkg() {
		return cantkg;
	}

	public void editarCantkg(double cant_kg) {
		this.cantkg = cant_kg;
	}
	
=======
	private String tipo;
	private int punto;
	
	public Residuo(String tipo, int punto) {
		this.tipo = tipo;
		this.punto = punto;
	}
	
	public String obetenerTipo() {
		return tipo;
	}

	public void cambiarTipo(String tipo) {
		this.tipo = tipo;
	}

	public int obtenerPunto() {
		return punto;
	}

	public void cambiarPunto(int punto) {
		this.punto = punto;
	}
>>>>>>> Stashed changes
}
