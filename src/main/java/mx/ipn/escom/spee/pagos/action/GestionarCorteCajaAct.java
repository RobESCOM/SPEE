package mx.ipn.escom.spee.pagos.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.CorteCaja;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.util.bs.GenericBs;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/pagos")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }),
		@Result(name = ActionSupport.ERROR, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }) })
public class GestionarCorteCajaAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;
	@Autowired
	private	GenericBs genericBs;

	private Integer idUsuario;
	
	private Usuario usuarioSel;

	public void validateCreate() {
		getUsuarioSel();
		ArchivoPagoDia archivo = new ArchivoPagoDia();
		archivo.setCorte(Boolean.FALSE);
		archivo.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
		List<ArchivoPagoDia> listPagosCorte = new ArrayList<>();
		for (ArchivoPagoDia archivoPagoDia : genericSearchBs.findByExample(archivo)) {
			archivoPagoDia.setCorte(Boolean.TRUE);
			listPagosCorte.add(archivoPagoDia);
		}
		genericBs.update(listPagosCorte);
		System.err.println(listPagosCorte);
		CorteCaja corteCaja = new CorteCaja();
		corteCaja.setEstado(true);
		corteCaja.setFechaCorte(new Date());
		Cuenta cuenta = genericSearchBs.findById(Cuenta.class, usuarioSel.getId());
		corteCaja.setIdCuenta(cuenta.getIdCuenta());
		genericBs.save(corteCaja);
	}

	public String create() {
		addActionMessage("Pago ha realizado el corte de caja exitosamente");
		SessionManager.clear();
		return SUCCESS;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Usuario getUsuarioSel() {
		if (SessionManager.get(NombreObjetosSesion.USUARIO_SESION) != null) {
			usuarioSel = (Usuario) SessionManager.get(NombreObjetosSesion.USUARIO_SESION);
		}
		return usuarioSel;
	}

	public void setUsuarioSel(Usuario usuarioSel) {
		this.usuarioSel = usuarioSel;
	}
}
