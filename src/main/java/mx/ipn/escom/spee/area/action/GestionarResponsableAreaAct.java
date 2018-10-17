package mx.ipn.escom.spee.area.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.area.bs.AreaBs;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/area")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
		"gestionar-respnsable-area" }) })
public class GestionarResponsableAreaAct extends GeneralActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3853253919957279842L;

	private Usuario usuarioSel;
	
	private String nbArea;
	
	@Autowired
	private GenericSearchBs genericSearchBs;
	
	@Autowired
	private AreaBs areaBs;
	
	private List<InformacionPersonal> listResponsablesArea;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR
				.getValor()) {
			listResponsablesArea = areaBs.obtenerInfoResponsables();
			for (InformacionPersonal info : listResponsablesArea) {
				nbArea = areaBs.obtieneAreaResponsable(info.getIdCuenta());
			}
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

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public AreaBs getAreaBs() {
		return areaBs;
	}

	public void setAreaBs(AreaBs areaBs) {
		this.areaBs = areaBs;
	}

	public List<InformacionPersonal> getListResponsablesArea() {
		return listResponsablesArea;
	}

	public void setListResponsablesArea(List<InformacionPersonal> listResponsablesArea) {
		this.listResponsablesArea = listResponsablesArea;
	}

	public String getNbArea() {
		return nbArea;
	}

	public void setNbArea(String nbArea) {
		this.nbArea = nbArea;
	}
	
	
}
