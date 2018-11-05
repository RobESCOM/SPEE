package mx.ipn.escom.spee.pagos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.mail.business.MailSender;
import mx.ipn.escom.spee.pagos.exception.SinPagosConCorteException;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.CorteCaja;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.util.Constantes;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.PropertyAccess;
import mx.ipn.escom.spee.util.bs.GenericBs;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Namespace("/pagos")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }),
		@Result(name = ActionSupport.ERROR, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }),
		@Result(name = "filesuccess", type = "stream", params = { "contentType", "application/pdf", "inputName",
				"archivoVisualizar.fileInputStream", "contentDisposition",
				"inline;filename=\"${archivoVisualizar.fileUploadFileName}\"", "bufferSize", "1024" }) })
@AllowedMethods({ "imprimirReporte", "vizualizarArchivo" })
public class GestionarCorteCajaAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GenericBs genericBs;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	private MailSender mailSender;

	private Archivo archivoVisualizar;

	private Integer idUsuario;

	private Usuario usuarioSel;

	public String validateCreate() {
		getUsuarioSel();
		ArchivoPagoDia archivo = new ArchivoPagoDia();
		archivo.setCorte(Boolean.FALSE);
		archivo.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
		List<ArchivoPagoDia> listPagosCorte = genericSearchBs.findByExample(archivo);
		if (!listPagosCorte.isEmpty()) {
			for (ArchivoPagoDia archivoPagoDia : genericSearchBs.findByExample(archivo)) {
				archivoPagoDia.setCorte(Boolean.TRUE);
				listPagosCorte.add(archivoPagoDia);
			}
			genericBs.update(listPagosCorte);
			System.err.println(listPagosCorte);
			CorteCaja corteCaja = new CorteCaja();
			corteCaja.setEstado(true);
			corteCaja.setFechaCorte(new Date());
			Cuenta cuenta = genericSearchBs.findById(Cuenta.class, usuarioSel.getId());
			corteCaja.setIdCuenta(cuenta.getIdCuenta());
			genericBs.save(corteCaja);
			Usuario usuario = genericSearchBs.findById(Usuario.class, Numeros.UNO.getValor());
			notificarSubdirector(usuario, corteCaja);
		} else {
			addActionMessage("No hay pagos disponibles para el corte de caja");
			return ERROR;
		}
		return SUCCESS;
	}

	public HttpHeaders imprimirReporte() {
		try {
			getUsuarioSel();
			ArchivoPagoDia archivo = new ArchivoPagoDia();
			archivo.setCorte(Boolean.FALSE);
			archivo.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> listPagosCorte = genericSearchBs.findByExample(archivo);
			return vizualizarArchivo(generarReporte(listPagosCorte));
		} catch (FileNotFoundException | JRException e) {
			return new DefaultHttpHeaders(ERROR).disableCaching();
		}
	}

	@SkipValidation
	public HttpHeaders vizualizarArchivo(Archivo archivo) {
		archivoVisualizar = new Archivo();
		setArchivoVisualizar(archivo);
		return new DefaultHttpHeaders("filesuccess").disableCaching();
	}

	public Archivo generarReporte(List<ArchivoPagoDia> listPagosCorte) throws FileNotFoundException, JRException {
		String ruta = "\\resources\\reportes\\reporte-pago-autorizados\\reporte-subdirector\\";
		System.err.println(ruta);
		ServletContext servletContext = ServletActionContext.getServletContext();
		String context = servletContext.getRealPath("/");

		return compilarReporteSubdirector(context, ruta, listPagosCorte);
	}

	private Archivo compilarReporteSubdirector(String context, String ruta, List<ArchivoPagoDia> listPagosCorte)
			throws JRException, FileNotFoundException {
		Archivo archivo = new Archivo();
		DateFormat outputFormatter = new SimpleDateFormat(PropertyAccess.getProperty("mx.edu.spee.formatDate"));
		String output = outputFormatter.format(new Date());
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fecha", output);
		parameters.put("listCorte", listPagosCorte);

		Session session = (Session) sessionFactory.getCurrentSession().getDelegate();
		SessionImpl sessionImpl = (SessionImpl) session;
		Connection conn = sessionImpl.connection();

		String r = context + ruta + "SubdirectorReporte.jrxml";
		System.err.println(context + ruta + "SubdirectorReporte.jrxml");
		JasperReport reporte = JasperCompileManager.compileReport(context + ruta + "SubdirectorReporte.jrxml");
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conn);
		JasperExportManager.exportReportToPdfFile(jasperPrint, context + ruta + "SubdirectorReporte.pdf");
		File file = new File(context + ruta + "SubdirectorReporte.pdf");
		archivo.setFileUploadFileName(file.getName());
		archivo.setFileInputStream(new FileInputStream(file));

		return archivo;
	}

	public void corteCaja() throws SinPagosConCorteException {
		getUsuarioSel();
		ArchivoPagoDia archivo = new ArchivoPagoDia();
		archivo.setCorte(Boolean.FALSE);
		archivo.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
		List<ArchivoPagoDia> listPagosCorte = genericSearchBs.findByExample(archivo);
		if (!listPagosCorte.isEmpty()) {
			for (ArchivoPagoDia archivoPagoDia : listPagosCorte) {
				archivoPagoDia.setCorte(Boolean.TRUE);
				listPagosCorte.add(archivoPagoDia);
			}
			genericBs.update(listPagosCorte);
			System.err.println(listPagosCorte);
			CorteCaja corteCaja = new CorteCaja();
			corteCaja.setEstado(true);
			corteCaja.setFechaCorte(new Date());
			Cuenta cuenta = genericSearchBs.findById(Cuenta.class, usuarioSel.getId());
			corteCaja.setIdCuenta(cuenta.getIdCuenta());
			genericBs.save(corteCaja);
			Usuario usuario = genericSearchBs.findById(Usuario.class, Numeros.UNO.getValor());
			notificarSubdirector(usuario, corteCaja);
		} else {
			throw new SinPagosConCorteException();
		}

	}

	public String create() {
		addActionMessage("Se ha realizado el corte de caja exitosamente");
		SessionManager.clear();
		return SUCCESS;
	}

	public void notificarSubdirector(Usuario usuario, CorteCaja corteCaja) {
		Map<String, String> mailProperties = new HashMap<>();
		Map<String, Object> templateParams = new HashMap<>();

		String ip = "http://localhost:8123";
		String contextPath = ServletActionContext.getRequest().getContextPath();
		String namespace = "/notificaciones/gestionar-notificaciones";
		mailProperties.put(Constantes.SUBJECT, "Sistema de Pagos Electrónicos ESCOM");
		mailProperties.put(Constantes.TEMPLATE, "mx/ipn/escom/spee/mail/templates/corteCaja.vm");

		templateParams.put("usuario", usuario.getLogin());
		templateParams.put("fechaEnvio", corteCaja.getFechaCorte());
		templateParams.put("urlNotifiaciones", ip + contextPath + namespace);

		List<String> destinatarios = new ArrayList<>();
		destinatarios.add(usuario.getLogin());

		try {
			mailSender.sendEmail(templateParams, mailProperties.get(Constantes.TEMPLATE), destinatarios,
					mailProperties.get(Constantes.SUBJECT), null);
		} catch (Exception ex) {
			addActionMessage("No se pudo notificar al usuario.");
		}

	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public GenericBs getGenericBs() {
		return genericBs;
	}

	public void setGenericBs(GenericBs genericBs) {
		this.genericBs = genericBs;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public Archivo getArchivoVisualizar() {
		return archivoVisualizar;
	}

	public void setArchivoVisualizar(Archivo archivoVisualizar) {
		this.archivoVisualizar = archivoVisualizar;
	}

}
