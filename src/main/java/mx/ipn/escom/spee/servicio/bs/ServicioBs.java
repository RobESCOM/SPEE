package mx.ipn.escom.spee.servicio.bs;

import java.io.Serializable;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;

@Service("servicioBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class ServicioBs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4369252223596461223L;

	@Transactional
	public void registrarServicio(CatalogoServicio servicio) {
		
	}
}
