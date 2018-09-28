package mx.edu.spee.controlacceso.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import mx.edu.spee.controlacceso.bs.UsuarioBs;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/control-acceso")
@Results({ @Result(name = ActionSupport.SUCCESS, type = "redirectAction", params = { "actionName",
		"login" }) })
public class RegistrarUsuarioAct extends GeneralActionSupport implements ModelDriven<InformacionPersonal> {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	private InformacionPersonal info;

	@Autowired
	private UsuarioBs usuarioBs;

	@Autowired
	private GenericSearchBs genericSearchBs;

	public String editNew() {
		return EDITNEW;
	}

	public void validateCreate() {
		if (usuario != null) {
			Usuario usuarioRegistrado = usuarioBs.registrar(usuario, info);
//			Cuenta cuenta = new Cuenta();
//			cuenta.setIdUsuario(usuario.getId());
//			info.setIdCuenta(genericSearchBs.findByExample(cuenta).get(0).getIdCuenta());
		}
	}

	public String create() {
		addActionMessage("Usuario se registró exitosamente, revise su correo electrónico");
		return SUCCESS;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioBs getUsuarioBs() {
		return usuarioBs;
	}

	public void setUsuarioBs(UsuarioBs usuarioBs) {
		this.usuarioBs = usuarioBs;
	}

	public InformacionPersonal getInfo() {
		return info;
	}

	public void setInfo(InformacionPersonal info) {
		this.info = info;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	@Override
	public InformacionPersonal getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
