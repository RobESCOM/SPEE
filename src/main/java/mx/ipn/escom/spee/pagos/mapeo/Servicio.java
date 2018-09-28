package mx.ipn.escom.spee.pagos.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tc03_servicio")
public class Servicio implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_servicio_area")
	private Integer id;

	@Column(name = "tx_clave")
	private String clave;

	@Column(name = "ds_servicio")
	private String descripcion;

	@Column(name = "nu_monto")
	private Double monto;

	public Servicio() {
		super();
	}

	public Servicio(Integer id, String clave, String descripcion, Double monto) {
		super();
		this.id = id;
		this.clave = clave;
		this.descripcion = descripcion;
		this.monto = monto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

}
