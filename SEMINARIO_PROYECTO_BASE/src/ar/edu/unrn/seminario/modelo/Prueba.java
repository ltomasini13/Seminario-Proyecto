package ar.edu.unrn.seminario.modelo;

import java.util.List;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDAOJDBC;

public class Prueba {
	public static void main(String[] args) throws SintaxisSQLException, DuplicateUniqueKeyException, NotNullException, DataEmptyException, NumbersException {
		ViviendaDAOJDBC vista = new ViviendaDAOJDBC();
		
		vista.crear(new Vivienda(new Ubicacion("Pedro Bronzetti", 450, null,40.4532, 60.7645), new Ciudadano("Laura", "Tomasini", "39354863", null)));
		
	}
}
