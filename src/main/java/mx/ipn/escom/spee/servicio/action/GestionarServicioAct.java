package mx.ipn.escom.spee.servicio.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

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
public class GestionarServicioAct extends GeneralActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222787025037672268L;
	
	private CatalogoServicio model;
	
	private List<CatalogoServicio> listServicios;
	
	private List<TipoServicio> listTipoServicios;
	
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
		listTipoServicios = genericSearchBs.findAll(TipoServicio.class);
		return EDITNEW;
	}
	
	public void validateCreate() {
		try {
			servicioBs.registrarServicio(model);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public String create() {
		addActionMessage("EL servicio se registró exitosamente.");;
		return SUCCESS;
	}
	
	public String edit() {
		return EDIT;
	}
	
	public String show() {
		return SHOW;
	}

	public List<CatalogoServicio> getListServicios() {
		return listServicios;
	}

	public void setListServicios(List<CatalogoServicio> listServicios) {
		this.listServicios = listServicios;
	}

	public Integer getIdSel() {
		if(SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID) != null) {
			idSel = (Integer) SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID);
		}
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		if(idSel != null){
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

	public CatalogoServicio getModel() {
		if(model == null) {
			model = new CatalogoServicio();
		}
		return model;
	}

	public void setModel(CatalogoServicio model) {
		this.model = model;
	}

	public List<TipoServicio> getListTipoServicios() {
		return listTipoServicios;
	}

	public void setListTipoServicios(List<TipoServicio> listTipoServicios) {
		this.listTipoServicios = listTipoServicios;
	}

	public List<CatalogoArea> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<CatalogoArea> listAreas) {
		this.listAreas = listAreas;
	}
}
