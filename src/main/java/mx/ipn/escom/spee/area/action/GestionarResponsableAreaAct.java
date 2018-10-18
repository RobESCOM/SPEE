package mx.ipn.escom.spee.area.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import mx.edu.spee.controlacceso.exception.UniqueException;
import mx.edu.spee.controlacceso.mapeo.Cuenta;
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
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
		"gestionar-responsable-area" }) })
public class GestionarResponsableAreaAct extends GeneralActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3853253919957279842L;

	private Usuario usuarioSel;

	private InformacionPersonal model;

	private String nbCelex;
	private String nbDentalM;
	private String nbDentalV;
	private String nbBiblio;
	private String nbCopia;
	private String nombreArea;

	private Integer idSel;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private AreaBs areaBs;

	private List<InformacionPersonal> listResponsablesArea;

	private List<CatalogoArea> listAreas;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR
				.getValor()) {
			String nbArea = "";
			listResponsablesArea = areaBs.obtenerInfoResponsables();
			for (InformacionPersonal info : listResponsablesArea) {
				nbArea = areaBs.obtieneAreaResponsable(info.getIdCuenta());
				switch (nbArea) {
				case "CELEX":
					nbCelex = nbArea;
					break;
				case "Biblioteca":
					nbBiblio = nbArea;
					break;
				case "Odontología Matutino":
					nbDentalM = nbArea;
					break;
				case "Odontología Vespertino":
					nbDentalV = nbArea;
					break;
				case "Fotocopiado":
					nbCopia = nbArea;
					break;
				default:
					break;
				}
			}
			return INDEX;
		} else {
			return NO_AUTORIZADO;
		}
	}

	public String editNew() {
		return EDITNEW;
	}

	public void validateCreate() {
		try {
			if (getFieldErrors().isEmpty() && getActionErrors().isEmpty()) {
				areaBs.registrarResponsable(model);
			} else {
				addActionError("Verifique su información.");
			}
		} catch (UniqueException e) {
			addActionError("El usuario ya existe.");
		}
	}

	public String create() {
		addActionMessage(
				"El responsable de área se ha registrado correctamente. Dirígete a la sección \"Gestionar áreas\" para asignarle un área.");
		return SUCCESS;
	}

	public String edit() {
		getIdSel();
		System.err.println("idSel -> " + idSel);
		InformacionPersonal infoAux = new InformacionPersonal();
		infoAux.setIdCuenta(idSel);
		model = genericSearchBs.findByExample(infoAux).get(Numeros.CERO.getValor());

		Cuenta cuenta = new Cuenta();
		CatalogoArea area = new CatalogoArea();
		cuenta = genericSearchBs.findById(Cuenta.class, model.getIdCuenta());
		area.setIdResponsable(cuenta.getIdCuenta());
		area = genericSearchBs.findByExample(area).get(Numeros.CERO.getValor());
		usuarioSel = genericSearchBs.findById(Usuario.class, cuenta.getIdUsuario());
		nombreArea = area.getNombreArea();
		return EDIT;
	}

	public void validateUpdate() {
		try {
			if (getFieldErrors().isEmpty() && getActionErrors().isEmpty()) {
				areaBs.editarResponsable(model);
			} else {
				addActionError("Verifique su información.");
			}
		} catch (UniqueException e) {
			addActionError("El usuario ya existe.");
		}
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

	public String getNbCelex() {
		return nbCelex;
	}

	public void setNbCelex(String nbCelex) {
		this.nbCelex = nbCelex;
	}

	public String getNbDentalM() {
		return nbDentalM;
	}

	public void setNbDentalM(String nbDentalM) {
		this.nbDentalM = nbDentalM;
	}

	public String getNbDentalV() {
		return nbDentalV;
	}

	public void setNbDentalV(String nbDentalV) {
		this.nbDentalV = nbDentalV;
	}

	public String getNbBiblio() {
		return nbBiblio;
	}

	public void setNbBiblio(String nbBiblio) {
		this.nbBiblio = nbBiblio;
	}

	public String getNbCopia() {
		return nbCopia;
	}

	public void setNbCopia(String nbCopia) {
		this.nbCopia = nbCopia;
	}

	@VisitorFieldValidator
	public InformacionPersonal getModel() {
		if (model == null) {
			model = new InformacionPersonal();
		}
		return model;
	}

	public void setModel(InformacionPersonal model) {
		this.model = model;
	}

	public List<CatalogoArea> getListAreas() {
		return listAreas;
	}

	public void setListAreas(List<CatalogoArea> listAreas) {
		this.listAreas = listAreas;
	}

	public Integer getIdSel() {
		if (SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID) != null) {
			idSel = (Integer) SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID);
		}
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		SessionManager.put(NombreObjetosSesion.SESSION_MODEL_ID, idSel);
		this.idSel = idSel;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}
}
