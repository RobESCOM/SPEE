package mx.ipn.escom.spee.pagos.mapeo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tp06_servicio_efectuado")
public class ServicioEfectuado implements Modelo, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	@JoinColumn(name="id_pago")
	private ArchivoPagoDia archivoPago;
	
	@Column(name = "fh_aprobado")
	private Date fh_aprobado;
	
	public ServicioEfectuado() {
		
	}

	public ArchivoPagoDia getArchivoPago() {
		return archivoPago;
	}

	public void setArchivoPago(ArchivoPagoDia archivoPago) {
		this.archivoPago = archivoPago;
	}

	public Date getFh_aprobado() {
		return fh_aprobado;
	}

	public void setFh_aprobado(Date fh_aprobado) {
		this.fh_aprobado = fh_aprobado;
	}
	
	
}
