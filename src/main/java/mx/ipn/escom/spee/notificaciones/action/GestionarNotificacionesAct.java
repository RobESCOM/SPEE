package mx.ipn.escom.spee.notificaciones.action;

import org.apache.struts2.convention.annotation.Namespace;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.util.ResultConstants;

@Namespace("/notificaciones")
public class GestionarNotificacionesAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	private Usuario usuarioSel;

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
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_DENTALES
				.getValor()) {
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
