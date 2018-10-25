package mx.ipn.escom.spee.impresiones.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.impresiones.bs.ImpresionesBs;
import mx.ipn.escom.spee.impresiones.exception.ImpresionesInsuficientesException;
import mx.ipn.escom.spee.impresiones.mapeo.*;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

import java.io.Serializable;
import java.util.List;

@Namespace("/impresiones")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName","control-impresiones" }) })
public class ControlImpresionesAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ImpresionesBs impresionesBs; 
	
	private Integer idSel;
	
	private List<CuentaImpresiones> cuentaImpresiones;
	
	private List<InformacionPersonal> informacionPersonal;
	
	private Cuenta usr;
	
	private InformacionPersonal usrInformacion;
	
	private CuentaImpresiones usrImpresiones;
	
	private int tipoImpresion, numeroImpresion;
	
	private GenericSearchBs genericSearchBs;
		
	public String index() {
		cuentaImpresiones = impresionesBs.obtenerCuentasConImpresiones();
		return INDEX;
	}
	
	public void validateCreate() throws ImpresionesInsuficientesException {
		impresionesBs.modCantidad(numeroImpresion,tipoImpresion,idSel);
	}
	
	public String create() {
		return SUCCESS;
	}
	
	public String editNew() {
		usrInformacion = impresionesBs.obtenerPersona(usr);
		usrImpresiones = impresionesBs.obtenerCuentaImpresion(usr.getIdCuenta());
		return EDITNEW;
	}

	public ImpresionesBs getImpresionesBs() {
		return impresionesBs;
	}

	public void setImpresionesBs(ImpresionesBs impresionesBs) {
		this.impresionesBs = impresionesBs;
	}

	public List<CuentaImpresiones> getCuentaImpresiones() {
		return cuentaImpresiones;
	}

	public void setCuentaImpresiones(List<CuentaImpresiones> cuentaImpresiones) {
		this.cuentaImpresiones = cuentaImpresiones;
	}

	public Cuenta getUsr() {
		return usr;
	}

	public void setUsr(Cuenta usr) {
		this.usr = usr;
	}

	public int getTipoImpresion() {
		return tipoImpresion;
	}

	public void setTipoImpresion(int tipoImpresion) {
		this.tipoImpresion = tipoImpresion;
	}

	public int getNumeroImpresion() {
		return numeroImpresion;
	}

	public void setNumeroImpresion(int numeroImpresion) {
		this.numeroImpresion = numeroImpresion;
	}

	public Serializable getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		if(idSel != null) {
			usr = genericSearchBs.findById(Cuenta.class, idSel);
		}
		this.idSel = idSel;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public List<InformacionPersonal> getInformacionPersonal() {
		return informacionPersonal;
	}

	public void setInformacionPersonal(List<InformacionPersonal> informacionPersonal) {
		this.informacionPersonal = informacionPersonal;
	}

	public InformacionPersonal getUsrInformacion() {
		return usrInformacion;
	}

	public void setUsrInformacion(InformacionPersonal usrInformacion) {
		this.usrInformacion = usrInformacion;
	}

	public CuentaImpresiones getUsrImpresiones() {
		return usrImpresiones;
	}

	public void setUsrImpresiones(CuentaImpresiones usrImpresiones) {
		this.usrImpresiones = usrImpresiones;
	}

	
	
}