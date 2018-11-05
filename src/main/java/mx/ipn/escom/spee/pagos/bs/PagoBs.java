package mx.ipn.escom.spee.pagos.bs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum;
import mx.ipn.escom.spee.notificaciones.mapeo.Notificacion;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.mail.business.MailSender;
import mx.ipn.escom.spee.pagos.exception.FormatoArchivoException;
import mx.ipn.escom.spee.pagos.exception.MailNoSendException;
import mx.ipn.escom.spee.pagos.exception.FolioDuplicadoException;
import mx.ipn.escom.spee.pagos.exception.TamanioArchivoException;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.pagos.mapeo.PagoSiga;
import mx.ipn.escom.spee.util.Constantes;
import mx.ipn.escom.spee.util.Meses;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.servicio.mapeo.TipoServicio.CatalogoTipoServicioEnum;
import mx.ipn.escom.spee.util.PropertyAccess;
import mx.ipn.escom.spee.util.bs.GenericBs;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.mapeo.AjaxResult;
import mx.ipn.escom.spee.util.mapeo.Modelo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service("pagoBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class PagoBs extends GenericBs<Modelo> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(PagoBs.class);

	public static final long CINCUENTA_MB = 6553600L;

	public static final String FORMATO_JPEG = "image/jpeg";

	public static final String FORMATO_JPG = "image/jpg";

	public static final String FORMATO_PNG = "image/png";

	public static final String FORMATO_PDF = "application/pdf";

	@Autowired
	private MailSender mailSender;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Transactional
	public void registrarPago(Archivo archivo, Usuario usuario, Integer idServicio, String folio) throws IOException,
			TamanioArchivoException, FormatoArchivoException, FolioDuplicadoException, MailNoSendException {

		ArchivoPagoDia archivoExample = new ArchivoPagoDia();
		archivoExample.setFolioOperacion(folio);
		if (!genericSearchBs.findByExample(archivoExample).isEmpty()) {
			throw new FolioDuplicadoException();
		}

		List<String> contentType = new ArrayList<>();
		contentType.add(FORMATO_JPEG);
		contentType.add(FORMATO_PNG);
		contentType.add(FORMATO_PDF);
		if (formatoArchivo(archivo, contentType)) {
			throw new FormatoArchivoException();
		}

		CatalogoServicio catalogoServicio = new CatalogoServicio();
		catalogoServicio.setClave(idServicio.toString());
		catalogoServicio.setIdArea(idServicio);
		Date currentDate = new Date();
		ArchivoPagoDia archivoPago = new ArchivoPagoDia();
		byte[] bfile = new byte[(int) archivo.getFileUpload().length()];
		FileInputStream fis = new FileInputStream(archivo.getFileUpload());
		archivoPago.setArchivo(bfile);
		fis.read(bfile);
		Cuenta cuenta = new Cuenta();
		cuenta.setIdUsuario(usuario.getId());
		archivoPago.setIdCatalogoServicio(idServicio);
		archivoPago.setIdUsuario(genericSearchBs.findByExample(cuenta).get(0).getIdCuenta());
		archivoPago.setIdEstadoPago(EstadoPagoEnum.REVISION.getIdEstatus());
		archivoPago.setIdTipoComprobante(CatalogoTipoServicioEnum.VOUCHER.getId());
		archivoPago.setFechaEnvio(currentDate);
		archivoPago.setIdCarpeta(1);
		archivoPago.setCorte(Boolean.FALSE);
		archivoPago.setFolioOperacion(folio);
		if (tamanioArchivo(archivo, CINCUENTA_MB)) {
			throw new TamanioArchivoException();
		}
		save(archivoPago);
		try {
			enviarEmailPago(usuario, archivoPago);
		} catch (Exception ex) {
			throw new MailNoSendException();
		}
		LOGGER.info("se ha registrado un pago");
		Notificacion notificacion = new Notificacion();
		notificacion.setFechaEnvio(currentDate);
		notificacion.setIdCuenta(genericSearchBs.findByExample(cuenta).get(0).getIdCuenta());
		notificacion.setMotivo("se ha registrado un pago");
		notificacion.setIdDestinatario(PerfilEnum.ENCARGADO_CAJA.getValor());
		save(notificacion);
	}

	@Transactional
	public void pagoEnCaja(Archivo archivo, Usuario usuario, ArchivoPagoDia model, String correo) {
		model.setFechaEnvio(new Date());
		model.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
		model.setIdTipoComprobante(CatalogoTipoServicioEnum.VOUCHER.getId());
		model.setIdCarpeta(Numeros.UNO.getValorInteger());
		model.setCorte(Boolean.FALSE);
		model.setFolioOperacion("Pago en Caja");

		byte[] bfile = new byte[30];
		model.setArchivo(bfile);
		genericDao.save(model);
	}

	public void enviarEmailPago(Usuario usuario, ArchivoPagoDia archivoPago) {
		Map<String, String> mailProperties = new HashMap<>();
		Map<String, Object> templateParams = new HashMap<>();

		String ip = "http://localhost:8123";
		String contextPath = ServletActionContext.getRequest().getContextPath();
		String namespace = "/notificaciones/gestionar-notificaciones";
		mailProperties.put(Constantes.SUBJECT, "Sistema de Pagos Electrónicos ESCOM");
		mailProperties.put(Constantes.TEMPLATE, "mx/ipn/escom/spee/mail/templates/pagoEnviado.vm");

		templateParams.put("usuario", usuario.getLogin());
		templateParams.put("fechaEnvio", archivoPago.getFechaEnvio());
		templateParams.put("conceptoPago",
				genericSearchBs.findById(CatalogoServicio.class, archivoPago.getIdCatalogoServicio()).getDescripcion());
		templateParams.put("urlNotifiaciones", ip + contextPath + namespace);

		List<String> destinatarios = new ArrayList<>();
		destinatarios.add(usuario.getLogin());

		mailSender.sendEmail(templateParams, mailProperties.get(Constantes.TEMPLATE), destinatarios,
				mailProperties.get(Constantes.SUBJECT), null);

	}

	private Boolean tamanioArchivo(Archivo archivo, long numeroBytes) {
		return (archivo.getFileUpload().length() > numeroBytes) ? true : false;
	}

	private Boolean formatoArchivo(Archivo archivo, List<String> contentTypes) {
		return (!contentTypes.contains(archivo.getFileUploadContentType())) ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	public void autorizarPago(Integer idSel) {
		ArchivoPagoDia archivoPagoDiaExample = new ArchivoPagoDia();
		archivoPagoDiaExample.setId(idSel);
		ArchivoPagoDia archivoPagoDia = genericSearchBs.findById(ArchivoPagoDia.class, idSel);
		archivoPagoDia.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
		update(archivoPagoDia);
	}

	public FileOutputStream mostrarPago(Integer id) {
		try {
			FileOutputStream fileOuputStream = new FileOutputStream("filename.pdf");
			fileOuputStream.write(genericSearchBs.findById(ArchivoPagoDia.class, id).getArchivo());
			return fileOuputStream;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	public void rechazarPago(Integer idSel) {
		ArchivoPagoDia archivoPagoDiaExample = new ArchivoPagoDia();
		archivoPagoDiaExample.setId(idSel);
		ArchivoPagoDia archivoPagoDia = genericSearchBs.findById(ArchivoPagoDia.class, idSel);
		archivoPagoDia.setIdEstadoPago(EstadoPagoEnum.RECHAZADO.getIdEstatus());
		update(archivoPagoDia);
	}

	public Archivo generarReporteCelex(List<ArchivoPagoDia> listPagosAutorizadosCelex)
			throws FileNotFoundException, JRException {
		return compilarReporteCelex(listPagosAutorizadosCelex);
	}

	private Archivo compilarReporteCelex(List<ArchivoPagoDia> listPagosAutorizadosCelex)
			throws JRException, FileNotFoundException {
		String ruta = PropertyAccess.getProperty("mx.edu.spee.pagos.celex.reporte.ruta");
		ServletContext servletContext = ServletActionContext.getServletContext();
		String context = servletContext.getRealPath("/");

		Archivo archivo = new Archivo();
		String rutaImagen = "";
		rutaImagen = context + PropertyAccess.getProperty("ruta.imagen.logo.full");
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("listaPagosAutorizadosCelex", listPagosAutorizadosCelex);
		parameters.put("rutaImagen", rutaImagen);

		JasperReport reporte = JasperCompileManager.compileReport(
				context + ruta + PropertyAccess.getProperty("mx.edu.spee.pagos.celex.nombre.archivoXML"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters,
				new net.sf.jasperreports.engine.JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				context + ruta + PropertyAccess.getProperty("mx.edu.spee.pagos.celex.nombre.archivoPDF"));
		File file = new File(context + ruta + PropertyAccess.getProperty("mx.edu.spee.pagos.celex.nombre.archivoPDF"));
		archivo.setFileUploadFileName(file.getName());

		return archivo;
	}

	public List<ArchivoPagoDia> obtenerPagosPorAutorizar() {
		ArchivoPagoDia archivo = new ArchivoPagoDia();
		archivo.setCorte(Boolean.FALSE);
		return genericSearchBs.findByExample(archivo);
	}

	public AjaxResult obtenerPagosUsuario(Integer idUsuario) {
		AjaxResult ajaxResult = new AjaxResult();
		ArchivoPagoDia pagoDia = new ArchivoPagoDia();
		pagoDia.setIdUsuario(idUsuario);
		ajaxResult.addCampo("pagos", genericSearchBs.findByExample(pagoDia));
		return ajaxResult;
	}

	public InformacionPersonal obtenerAlumno(ArchivoPagoDia pago) {
		Cuenta cuenta = genericSearchBs.findById(Cuenta.class, pago.getIdUsuario());
		return obtenerPersona(cuenta);
	}

	public InformacionPersonal obtenerPersona(Cuenta cuentaImpresiones) {
		InformacionPersonal infoPersonal = new InformacionPersonal();
		infoPersonal.setIdCuenta(cuentaImpresiones.getIdCuenta());
		List<InformacionPersonal> infoPersona = genericSearchBs.findByExample(infoPersonal);
		return infoPersona.get(Numeros.CERO.getValor());
	}

	public String obtenerArchivo(ArchivoPagoDia archivo) throws IOException {
		byte[] encoded = Base64.getEncoder().encode(archivo.getArchivo());
		return new String(encoded);
	}

	public int obtenerAnio(ArchivoPagoDia pago) {
		Calendar anio = Calendar.getInstance();
		anio.setTime(pago.getFechaEnvio());
		if (anio.get(Calendar.YEAR) == Numeros.ANIO_ACTUAL.getValor())
			return Numeros.ANIO_ACTUAL.getValor();
		else if (anio.get(Calendar.YEAR) == Numeros.ANIO_UNO.getValor())
			return Numeros.ANIO_UNO.getValor();
		else if (anio.get(Calendar.YEAR) == Numeros.ANIO_DOS.getValor())
			return Numeros.ANIO_DOS.getValor();
		else if (anio.get(Calendar.YEAR) == Numeros.ANIO_TRES.getValor())
			return Numeros.ANIO_TRES.getValor();
		else if (anio.get(Calendar.YEAR) == Numeros.ANIO_CUATRO.getValor())
			return Numeros.ANIO_CUATRO.getValor();
		else if (anio.get(Calendar.YEAR) == Numeros.ANIO_CINCO.getValor())
			return Numeros.ANIO_CINCO.getValor();
		return Numeros.UNO_NEGATIVO.getValor();
	}

	public String obtenerAnioMes(ArchivoPagoDia pago, int anios) {
		Calendar anio = Calendar.getInstance();
		anio.setTime(pago.getFechaEnvio());
		if (anio.get(Calendar.YEAR) == anios) {
			System.out.println();
			if (anio.get(Calendar.MONTH) == Meses.ENE.getIdValor()) {
				return Meses.ENE.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.FEB.getIdValor()) {
				return Meses.FEB.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.MAR.getIdValor()) {
				return Meses.MAR.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.ABR.getIdValor()) {
				return Meses.ABR.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.MAY.getIdValor()) {
				return Meses.MAY.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.JUN.getIdValor()) {
				return Meses.JUN.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.JUL.getIdValor()) {
				return Meses.JUL.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.AGO.getIdValor()) {
				return Meses.AGO.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.SEP.getIdValor()) {
				return Meses.SEP.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.OCT.getIdValor()) {
				return Meses.OCT.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.NOV.getIdValor()) {
				return Meses.NOV.getNombre();
			} else if (anio.get(Calendar.MONTH) == Meses.DIC.getIdValor()) {
				return Meses.DIC.getNombre();
			}
		}
		return "";
	}

	public boolean obtenerPagos(ArchivoPagoDia pago, int year, String mes) {
		Calendar anio = Calendar.getInstance();
		anio.setTime(pago.getFechaEnvio());
		if (anio.get(Calendar.YEAR) == year) {
			if (anio.get(Calendar.MONTH) == getMes(mes)) {
				return true;
			}
		}
		return false;
	}

	public int getMes(String mes) {
		if (mes.equals(Meses.ENE.getNombre()))
			return Numeros.CERO.getValor();
		else if (mes.equals(Meses.FEB.getNombre()))
			return Numeros.UNO.getValor();
		else if (mes.equals(Meses.MAR.getNombre()))
			return Numeros.DOS.getValor();
		else if (mes.equals(Meses.ABR.getNombre()))
			return Numeros.TRES.getValor();
		else if (mes.equals(Meses.MAY.getNombre()))
			return Numeros.CUATRO.getValor();
		else if (mes.equals(Meses.JUN.getNombre()))
			return Numeros.CINCO.getValor();
		else if (mes.equals(Meses.JUL.getNombre()))
			return Numeros.SEIS.getValor();
		else if (mes.equals(Meses.AGO.getNombre()))
			return Numeros.SIETE.getValor();
		else if (mes.equals(Meses.SEP.getNombre()))
			return Numeros.OCHO.getValor();
		else if (mes.equals(Meses.OCT.getNombre()))
			return Numeros.NUEVE.getValor();
		else if (mes.equals(Meses.NOV.getNombre()))
			return Numeros.DIEZ.getValor();
		else if (mes.equals(Meses.DIC.getNombre()))
			return Numeros.ONCE.getValor();

		return Numeros.UNO_NEGATIVO.getValor();
	}

	public String[] obtenerTotalAnio(List<ArchivoPagoDia> pagos) {
		String[] anio = new String[3];
		int cantidad = 0;
		Double total = 0.00;
		boolean fecha = true;
		for (ArchivoPagoDia pag : pagos) {
			if (fecha)
				anio[Numeros.CERO.getValor()] = obtenerAnio(pag) + "";
			cantidad++;
			total = total + pag.getCatalogoServicio().getPrecio();
		}
		anio[Numeros.UNO.getValor()] = cantidad + "";
		anio[Numeros.DOS.getValor()] = String.format("%.2f", total);

		return anio;
	}

	public String[] obtenerTotalMes(List<ArchivoPagoDia> pagos) {
		String[] anio = new String[3];
		int cantidad = 0;
		Double total = 0.00;
		for (ArchivoPagoDia pag : pagos) {
			anio[Numeros.CERO.getValor()] = obtenerAnioMes(pag, obtenerAnio(pag));
			cantidad++;
			total = total + pag.getCatalogoServicio().getPrecio();
		}
		anio[Numeros.UNO.getValor()] = cantidad + "";
		anio[Numeros.DOS.getValor()] = String.format("%.2f", total);
		return anio;
	}

	public String dateForm(ArchivoPagoDia pago) {
		Calendar anio = Calendar.getInstance();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		return format1.format(anio.getTime());
	}

	public String moneyForm(ArchivoPagoDia pago) {
		return String.format("%.2f", pago.getCatalogoServicio().getPrecio());
	}

	public boolean findSigaAsociado(Integer idPago) {
		PagoSiga comprobante = new PagoSiga();
		comprobante.setIdPago(idPago);
		System.err.println(!genericSearchBs.findByExample(comprobante).isEmpty());
		return !genericSearchBs.findByExample(comprobante).isEmpty();
	}

	public String obtenerTipo(String tipo) {
		return tipo;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

}