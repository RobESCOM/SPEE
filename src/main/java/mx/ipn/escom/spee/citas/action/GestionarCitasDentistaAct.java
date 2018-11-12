package mx.ipn.escom.spee.citas.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.citas.bs.CitaBs;
import mx.ipn.escom.spee.citas.mapeo.Cita;
import mx.ipn.escom.spee.citas.mapeo.HoraServicio;
import mx.ipn.escom.spee.citas.mapeo.EstadoCita.EstadoCitaEnum;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/citas")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
		"gestionar-citas-dentista"}) })
@AllowedMethods({ "cancelarCita", "marcarInasistencia" })
public class GestionarCitasDentistaAct extends GeneralActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7368519586979382962L;

	private Usuario usuarioSel;

	private Cita model;

	private Integer idSel;

	private String txLogin;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private CitaBs citaBs;

	private List<Cita> listCitasMatutino;

	private List<Cita> listCitasVespertino;

	public String index() {
		getUsuarioSel();
		txLogin = usuarioSel.getLogin();
		List<Cita> listCitasAux = new ArrayList<>();
		listCitasMatutino = new ArrayList<>();
		listCitasVespertino = new ArrayList<>();
		HoraServicio hora = new HoraServicio();
		if (usuarioSel.getLogin().equals("administrador_dentales_matutino@ipn.com.mx")) {
			Cita citaAux = new Cita();
			citaAux.setIdEstado(EstadoCitaEnum.PENDIENTE.getId());
			listCitasAux = genericSearchBs.findByExample(citaAux);
			for (Cita cita : listCitasAux) {
				hora = genericSearchBs.findById(HoraServicio.class, cita.getIdHora());
				if (hora.getTurno().equals("M")) {
					listCitasMatutino.add(cita);
				}
			}
		} else {
			Cita citaAux = new Cita();
			citaAux.setIdEstado(EstadoCitaEnum.PENDIENTE.getId());			
			listCitasAux = genericSearchBs.findByExample(citaAux);
			for (Cita cita : listCitasAux) {
				hora = genericSearchBs.findById(HoraServicio.class, cita.getIdHora());
				if (hora.getTurno().equals("V")) {
					listCitasVespertino.add(cita);
				}
			}
		}
		return INDEX;
	}

	@SkipValidation
	public String cancelarCita() {
		getIdSel();
		citaBs.cancelarCitaDentista(idSel);
		addActionMessage("Se canceló la cita correctamente.");
		return SUCCESS;
	}
	
	@SkipValidation
	public String marcarInasistencia() {
		getIdSel();
		citaBs.marcarInasistencia(idSel);
		addActionMessage("Inasistencia marcada correctamente.");
		return SUCCESS;
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

	public String getTxLogin() {
		return txLogin;
	}

	public void setTxLogin(String txLogin) {
		this.txLogin = txLogin;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public List<Cita> getListCitasMatutino() {
		return listCitasMatutino;
	}

	public void setListCitasMatutino(List<Cita> listCitasMatutino) {
		this.listCitasMatutino = listCitasMatutino;
	}

	public List<Cita> getListCitasVespertino() {
		return listCitasVespertino;
	}

	public void setListCitasVespertino(List<Cita> listCitasVespertino) {
		this.listCitasVespertino = listCitasVespertino;
	}

}
