package mx.edu.spee.controlacceso.bs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Perfil;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.mail.business.MailSender;
import mx.ipn.escom.spee.util.Constantes;
import mx.ipn.escom.spee.util.SHADigest;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.dao.GenericDao;

@Service("usuarioBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class UsuarioBs implements Serializable {

	private static final long serialVersionUID = -5310565999405879581L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private MailSender mailSender;

	public Usuario buscarUsuario(String login) {
		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		List<Usuario> listUsuarios = genericSearchBs.findByExample(usuario);
		return !listUsuarios.isEmpty() ? listUsuarios.get(0) : null;
	}

	@Transactional(rollbackFor = Exception.class)
	public Usuario registrar(Usuario usuario, InformacionPersonal info) {
		usuario.setPassword(SHADigest.digest(usuario.getPassword()));
		usuario.setActivo(Boolean.TRUE);
		genericDao.save(usuario);
		crearCuenta(usuario);
		guardarInformacionPersonal(usuario, info);
		enviarEmailConfirmacion(usuario);
		return usuario;
	}

	public void guardarInformacionPersonal(Usuario usuario, InformacionPersonal info) {
		info.setCorreo(usuario.getLogin());
		if (info.getBoleta() == null || info.getBoleta().equals("")) {
			info.setBoleta(Perfil.PerfilEnum.NO_APLICA.getNombre());
		} else if (info.getNoEmpleado() == null || info.getNoEmpleado().equals("")) {
			info.setNoEmpleado(Perfil.PerfilEnum.NO_APLICA.getNombre());
		}
		genericDao.save(info);
	}

	private void crearCuenta(Usuario usuario) {
		Cuenta cuenta = new Cuenta();
		//cuenta.setIdUsuario(genericSearchBs.findByExample(usuario).get(0).getId());
		cuenta.setIdPerfil(Perfil.PerfilEnum.ALUMNO.getValor());
		genericDao.save(cuenta);
	}

	public void enviarEmailConfirmacion(Usuario usuario) {
		Map<String, String> mailProperties = new HashMap<>();
		Map<String, Object> templateParams = new HashMap<>();

		mailProperties.put(Constantes.SUBJECT, "Email de Prueba");
		mailProperties.put(Constantes.TEMPLATE, "mx/ipn/escom/spee/mail/templates/confirmacionCuenta.vm");

		List<String> destinatarios = new ArrayList<>();
		destinatarios.add(usuario.getLogin());

		mailSender.sendEmail(templateParams, mailProperties.get(Constantes.TEMPLATE), destinatarios,
				mailProperties.get(Constantes.SUBJECT), null);
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public GenericDao getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDao genericDao) {
		this.genericDao = genericDao;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

}
