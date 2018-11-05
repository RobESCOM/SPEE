package mx.ipn.escom.spee.pagos.mapeo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tp07_pago_siga")
public class PagoSiga implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_siga")
	private Integer id;

	@Column(name = "tx_archivo")
	private byte[] archivo;

	@Column(name = "fh_envio")
	private Date fechaEnvio;

	@Column(name = "id_cuenta")
	private Integer idCuenta;

	@Column(name = "id_pago")
	private Integer idPago;

	public PagoSiga() {
		super();
	}

	public PagoSiga(Integer id, byte[] archivo, Date fechaEnvio, Integer idCuenta, Integer idPago) {
		super();
		this.id = id;
		this.archivo = archivo;
		this.fechaEnvio = fechaEnvio;
		this.idCuenta = idCuenta;
		this.idPago = idPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

}
