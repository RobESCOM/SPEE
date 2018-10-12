package mx.ipn.escom.spee.notificaciones.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tc04_notificacion")
public class Notificacion implements Modelo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_notificacion")
	private Integer id;

	@Column(name = "tx_motivo")
	private String motivo;

	@Column(name = "id_cuenta")
	private Integer idCuenta;

	public Notificacion() {
		super();
	}

	public Notificacion(Integer id, String motivo, Integer idCuenta) {
		super();
		this.id = id;
		this.motivo = motivo;
		this.idCuenta = idCuenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

}
