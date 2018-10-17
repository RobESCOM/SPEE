package mx.ipn.escom.spee.area.bs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum;
import mx.ipn.escom.spee.area.mapeo.CatalogoArea;
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

	@Autowired
	private GenericSearchBs genericSearchBs;
	
	/**
	 * Obtiene la información personal de todos los responsables de área
	 * 
	 * @return infoResponsables Lista con la info personal de los responsables de área
	 */
	public List<InformacionPersonal> obtenerInfoResponsables() {
		List<Cuenta> cuentasResponsables = new ArrayList<>();
		List<InformacionPersonal> infoResposables = new ArrayList<>();
		
		// Genera una lista con las cuentas de los responsables de áreas
		List<Cuenta> cuentas = genericSearchBs.findAll(Cuenta.class);
		for (Cuenta cuenta : cuentas) {
			if(cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_BIBLIOTECA.getValor() || cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_CELEX.getValor() 
					|| cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_DENTALES.getValor() || cuenta.getIdPerfil() == PerfilEnum.ADMINISTRADOR_FOTOCOPIADO.getValor()) {
				cuentasResponsables.add(cuenta);
			}
		}
		
		// Genera una lista con la info personal de cada responsable de área
		for (Cuenta cuenta : cuentasResponsables) {
			if(cuenta.getEstatus()) {
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
	 * @param idCuenta id de la cuenta asignada a un responsable
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
}
