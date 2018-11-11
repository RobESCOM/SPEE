package mx.ipn.escom.spee.citas.mapeo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tcd03_nota_pago")
public class NotaPago implements Modelo, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3461644614028611038L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_nota_pago")
	private Integer id;
	
	@Column(name = "fh_emision")
	private Date fechaEmision;
	
	@Column(name = "id_consulta")
	private Integer idConsulta;
	
	@OneToOne
	@JoinColumn(name = "id_consulta", referencedColumnName = "id_consulta", insertable = false, updatable = false)
	private Consulta consulta;
	
	@ManyToOne
	@JoinColumn(name = "id_catalogo_servicio", referencedColumnName = "id_catalogo_servicio", insertable= false, updatable = false)
	private CatalogoServicio servicio;
	
	public NotaPago() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public CatalogoServicio getServicio() {
		return servicio;
	}

	public void setServicio(CatalogoServicio servicio) {
		this.servicio = servicio;
	}
	
	
}
