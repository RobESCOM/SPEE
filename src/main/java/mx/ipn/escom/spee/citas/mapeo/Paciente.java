package mx.ipn.escom.spee.citas.mapeo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.edu.spee.controlacceso.mapeo.Perfil;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tcd06_paciente")
public class Paciente implements Modelo, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7242065935494719208L;

	@Id
	@Column(name = "clave")
	private String clave;
	
	@Column(name = "tx_nombre")
	private String nombre;
	
	@Column(name = "tx_primer_apellido")
	private String primerApellido;
	
	@Column(name = "tx_segundo_apellido")
	private String segundoApellido;
	
	@Column(name = "tx_celular")
	private String celular;
	
	@Column(name = "tx_correo")
	private String correo;
	
	@Column(name = "id_perfil")
	private Integer idPerfil;
	
	@OneToOne
	@JoinColumn(name = "id_perfil", referencedColumnName = "id_perfil", insertable = false, updatable = false)
	private Perfil perfil;
	
	@OneToMany(mappedBy = "paciente")
	private List<Consulta> listConsultas;
	
	public Paciente() {
		super();
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Integer getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Consulta> getListConsultas() {
		return listConsultas;
	}

	public void setListConsultas(List<Consulta> listConsultas) {
		this.listConsultas = listConsultas;
	}

}
