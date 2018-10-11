package mx.ipn.escom.spee.area.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import mx.ipn.escom.spee.action.GeneralActionSupport;

@Namespace("/area")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName", "gestionar-area" }) })
public class GestionarAreaAct extends GeneralActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6795647250867383176L;

	public String index() {
		return INDEX;
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
}
