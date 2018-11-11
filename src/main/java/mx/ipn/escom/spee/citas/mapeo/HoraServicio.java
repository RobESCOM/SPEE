package mx.ipn.escom.spee.citas.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tcd05_hora_servicio")
public class HoraServicio implements Modelo, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1587739531555924261L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_hora_servicio")
	private Integer id;
	
	@Column(name = "tx_hora")
	private String hora;
	
	@Column(name = "tx_turno")
	private String turno;
	
	@Column(name = "st_activo")
	private Boolean estatus;
	
	@OneToOne(mappedBy = "horaServicio")
	private Cita cita;
	
	public HoraServicio() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}
	
	@Override
	public String toString() {
		return hora;
	}
	
}
