package mx.ipn.escom.spee.pagos.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.Archivo;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.mail.business.MailSender;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.Constantes;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.dao.GenericDao;

@Namespace("/pagos")
@Results({ @Result(name = PagoCajaAct.SUCCESS, type = "redirectAction", params = { "actionName", "pago-caja/new" }),
		@Result(name = PagoCajaAct.ERROR, type = "redirectAction", params = { "actionName", "pago-caja/new" }) })
public class PagoCajaAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PagoBs pagoBs;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private MailSender mailSender;

	private GenericDao genericDao;

	private List<CatalogoServicio> listServicios;

	private ArchivoPagoDia model;

	private Archivo archivo;

	private String correo;

	private Usuario usuarioSel;

	public String editNew() {
		listServicios = genericSearchBs.findAll(CatalogoServicio.class);
		return EDITNEW;
	}

	public void validateCreate() {
		getUsuarioSel();
		if (getFieldErrors().isEmpty() && getActionErrors().isEmpty()) {
			pagoBs.pagoEnCaja(archivo, usuarioSel, model, correo);
			enviarEmailPago(correo, model);
		} else {
			addActionError("Verifique su información.");
		}

	}

	public String create() {
		addActionMessage("Se adjunto el pago exitosamente");
		return SUCCESS;
	}

	public void enviarEmailPago(String usuario, ArchivoPagoDia archivoPago) {
		Map<String, String> mailProperties = new HashMap<>();
		Map<String, Object> templateParams = new HashMap<>();

		String ip = Constantes.IP;
		String contextPath = ServletActionContext.getRequest().getContextPath();
		String namespace = "/control-acceso/login";
		mailProperties.put(Constantes.SUBJECT, "Sistema de Pagos Electrónicos ESCOM");
		mailProperties.put(Constantes.TEMPLATE, "mx/ipn/escom/spee/mail/templates/pagoCaja.vm");

		templateParams.put("usuario", usuario);
		templateParams.put("fechaEnvio", archivoPago.getFechaEnvio());
		templateParams.put("conceptoPago",
				genericSearchBs.findById(CatalogoServicio.class, archivoPago.getIdCatalogoServicio()).getDescripcion());
		templateParams.put("urlNotifiaciones", ip + contextPath + namespace);

		List<String> destinatarios = new ArrayList<>();
		destinatarios.add(usuario);

		try {
			mailSender.sendEmail(templateParams, mailProperties.get(Constantes.TEMPLATE), destinatarios,
					mailProperties.get(Constantes.SUBJECT), null);
		} catch (Exception ex) {
			addActionMessage("No se pudo notificar al usuario.");
		}

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

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public List<CatalogoServicio> getListServicios() {
		return listServicios;
	}

	public void setListServicios(List<CatalogoServicio> listServicios) {
		this.listServicios = listServicios;
	}

	public ArchivoPagoDia getModel() {
		return model;
	}

	public void setModel(ArchivoPagoDia model) {
		this.model = model;
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}

	public Archivo getArchivo() {
		return archivo;
	}

	public void setArchivo(Archivo archivo) {
		this.archivo = archivo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

}
