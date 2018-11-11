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
import javax.persistence.Table;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tcd04_consulta")
public class Consulta implements Modelo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1026905290216309814L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_consulta")
	private Integer id;
	
	@Column(name = "fh_consulta")
	private Date fechaConsulta;
	
	@Column(name = "tx_hora")
	private String hora;
	
	@Column(name = "tx_observaciones")
	private String observaciones;
	
	@Column(name = "paciente")
	private String idPaciente;
	
	@Column(name = "dentista")
	private Integer idDentista;
	
	@ManyToOne
	@JoinColumn(name = "paciente", referencedColumnName = "clave", insertable = false, updatable = false)
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "dentista", referencedColumnName = "id_cuenta", insertable = false, updatable = false)
	private Cuenta cuenta;
	
	public Consulta() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(String idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Integer getIdDentista() {
		return idDentista;
	}

	public void setIdDentista(Integer idDentista) {
		this.idDentista = idDentista;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}	
}
