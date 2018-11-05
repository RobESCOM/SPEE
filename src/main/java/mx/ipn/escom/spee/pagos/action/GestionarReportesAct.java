package mx.ipn.escom.spee.pagos.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.CorteCaja;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.util.PropertyAccess;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Namespace("/pagos")
@Results({ @Result(name = "filesuccess", type = "stream", params = { "contentType", "application/pdf", "inputName",
		"archivoVisualizar.fileInputStream", "contentDisposition",
		"inline;filename=\"${archivoVisualizar.fileUploadFileName}\"", "bufferSize", "1024" }) })
@AllowedMethods({ "imprimirReporte", "vizualizarArchivo" })
public class GestionarReportesAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	protected SessionFactory sessionFactory;

	private List<CorteCaja> listCorteCaja;

	private Archivo archivoVisualizar;

	private Usuario usuarioSel;

	public String index() {
		listCorteCaja = genericSearchBs.findAll(CorteCaja.class);
		getUsuarioSel();
		ArchivoPagoDia archivo = new ArchivoPagoDia();
		archivo.setCorte(Boolean.TRUE);
		archivo.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
		List<ArchivoPagoDia> listPagosCorte = genericSearchBs.findByExample(archivo);
		
		return INDEX;
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
		String ruta = "/resources/reportes/reporte-pago-autorizados/reporte-subdirector/";
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

		JasperReport reporte = JasperCompileManager.compileReport(context + ruta + "SubdirectorReporte.jrxml");
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, conn);
		JasperExportManager.exportReportToPdfFile(jasperPrint, context + ruta + "SubdirectorReporte.pdf");
		File file = new File(context + ruta + "SubdirectorReporte.pdf");
		archivo.setFileUploadFileName(file.getName());
		archivo.setFileInputStream(new FileInputStream(file));

		return archivo;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public Archivo getArchivoVisualizar() {
		return archivoVisualizar;
	}

	public void setArchivoVisualizar(Archivo archivoVisualizar) {
		this.archivoVisualizar = archivoVisualizar;
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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<CorteCaja> getListCorteCaja() {
		return listCorteCaja;
	}

	public void setListCorteCaja(List<CorteCaja> listCorteCaja) {
		this.listCorteCaja = listCorteCaja;
	}

}
