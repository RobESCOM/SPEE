package mx.ipn.escom.spee.citas.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import mx.ipn.escom.spee.action.GeneralActionSupport;

@Namespace("/citas")
@Results({
	@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName", "gestionar-citas-dentales/new" }) })
public class GestionarCitasDentalesAct extends GeneralActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String index() {
		return INDEX;
	}
	
	public String editNew() {
		return EDITNEW;
	}
	
	public void validateCreate() {
		
	}
	
	public String create() {
		addActionMessage("Se ha agendado su cita exitosamente");
		return SUCCESS;
	}
}
