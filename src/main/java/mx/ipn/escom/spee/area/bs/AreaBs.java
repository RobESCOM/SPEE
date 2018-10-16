package mx.ipn.escom.spee.area.bs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

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

	private GenericSearchBs genericSearchBs;
	
	/**
	 * Obtiene a los responsables de área activos
	 * 
	 * @return listInfo lista con la información personal de los responsables de
	 *         área activos
	 */
	public List<InformacionPersonal> obtenerResponsablesArea() {
		List<Cuenta> cuentasResponsables = new ArrayList<>();
		List<InformacionPersonal> listInfo = new ArrayList<>();

		// Se forma una lista con las cuentas cuyo perfil pertenezca a algún
		// responsable de área
		List<Cuenta> cuentas = genericSearchBs.findAll(Cuenta.class);
		for (Cuenta cuenta : cuentas) {
			if (cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_BIBLIOTECA.getValor()
					|| cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_CELEX.getValor()
					|| cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_DENTALES.getValor()
					|| cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_FOTOCOPIADO.getValor()) {
				cuentasResponsables.add(cuenta);
			}
		}

		// Se forma una lista con la información personal de las cuentas
		// obtenidas anteriormente
		for (Cuenta cta : cuentasResponsables) {
			if (cta.getEstatus()) {
				InformacionPersonal info = new InformacionPersonal();
				info.setIdCuenta(cta.getIdCuenta());
				List<InformacionPersonal> infoPersonal = genericSearchBs.findByExample(info);
				listInfo.add(infoPersonal.get(Numeros.CERO.getValor()));
			}
		}
		return listInfo;
	}
}
