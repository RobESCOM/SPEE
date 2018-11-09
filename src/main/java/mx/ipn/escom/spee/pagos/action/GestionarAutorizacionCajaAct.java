package mx.ipn.escom.spee.pagos.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;

@Namespace("/pagos")
@Results({
		@Result(name = GestionarAutorizacionPagosAct.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-caja" }),
		@Result(name = GestionarAutorizacionPagosAct.ERROR, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-caja" }) })
public class GestionarAutorizacionCajaAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	private Usuario usuarioSel;

	public String editNew() {
		getUsuarioSel();
		if (usuarioSel != null) {
			if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ENCARGADO_CAJA
					.getValor()) {
				return EDITNEW;
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

}
