package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

public class ViviendaDTO {
		private Integer id;
		private String calle;
		private int numero;
		private String barrio;
		private double latitud;
		private double longitud;
		private String nomApeDue�o;
		private LocalDateTime fechaYhora;
		
		
		public ViviendaDTO(Integer id, String calle, int numero, String barrio, double latitud, double longitud, String nomApeDue�o,
				LocalDateTime fechaYhora) {
			this.id=id;
			this.calle = calle;
			this.numero = numero;
			this.barrio = barrio;
			this.latitud = latitud;
			this.longitud = longitud;
			this.nomApeDue�o = nomApeDue�o;
			this.fechaYhora = fechaYhora;
		}

		public Integer obtenerId() {
			return id;
		}
		
		public void editarId(Integer id) {
			this.id=id;
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


		public String obtenerBarrio() {
			return barrio;
		}


		public void editarBarrio(String barrio) {
			this.barrio = barrio;
		}


		public double obtenerLatitud() {
			return latitud;
		}


		public void editarLatitud(float latitud) {
			this.latitud = latitud;
		}


		public double obtenerLongitu() {
			return longitud;
		}


		public void editarLongitu(float longitu) {
			this.longitud = longitu;
		}


		public String obtenerNomApeDue�o() {
			return nomApeDue�o;
		}


		public void editarNomApeDue�o(String nomApeDue�o) {
			this.nomApeDue�o = nomApeDue�o;
		}


		public LocalDateTime obtenerFechaYhora() {
			return fechaYhora;
		}


		public void editarFechaYhora(LocalDateTime fechaYhora) {
			this.fechaYhora = fechaYhora;
		} 
		
		
		
}
