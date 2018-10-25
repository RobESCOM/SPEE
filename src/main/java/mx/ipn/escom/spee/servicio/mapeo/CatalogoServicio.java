package mx.ipn.escom.spee.servicio.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import mx.ipn.escom.spee.area.mapeo.CatalogoArea;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tc02_catalogo_servicio")
public class CatalogoServicio implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_catalogo_servicio")
	private Integer id;

	@Column(name = "tx_clave")
	private String clave;

	@Column(name = "ds_servicio")
	private String descripcion;

	@Column(name = "precio")
	private String precio;

	@Column(name = "st_activo")
	private Boolean estatus;

	@Column(name = "id_area")
	private Integer idArea;

	@Column(name = "id_tipo_servicio")
	private String idTipoServicio;

	@ManyToOne
	@JoinColumn(name = "id_area", referencedColumnName = "id_area", insertable = false, updatable = false)
	private CatalogoArea area;

	@ManyToOne
	@JoinColumn(name = "id_tipo_servicio", referencedColumnName = "id_tipo_servicio", insertable = false, updatable = false)
	private TipoServicio tipoServicio;

	public CatalogoServicio() {
		super();
	}

	public CatalogoServicio(Integer id, String clave, String descripcion, String precio, Integer idArea,
			String idTipoServicio) {
		super();
		this.id = id;
		this.clave = clave;
		this.descripcion = descripcion;
		this.precio = precio;
		this.idArea = idArea;
		this.idTipoServicio = idTipoServicio;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(message = "Campo obligatorio", type = ValidatorType.FIELD) }, requiredFields = {
					@RequiredFieldValidator(message = "Campo obligatorio", type = ValidatorType.FIELD) }, stringLengthFields = {
							@StringLengthFieldValidator(maxLength = "3", minLength = "4", trim = true, message = "La clave del servicio consta de tres a cuatro digitos.") })
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

	@Validations(requiredFields = {
			@RequiredFieldValidator(message = "Campo obligatorio", type = ValidatorType.FIELD) })
	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	@Validations(requiredFields = {
			@RequiredFieldValidator(message = "Campo obligatorio", type = ValidatorType.FIELD) })
	public Integer getIdArea() {
		return idArea;
	}

	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}

	@Validations(requiredFields = {
			@RequiredFieldValidator(message = "Campo obligatorio", type = ValidatorType.FIELD) })
	public String getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(String idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public CatalogoArea getArea() {
		return area;
	}

	public void setArea(CatalogoArea area) {
		this.area = area;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

}
