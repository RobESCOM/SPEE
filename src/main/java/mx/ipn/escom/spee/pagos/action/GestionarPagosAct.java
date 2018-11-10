package mx.ipn.escom.spee.pagos.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
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

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.area.mapeo.CatalogoArea.CatalogoAreaEnum;
import mx.ipn.escom.spee.pagos.bs.GestionarServiciosBs;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.exception.MailNoSendException;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.pagos.mapeo.PagoSiga;
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
				"jsonResult.*" }),
		@Result(name = "getImgService", type = "json", params = { "root", "action", "includeProperties",
				"ajaxResult.*" }),
		@Result(name = "filesuccess", type = "stream", params = { "contentType",
				"application/pdf,application/png,application/jpg", "inputName", "inputStream", "contentDisposition",
				"inline;filename=\"${inputStream.fileUploadFileName}\"", "bufferSize", "1024" }) })
@AllowedMethods({ "generarReporte", "visualizarArchivo", "getPaymentsByUserId", "getImgService" })
public class GestionarPagosAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	public static final String GENERAR_REPORTE = "generarReporte";

	public static final String GET_PAYMENT_BY_USER = "getPaymentsByUserId";

	public static final String GET_IMGB = "getImgService";

	@Autowired
	private PagoBs pagoBs;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GestionarServiciosBs gestionarServiciosBs;

	private InformacionPersonal infoUsuario;

	private List<ArchivoPagoDia> listPagos;

	private PagoSiga pagoSiga;

	private Archivo archivoVisualizar;

	private Usuario usuarioSel;

	private AjaxResult ajaxResult;

	private List<ArchivoPagoDia> jsonResult;

	private Integer idUser;

	private Integer idPago;

	private Integer idServicio;

	private Integer folio;

	private byte[] arch;

	private byte[] data;

	private File file;

	public InputStream inputStream;

	public String index() {
		getUsuarioSel();
		if (usuarioSel != null) {
			if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_CELEX.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				List<ArchivoPagoDia> pagoArea = new ArrayList<>();
				listPagos = new ArrayList<>();
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.CELEX.getIdEstatus()) {
						listPagos.add(pagado);
					}
				}
				return ResultConstants.ADMINISTRADOR_CELEX;
			} else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_DENTALES.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				List<ArchivoPagoDia> pagoArea = new ArrayList<>();
				listPagos = new ArrayList<>();
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.DENTALES.getIdEstatus()) {
						listPagos.add(pagado);
					}
				}
				return ResultConstants.ADMINISTRADOR_DENTALES;
			} else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_BIBLIOTECA.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				List<ArchivoPagoDia> pagoArea = new ArrayList<>();
				listPagos = new ArrayList<>();
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.BIBLIOTECA.getIdEstatus()) {
						listPagos.add(pagado);
					}
				}
				return ResultConstants.ADMINISTRADOR_BIBLIOTECA;
			} else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_FOTOCOPIADO
							.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				List<ArchivoPagoDia> pagoArea = new ArrayList<>();
				listPagos = new ArrayList<>();
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.FOTOCOPIADO.getIdEstatus()) {
						listPagos.add(pagado);
					}
				}
				return ResultConstants.ADMINISTRADOR_IMPRESIONES;
			} else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
					.getValor()
					|| usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.EXTERNO
							.getValor()
					|| usuarioSel.getPerfilActivo()
							.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.TRABAJADOR.getValor()) {
				Cuenta cuenta = new Cuenta();
				cuenta.setIdUsuario(usuarioSel.getId());
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdUsuario(genericSearchBs.findByExample(cuenta).get(0).getIdCuenta());
				listPagos = genericSearchBs.findByExample(archivoPago);
				return ResultConstants.ALUMNO;
			} else {
				return NO_AUTORIZADO;
			}
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

	public HttpHeaders generarReporte() {
		List<ArchivoPagoDia> listPagosAutorizados = new ArrayList<>();
		Archivo archivo = new Archivo();
		try {
			archivo = pagoBs.generarReporteCelex(listPagosAutorizados);
		} catch (FileNotFoundException | JRException e) {
			addActionError(getText("Archivo no se pudo crear"));
		}

		return visualizarArchivo(archivo);
	}

	public String show() {
		pagoSiga = pagoBs.siga(idUser, idPago);
		return SHOW;
	}

	@SkipValidation
	public HttpHeaders visualizarArchivo(Archivo archivo) {
		archivoVisualizar = new Archivo();
		return new DefaultHttpHeaders("filesuccess").disableCaching();
	}

	@SkipValidation
	public HttpHeaders visualizarArchivo() {
		try {
			FileOutputStream fileOuputStream = new FileOutputStream("filename.pdf");
			fileOuputStream.write(genericSearchBs.findById(ArchivoPagoDia.class, idPago).getArchivo());
			File file = new File("filename.pdf");
			inputStream = new DataInputStream(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new DefaultHttpHeaders("filesuccess").disableCaching();
	}

	@SkipValidation
	public String getPaymentsByUserId() {
		getJsonResult();
		ArchivoPagoDia archivo = new ArchivoPagoDia();
		archivo.setIdUsuario(idUser);
		jsonResult = genericSearchBs.findByExample(archivo);
		for (ArchivoPagoDia pagon : jsonResult) {
			pagon.setArchivo(null);
		}
		SessionManager.put(NombreObjetosSesion.AJAX_RESULT, jsonResult);
		return GET_PAYMENT_BY_USER;
	}

	public List<ArchivoPagoDia> getJsonResult() {
		this.jsonResult = (List<ArchivoPagoDia>) SessionManager.get(NombreObjetosSesion.AJAX_RESULT);
		if (jsonResult == null) {
			jsonResult = new ArrayList<ArchivoPagoDia>();
			SessionManager.put(NombreObjetosSesion.AJAX_RESULT, jsonResult);
		}
		return jsonResult;
	}

	public void setJsonResult(List<ArchivoPagoDia> ajaxResult) {
		this.jsonResult = ajaxResult;
	}

	public String getImgService() throws IOException, MailNoSendException {
		getFile();
		pagoBs.guardarPagoMovil(file, idServicio, idUser, folio);
		return GET_IMGB;
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

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public byte[] getArch() {
		return arch;
	}

	public void setArch(byte[] arch) {
		this.arch = arch;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Integer getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Integer idServicio) {
		this.idServicio = idServicio;
	}

	public Integer getFolio() {
		return folio;
	}

	public void setFolio(Integer folio) {
		this.folio = folio;
	}
	
	public PagoSiga getPagoSiga() {
		return pagoSiga;
	}

	public void setPagoSiga(PagoSiga pagoSiga) {
		this.pagoSiga = pagoSiga;
	}

}
