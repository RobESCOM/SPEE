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
@Table(name = "tp04_corte_caja")
public class CorteCaja implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_corte_caja")
	private Integer id;

	@Column(name = "id_cuenta")
	private Integer idCuenta;

	@Column(name = "st_corte")
	private Boolean estado;

	@Column(name = "fh_corte")
	private Date fechaCorte;

	public Integer getId() {
		return id;
	}

	public CorteCaja() {
		super();
	}

	public CorteCaja(Integer id, Integer idCuenta, Boolean estado, Date fechaCorte) {
		super();
		this.id = id;
		this.idCuenta = idCuenta;
		this.estado = estado;
		this.fechaCorte = fechaCorte;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFechaCorte() {
		return fechaCorte;
	}

	public void setFechaCorte(Date fechaCorte) {
		this.fechaCorte = fechaCorte;
	}

}
