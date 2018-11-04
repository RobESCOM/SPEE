package mx.ipn.escom.spee.impresiones.mapeo;

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

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name="tf02_fh_impresion")
public class FhImpresiones implements Modelo,Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	
	@Column(name="fh_impresion")
	private Date fh_impresion;
	
	@Column(name="num_impresion")
	private int num_impresion;
	
	@ManyToOne
	@JoinColumn(name="id_cuenta")
	private CuentaImpresiones cuentaImpresion;
	
	@Column(name="hour")
	private Date hour;
	
	public FhImpresiones() {
		
	}
	
	public FhImpresiones(Date fh_impresion, int num_impresion, CuentaImpresiones cuentaImpresion, Date hour) {
		this.fh_impresion=fh_impresion;
		this.num_impresion=num_impresion;
		this.cuentaImpresion=cuentaImpresion;
		this.hour = hour;
	}
	
	public FhImpresiones(Integer id,Date fh_impresion, int num_impresion, CuentaImpresiones cuentaImpresion, Date hour) {
		this.id=id;
		this.fh_impresion=fh_impresion;
		this.num_impresion=num_impresion;
		this.cuentaImpresion=cuentaImpresion;
		this.hour = hour;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getFh_impresion() {
		return fh_impresion;
	}

	public void setFh_impresion(Date fh_impresion) {
		this.fh_impresion = fh_impresion;
	}

	public int getNum_impresion() {
		return num_impresion;
	}

	public void setNum_impresion(int num_impresion) {
		this.num_impresion = num_impresion;
	}

	public CuentaImpresiones getCuentaImpresion() {
		return cuentaImpresion;
	}

	public void setCuentaImpresion(CuentaImpresiones cuentaImpresion) {
		this.cuentaImpresion = cuentaImpresion;
	}
	
}
