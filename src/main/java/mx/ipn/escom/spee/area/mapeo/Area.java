package mx.ipn.escom.spee.area.mapeo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.mapeo.Modelo;

/**
 * @author Robert
 *
 */
@Entity
@Table(name = "tc01_catalogo_area")
public class Area implements Modelo {
	
	
	/**
	 * Id del area que tiene asociados servicios
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_area")
	private Integer idArea;
	
	@Column(name = "nb_area")
	private String nombreArea;
	
	@Column(name = "ds_descripcion")
	private String descripcion;
	
	@Column(name = "st_activa")
	private Boolean estatus;
	
	@OneToOne
	@JoinColumn(name = "responsable", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
	private Cuenta cuenta;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_area", referencedColumnName = "id_area", insertable = false, updatable = false)
	private List<CatalogoServicio> catalogoServicios;

	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public List<CatalogoServicio> getCatalogoServicios() {
		return catalogoServicios;
	}

	public void setCatalogoServicios(List<CatalogoServicio> catalogoServicios) {
		this.catalogoServicios = catalogoServicios;
	}
	
	
	
}
