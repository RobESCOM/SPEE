package mx.ipn.escom.spee.pagos.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import mx.ipn.escom.spee.util.dao.GenericDao;

@Repository("catalogoServiciosDao")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class CatalogoServiciosDao extends GenericDao {
	
	private static final long serialVersionUID = 1L;

}
