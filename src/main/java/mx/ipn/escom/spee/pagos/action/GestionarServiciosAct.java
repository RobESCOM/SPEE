package mx.ipn.escom.spee.pagos.action;

import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.pagos.bs.GestionarServiciosBs;
import mx.ipn.escom.spee.pagos.mapeo.Servicio;
import mx.ipn.escom.spee.pagos.mapeo.ServicioArea;
import mx.ipn.escom.spee.util.ResultConstants;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.mapeo.AjaxResult;

@Namespace("/pagos")
@Results({
		@Result(name = "jsonTest", type = "json", params = { "root", "action", "includeProperties", "ajaxResult.*" }) })
@AllowedMethods({ "jsonTest" })
public class GestionarServiciosAct extends GeneralActionSupport {

	public static final long serialVersionUID = 1L;

	public static final String JSON_TEST = "jsonTest";

	@Autowired
	private GestionarServiciosBs gestionarServiciosBs;

	@Autowired
	private GenericSearchBs genericSearchBs;
	
	private InformacionPersonal infoUsuario;

	private Usuario usuarioSel;

	private List<Servicio> listCatalogoServicios;

	private AjaxResult ajaxResult;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
				.getValor()) {
			infoUsuario = gestionarServiciosBs.obtenerInformacionPersonal(usuarioSel);
			listCatalogoServicios = genericSearchBs.findAll(Servicio.class);
			return ResultConstants.ALUMNO;
		} else {
			return NO_AUTORIZADO;
		}
	}

	@SkipValidation
	public String jsonTest() {
		getAjaxResult();
		ajaxResult = obtenerArchivos();
		SessionManager.put(NombreObjetosSesion.AJAX_RESULT, ajaxResult);
		return JSON_TEST;
	}

	public AjaxResult obtenerArchivos() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.addCampo("pagos", genericSearchBs.findAll(ServicioArea.class));
		return ajaxResult;
	}

	public GestionarServiciosBs getGestionarServiciosBs() {
		return gestionarServiciosBs;
	}

	public void setGestionarServiciosBs(GestionarServiciosBs gestionarServiciosBs) {
		this.gestionarServiciosBs = gestionarServiciosBs;
	}

	public List<Servicio> getListCatalogoServicios() {
		return listCatalogoServicios;
	}

	public void setListCatalogoServicios(List<Servicio> listCatalogoServicios) {
		this.listCatalogoServicios = listCatalogoServicios;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
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

	public Usuario getUsuarioSel() {
		if (SessionManager.get(NombreObjetosSesion.USUARIO_SESION) != null) {
			usuarioSel = (Usuario) SessionManager.get(NombreObjetosSesion.USUARIO_SESION);
		}
		return usuarioSel;
	}

	public void setUsuarioSel(Usuario usuarioSel) {
		this.usuarioSel = usuarioSel;
	}

	public InformacionPersonal getInfoUsuario() {
		return infoUsuario;
	}

	public void setInfoUsuario(InformacionPersonal infoUsuario) {
		this.infoUsuario = infoUsuario;
	}

}
