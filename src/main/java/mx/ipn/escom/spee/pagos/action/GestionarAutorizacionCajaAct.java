package mx.ipn.escom.spee.pagos.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import mx.ipn.escom.spee.action.GeneralActionSupport;

@Namespace("/pagos")
@Results({
	@Result(name = GestionarAutorizacionPagosAct.SUCCESS, type = "redirectAction", params = { "actionName",
			"gestionar-autorizacion-caja" }),
	@Result(name = GestionarAutorizacionPagosAct.ERROR, type = "redirectAction", params = { "actionName",
			"gestionar-autorizacion-caja" }) })
public class GestionarAutorizacionCajaAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;
	
	public String editNew() {
		return EDITNEW;
	}

}
