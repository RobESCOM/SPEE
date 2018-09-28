package mx.ipn.escom.spee.pagos.bs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
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

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.pagos.mapeo.ServicioArea;
import mx.ipn.escom.spee.util.Base64FileConverter;
import mx.ipn.escom.spee.util.PropertyAccess;
import mx.ipn.escom.spee.util.bs.GenericBs;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
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

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private Base64FileConverter base64;

	@Transactional
	public void registrarPago(Archivo archivo, Usuario usuario, Integer idServicio) throws IOException {
		ServicioArea catalogoServicio = new ServicioArea();
		// catalogoServicio.setClave(idServicio.toString());
		ServicioArea servicio = genericSearchBs.findByExample(catalogoServicio).get(0);
		Date currentDate = new Date();
		ArchivoPagoDia archivoPago = new ArchivoPagoDia();

		byte[] bfile = new byte[(int) archivo.getFileUpload().length()];
		FileInputStream fis = new FileInputStream(archivo.getFileUpload());
		archivoPago.setArchivo(bfile);
		fis.read(bfile);
		archivoPago.setUsuario(genericSearchBs.findById(Usuario.class, usuario.getId()));
		archivoPago.setIdUsuario(usuario.getId());
		archivoPago.setIdEstadoPago(EstadoPagoEnum.REVISION.getIdEstatus());
		archivoPago.setFechaEnvio(currentDate);
		archivoPago.setNombreArchivo(archivo.getFileUploadFileName());
		save(archivoPago);
		LOGGER.info("se ha registrado un pago");
	}

	@Transactional(rollbackFor = Exception.class)
	public void autorizarPago(Integer idSel) {
		ArchivoPagoDia archivoPagoDiaExample = new ArchivoPagoDia();
		archivoPagoDiaExample.setId(idSel);
		ArchivoPagoDia archivoPagoDia = genericSearchBs.findById(ArchivoPagoDia.class, idSel);
		// archivoPagoDia.setIdEstado(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
		update(archivoPagoDia);
	}

	public void mostrarPago(byte[] archivo) {
		try {
			FileOutputStream fileOuputStream = new FileOutputStream("filename.pdf");
			fileOuputStream.write(genericSearchBs.findById(ArchivoPagoDia.class, 11).getArchivo());
			System.err.println(fileOuputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void rechazarPago(Integer idSel) {
		ArchivoPagoDia archivoPagoDiaExample = new ArchivoPagoDia();
		archivoPagoDiaExample.setId(idSel);
		ArchivoPagoDia archivoPagoDia = genericSearchBs.findById(ArchivoPagoDia.class, idSel);
		// archivoPagoDia.setIdEstado(EstadoPagoEnum.RECHAZADO.getIdEstatus());
		update(archivoPagoDia);
	}

	public Archivo generarReporteCelex(List<ArchivoPagoDia> listPagosAutorizadosCelex) throws FileNotFoundException, JRException {
		return compilarReporteCelex(listPagosAutorizadosCelex);
	}

	private Archivo compilarReporteCelex(List<ArchivoPagoDia> listPagosAutorizadosCelex)
			throws JRException, FileNotFoundException {
		String ruta = PropertyAccess.getProperty("mx.edu.eld.admision.listaAspirantes.reporte.ruta");
		ServletContext servletContext = ServletActionContext.getServletContext();
		String context = servletContext.getRealPath("/");

		Archivo archivo = new Archivo();
		String rutaImagen = "";
		rutaImagen = context + PropertyAccess.getProperty("ruta.imagen.logo.full");
		Map<String, Object> parameters = new HashMap<>();

		parameters.put("listaPagosAutorizadosCelex", listPagosAutorizadosCelex);
		parameters.put("rutaImagen", rutaImagen);

		JasperReport reporte = JasperCompileManager.compileReport(
				context + ruta + PropertyAccess.getProperty("mx.edu.eld.admision.listaAspirantes.nombre.archivoXML"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters,
				new net.sf.jasperreports.engine.JREmptyDataSource());
		JasperExportManager.exportReportToPdfFile(jasperPrint,
				context + ruta + PropertyAccess.getProperty("mx.edu.eld.admision.listaAspirantes.nombre.archivoPDF"));
		File file = new File(
				context + ruta + PropertyAccess.getProperty("mx.edu.eld.admision.listaAspirantes.nombre.archivoPDF"));
		archivo.setFileUploadFileName(file.getName());
		//archivo.setFileInputStream(new FileInputStream(file));

		return archivo;
	}

	public List<ArchivoPagoDia> obtenerPagosPorAutorizar() {
		return genericSearchBs.findAll(ArchivoPagoDia.class);
	}

}
