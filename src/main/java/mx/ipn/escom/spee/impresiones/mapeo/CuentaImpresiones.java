package mx.ipn.escom.spee.impresiones.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name="tf01_control_impresiones")
public class CuentaImpresiones implements Modelo, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	@JoinColumn(name="id_cuenta")
	private Cuenta id;
	
	@Column(name="nu_impresiones")
	private Integer nu_impresiones;
	
	public CuentaImpresiones() {
		
	}
	
	public CuentaImpresiones(Cuenta id, Integer nu_impresiones) {
		this.id=id;
		this.nu_impresiones=nu_impresiones;
	}

	public Cuenta getId() {
		return id;
	}

	public void setId(Cuenta id) {
		this.id = id;
	}

	public Integer getNu_impresiones() {
		return nu_impresiones;
	}

	public void setNu_impresiones(Integer nu_impresiones) {
		this.nu_impresiones = nu_impresiones;
	}

	
}
