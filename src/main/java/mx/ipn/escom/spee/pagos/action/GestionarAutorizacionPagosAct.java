package mx.ipn.escom.spee.pagos.action;

import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/pagos")
@AllowedMethods({ "autorizarPago", "rechazarPago" })
@Results({
		@Result(name = GestionarAutorizacionPagosAct.ERROR, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }),
		@Result(name = GestionarAutorizacionPagosAct.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }) })
public class GestionarAutorizacionPagosAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private PagoBs pagoBs;

	private Integer idSel;

	private ArchivoPagoDia model;

	private List<ArchivoPagoDia> listArchivoPagosRevision;

	public String index() {
		listArchivoPagosRevision = pagoBs.obtenerPagosPorAutorizar();
		return INDEX;
	}

	public String show() {
		return SHOW;
	}

	public String editNew() {
		return EDITNEW;
	}

	public String create() {
		addActionMessage("Se adjunto el comprobante exitosamente");
		return SUCCESS;
	}
	
	public String edit() {
		return EDIT;
	}

	public String autorizarPago() {
		getIdSel();
		pagoBs.autorizarPago(idSel);
		addActionMessage(getText("Se ha autorizado el pago"));
		return SUCCESS;
	}

	public String rechazarPago() {
		getIdSel();
		pagoBs.rechazarPago(idSel);
		addActionMessage("Se ha rechazado el pago");
		return SUCCESS;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public List<ArchivoPagoDia> getListArchivoPagosRevision() {
		return listArchivoPagosRevision;
	}

	public void setListArchivoPagosRevision(List<ArchivoPagoDia> listArchivoPagosRevision) {
		this.listArchivoPagosRevision = listArchivoPagosRevision;
	}

	public PagoBs getPagoBs() {
		return pagoBs;
	}

	public void setPagoBs(PagoBs pagoBs) {
		this.pagoBs = pagoBs;
	}

	public Integer getIdSel() {
		return idSel;
	}

	public void setIdSel(Integer idSel) {
		if (idSel != null) {
			model = genericSearchBs.findById(ArchivoPagoDia.class, idSel);
		}
		this.idSel = idSel;
	}

	public ArchivoPagoDia getModel() {
		return model;
	}

	public void setModel(ArchivoPagoDia model) {
		this.model = model;
	}

}
