package mx.edu.spee.controlacceso.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name="tau05_usr_ipn")
public class UsuarioIpn implements Modelo, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name="id_inf_personal")
	private InformacionPersonal infoPersonal;
	
	@Column(name="num_ipn")
	private String numIpn;
	
	public UsuarioIpn() {
		
	}

	public InformacionPersonal getInfoPersonal() {
		return infoPersonal;
	}

	public void setInfoPersonal(InformacionPersonal infoPersonal) {
		this.infoPersonal = infoPersonal;
	}

	public String getNumIpn() {
		return numIpn;
	}

	public void setNumIpn(String numIpn) {
		this.numIpn = numIpn;
	}
	
}
