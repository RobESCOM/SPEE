package mx.ipn.escom.spee.citas.bs;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.edu.spee.controlacceso.exception.CitaOcupadaException;
import mx.edu.spee.controlacceso.exception.FechaInvalidaException;
import mx.ipn.escom.spee.citas.mapeo.Cita;
import mx.ipn.escom.spee.citas.mapeo.EstadoCita.EstadoCitaEnum;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;
import mx.ipn.escom.spee.util.dao.GenericDao;

@Service("citaBs")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class CitaBs implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private GenericSearchBs genericSearchBs;

	@Autowired
	private GenericDao genericDao;

	public List<Cita> obtenerCitasAlumno(Integer idCuenta) {
		Cita cita = new Cita();
		cita.setIdCuenta(idCuenta);
		List<Cita> listCitas = genericSearchBs.findByExample(cita);
		return listCitas;
	}

	@Transactional(rollbackFor = Exception.class)
	public void registrarCita(Cita cita, Integer idCuenta) throws CitaOcupadaException {
		Cita citaPendiente = new Cita();
		cita.setIdEstado(EstadoCitaEnum.PENDIENTE.getId());
		List<Cita> listCitasPendientes = genericSearchBs.findByExample(citaPendiente);
		if (!listCitasPendientes.isEmpty()) {
			for (Cita citaAux : listCitasPendientes) {
				String fechaAnterior = convertirFechaDateString(citaAux.getFecha());
				String fechaActual = convertirFechaDateString(cita.getFecha());
				if (fechaAnterior.equals(fechaActual) && citaAux.getIdHora() == cita.getIdHora()) {
					throw new CitaOcupadaException();
				}
			}
		}
		cita.setIdEstado(EstadoCitaEnum.PENDIENTE.getId());
		cita.setIdCuenta(idCuenta);
		genericDao.save(cita);
	}

	public Date convertirFechaStringDate(String fecha) throws FechaInvalidaException {
		DateFormat formato = new SimpleDateFormat("dd/MMM/yyyy");
		Date fechaCita = new Date();
		
		try {
			if(fecha.equals("")) {
				throw new FechaInvalidaException();
			}
			fechaCita = formato.parse(fecha);
			return fechaCita;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fechaCita;
	}
	
	public String convertirFechaDateString(Date fecha) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String fechaStr = formato.format(fecha);
		return fechaStr;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void cancelarCita(Integer idCita) {
		Cita cita = genericSearchBs.findById(Cita.class, idCita);
		cita.setIdEstado(EstadoCitaEnum.CANCELADA.getId());
		genericDao.update(cita);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void cancelarCitaDentista(Integer idCita) {
		Cita cita = genericSearchBs.findById(Cita.class, idCita);
		cita.setIdEstado(EstadoCitaEnum.CANCELADA.getId());
		genericDao.update(cita);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void marcarInasistencia(Integer idCita) {
		Cita cita = genericSearchBs.findById(Cita.class, idCita);
		cita.setIdEstado(EstadoCitaEnum.INASISTENCIA.getId());
		genericDao.update(cita);

	}
	
	
}
