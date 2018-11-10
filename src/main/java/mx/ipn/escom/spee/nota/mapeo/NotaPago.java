package mx.ipn.escom.spee.nota.mapeo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tcd03_nota_pago")
public class NotaPago implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nota_pago")
	private Integer id_nota;

	@Column(name = "fh_emision")
	private Date fhEmision;

	@Column(name = "id_consulta")
	private Integer idConsulta;

	@Column(name = "id_catalogo_servicio")
	private Integer idCatalogoServicio;

	@Column(name = "id_cuenta")
	private Integer idCuenta;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_catalogo_servicio", referencedColumnName = "id_catalogo_servicio", insertable = false, updatable = false)
	private CatalogoServicio catalogoServicio;

	public NotaPago() {
		super();
	}

	public NotaPago(Integer id_nota, Date fh_emision, Integer idConsulta, Integer idCatalogoServicio) {
		super();
		this.id_nota = id_nota;
		this.fhEmision = fh_emision;
		this.idConsulta = idConsulta;
		this.idCatalogoServicio = idCatalogoServicio;
	}

	public Integer getId_nota() {
		return id_nota;
	}

	public void setId_nota(Integer id_nota) {
		this.id_nota = id_nota;
	}

	public Date getFh_emision() {
		return fhEmision;
	}

	public void setFh_emision(Date fh_emision) {
		this.fhEmision = fh_emision;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Integer getIdCatalogoServicio() {
		return idCatalogoServicio;
	}

	public void setIdCatalogoServicio(Integer idCatalogoServicio) {
		this.idCatalogoServicio = idCatalogoServicio;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Date getFhEmision() {
		return fhEmision;
	}

	public void setFhEmision(Date fhEmision) {
		this.fhEmision = fhEmision;
	}

	public CatalogoServicio getCatalogoServicio() {
		return catalogoServicio;
	}

	public void setCatalogoServicio(CatalogoServicio catalogoServicio) {
		this.catalogoServicio = catalogoServicio;
	}

	@Override
	public String toString() {
		return "NotaPago [id_nota=" + id_nota + ", fhEmision=" + fhEmision + ", idConsulta=" + idConsulta
				+ ", idCatalogoServicio=" + idCatalogoServicio + ", idCuenta=" + idCuenta + ", catalogoServicio="
				+ catalogoServicio + "]";
	}

}
