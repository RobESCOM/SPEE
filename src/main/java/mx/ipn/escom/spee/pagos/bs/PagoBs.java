package mx.ipn.escom.spee.pagos.bs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.pagos.exception.FormatoArchivoException;
import mx.ipn.escom.spee.pagos.exception.FolioDuplicadoException;
import mx.ipn.escom.spee.pagos.exception.TamanioArchivoException;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.pagos.mapeo.TipoServicio.CatalogoTipoServicioEnum;
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
	private GenericSearchBs genericSearchBs;

	@Transactional
	public void registrarPago(Archivo archivo, Usuario usuario, Integer idServicio, String folio)
			throws IOException, TamanioArchivoException, FormatoArchivoException, FolioDuplicadoException {

		ArchivoPagoDia archivoExample = new ArchivoPagoDia();
		archivoExample.setFolioOperacion(folio);
		if(!genericSearchBs.findByExample(archivoExample).isEmpty()) {
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
		Date currentDate = new Date();
		ArchivoPagoDia archivoPago = new ArchivoPagoDia();
		byte[] bfile = new byte[(int) archivo.getFileUpload().length()];
		FileInputStream fis = new FileInputStream(archivo.getFileUpload());
		archivoPago.setArchivo(bfile);
		fis.read(bfile);
		CatalogoServicio catalogo = new CatalogoServicio();
		catalogo.setId(idServicio);
		Cuenta cuenta = new Cuenta();
		cuenta.setIdUsuario(usuario.getId());
		archivoPago.setIdCatalogoServicio(genericSearchBs.findByExample(catalogo).get(0).getId());
		archivoPago.setIdUsuario(genericSearchBs.findByExample(cuenta).get(0).getIdCuenta());
		archivoPago.setIdEstadoPago(EstadoPagoEnum.REVISION.getIdEstatus());
		archivoPago.setIdTipoComprobante(CatalogoTipoServicioEnum.VOUCHER.getId());
		archivoPago.setFechaEnvio(currentDate);
		archivoPago.setIdCarpeta(1);
		archivoPago.setFolioOperacion(folio);
		if (tamanioArchivo(archivo, CINCUENTA_MB)) {
			throw new TamanioArchivoException();
		}	
		save(archivoPago);
		LOGGER.info("se ha registrado un pago");

	}

	private Boolean tamanioArchivo(Archivo archivo, long numeroBytes) {
		System.err.println(archivo.getFileUpload().length());
		return (archivo.getFileUpload().length() > numeroBytes) ? true : false;
	}

	private Boolean formatoArchivo(Archivo archivo, List<String> contentTypes) {
		System.err.println((!contentTypes.contains(archivo.getFileUploadContentType())));
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

	public void mostrarPago(byte[] archivo) {
		try {
			FileOutputStream fileOuputStream = new FileOutputStream("filename.pdf");
			fileOuputStream.write(genericSearchBs.findById(ArchivoPagoDia.class, 11).getArchivo());
			System.err.println(fileOuputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		// archivo.setFileInputStream(new FileInputStream(file));

		return archivo;
	}

	public List<ArchivoPagoDia> obtenerPagosPorAutorizar() {
		return genericSearchBs.findAll(ArchivoPagoDia.class);
	}

	public AjaxResult obtenerPagosUsuario(Integer idUsuario) {
		AjaxResult ajaxResult = new AjaxResult();
		ArchivoPagoDia pagoDia = new ArchivoPagoDia();
		pagoDia.setIdUsuario(idUsuario);
		ajaxResult.addCampo("pagos", genericSearchBs.findByExample(pagoDia));
		return ajaxResult;
	}

}
