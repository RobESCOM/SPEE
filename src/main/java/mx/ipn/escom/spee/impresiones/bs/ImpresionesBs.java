package mx.ipn.escom.spee.impresiones.bs;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import mx.edu.spee.controlacceso.exception.UsuarioNoEncontradoException;
import mx.edu.spee.controlacceso.mapeo.Cuenta;
import mx.edu.spee.controlacceso.mapeo.InformacionPersonal;
import mx.ipn.escom.spee.impresiones.exception.ImpresionesInsuficientesException;
import mx.ipn.escom.spee.impresiones.mapeo.CuentaImpresiones;
import mx.ipn.escom.spee.impresiones.mapeo.FhImpresiones;
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
	
	@Autowired
	private GenericBs<FhImpresiones> genericBsImp;

	private CuentaImpresiones cuentaImpresiones;
	
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
		LocalDateTime now = LocalDateTime.now();  
		Calendar myCal = Calendar.getInstance();
		Calendar myHour = Calendar.getInstance();
		myCal.set(Calendar.YEAR, now.getYear());
		myCal.set(Calendar.MONTH, now.getMonthValue());
		myCal.set(Calendar.DAY_OF_MONTH, now.getDayOfMonth());
		myHour.set(Calendar.HOUR, now.getHour());
		myHour.set(Calendar.MINUTE, now.getMinute());
		myHour.set(Calendar.SECOND, now.getSecond());
		Date theDate = myCal.getTime();
		Date theHour = myHour.getTime();
		switch(tipoImpresion) {
			case 2:
				impresion = impresion * Numeros.CINCO.getValor();
				break;
			case 3:
				impresion = impresion * Numeros.QUINCE.getValor();
				break;
			case 4:
				impresion = impresion * Numeros.QUINCE.getValor();
				break;
			case 5:
				impresion = impresion * Numeros.TREINTA.getValor();
				break;
			case 6:
				impresion = impresion * Numeros.SESENTA.getValor();
				break;
		}
		if(nu_impresiones < impresion) {
			throw new ImpresionesInsuficientesException();
		} else {
			usr.setNu_impresiones(nu_impresiones - impresion);
			FhImpresiones fhImpresion = new FhImpresiones(theDate,impresion,usr,theHour);
			genericBsImp.save(fhImpresion);
			genericBs.update(usr);
		}
	}
	
	public boolean agregarImpresiones(int clave, int numeroImp, int tipoUsuario) throws UsuarioNoEncontradoException {
		setCuentaImpresiones(new CuentaImpresiones());
		setCuentaImpresiones(genericSearchBs.findById(CuentaImpresiones.class, clave));
		if(getCuentaImpresiones() == null) {
			Cuenta cuenta = genericSearchBs.findById(Cuenta.class, clave);
			if(cuenta != null) {
				setCuentaImpresiones(new CuentaImpresiones(cuenta, numeroImp));
				genericBs.save(getCuentaImpresiones());
				return true;
			}
			return false;
			
		}
		else {
			getCuentaImpresiones().setNu_impresiones(getCuentaImpresiones().getNu_impresiones()+numeroImp);
			genericBs.update(getCuentaImpresiones());
			return true;
		}
	}
	
	public InformacionPersonal obtenerPersona(Cuenta cuentaImpresiones) {
		InformacionPersonal infoPersonal = new InformacionPersonal();
		infoPersonal.setIdCuenta(cuentaImpresiones.getIdCuenta());
		List<InformacionPersonal> infoPersona = genericSearchBs.findByExample(infoPersonal);
		return infoPersona.get(Numeros.CERO.getValor());
	}

	public CuentaImpresiones getCuentaImpresiones() {
		return cuentaImpresiones;
	}

	public void setCuentaImpresiones(CuentaImpresiones cuentaImpresiones) {
		this.cuentaImpresiones = cuentaImpresiones;
	}
}
