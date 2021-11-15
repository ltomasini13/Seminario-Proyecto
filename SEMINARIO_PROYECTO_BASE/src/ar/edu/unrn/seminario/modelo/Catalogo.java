package ar.edu.unrn.seminario.modelo;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {

	private List<Beneficio> listaBeneficios = new ArrayList<Beneficio>();
	
	public Catalogo (List<Beneficio> lista) {
		
		this.listaBeneficios = lista;
	}

	public List<Beneficio> obtenerListaBeneficios() {
		return listaBeneficios;
	}

	public void editarListaBeneficios(List<Beneficio> listaBeneficios) {
		this.listaBeneficios = listaBeneficios;
	}
	
	public void agregarBeneficio(Beneficio b) {
		this.listaBeneficios.add(b);
	}
}
