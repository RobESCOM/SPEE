package mx.ipn.escom.spee.pagos.action;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.validation.SkipValidation;

import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.pagos.bs.GestionarServiciosBs;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.util.ResultConstants;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import net.sf.jasperreports.engine.JRException;

@Namespace("/pagos")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-pagos/new" }),
		@Result(name = "generarReporte", type = "redirectAction", params = { "actionName", "gestionar-pagos/new" }) })
@AllowedMethods({ "generarReporte" })
public class GestionarPagosAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String GENERAR_REPORTE = "generarReporte";

	@Autowired
	private PagoBs pagoBs;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GestionarServiciosBs gestionarServiciosBs;

	private InformacionPersonal infoUsuario;

	private List<ArchivoPagoDia> listPagos;

	private Archivo archivo;

	private Usuario usuarioSel;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_CELEX.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			// archivoPago.setIdArea(CatalogoAreaEnum.CELEX.getIdEstatus());
			listPagos = genericSearchBs.findByExample(archivoPago);
			// infoUsuario = gestionarServiciosBs.obtenerInformacionPersonal(usuarioSel);
			return ResultConstants.ADMINISTRADOR_CELEX;
		} else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdUsuario(usuarioSel.getId());
			archivoPago.setIdEstadoPago(EstadoPagoEnum.REVISION.getIdEstatus());
			listPagos = genericSearchBs.findByExample(archivoPago);
			return ResultConstants.ALUMNO;
		} else {
			return NO_AUTORIZADO;
		}
	}

	public String editNew() {
		return EDITNEW;
	}

	public void validateCreate() {

	}

	public String create() {
		addActionMessage("Pago se envió exitosamente");
		return SUCCESS;
	}

	@SkipValidation
	public String generarReporte() {
		List<ArchivoPagoDia> listPagosAutorizados = new ArrayList<>();
		try {
			archivo = pagoBs.generarReporteCelex(listPagosAutorizados);
		} catch (FileNotFoundException | JRException e) {
			addActionError(getText("Archivo no se pudo crear"));
		}
		return GENERAR_REPORTE;
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

	public PagoBs getPagoBs() {
		return pagoBs;
	}

	public void setPagoBs(PagoBs pagoBs) {
		this.pagoBs = pagoBs;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public List<ArchivoPagoDia> getListPagos() {
		return listPagos;
	}

	public void setListPagos(List<ArchivoPagoDia> listPagos) {
		this.listPagos = listPagos;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public GestionarServiciosBs getGestionarServiciosBs() {
		return gestionarServiciosBs;
	}

	public void setGestionarServiciosBs(GestionarServiciosBs gestionarServiciosBs) {
		this.gestionarServiciosBs = gestionarServiciosBs;
	}

	public InformacionPersonal getInfoUsuario() {
		return infoUsuario;
	}

	public void setInfoUsuario(InformacionPersonal infoUsuario) {
		this.infoUsuario = infoUsuario;
	}

}
