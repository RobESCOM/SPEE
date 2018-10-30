package mx.ipn.escom.spee.pagos.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;

import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;

@Namespace("/pagos")
public class GestionarPagosAutorizadosAct extends GeneralActionSupport {

	private List<ArchivoPagoDia> listPagosAutorizados;
	
	private PagoBs pagoBs;
	
	private static final long serialVersionUID = 1L;

	public String editNew() {
		listPagosAutorizados = pagoBs.obtenerPagosPorAutorizar();
		return EDITNEW;
	}
	
	

	public PagoBs getPagoBs() {
		return pagoBs;
	}



	public void setPagoBs(PagoBs pagoBs) {
		this.pagoBs = pagoBs;
	}



	public List<ArchivoPagoDia> getListPagosAutorizados() {
		return listPagosAutorizados;
	}

	public void setListPagosAutorizados(List<ArchivoPagoDia> listPagosAutorizados) {
		this.listPagosAutorizados = listPagosAutorizados;
	}

}
