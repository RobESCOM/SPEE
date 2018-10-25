package mx.ipn.escom.spee.nota.mapeo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.mapeo.Modelo;

//@Entity
//@Table(name="tb02_nota_pago")
public class NotaPago implements Modelo, Serializable{

	private static final long serialVersionUID = 1L;

//	@Id
//	@Column(name="id_nota")
//	private Integer id_nota;
//	
//	@Column(name="fh_emision")
//	private Date fh_emision;
//	
//	@Column(name="cant_pago")
//	private BigDecimal cant_pago;
//	
//	@OneToOne
//	@JoinColumn(name="id_cuenta")
//	private Cuenta cuenta;
//	
//	@OneToOne
//	@JoinColumn(name="id_catalogo_servicio")
//	private CatalogoServicio catalogServicio;
	
	
}
