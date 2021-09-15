package ar.edu.unrn.seminario.api;

import ar.unrn.edu.ar.seminario.accesos.ViviendaDAOJDBC;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDao;

public class PersistenceApi {
	private ViviendaDao viviendaDao;
	

	public PersistenceApi() {
		viviendaDao = new ViviendaDAOJDBC();
		
	}
}
