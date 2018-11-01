package mx.ipn.escom.spee.area.bs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.spee.controlacceso.bs.UsuarioBs;
import mx.edu.spee.controlacceso.exception.UniqueException;
import mx.edu.spee.controlacceso.exception.UserActiveException;
import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum;
import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.area.mapeo.CatalogoArea;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.SHADigest;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.dao.GenericDao;

/**
 * @author Robert
 *
 */
@Service("areaBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class AreaBs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1529651412567058850L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GenericDao genericDao;

	@Autowired
	private UsuarioBs usuarioBs;

	/**
	 * Obtiene la información personal de todos los responsables de área
	 * 
	 * @return infoResponsables Lista con la info personal de los responsables
	 *         de área
	 */
	public List<InformacionPersonal> obtenerInfoResponsables() {
		List<Cuenta> cuentasResponsables = new ArrayList<>();
		List<InformacionPersonal> infoResposables = new ArrayList<>();

		// Genera una lista con las cuentas de los responsables de áreas
		List<Cuenta> cuentas = genericSearchBs.findAll(Cuenta.class);
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_BIBLIOTECA.getValor()
					|| cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_CELEX.getValor()
					|| cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_DENTALES.getValor()
					|| cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_FOTOCOPIADO.getValor()) {
				cuentasResponsables.add(cuenta);
			}
		}

		// Genera una lista con la info personal de cada responsable de área
		for (Cuenta cuenta : cuentasResponsables) {
			if (cuenta.getEstatus()) {
				InformacionPersonal info = new InformacionPersonal();
				info.setIdCuenta(cuenta.getIdCuenta());
				List<InformacionPersonal> infoPersonal = genericSearchBs.findByExample(info);
				infoResposables.add(infoPersonal.get(Numeros.CERO.getValor()));
			}
		}
		return infoResposables;
	}

	/**
	 * Obtiene el área de un responsable de área
	 * 
	 * @param idCuenta
	 *            id de la cuenta asignada a un responsable
	 * 
	 * @return nombre del área
	 */
	public String obtieneAreaResponsable(Integer idCuenta) {
		CatalogoArea area = new CatalogoArea();
		area.setIdResponsable(idCuenta);
		List<CatalogoArea> infoArea = genericSearchBs.findByExample(area);
		area = infoArea.get(Numeros.CERO.getValor());

		return area.getNombreArea();
	}

	@Transactional(rollbackFor = Exception.class)
	public void registrarResponsable(InformacionPersonal model) throws UniqueException {
		Usuario usr = new Usuario();
		usr.setLogin(model.getCorreo());
		if (!genericSearchBs.findByExample(usr).isEmpty()) {
			throw new UniqueException();
		} else {
			usr.setPassword(SHADigest.digest("password"));
			Date fechaAlta = new Date();
			usr.setFechaAlta(fechaAlta);
			genericDao.save(usr);
			usuarioBs.crearCuentaPerfilDefault(usr);
			// Obtenemos el id_cuenta del usuario creado
			Usuario usuarioCreado = new Usuario();
			usuarioCreado.setLogin(model.getCorreo());
			usuarioCreado = genericSearchBs.findByExample(usuarioCreado).get(Numeros.CERO.getValor());
			Cuenta cuentaCreada = new Cuenta();
			cuentaCreada.setIdUsuario(usuarioCreado.getId());
			cuentaCreada = genericSearchBs.findByExample(cuentaCreada).get(Numeros.CERO.getValor());
			Integer idCuenta = cuentaCreada.getIdCuenta();

			// Guardamos la info personal del responsable
			model.setIdCuenta(idCuenta);
			model.setCorreoInicio(model.getCorreo());
			genericDao.save(model);
			// usuarioBs.enviarEmailConfirmacion(usuarioCreado);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void editarResponsable(InformacionPersonal model, String correo) throws UniqueException {
		String email = model.getCorreo();
		if (email.equals(correo)) {
			genericDao.updateMerge(model);
		} else {
			Usuario usr = new Usuario();
			usr.setLogin(model.getCorreo());
			if (!genericSearchBs.findByExample(usr).isEmpty())
				throw new UniqueException();
			genericDao.update(usr);
			genericDao.updateMerge(model);
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public Boolean darBajaResponsable(Integer idCuenta) {
		Cuenta cuenta = genericSearchBs.findById(Cuenta.class, idCuenta);
		if (cuenta != null) {
			cuenta.setEstatus(false);
			genericDao.update(cuenta);
			return true;
		} else {
			return false;
		}
	}

	public List<CatalogoArea> obtieneAreasActivas() {
		CatalogoArea area = new CatalogoArea();
		List<CatalogoArea> listAreas = new ArrayList<>();
		area.setEstatus(true);
		listAreas = genericSearchBs.findByExample(area);
		return listAreas;
	}

	public String obtenerNombreResponsable(Integer idCuenta) {
		InformacionPersonal infoResponsable = new InformacionPersonal();
		infoResponsable.setIdCuenta(idCuenta);
		infoResponsable = genericSearchBs.findByExample(infoResponsable).get(Numeros.CERO.getValor());
		String nombre = infoResponsable.getNombre();
		String primerAp = infoResponsable.getPrimerApellido();
		String segundoAp = infoResponsable.getSegundoApellido();
		return nombre + " " + primerAp + " " + segundoAp;
	}

	public List<InformacionPersonal> obtenerInfoResponsablesDefault() {
		List<Cuenta> cuentasResponsables = new ArrayList<>();
		List<InformacionPersonal> infoResposables = new ArrayList<>();

		// Genera una lista con las cuentas de los responsables de áreas con
		// perfil default
		List<Cuenta> cuentas = genericSearchBs.findAll(Cuenta.class);
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getIdPerfil() == PerfilEnum.DEFAULT.getValor()) {
				cuentasResponsables.add(cuenta);
			}
		}

		// Genera una lista con la info personal de cada responsable de área
		for (Cuenta cuenta : cuentasResponsables) {
			if (cuenta.getEstatus()) {
				InformacionPersonal info = new InformacionPersonal();
				info.setIdCuenta(cuenta.getIdCuenta());
				List<InformacionPersonal> infoPersonal = genericSearchBs.findByExample(info);
				infoResposables.add(infoPersonal.get(Numeros.CERO.getValor()));
			}
		}
		return infoResposables;
	}

	@Transactional(rollbackFor = Exception.class)
	public void editarArea(CatalogoArea area, Integer idArea) throws UserActiveException {
		CatalogoArea areaAux = genericSearchBs.findById(CatalogoArea.class, idArea);
		if (areaAux.getIdResponsable() == area.getIdResponsable()) {
			genericDao.update(area);
		} else {
			Cuenta cuentaResponsable = genericSearchBs.findById(Cuenta.class, areaAux.getIdResponsable());
			if (cuentaResponsable.getEstatus()) {
				throw new UserActiveException();
			} else {
				genericDao.update(area);
			}
		}
	}
}
