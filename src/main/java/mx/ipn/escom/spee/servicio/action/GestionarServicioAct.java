package mx.ipn.escom.spee.servicio.action;

import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import mx.edu.spee.controlacceso.exception.UniqueException;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.area.bs.AreaBs;
import mx.ipn.escom.spee.area.mapeo.CatalogoArea;
import mx.ipn.escom.spee.servicio.bs.ServicioBs;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.servicio.mapeo.TipoServicio;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/servicio")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
		"gestionar-servicio" }) })
@AllowedMethods({ "bajaServicio", "reactivarServicio" })
public class GestionarServicioAct extends GeneralActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7222787025037672268L;

	private CatalogoServicio model;

	private List<CatalogoServicio> listServicios;

	private List<TipoServicio> listTipos;

	private List<CatalogoArea> listAreas;

	private Integer idSel;

	@Autowired
	private AreaBs areaBs;

	@Autowired
	private ServicioBs servicioBs;

	@Autowired
	private GenericSearchBs genericSearchBs;

	public String index() {
		listServicios = genericSearchBs.findAll(CatalogoServicio.class);
		return INDEX;
	}

	public String editNew() {
		listAreas = areaBs.obtieneAreasActivas();
		listTipos = genericSearchBs.findAll(TipoServicio.class);
		return EDITNEW;
	}

	public void validateCreate() {
		try {
			if (getFieldErrors().isEmpty() && getActionErrors().isEmpty()) {
				servicioBs.registrarServicio(model);
			} else {
				addActionError("Verifique su información.");
			}
		} catch (UniqueException ue) {
			addActionError("El servicio que intenta registrar ya existe. Verifique la clave del servicio.");
		}
	}

	public String create() {
		addActionMessage("El servicio se registró exitosamente.");
		return SUCCESS;
	}

	public String edit() {
		getIdSel();
		model = genericSearchBs.findById(CatalogoServicio.class, idSel);
		listAreas = areaBs.obtieneAreasActivas();
		CatalogoArea areaAux = new CatalogoArea();
		TipoServicio tipoAux = new TipoServicio();
		for (CatalogoArea area : listAreas) {
			if (model.getIdArea() == area.getId()) {
				areaAux = area;
			}
		}
		listTipos = genericSearchBs.findAll(TipoServicio.class);
		for (TipoServicio tipo : listTipos) {
			if (model.getIdTipoServicio() == tipo.getId()) {
				tipoAux = tipo;
			}
		}
		listAreas.remove(areaAux);
		listTipos.remove(tipoAux);

		return EDIT;
	}

	public void validateUpdate() throws UniqueException {
		try {
			if (getActionErrors().isEmpty() && getFieldErrors().isEmpty()) {
				model.setId(idSel);
				servicioBs.editarServicio(model);
			} else {
				addActionError("Verifique su información.");
				listAreas = areaBs.obtieneAreasActivas();
			}
		} catch (UniqueException ue) {
			addActionError("El servicio que intenta registrar ya existe. Verifique la clave del servicio.");
		}
	}

	public String update() {
		addActionMessage("La información del servicio se actualizó correctamente.");
		return SUCCESS;
	}

	public String show() {
		getIdSel();
		model = genericSearchBs.findById(CatalogoServicio.class, idSel);
		return SHOW;
	}

	@SkipValidation
	public String bajaServicio() {
		if (servicioBs.darBajaServicio(idSel)) {
			addActionMessage("El servicio se dio de baja correctamente.");
			return SUCCESS;
		} else {
			return NO_AUTORIZADO;
		}
	}

	@SkipValidation
	public String reactivarServicio() {
		if (servicioBs.reactivarServicio(idSel)) {
			addActionMessage("El servicio se reactivó exitosamente.");
			return SUCCESS;
		} else {
			return NO_AUTORIZADO;
		}
	}

	public List<CatalogoServicio> getListServicios() {
		return listServicios;
	}

	public void setListServicios(List<CatalogoServicio> listServicios) {
		this.listServicios = listServicios;
	}

	public Integer getIdSel() {
		if (SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID) != null) {
			idSel = (Integer) SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID);
		}
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		if (idSel != null) {
			SessionManager.put(NombreObjetosSesion.SESSION_MODEL_ID, idSel);
		}
		this.idSel = idSel;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	@VisitorFieldValidator
	public CatalogoServicio getModel() {
		if (model == null) {
			model = new CatalogoServicio();
		}
		return model;
	}

	public void setModel(CatalogoServicio model) {
		this.model = model;
	}

	public List<CatalogoArea> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<CatalogoArea> listAreas) {
		this.listAreas = listAreas;
	}

	public AreaBs getAreaBs() {
		return areaBs;
	}

	public void setAreaBs(AreaBs areaBs) {
		this.areaBs = areaBs;
	}

	public ServicioBs getServicioBs() {
		return servicioBs;
	}

	public void setServicioBs(ServicioBs servicioBs) {
		this.servicioBs = servicioBs;
	}

	public List<TipoServicio> getListTipos() {
		return listTipos;
	}

	public void setListTipos(List<TipoServicio> listTipos) {
		this.listTipos = listTipos;
	}
}
