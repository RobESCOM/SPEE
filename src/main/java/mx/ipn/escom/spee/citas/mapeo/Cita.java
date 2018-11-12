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

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tcd01_cita")
public class Cita implements Modelo, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3441564952993695105L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cita")
	private Integer id;
	
	@Column(name = "fh_cita")
	private Date fecha;
	
	@Column(name = "id_estado_cita")
	private Integer idEstado;
	
	@Column(name = "id_hora_servicio")
	private Integer idHora;
	
	@Column(name = "id_cuenta")
	private Integer idCuenta;
	
	@OneToOne
	@JoinColumn(name = "id_cuenta", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
	private Cuenta cuenta;
	
	@ManyToOne
	@JoinColumn(name = "id_hora_servicio", referencedColumnName = "id_hora_servicio", insertable = false, updatable = false)
	private HoraServicio horaServicio;
	
	@ManyToOne
	@JoinColumn(name = "id_estado_cita", referencedColumnName = "id_estado_cita", insertable = false, updatable = false)
	private EstadoCita estadoCita;
	
	public Cita() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	@Validations(requiredFields = {
			@RequiredFieldValidator(message = "Campo Obligatorio", type = ValidatorType.FIELD) })
	public Integer getIdHora() {
		return idHora;
	}

	public void setIdHora(Integer idHora) {
		this.idHora = idHora;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public HoraServicio getHoraServicio() {
		return horaServicio;
	}

	public void setHoraServicio(HoraServicio horaServicio) {
		this.horaServicio = horaServicio;
	}

	public EstadoCita getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(EstadoCita estadoCita) {
		this.estadoCita = estadoCita;
	}
}
