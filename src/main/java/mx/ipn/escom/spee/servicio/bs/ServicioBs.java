package mx.ipn.escom.spee.servicio.bs;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.spee.controlacceso.exception.UniqueException;
import mx.ipn.escom.spee.servicio.mapeo.CatalogoServicio;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.dao.GenericDao;

@Service("servicioBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class ServicioBs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4369252223596461223L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GenericDao genericDao;

	@Transactional(rollbackFor = Exception.class)
	public void registrarServicio(CatalogoServicio servicio) throws UniqueException {
		List<CatalogoServicio> servicios = genericSearchBs.findAll(CatalogoServicio.class);
		for (CatalogoServicio catalogoServicio : servicios) {
			if (catalogoServicio.getClave() == servicio.getClave()) {
				throw new UniqueException();
			} else {
				servicio.setEstatus(true);
				genericDao.save(servicio);
			}
		}
	}

	@Transactional(rollbackFor = Exception.class)
	public void editarServicio(CatalogoServicio servicio) throws UniqueException {
		List<CatalogoServicio> servicios = genericSearchBs.findAll(CatalogoServicio.class);
		for (CatalogoServicio catalogoServicio : servicios) {
			if (catalogoServicio.getClave() == servicio.getClave()) {
				throw new UniqueException();
			}
		}
		servicio.setEstatus(true);
		genericDao.updateMerge(servicio);
	}

	@Transactional(rollbackFor = Exception.class)
	public Boolean darBajaServicio(Integer idServicio) {
		CatalogoServicio servicio = genericSearchBs.findById(CatalogoServicio.class, idServicio);
		if (servicio != null) {
			servicio.setEstatus(false);
			genericDao.update(servicio);
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public Boolean reactivarServicio(Integer idServicio) {
		CatalogoServicio servicio = genericSearchBs.findById(CatalogoServicio.class, idServicio);
		if(servicio != null) {
			servicio.setEstatus(true);
			genericDao.update(servicio);
			return true;
		}
		return false;	
	}
}
