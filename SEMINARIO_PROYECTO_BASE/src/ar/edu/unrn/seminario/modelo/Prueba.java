package ar.edu.unrn.seminario.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import ar.edu.unrn.seminario.exception.DataEmptyException;
import ar.edu.unrn.seminario.exception.DuplicateUniqueKeyException;
import ar.edu.unrn.seminario.exception.NotNullException;
import ar.edu.unrn.seminario.exception.NumbersException;
import ar.edu.unrn.seminario.exception.SintaxisSQLException;
import ar.unrn.edu.ar.seminario.accesos.ViviendaDAOJDBC;

public class Prueba {
	public static void main(String[] args) throws SintaxisSQLException, DuplicateUniqueKeyException, NotNullException, DataEmptyException, NumbersException {
		ViviendaDAOJDBC vista = new ViviendaDAOJDBC();
		
		
		LocalDateTime.parse(LocalDateTime.now().toString());
	}
}
