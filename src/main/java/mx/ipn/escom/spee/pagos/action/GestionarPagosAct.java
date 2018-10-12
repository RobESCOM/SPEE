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
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.pagos.bs.GestionarServiciosBs;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.CatalogoArea.CatalogoAreaEnum;
import mx.ipn.escom.spee.pagos.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.util.ResultConstants;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.mapeo.AjaxResult;
import net.sf.jasperreports.engine.JRException;

@Namespace("/pagos")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-pagos/new" }),
		@Result(name = "generarReporte", type = "redirectAction", params = { "actionName", "gestionar-pagos/new" }),
		@Result(name = "getPaymentsByUserId", type = "json", params = { "root", "action", "includeProperties",
				"ajaxResult.*" }),
		@Result(name = "filesuccess", type = "stream", params = { "contentType", "application/pdf", "inputName",
				"archivoVisualizar.fileInputStream", "contentDisposition",
				"inline;filename=\"${archivoVisualizar.fileUploadFileName}\"", "bufferSize", "1024" }) })
@AllowedMethods({ "imprimirReporte", "vizualizarArchivo", "getPaymentsByUserId" })
public class GestionarPagosAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String GENERAR_REPORTE = "generarReporte";

	public static final String GET_PAYMENT_BY_USER = "getPaymentsByUserId";

	@Autowired
	private PagoBs pagoBs;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GestionarServiciosBs gestionarServiciosBs;

	private InformacionPersonal infoUsuario;

	private List<ArchivoPagoDia> listPagos;

	private Archivo archivoVisualizar;

	private Usuario usuarioSel;

	private AjaxResult ajaxResult;

	private Integer idUser;

	public String index() {
		getUsuarioSel();
		if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_CELEX.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			CatalogoServicio catalogo = new CatalogoServicio();
			catalogo.setIdArea(CatalogoAreaEnum.CELEX.getIdEstatus());
			archivoPago.setCatalogoServicio(catalogo);
			listPagos = genericSearchBs.findByExample(archivoPago);
			infoUsuario = gestionarServiciosBs.obtenerInformacionPersonal(usuarioSel);
			return ResultConstants.ADMINISTRADOR_CELEX;
		} else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdUsuario(usuarioSel.getId());
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

	public HttpHeaders imprimirReporte() {
		List<ArchivoPagoDia> listPagosAutorizados = new ArrayList<>();
		Archivo archivo = new Archivo();
		try {
			archivo = pagoBs.generarReporteCelex(listPagosAutorizados);
		} catch (FileNotFoundException | JRException e) {
			addActionError(getText("Archivo no se pudo crear"));
		}

		return vizualizarArchivo(archivo);
	}

	@SkipValidation
	public HttpHeaders vizualizarArchivo(Archivo archivo) {
		archivoVisualizar = new Archivo();
		setArchivoVisualizar(archivo);
		return new DefaultHttpHeaders("filesuccess").disableCaching();
	}

	@SkipValidation
	public String getPaymentsByUserId() {
		getUsuarioSel();
		if (usuarioSel != null) {
			if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
					.getValor()) {
				getAjaxResult();
				ajaxResult = pagoBs.obtenerPagosUsuario(idUser);
				SessionManager.put(NombreObjetosSesion.AJAX_RESULT, ajaxResult);
				return GET_PAYMENT_BY_USER;
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

	public Archivo getArchivoVisualizar() {
		return archivoVisualizar;
	}

	public void setArchivoVisualizar(Archivo archivoVisualizar) {
		this.archivoVisualizar = archivoVisualizar;
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

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

}
