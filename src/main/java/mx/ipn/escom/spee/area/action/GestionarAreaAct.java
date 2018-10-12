package mx.ipn.escom.spee.area.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;

@Namespace("/area")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName", "gestionar-area" }) })
public class GestionarAreaAct extends GeneralActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6795647250867383176L;

	private Usuario usuarioSel;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR
				.getValor()) {
			return INDEX;
		} else {
			return NO_AUTORIZADO;
		}
	}

	public String editNew() {
		return EDITNEW;
	}

	public String edit() {
		return EDIT;
	}

	public String show() {
		return SHOW;
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
