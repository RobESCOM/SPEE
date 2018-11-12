package mx.ipn.escom.spee.citas.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.VisitorFieldValidator;

import mx.edu.spee.controlacceso.exception.CitaOcupadaException;
import mx.edu.spee.controlacceso.exception.FechaInvalidaException;
import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.citas.bs.CitaBs;
import mx.ipn.escom.spee.citas.mapeo.Cita;
import mx.ipn.escom.spee.citas.mapeo.HoraServicio;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.mapeo.AjaxResult;

@Namespace("/citas")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-citas-dentales" }),
		@Result(name = "getAppointmentByUserId", type = "json", params = { "root", "action", "includeProperties",
				"ajaxResult.*" }) })
@AllowedMethods({ "getAppointmentByUserId", "cancelarCita" })
public class GestionarCitasDentalesAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String GET_NOTES_BY_USER = "getAppointmentByUserId";

	private Usuario usuarioSel;

	private Cita model;

	private AjaxResult ajaxResult;

	private Integer idSel;

	private String fecha;

	private List<Cita> listCitas;

	private List<HoraServicio> listHoras;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private CitaBs citaBs;

	public String index() {
		getUsuarioSel();
		Cuenta cuenta = new Cuenta();
		cuenta.setIdUsuario(usuarioSel.getId());
		cuenta = genericSearchBs.findByExample(cuenta).get(Numeros.CERO.getValor());
		if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_DENTALES.getValor()
				|| usuarioSel.getPerfilActivo().getId() == PerfilEnum.ALUMNO.getValor()) {
			listCitas = citaBs.obtenerCitasAlumno(cuenta.getIdCuenta());
			return INDEX;
		} else {
			return NO_AUTORIZADO;
		}
	}

	public String editNew() {
		getUsuarioSel();
		Cuenta cuenta = new Cuenta();
		cuenta.setIdUsuario(usuarioSel.getId());
		cuenta = genericSearchBs.findByExample(cuenta).get(Numeros.CERO.getValor());
		SessionManager.put(NombreObjetosSesion.CUENTA_ID, cuenta.getIdCuenta());
		listHoras = genericSearchBs.findAll(HoraServicio.class);
		return EDITNEW;
	}

	public void validateCreate() {
		try {
			if (getFieldErrors().isEmpty() && getActionErrors().isEmpty()) {
				Date fechaCita;
				fechaCita = citaBs.convertirFechaStringDate(fecha);
				model.setFecha(fechaCita);
				citaBs.registrarCita(model, (Integer) SessionManager.get(NombreObjetosSesion.CUENTA_ID));
			} else {
				addActionError("Verifique su información.");
				listHoras = genericSearchBs.findAll(HoraServicio.class);
			}
		} catch (CitaOcupadaException co) {
			addActionError("La fecha y hora de la cita ya se encuentran ocupadas. Elige una fecha u hora distinta.");
			listHoras = genericSearchBs.findAll(HoraServicio.class);
		} catch (FechaInvalidaException fi) {
			addActionError("Ingresa una fecha para la cita.");
			listHoras = genericSearchBs.findAll(HoraServicio.class);
		}
	}

	public String create() {
		addActionMessage("Se ha agendado su cita exitosamente.");
		return SUCCESS;
	}

	
	@SkipValidation
	public String cancelarCita() {
		getIdSel();
		citaBs.cancelarCita(idSel);
		addActionMessage("Se canceló su cita correctamente.");
		return SUCCESS;
	}
	
	@SkipValidation
	public String getAppointmentByUserId() {
		getUsuarioSel();
		if (usuarioSel != null) {
			if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
					.getValor()) {
				getAjaxResult();
				// ajaxResult = notasBs.obtenerCitasUsuario(idUser);
				SessionManager.put(NombreObjetosSesion.AJAX_RESULT, ajaxResult);
				return GET_NOTES_BY_USER;
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

	public Integer getIdSel() {
		if (SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID) != null)
			idSel = (Integer) SessionManager.get(NombreObjetosSesion.SESSION_MODEL_ID);
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		if (idSel != null) {
			SessionManager.put(NombreObjetosSesion.SESSION_MODEL_ID, idSel);
			model = genericSearchBs.findById(Cita.class, idSel);
		}
		this.idSel = idSel;
	}

	public List<Cita> getListCitas() {
		return listCitas;
	}

	public void setListCitas(List<Cita> listCitas) {
		this.listCitas = listCitas;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public CitaBs getCitaBs() {
		return citaBs;
	}

	public void setCitaBs(CitaBs citaBs) {
		this.citaBs = citaBs;
	}

	@VisitorFieldValidator
	public Cita getModel() {
		return model;
	}

	public void setModel(Cita model) {
		this.model = model;
	}

	public List<HoraServicio> getListHoras() {
		return listHoras;
	}

	public void setListHoras(List<HoraServicio> listHoras) {
		this.listHoras = listHoras;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
