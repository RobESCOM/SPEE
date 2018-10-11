package mx.ipn.escom.spee.pagos.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionSupport;

import mx.ipn.escom.spee.action.GeneralActionSupport;

@Namespace("/pagos")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
		"gestionar-actividad-cajero" }) })
public class GestionarActividadCajeroAct extends GeneralActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7436648542686964907L;

	public String index() {
		return INDEX;
	}
}
