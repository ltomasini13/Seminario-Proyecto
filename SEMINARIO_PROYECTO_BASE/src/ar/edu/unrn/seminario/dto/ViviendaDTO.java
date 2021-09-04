package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

public class ViviendaDTO {
		private String calle;
		private int numero;
		private String barrio;
		private float latitud;
		private float longitu;
		private String nomApeDue�o;
		private LocalDateTime fechaYhora;
		
		
		public ViviendaDTO(String calle, int numero, String barrio, float latitud, float longitu, String nomApeDue�o,
				LocalDateTime fechaYhora) {
			this.calle = calle;
			this.numero = numero;
			this.barrio = barrio;
			this.latitud = latitud;
			this.longitu = longitu;
			this.nomApeDue�o = nomApeDue�o;
			this.fechaYhora = fechaYhora;
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


		public float obtenerLatitud() {
			return latitud;
		}


		public void editarLatitud(float latitud) {
			this.latitud = latitud;
		}


		public float obtenerLongitu() {
			return longitu;
		}


		public void editarLongitu(float longitu) {
			this.longitu = longitu;
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
