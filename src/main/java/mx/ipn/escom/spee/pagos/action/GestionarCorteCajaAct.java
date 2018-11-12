package mx.ipn.escom.spee.pagos.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
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
import mx.ipn.escom.spee.pagos.mapeo.PagosCorte;
import mx.ipn.escom.spee.util.Constantes;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.bs.GenericBs;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/pagos")
@Results({
		@Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }),
		@Result(name = ActionSupport.ERROR, type = "redirectAction", params = { "actionName",
				"gestionar-autorizacion-pagos" }),
		@Result(name = "filesuccess", type = "stream", params = { "contentType", "application/pdf", "inputName",
				"archivoVisualizar.fileInputStream", "contentDisposition",
				"inline;filename=\"${archivoVisualizar.fileUploadFileName}\"", "bufferSize", "1024" }) })
public class GestionarCorteCajaAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GenericBs genericBs;

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
		guardarPagosConCorte(listPagosCorte);
		if (!listPagosCorte.isEmpty()) {
			for (ArchivoPagoDia archivoPagoDia : genericSearchBs.findByExample(archivo)) {
				archivoPagoDia.setCorte(Boolean.TRUE);
				listPagosCorte.add(archivoPagoDia);
			}
			genericBs.update(listPagosCorte);
			CorteCaja corteCaja = new CorteCaja();
			corteCaja.setEstado(true);
			corteCaja.setFechaCorte(new Date());
			corteCaja.setTotal(sumarTotalCorteCaja(listPagosCorte));
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

	private void guardarPagosConCorte(List<ArchivoPagoDia> listPagosCorte) {
		List<CorteCaja> listCorteCaja = genericSearchBs.findAll(CorteCaja.class);
		CorteCaja ultimoCorteCaja = listCorteCaja.get(listCorteCaja.size() - 1);
		for (ArchivoPagoDia pagosCorte : listPagosCorte) {
			PagosCorte pagosConCorte = new PagosCorte();
			pagosConCorte.setFechaPago(pagosCorte.getFechaEnvio());
			pagosConCorte.setIdCorteCaja(ultimoCorteCaja.getId());
			pagosConCorte.setIdPago(pagosCorte.getId());
			pagosConCorte.setIdCuenta(ultimoCorteCaja.getIdCuenta());
			genericBs.save(pagosConCorte);
		}
	}

	private Double sumarTotalCorteCaja(List<ArchivoPagoDia> listPagosCorte) {
		Double total = 0.0;
		for (ArchivoPagoDia archivoPagoDia : listPagosCorte) {
			total += archivoPagoDia.getCatalogoServicio().getPrecio();
		}
		return total;
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
