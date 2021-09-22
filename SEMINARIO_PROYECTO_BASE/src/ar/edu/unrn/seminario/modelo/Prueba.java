package ar.edu.unrn.seminario.modelo;

import java.util.List;

import ar.unrn.edu.ar.seminario.accesos.ViviendaDAOJDBC;

public class Prueba {
	public static void main(String[] args) {
		ViviendaDAOJDBC vista = new ViviendaDAOJDBC();
		
		List<Vivienda> vivis = vista.listarTodas();
		vivis.toString();
	}
}
