package mx.ipn.escom.spee.pagos.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.mail.business.MailSender;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.Constantes;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/pagos")
@Results({
		@Result(name = GestionarAutorizacionPagosAct.ERROR, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }),
		@Result(name = GestionarAutorizacionPagosAct.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }) })
@AllowedMethods({ "autorizarPago", "rechazarPago" })
public class GestionarAutorizacionPagosAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private PagoBs pagoBs;

	@Autowired
	private MailSender mailSender;

	private Integer idSel;

	private Usuario usuarioSel;

	private ArchivoPagoDia model;

	private ArchivoPagoDia pago;
	
	private String tipoArchivo;

	private File file;

	private List<ArchivoPagoDia> listArchivoPagosRevision;

	public InputStream inputStream;
	
	

	public String index() {
		getUsuarioSel();
		listArchivoPagosRevision = pagoBs.obtenerPagosPorAutorizar();
		return INDEX;
	}

	public String show() {
		getUsuarioSel();
		pago = genericSearchBs.findById(ArchivoPagoDia.class, idSel);
		tipoArchivo = getImageType(pago.getArchivo());
//		try {
//			FileOutputStream fileOuputStream;
//			fileOuputStream = new FileOutputStream("filename.pdf");
//			fileOuputStream.write(model.getArchivo());
//			file = new File("filename.pdf");
//			inputStream = new DataInputStream(new FileInputStream(file));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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

	@SkipValidation
	public String autorizarPago() {
		getModel();
		pagoBs.autorizarPago(model.getId());
		addActionMessage(getText("Se ha autorizado el pago"));
		Cuenta cuenta = genericSearchBs.findById(Cuenta.class, model.getIdUsuario());
		Usuario usuario = genericSearchBs.findById(Usuario.class, cuenta.getIdUsuario());
		enviarEmailPago(usuario, model);
		return SUCCESS;
	}

	@SkipValidation
	public String rechazarPago() {
		getModel();
		pagoBs.rechazarPago(model.getId());
		addActionMessage("Se ha rechazado el pago");
		Cuenta cuenta = genericSearchBs.findById(Cuenta.class, model.getIdUsuario());
		Usuario usuario = genericSearchBs.findById(Usuario.class, cuenta.getIdUsuario());
		enviarEmailPagoRechazado(usuario, model);
		return SUCCESS;
	}

	public void enviarEmailPago(Usuario usuario, ArchivoPagoDia archivoPago) {
		Map<String, String> mailProperties = new HashMap<>();
		Map<String, Object> templateParams = new HashMap<>();

		String ip = "http://localhost:8123";
		String contextPath = ServletActionContext.getRequest().getContextPath();
		String namespace = "/notificaciones/gestionar-notificaciones";
		mailProperties.put(Constantes.SUBJECT, "Sistema de Pagos Electrónicos ESCOM");
		mailProperties.put(Constantes.TEMPLATE, "mx/ipn/escom/spee/mail/templates/pagoAutorizado.vm");

		templateParams.put("usuario", usuario.getLogin());
		templateParams.put("fechaEnvio", archivoPago.getFechaEnvio());
		templateParams.put("conceptoPago",
				genericSearchBs.findById(CatalogoServicio.class, archivoPago.getIdCatalogoServicio()).getDescripcion());
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

	public void enviarEmailPagoRechazado(Usuario usuario, ArchivoPagoDia archivoPago) {
		Map<String, String> mailProperties = new HashMap<>();
		Map<String, Object> templateParams = new HashMap<>();

		String ip = "http://localhost:8123";
		String contextPath = ServletActionContext.getRequest().getContextPath();
		String namespace = "/notificaciones/gestionar-notificaciones";
		mailProperties.put(Constantes.SUBJECT, "Sistema de Pagos Electrónicos ESCOM");
		mailProperties.put(Constantes.TEMPLATE, "mx/ipn/escom/spee/mail/templates/pagoRechazado.vm");

		templateParams.put("usuario", usuario.getLogin());
		templateParams.put("fechaEnvio", archivoPago.getFechaEnvio());
		templateParams.put("conceptoPago",
				genericSearchBs.findById(CatalogoServicio.class, archivoPago.getIdCatalogoServicio()).getDescripcion());
		templateParams.put("urlNotifiaciones", ip + contextPath + namespace);

		List<String> destinatarios = new ArrayList<>();
		destinatarios.add(usuario.getLogin());

		try {
			mailSender.sendEmail(templateParams, mailProperties.get(Constantes.TEMPLATE), destinatarios,
					mailProperties.get(Constantes.SUBJECT), null);
		} catch (Exception ex) {
			addActionMessage("No se pudo envíar email");
		}
	}
	
	private boolean isMatch(byte[] pattern, byte[] data) {
        if (pattern.length <= data.length) {
            for (int idx = 0; idx < pattern.length; ++idx) {
                if (pattern[idx] != data[idx])
                    return false;
            }
            return true;
        }
 
        return false;
    }
	
	 private String getImageType(byte[] data) {
//       filetype    magic number(hex)
//       jpg         FF D8 FF
//       png         89 50 4E 47 0D 0A 1A 0A
		 
       final byte[] pngPattern = new byte[] { (byte)0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A};
       final byte[] jpgPattern = new byte[] { (byte)0xFF, (byte)0xD8, (byte)0xFF};
       
       if (isMatch(pngPattern, data))
           return "image/png";

       else if (isMatch(jpgPattern, data))
           return "image/jpg";

       return "application/pdf";
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

	public ArchivoPagoDia getPago() {
		return pago;
	}

	public void setPago(ArchivoPagoDia pago) {
		this.pago = pago;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
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

	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	
	
}
