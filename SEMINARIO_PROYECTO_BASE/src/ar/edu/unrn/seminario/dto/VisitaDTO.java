package ar.edu.unrn.seminario.dto;

import java.time.LocalDateTime;

public class VisitaDTO {
		private Integer id;
		private LocalDateTime fechaVisita;
		private String observacion;
		private LocalDateTime fechaOrden;
		
		
		public VisitaDTO( Integer id, LocalDateTime fechaVisita, String observacion, LocalDateTime fechaOrden) {
			this.id=id;
			this.fechaVisita = fechaVisita;
			this.observacion = observacion;
			this.fechaOrden = fechaOrden;
		}


		public LocalDateTime obtenerFechaVisita() {
			return fechaVisita;
		}


		public String obtenerObservacion() {
			return observacion;
		}


		public LocalDateTime obtenerFechaOrden() {
			return fechaOrden;
		}


		public Integer obtenerId() {
			return id;
		}
		
		
		
		
}
