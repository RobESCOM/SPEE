package mx.ipn.escom.spee.pagos.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.nota.mapeo.NotaPago;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.mapeo.AjaxResult;

@Namespace("/pagos")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName", "gestionar-pagos" }),
		@Result(name = "getNotesByUserId", type = "json", params = { "root", "action", "includeProperties",
				"ajaxResult.*" }) })
@AllowedMethods({ "getNotesByUserId" })
public class GestionarNotasPagoAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String GET_NOTES_BY_USER = "getNotesByUserId";
	
	@Autowired
	private GenericSearchBs genericSearchBs;

	private Usuario usuarioSel;

	private List<NotaPago> ajaxResult;

	private Integer idUser;

	public String index() {
		return INDEX;
	}

	public String show() {
		return SHOW;
	}

	public void validateCreate() {

	}

	public String create() {
		addActionMessage("... exitosamente");
		return SUCCESS;
	}

	@SkipValidation
	public String getNotesByUserId() {
		getAjaxResult();
		NotaPago notaExample = new NotaPago();
		notaExample.setIdCuenta(idUser);
		List<NotaPago> l = genericSearchBs.findAll(NotaPago.class);
		ajaxResult = genericSearchBs.findByExample(notaExample);
		SessionManager.put(NombreObjetosSesion.AJAX_RESULT, ajaxResult);
		return GET_NOTES_BY_USER;
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

	public List<NotaPago> getAjaxResult() {
		this.ajaxResult = (List<NotaPago>) SessionManager.get(NombreObjetosSesion.AJAX_RESULT);
		if (ajaxResult == null) {
			ajaxResult = new ArrayList<NotaPago>();
			SessionManager.put(NombreObjetosSesion.AJAX_RESULT, ajaxResult);
		}
		return ajaxResult;
	}

	public void setAjaxResult(List<NotaPago> ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}
	
	

}
