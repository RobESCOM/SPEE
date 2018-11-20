package mx.ipn.escom.spee.area.action;

import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import mx.edu.spee.controlacceso.exception.UserActiveException;
import mx.edu.spee.controlacceso.exception.UserInactiveException;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.area.bs.AreaBs;
import mx.ipn.escom.spee.area.mapeo.CatalogoArea;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/area")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName", "gestionar-area" }) })
@AllowedMethods({ "bajaArea", "reactivarArea" })
public class GestionarAreaAct extends GeneralActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6795647250867383176L;

	private Usuario usuarioSel;

	private Integer idSel;

	private Integer idResponsableDefault;

	private String nombreResponsable;

	private List<CatalogoArea> listAreas;

	private List<InformacionPersonal> listResponsables;

	private CatalogoArea model;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private AreaBs areaBs;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR
				.getValor()) {
			listAreas = genericSearchBs.findAll(CatalogoArea.class);
			if(!listAreas.isEmpty()){
				return INDEX;
			}
			else {
				addActionError("No existen áreas registradas.");
				return INDEX;
			}			
		} else {
			return NO_AUTORIZADO;
		}
	}

	public String edit() {
		getIdSel();
		model = genericSearchBs.findById(CatalogoArea.class, idSel);
		if(areaBs.validaResponsableActivo(model.getIdResponsable())) {
			addActionError("El responsable del área se encuentra inactivo. Asigna un nuevo responsable.");
		}
		listResponsables = areaBs.obtenerInfoResponsablesDefault();
		InformacionPersonal infoAux = new InformacionPersonal();
		for (InformacionPersonal info : listResponsables) {
			if (info.getIdCuenta() == model.getIdResponsable()) {
				infoAux = info;
			}
		}
		listResponsables.remove(infoAux);
		nombreResponsable = areaBs.obtenerNombreResponsable(model.getIdResponsable());
		return EDIT;
	}

	public void validateUpdate() {
		try {
			if (getFieldErrors().isEmpty() && getActionErrors().isEmpty()) {
				areaBs.editarArea(model, idSel);
			} else {
				addActionError("Verifique su información.");
				listResponsables = areaBs.obtenerInfoResponsablesDefault();
			}
		} catch (UserActiveException ua) {
			addActionError(
					"El responsable del área actual sigue activo. Dirígete a \"Gestionar responsables de área\" para darlo de baja.");
			listResponsables = areaBs.obtenerInfoResponsablesDefault();
		}
		catch (UserInactiveException ui) {
			addActionError("El responsable del área se encuentra inactivo. Asigna un nuevo responsable.");
			listResponsables = areaBs.obtenerInfoResponsablesDefault();
		}
	}

	public String update() {
		addActionMessage("Se actualizó la información del área correctamente.");
		return SUCCESS;
	}

	@SkipValidation
	public String bajaArea() {
		getIdSel();
		if (areaBs.darBajaArea(idSel)) {
			addActionMessage("El área se dio de baja exitosamente.");
			return SUCCESS;
		} else {
			return NO_AUTORIZADO;
		}
	}
	
	@SkipValidation
	public String reactivarArea() {
		getIdSel();
		if(areaBs.reactivarArea(idSel)) {
			addActionMessage("El área se reactivó exitosamente.");
			return SUCCESS;
		}
		else {
			return NO_AUTORIZADO;
		}
	}

	public String show() {
		getIdSel();
		model = genericSearchBs.findById(CatalogoArea.class, idSel);
		InformacionPersonal info = new InformacionPersonal();
		info.setIdCuenta(model.getIdResponsable());
		info = genericSearchBs.findByExample(info).get(Numeros.CERO.getValor());
		nombreResponsable = info.getNombre() + " " + info.getPrimerApellido() + " " + info.getSegundoApellido();
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

	public List<CatalogoArea> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<CatalogoArea> listAreas) {
		this.listAreas = listAreas;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	@VisitorFieldValidator
	public CatalogoArea getModel() {
		return model;
	}

	public void setModel(CatalogoArea model) {
		this.model = model;
	}

	public List<InformacionPersonal> getListResponsables() {
		return listResponsables;
	}

	public void setListResponsables(List<InformacionPersonal> listResponsables) {
		this.listResponsables = listResponsables;
	}

	public AreaBs getAreaBs() {
		return areaBs;
	}

	public void setAreaBs(AreaBs areaBs) {
		this.areaBs = areaBs;
	}

	public Integer getIdSel() {
		if (SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID) != null)
			idSel = (Integer) SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID);
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		if (idSel != null) {
			SessionManager.put(NombreObjetosSesion.SESSION_MODEL_ID, idSel);
			model = genericSearchBs.findById(CatalogoArea.class, idSel);
		}

		this.idSel = idSel;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}

	public Integer getIdResponsableDefault() {
		return idResponsableDefault;
	}

	public void setIdResponsableDefault(Integer idResponsableDefault) {
		this.idResponsableDefault = idResponsableDefault;
	}
}
