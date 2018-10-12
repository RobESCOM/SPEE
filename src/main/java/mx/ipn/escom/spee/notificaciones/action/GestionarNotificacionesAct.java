package mx.ipn.escom.spee.notificaciones.action;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.opensymphony.xwork2.ActionSupport;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.util.ResultConstants;
import mx.ipn.escom.spee.util.mapeo.AjaxResult;

@Namespace("/notificaciones")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-notificaciones" }),
		@Result(name = "getNotificationsByUserId", type = "json", params = { "root", "action", "includeProperties",
				"ajaxResult.*" }) })
@AllowedMethods({ "getNotificationsByUserId" })
public class GestionarNotificacionesAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String GET_NOTIFICATIONS_BY_USER = "getNotificationsByUserId";

	private Usuario usuarioSel;

	private AjaxResult ajaxResult;

	private Integer idUser;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
				.getValor()) {
			return ResultConstants.ALUMNO;
		}
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ENCARGADO_CAJA
				.getValor()) {
			return ResultConstants.ENCARGADO_CAJA;
		}
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.CONTADOR
				.getValor()) {
			return ResultConstants.CONTADOR;
		}
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.EXTERNO
				.getValor()) {
			return ResultConstants.EXTERNO;
		}
		if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_DENTALES.getValor()) {
			return ResultConstants.ADMINISTRADOR_DENTALES;
		}
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR
				.getValor()) {
			return ResultConstants.SUBDIRECTOR;
		}
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.TRABAJADOR
				.getValor()) {
			return ResultConstants.TRABAJADOR;
		} else {
			return NO_AUTORIZADO;
		}
	}

	public void validateCreate() {

	}

	public String create() {
		addActionMessage("... exitosamente");
		return SUCCESS;
	}

	@SkipValidation
	public String getNotificationsByUserId() {
		getUsuarioSel();
		if (usuarioSel != null) {
			if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
					.getValor()) {
				getAjaxResult();
				// ajaxResult = notificacionesBs.obtenerPagosUsuario(idUser);
				SessionManager.put(NombreObjetosSesion.AJAX_RESULT, ajaxResult);
				return GET_NOTIFICATIONS_BY_USER;
			} else {
				return NO_AUTORIZADO;
			}
		} else {
			return NO_AUTORIZADO;
		}
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

	public AjaxResult getAjaxResult() {
		this.ajaxResult = (AjaxResult) SessionManager.get(NombreObjetosSesion.AJAX_RESULT);
		if (ajaxResult == null) {
			ajaxResult = new AjaxResult();
			SessionManager.put(NombreObjetosSesion.AJAX_RESULT, ajaxResult);
		}
		return ajaxResult;
	}

	public void setAjaxResult(AjaxResult ajaxResult) {
		this.ajaxResult = ajaxResult;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

}
