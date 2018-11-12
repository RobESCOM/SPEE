package mx.ipn.escom.spee.pagos.mapeo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tp08_pagos_corte")
public class PagosCorte implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pago_corte")
	private Integer id;

	@Column(name = "id_corte_caja")
	private Integer idCorteCaja;

	@Column(name = "id_pago")
	private Integer idPago;

	@Column(name = "fecha_pago")
	private Date fechaPago;

	@Column(name = "id_cuenta")
	private Integer idCuenta;

	public PagosCorte() {
		super();
	}

	public PagosCorte(Integer id, Integer idCorteCaja, Integer idPago, Date fechaPago, Integer idCuenta) {
		super();
		this.id = id;
		this.idCorteCaja = idCorteCaja;
		this.idPago = idPago;
		this.fechaPago = fechaPago;
		this.idCuenta = idCuenta;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCorteCaja() {
		return idCorteCaja;
	}

	public void setIdCorteCaja(Integer idCorteCaja) {
		this.idCorteCaja = idCorteCaja;
	}

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

}
