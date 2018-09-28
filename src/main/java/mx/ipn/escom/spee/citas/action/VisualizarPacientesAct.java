package mx.ipn.escom.spee.citas.action;

import org.apache.struts2.convention.annotation.Namespace;

import mx.ipn.escom.spee.action.GeneralActionSupport;

@Namespace("/citas")
public class VisualizarPacientesAct extends GeneralActionSupport{
	
private static final long serialVersionUID = 1L;
	
	public String index() {
		return INDEX;
	}
}
