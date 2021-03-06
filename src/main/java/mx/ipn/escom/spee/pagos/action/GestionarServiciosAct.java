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

import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.pagos.bs.GestionarServiciosBs;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.ResultConstants;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.mapeo.AjaxResult;

@Namespace("/pagos")
@Results({
		@Result(name = GestionarServiciosAct.GET_ALL_SERVICES, type = "json", params = { "root", "action",
				"includeProperties", "ajaxResult.*" }),
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-servicios" }) })
@AllowedMethods({ "getAllServices" })
public class GestionarServiciosAct extends GeneralActionSupport {

	public static final long serialVersionUID = 1L;

	public static final String GET_ALL_SERVICES = "getAllServices";

	@Autowired
	private GestionarServiciosBs gestionarServiciosBs;

	@Autowired
	private GenericSearchBs genericSearchBs;

	private InformacionPersonal infoUsuario;

	private Usuario usuarioSel;

	private List<CatalogoServicio> listCatalogoServicios;

	private List<CatalogoServicio> result;

	private AjaxResult ajaxResult;

	private Integer id;

	public String index() {
		getUsuarioSel();
		if (usuarioSel != null) {
			if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
					.getValor() || usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.EXTERNO
					.getValor() || usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.TRABAJADOR
					.getValor() ) {
				infoUsuario = gestionarServiciosBs.obtenerInformacionPersonal(usuarioSel);
				listCatalogoServicios = genericSearchBs.findAll(CatalogoServicio.class);
				return ResultConstants.ALUMNO;
			} else {
				return NO_AUTORIZADO;
			}
		} else {
			return NO_AUTORIZADO;
		}
	}

	public void validateCreate() {

	}

	public String create() {
		addActionMessage("Pago se envió exitosamente");
		return SUCCESS;
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

	@SkipValidation
	public String getAllServices() {
		getAjaxResult();
		result = genericSearchBs.findAll(CatalogoServicio.class);
		SessionManager.put(NombreObjetosSesion.AJAX_RESULT, result);
		return GET_ALL_SERVICES;
	}

	public AjaxResult obtenerServicios() {
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.addCampo(genericSearchBs.findAll(CatalogoServicio.class));
		return ajaxResult;
	}

	public GestionarServiciosBs getGestionarServiciosBs() {
		return gestionarServiciosBs;
	}

	public void setGestionarServiciosBs(GestionarServiciosBs gestionarServiciosBs) {
		this.gestionarServiciosBs = gestionarServiciosBs;
	}

	public List<CatalogoServicio> getListCatalogoServicios() {
		return listCatalogoServicios;
	}

	public void setListCatalogoServicios(List<CatalogoServicio> listCatalogoServicios) {
		this.listCatalogoServicios = listCatalogoServicios;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public List<CatalogoServicio> getAjaxResult() {
		this.result = (List<CatalogoServicio>) SessionManager.get(NombreObjetosSesion.AJAX_RESULT);
		if (result == null) {
			result = new ArrayList<CatalogoServicio>();
			SessionManager.put(NombreObjetosSesion.AJAX_RESULT, result);
		}
		return result;
	}

	public void setAjaxResult(List<CatalogoServicio> ajaxResult) {
		this.result = ajaxResult;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
