package mx.ipn.escom.spee.impresiones.bs;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.ipn.escom.spee.impresiones.exception.ImpresionesInsuficientesException;
import mx.ipn.escom.spee.impresiones.mapeo.CuentaImpresiones;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.bs.GenericBs;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Service("impresionesBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class ImpresionesBs implements Serializable{

	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;
	
	@Autowired
	private GenericBs<CuentaImpresiones> genericBs;

	
	public List<CuentaImpresiones> obtenerCuentasConImpresiones() {	
		List<CuentaImpresiones> listaCuentaImpresiones= genericSearchBs.findAll(CuentaImpresiones.class);
		return listaCuentaImpresiones;
	}
	
	public CuentaImpresiones obtenerCuentaImpresion(Serializable id) {
		CuentaImpresiones usr = genericSearchBs.findById(CuentaImpresiones.class, id);
		return usr;
	}
	
	public InformacionPersonal obtenerUsuario(CuentaImpresiones cuentaImpresion) {
		Cuenta cuenta = genericSearchBs.findById(Cuenta.class,cuentaImpresion.getId().getIdCuenta());
		return obtenerPersona(cuenta);
	}
	
	public void modCantidad(int impresion, int tipoImpresion, Integer idSel) throws ImpresionesInsuficientesException {
		CuentaImpresiones usr = genericSearchBs.findById(CuentaImpresiones.class, idSel);
		int nu_impresiones = usr.getNu_impresiones();
		switch(tipoImpresion) {
			case 2:
				impresion = impresion * 5;
				break;
			case 3:
				impresion = impresion * 15;
				break;
			case 4:
				impresion = impresion * 15;
				break;
			case 5:
				impresion = impresion * 30;
				break;
			case 6:
				impresion = impresion * 60;
				break;
		}
		if(nu_impresiones < impresion) {
			throw new ImpresionesInsuficientesException();
		} else {
			usr.setNu_impresiones(nu_impresiones - impresion);
			genericBs.update(usr);
		}
	}
	
	public InformacionPersonal obtenerPersona(Cuenta cuentaImpresiones) {
		InformacionPersonal infoPersonal = new InformacionPersonal();
		infoPersonal.setIdCuenta(cuentaImpresiones.getIdCuenta());
		List<InformacionPersonal> infoPersona = genericSearchBs.findByExample(infoPersonal);
		return infoPersona.get(Numeros.CERO.getValor());
	}
}
