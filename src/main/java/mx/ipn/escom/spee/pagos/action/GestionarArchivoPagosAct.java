package mx.ipn.escom.spee.pagos.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.AllowedMethods;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import mx.edu.spee.controlacceso.mapeo.Usuario;
import mx.ipn.escom.spee.action.GeneralActionSupport;
import mx.ipn.escom.spee.action.NombreObjetosSesion;
import mx.ipn.escom.spee.action.SessionManager;
import mx.ipn.escom.spee.area.mapeo.CatalogoArea.CatalogoAreaEnum;
import mx.ipn.escom.spee.pagos.bs.PagoBs;
import mx.ipn.escom.spee.pagos.mapeo.ArchivoPagoDia;
import mx.ipn.escom.spee.pagos.mapeo.PagoSiga;
import mx.ipn.escom.spee.pagos.mapeo.EstadoPago.EstadoPagoEnum;
import mx.ipn.escom.spee.util.Meses;
import mx.ipn.escom.spee.util.Numeros;
import mx.ipn.escom.spee.util.bs.GenericSearchBs;

@Namespace("/pagos")
@AllowedMethods({"Meses" , "showPago"})
public class GestionarArchivoPagosAct extends GeneralActionSupport {

	private static final long serialVersionUID = 1L;

	protected static final String MESES = "meses";
	
	protected static final String SHOWPAGO = "showPago";

	private List<List<ArchivoPagoDia>> listArchivosPago = new ArrayList<List<ArchivoPagoDia>>();

	private List<ArchivoPagoDia> pagosDate = new ArrayList<>();

	private String listAnio;

	private String listMes;

	private PagoBs pagoBs;

	private boolean permisos;
	
	private Integer idUser;

	private Integer idPago;
	
	private PagoSiga pagoSiga;

	@Autowired
	private GenericSearchBs genericSearchBs;

	private Usuario usuarioSel;

	public String index() {
		int anio;
		getUsuarioSel();
		if (usuarioSel != null) {
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			List<ArchivoPagoDia> anioActual = new ArrayList<>();
			List<ArchivoPagoDia> anioUno = new ArrayList<>();
			List<ArchivoPagoDia> anioDos = new ArrayList<>();
			List<ArchivoPagoDia> anioTres = new ArrayList<>();
			List<ArchivoPagoDia> anioCuatro = new ArrayList<>();
			List<ArchivoPagoDia> anioCinco = new ArrayList<>();

			if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_CELEX.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					anio = pagoBs.obtenerAnio(pagado);
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.CELEX.getIdEstatus()) {
						if (anio == Numeros.ANIO_ACTUAL.getValor())
							anioActual.add(pagado);
						else if (anio == Numeros.ANIO_UNO.getValor())
							anioUno.add(pagado);
						else if (anio == Numeros.ANIO_DOS.getValor())
							anioDos.add(pagado);
						else if (anio == Numeros.ANIO_TRES.getValor())
							anioTres.add(pagado);
						else if (anio == Numeros.ANIO_CUATRO.getValor())
							anioCuatro.add(pagado);
						else if (anio == Numeros.ANIO_CINCO.getValor())
							anioCinco.add(pagado);
					}
				}
			} else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_BIBLIOTECA.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					anio = pagoBs.obtenerAnio(pagado);
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.BIBLIOTECA.getIdEstatus()) {
						if (anio == Numeros.ANIO_ACTUAL.getValor())
							anioActual.add(pagado);
						else if (anio == Numeros.ANIO_UNO.getValor())
							anioUno.add(pagado);
						else if (anio == Numeros.ANIO_DOS.getValor())
							anioDos.add(pagado);
						else if (anio == Numeros.ANIO_TRES.getValor())
							anioTres.add(pagado);
						else if (anio == Numeros.ANIO_CUATRO.getValor())
							anioCuatro.add(pagado);
						else if (anio == Numeros.ANIO_CINCO.getValor())
							anioCinco.add(pagado);
					}
				}
			}

			else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_DENTALES.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					anio = pagoBs.obtenerAnio(pagado);
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.DENTALES.getIdEstatus()) {
						if (anio == Numeros.ANIO_ACTUAL.getValor())
							anioActual.add(pagado);
						else if (anio == Numeros.ANIO_UNO.getValor())
							anioUno.add(pagado);
						else if (anio == Numeros.ANIO_DOS.getValor())
							anioDos.add(pagado);
						else if (anio == Numeros.ANIO_TRES.getValor())
							anioTres.add(pagado);
						else if (anio == Numeros.ANIO_CUATRO.getValor())
							anioCuatro.add(pagado);
						else if (anio == Numeros.ANIO_CINCO.getValor())
							anioCinco.add(pagado);
					}
				}
			}

			else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_FOTOCOPIADO
							.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					anio = pagoBs.obtenerAnio(pagado);
					if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.FOTOCOPIADO.getIdEstatus()) {
						if (anio == Numeros.ANIO_ACTUAL.getValor())
							anioActual.add(pagado);
						else if (anio == Numeros.ANIO_UNO.getValor())
							anioUno.add(pagado);
						else if (anio == Numeros.ANIO_DOS.getValor())
							anioDos.add(pagado);
						else if (anio == Numeros.ANIO_TRES.getValor())
							anioTres.add(pagado);
						else if (anio == Numeros.ANIO_CUATRO.getValor())
							anioCuatro.add(pagado);
						else if (anio == Numeros.ANIO_CINCO.getValor())
							anioCinco.add(pagado);
					}
				}
			}

			else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.CONTADOR
					.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					anio = pagoBs.obtenerAnio(pagado);
					if (anio == Numeros.ANIO_ACTUAL.getValor())
						anioActual.add(pagado);
					else if (anio == Numeros.ANIO_UNO.getValor())
						anioUno.add(pagado);
					else if (anio == Numeros.ANIO_DOS.getValor())
						anioDos.add(pagado);
					else if (anio == Numeros.ANIO_TRES.getValor())
						anioTres.add(pagado);
					else if (anio == Numeros.ANIO_CUATRO.getValor())
						anioCuatro.add(pagado);
					else if (anio == Numeros.ANIO_CINCO.getValor())
						anioCinco.add(pagado);
				}
			}

			else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					anio = pagoBs.obtenerAnio(pagado);
					if (anio == Numeros.ANIO_ACTUAL.getValor())
						anioActual.add(pagado);
					else if (anio == Numeros.ANIO_UNO.getValor())
						anioUno.add(pagado);
					else if (anio == Numeros.ANIO_DOS.getValor())
						anioDos.add(pagado);
					else if (anio == Numeros.ANIO_TRES.getValor())
						anioTres.add(pagado);
					else if (anio == Numeros.ANIO_CUATRO.getValor())
						anioCuatro.add(pagado);
					else if (anio == Numeros.ANIO_CINCO.getValor())
						anioCinco.add(pagado);
				}
			}

			else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
					.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					if (pagado.getIdUsuario() == usuarioSel.getId()) {
						anio = pagoBs.obtenerAnio(pagado);
						if (anio == Numeros.ANIO_ACTUAL.getValor())
							anioActual.add(pagado);
						else if (anio == Numeros.ANIO_UNO.getValor())
							anioUno.add(pagado);
						else if (anio == Numeros.ANIO_DOS.getValor())
							anioDos.add(pagado);
						else if (anio == Numeros.ANIO_TRES.getValor())
							anioTres.add(pagado);
						else if (anio == Numeros.ANIO_CUATRO.getValor())
							anioCuatro.add(pagado);
						else if (anio == Numeros.ANIO_CINCO.getValor())
							anioCinco.add(pagado);
					}
				}
			}

			else if (usuarioSel.getPerfilActivo()
					.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.TRABAJADOR.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					if (pagado.getIdUsuario() == usuarioSel.getId()) {
						anio = pagoBs.obtenerAnio(pagado);
						if (anio == Numeros.ANIO_ACTUAL.getValor())
							anioActual.add(pagado);
						else if (anio == Numeros.ANIO_UNO.getValor())
							anioUno.add(pagado);
						else if (anio == Numeros.ANIO_DOS.getValor())
							anioDos.add(pagado);
						else if (anio == Numeros.ANIO_TRES.getValor())
							anioTres.add(pagado);
						else if (anio == Numeros.ANIO_CUATRO.getValor())
							anioCuatro.add(pagado);
						else if (anio == Numeros.ANIO_CINCO.getValor())
							anioCinco.add(pagado);
					}
				}
			}

			else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.EXTERNO
					.getValor()) {
				ArchivoPagoDia archivoPago = new ArchivoPagoDia();
				archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
				pagoArea = genericSearchBs.findByExample(archivoPago);
				for (ArchivoPagoDia pagado : pagoArea) {
					if (pagado.getIdUsuario() == usuarioSel.getId()) {
						anio = pagoBs.obtenerAnio(pagado);
						if (anio == Numeros.ANIO_ACTUAL.getValor())
							anioActual.add(pagado);
						else if (anio == Numeros.ANIO_UNO.getValor())
							anioUno.add(pagado);
						else if (anio == Numeros.ANIO_DOS.getValor())
							anioDos.add(pagado);
						else if (anio == Numeros.ANIO_TRES.getValor())
							anioTres.add(pagado);
						else if (anio == Numeros.ANIO_CUATRO.getValor())
							anioCuatro.add(pagado);
						else if (anio == Numeros.ANIO_CINCO.getValor())
							anioCinco.add(pagado);
					}
				}
			}

			else {
				return NO_AUTORIZADO;
			}

			listArchivosPago.add(anioActual);
			listArchivosPago.add(anioUno);
			listArchivosPago.add(anioDos);
			listArchivosPago.add(anioTres);
			listArchivosPago.add(anioCuatro);
			listArchivosPago.add(anioCinco);

			return INDEX;
		} else {
			return NO_AUTORIZADO;
		}
	}

	public String Meses() {
		getUsuarioSel();
		List<ArchivoPagoDia> pagoArea = new ArrayList<>();
		List<ArchivoPagoDia> ene = new ArrayList<>();
		List<ArchivoPagoDia> feb = new ArrayList<>();
		List<ArchivoPagoDia> mar = new ArrayList<>();
		List<ArchivoPagoDia> abr = new ArrayList<>();
		List<ArchivoPagoDia> may = new ArrayList<>();
		List<ArchivoPagoDia> jun = new ArrayList<>();
		List<ArchivoPagoDia> jul = new ArrayList<>();
		List<ArchivoPagoDia> ago = new ArrayList<>();
		List<ArchivoPagoDia> sep = new ArrayList<>();
		List<ArchivoPagoDia> oct = new ArrayList<>();
		List<ArchivoPagoDia> nov = new ArrayList<>();
		List<ArchivoPagoDia> dic = new ArrayList<>();

		getListAnio();
		getListMes();

		if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_CELEX.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.CELEX.getIdEstatus()) {
					anioMes = pagoBs.obtenerAnioMes(pagado, anio);
					if (anioMes.equals(Meses.ENE.getNombre())) {
						ene.add(pagado);
					} else if (anioMes.equals(Meses.FEB.getNombre())) {
						feb.add(pagado);
					} else if (anioMes.equals(Meses.MAR.getNombre())) {
						mar.add(pagado);
					} else if (anioMes.equals(Meses.ABR.getNombre())) {
						abr.add(pagado);
					} else if (anioMes.equals(Meses.MAY.getNombre())) {
						may.add(pagado);
					} else if (anioMes.equals(Meses.JUN.getNombre())) {
						jun.add(pagado);
					} else if (anioMes.equals(Meses.JUL.getNombre())) {
						jul.add(pagado);
					} else if (anioMes.equals(Meses.AGO.getNombre())) {
						ago.add(pagado);
					} else if (anioMes.equals(Meses.SEP.getNombre())) {
						sep.add(pagado);
					} else if (anioMes.equals(Meses.OCT.getNombre())) {
						oct.add(pagado);
					} else if (anioMes.equals(Meses.NOV.getNombre())) {
						nov.add(pagado);
					} else if (anioMes.equals(Meses.DIC.getNombre())) {
						dic.add(pagado);
					}
				}
			}
		} else if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_BIBLIOTECA.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.BIBLIOTECA.getIdEstatus()) {
					anioMes = pagoBs.obtenerAnioMes(pagado, anio);
					if (anioMes.equals(Meses.ENE.getNombre())) {
						ene.add(pagado);
					} else if (anioMes.equals(Meses.FEB.getNombre())) {
						feb.add(pagado);
					} else if (anioMes.equals(Meses.MAR.getNombre())) {
						mar.add(pagado);
					} else if (anioMes.equals(Meses.ABR.getNombre())) {
						abr.add(pagado);
					} else if (anioMes.equals(Meses.MAY.getNombre())) {
						may.add(pagado);
					} else if (anioMes.equals(Meses.JUN.getNombre())) {
						jun.add(pagado);
					} else if (anioMes.equals(Meses.JUL.getNombre())) {
						jul.add(pagado);
					} else if (anioMes.equals(Meses.AGO.getNombre())) {
						ago.add(pagado);
					} else if (anioMes.equals(Meses.SEP.getNombre())) {
						sep.add(pagado);
					} else if (anioMes.equals(Meses.OCT.getNombre())) {
						oct.add(pagado);
					} else if (anioMes.equals(Meses.NOV.getNombre())) {
						nov.add(pagado);
					} else if (anioMes.equals(Meses.DIC.getNombre())) {
						dic.add(pagado);
					}
				}
			}
		} else if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_DENTALES.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.DENTALES.getIdEstatus()) {
					anioMes = pagoBs.obtenerAnioMes(pagado, anio);
					if (anioMes.equals(Meses.ENE.getNombre())) {
						ene.add(pagado);
					} else if (anioMes.equals(Meses.FEB.getNombre())) {
						feb.add(pagado);
					} else if (anioMes.equals(Meses.MAR.getNombre())) {
						mar.add(pagado);
					} else if (anioMes.equals(Meses.ABR.getNombre())) {
						abr.add(pagado);
					} else if (anioMes.equals(Meses.MAY.getNombre())) {
						may.add(pagado);
					} else if (anioMes.equals(Meses.JUN.getNombre())) {
						jun.add(pagado);
					} else if (anioMes.equals(Meses.JUL.getNombre())) {
						jul.add(pagado);
					} else if (anioMes.equals(Meses.AGO.getNombre())) {
						ago.add(pagado);
					} else if (anioMes.equals(Meses.SEP.getNombre())) {
						sep.add(pagado);
					} else if (anioMes.equals(Meses.OCT.getNombre())) {
						oct.add(pagado);
					} else if (anioMes.equals(Meses.NOV.getNombre())) {
						nov.add(pagado);
					} else if (anioMes.equals(Meses.DIC.getNombre())) {
						dic.add(pagado);
					}
				}
			}
		}

		else if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_FOTOCOPIADO.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.FOTOCOPIADO.getIdEstatus()) {
					anioMes = pagoBs.obtenerAnioMes(pagado, anio);
					if (anioMes.equals(Meses.ENE.getNombre())) {
						ene.add(pagado);
					} else if (anioMes.equals(Meses.FEB.getNombre())) {
						feb.add(pagado);
					} else if (anioMes.equals(Meses.MAR.getNombre())) {
						mar.add(pagado);
					} else if (anioMes.equals(Meses.ABR.getNombre())) {
						abr.add(pagado);
					} else if (anioMes.equals(Meses.MAY.getNombre())) {
						may.add(pagado);
					} else if (anioMes.equals(Meses.JUN.getNombre())) {
						jun.add(pagado);
					} else if (anioMes.equals(Meses.JUL.getNombre())) {
						jul.add(pagado);
					} else if (anioMes.equals(Meses.AGO.getNombre())) {
						ago.add(pagado);
					} else if (anioMes.equals(Meses.SEP.getNombre())) {
						sep.add(pagado);
					} else if (anioMes.equals(Meses.OCT.getNombre())) {
						oct.add(pagado);
					} else if (anioMes.equals(Meses.NOV.getNombre())) {
						nov.add(pagado);
					} else if (anioMes.equals(Meses.DIC.getNombre())) {
						dic.add(pagado);
					}
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.CONTADOR
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);
			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				anioMes = pagoBs.obtenerAnioMes(pagado, anio);
				if (anioMes.equals(Meses.ENE.getNombre())) {
					ene.add(pagado);
				} else if (anioMes.equals(Meses.FEB.getNombre())) {
					feb.add(pagado);
				} else if (anioMes.equals(Meses.MAR.getNombre())) {
					mar.add(pagado);
				} else if (anioMes.equals(Meses.ABR.getNombre())) {
					abr.add(pagado);
				} else if (anioMes.equals(Meses.MAY.getNombre())) {
					may.add(pagado);
				} else if (anioMes.equals(Meses.JUN.getNombre())) {
					jun.add(pagado);
				} else if (anioMes.equals(Meses.JUL.getNombre())) {
					jul.add(pagado);
				} else if (anioMes.equals(Meses.AGO.getNombre())) {
					ago.add(pagado);
				} else if (anioMes.equals(Meses.SEP.getNombre())) {
					sep.add(pagado);
				} else if (anioMes.equals(Meses.OCT.getNombre())) {
					oct.add(pagado);
				} else if (anioMes.equals(Meses.NOV.getNombre())) {
					nov.add(pagado);
				} else if (anioMes.equals(Meses.DIC.getNombre())) {
					dic.add(pagado);
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				anioMes = pagoBs.obtenerAnioMes(pagado, anio);
				if (anioMes.equals(Meses.ENE.getNombre())) {
					ene.add(pagado);
				} else if (anioMes.equals(Meses.FEB.getNombre())) {
					feb.add(pagado);
				} else if (anioMes.equals(Meses.MAR.getNombre())) {
					mar.add(pagado);
				} else if (anioMes.equals(Meses.ABR.getNombre())) {
					abr.add(pagado);
				} else if (anioMes.equals(Meses.MAY.getNombre())) {
					may.add(pagado);
				} else if (anioMes.equals(Meses.JUN.getNombre())) {
					jun.add(pagado);
				} else if (anioMes.equals(Meses.JUL.getNombre())) {
					jul.add(pagado);
				} else if (anioMes.equals(Meses.AGO.getNombre())) {
					ago.add(pagado);
				} else if (anioMes.equals(Meses.SEP.getNombre())) {
					sep.add(pagado);
				} else if (anioMes.equals(Meses.OCT.getNombre())) {
					oct.add(pagado);
				} else if (anioMes.equals(Meses.NOV.getNombre())) {
					nov.add(pagado);
				} else if (anioMes.equals(Meses.DIC.getNombre())) {
					dic.add(pagado);
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				anioMes = pagoBs.obtenerAnioMes(pagado, anio);
				if (pagado.getIdUsuario() == usuarioSel.getId()) {
					if (anioMes.equals(Meses.ENE.getNombre())) {
						ene.add(pagado);
					} else if (anioMes.equals(Meses.FEB.getNombre())) {
						feb.add(pagado);
					} else if (anioMes.equals(Meses.MAR.getNombre())) {
						mar.add(pagado);
					} else if (anioMes.equals(Meses.ABR.getNombre())) {
						abr.add(pagado);
					} else if (anioMes.equals(Meses.MAY.getNombre())) {
						may.add(pagado);
					} else if (anioMes.equals(Meses.JUN.getNombre())) {
						jun.add(pagado);
					} else if (anioMes.equals(Meses.JUL.getNombre())) {
						jul.add(pagado);
					} else if (anioMes.equals(Meses.AGO.getNombre())) {
						ago.add(pagado);
					} else if (anioMes.equals(Meses.SEP.getNombre())) {
						sep.add(pagado);
					} else if (anioMes.equals(Meses.OCT.getNombre())) {
						oct.add(pagado);
					} else if (anioMes.equals(Meses.NOV.getNombre())) {
						nov.add(pagado);
					} else if (anioMes.equals(Meses.DIC.getNombre())) {
						dic.add(pagado);
					}
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.TRABAJADOR
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				anioMes = pagoBs.obtenerAnioMes(pagado, anio);
				if (pagado.getIdUsuario() == usuarioSel.getId()) {
					if (anioMes.equals(Meses.ENE.getNombre())) {
						ene.add(pagado);
					} else if (anioMes.equals(Meses.FEB.getNombre())) {
						feb.add(pagado);
					} else if (anioMes.equals(Meses.MAR.getNombre())) {
						mar.add(pagado);
					} else if (anioMes.equals(Meses.ABR.getNombre())) {
						abr.add(pagado);
					} else if (anioMes.equals(Meses.MAY.getNombre())) {
						may.add(pagado);
					} else if (anioMes.equals(Meses.JUN.getNombre())) {
						jun.add(pagado);
					} else if (anioMes.equals(Meses.JUL.getNombre())) {
						jul.add(pagado);
					} else if (anioMes.equals(Meses.AGO.getNombre())) {
						ago.add(pagado);
					} else if (anioMes.equals(Meses.SEP.getNombre())) {
						sep.add(pagado);
					} else if (anioMes.equals(Meses.OCT.getNombre())) {
						oct.add(pagado);
					} else if (anioMes.equals(Meses.NOV.getNombre())) {
						nov.add(pagado);
					} else if (anioMes.equals(Meses.DIC.getNombre())) {
						dic.add(pagado);
					}
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.EXTERNO
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			pagoArea = genericSearchBs.findByExample(archivoPago);

			String anioMes;
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				anioMes = pagoBs.obtenerAnioMes(pagado, anio);
				if (pagado.getIdUsuario() == usuarioSel.getId()) {
					if (anioMes.equals(Meses.ENE.getNombre())) {
						ene.add(pagado);
					} else if (anioMes.equals(Meses.FEB.getNombre())) {
						feb.add(pagado);
					} else if (anioMes.equals(Meses.MAR.getNombre())) {
						mar.add(pagado);
					} else if (anioMes.equals(Meses.ABR.getNombre())) {
						abr.add(pagado);
					} else if (anioMes.equals(Meses.MAY.getNombre())) {
						may.add(pagado);
					} else if (anioMes.equals(Meses.JUN.getNombre())) {
						jun.add(pagado);
					} else if (anioMes.equals(Meses.JUL.getNombre())) {
						jul.add(pagado);
					} else if (anioMes.equals(Meses.AGO.getNombre())) {
						ago.add(pagado);
					} else if (anioMes.equals(Meses.SEP.getNombre())) {
						sep.add(pagado);
					} else if (anioMes.equals(Meses.OCT.getNombre())) {
						oct.add(pagado);
					} else if (anioMes.equals(Meses.NOV.getNombre())) {
						nov.add(pagado);
					} else if (anioMes.equals(Meses.DIC.getNombre())) {
						dic.add(pagado);
					}
				}
			}
		}

		else
			return NO_AUTORIZADO;

		listArchivosPago.add(ene);
		listArchivosPago.add(feb);
		listArchivosPago.add(mar);
		listArchivosPago.add(abr);
		listArchivosPago.add(may);
		listArchivosPago.add(jun);
		listArchivosPago.add(jul);
		listArchivosPago.add(ago);
		listArchivosPago.add(sep);
		listArchivosPago.add(oct);
		listArchivosPago.add(nov);
		listArchivosPago.add(dic);
		return MESES;
	}

	public String show() {
		getUsuarioSel();
		permisos = false;
		if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_CELEX.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.CELEX.getIdEstatus()) {
					if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
						pagosDate.add(pagado);
					}

				}
			}
		} else if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_BIBLIOTECA.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.BIBLIOTECA.getIdEstatus()) {
					if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
						pagosDate.add(pagado);
					}

				}
			}
		} else if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_DENTALES.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.DENTALES.getIdEstatus()) {
					if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
						pagosDate.add(pagado);
					}

				}
			}
		} else if (usuarioSel.getPerfilActivo()
				.getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ADMINISTRADOR_FOTOCOPIADO.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagado.getCatalogoServicio().getArea().getId() == CatalogoAreaEnum.FOTOCOPIADO.getIdEstatus()) {
					if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
						pagosDate.add(pagado);
					}

				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.CONTADOR
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			permisos = true;
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);
			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
					pagosDate.add(pagado);
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.SUBDIRECTOR
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			permisos = true;
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
					pagosDate.add(pagado);
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.ALUMNO
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			permisos = true;
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
					if (pagado.getIdUsuario() == usuarioSel.getId()) {
						pagosDate.add(pagado);
					}
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.TRABAJADOR
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			permisos = true;
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
					if (pagado.getIdUsuario() == usuarioSel.getId()) {
						pagosDate.add(pagado);
					}
				}
			}
		}

		else if (usuarioSel.getPerfilActivo().getId() == mx.edu.spee.controlacceso.mapeo.Perfil.PerfilEnum.EXTERNO
				.getValor()) {
			ArchivoPagoDia archivoPago = new ArchivoPagoDia();
			permisos = true;
			archivoPago.setIdEstadoPago(EstadoPagoEnum.AUTORIZADO.getIdEstatus());
			List<ArchivoPagoDia> pagoArea = new ArrayList<>();
			pagoArea = genericSearchBs.findByExample(archivoPago);

			int anio = Integer.parseInt(listAnio);
			for (ArchivoPagoDia pagado : pagoArea) {
				if (pagoBs.obtenerPagos(pagado, anio, listMes)) {
					if (pagado.getIdUsuario() == usuarioSel.getId()) {
						pagosDate.add(pagado);
					}
				}
			}
		}

		return SHOW;
	}
	
	public String showPago() {
		getUsuarioSel();
		if(usuarioSel != null) {
			pagoSiga = pagoBs.siga(idUser, idPago);
			return SHOWPAGO;
		}
		return NO_AUTORIZADO;
	}

	public Usuario getUsuarioSel() {
		if (SessionManager.get(NombreObjetosSesion.USUARIO_SESION) != null) {
			usuarioSel = (Usuario) SessionManager.get(NombreObjetosSesion.USUARIO_SESION);
		}
		return usuarioSel;
	}

	public PagoBs getPagoBs() {
		return pagoBs;
	}

	public void setPagoBs(PagoBs pagoBs) {
		this.pagoBs = pagoBs;
	}

	public void setUsuarioSel(Usuario usuarioSel) {
		this.usuarioSel = usuarioSel;
	}

	public List<List<ArchivoPagoDia>> getListArchivosPago() {
		return listArchivosPago;
	}

	public void setListArchivosPago(List<List<ArchivoPagoDia>> listArchivosPago) {
		this.listArchivosPago = listArchivosPago;
	}

	public String getListAnio() {
		if (listAnio != null) {
			listAnio = (String) SessionManager.get(NombreObjetosSesion.ANIO);
		}
		return listAnio;
	}

	public void setListAnio(String listAnio) {
		if (listAnio != null) {
			SessionManager.put(NombreObjetosSesion.ANIO, listAnio);
		}
		this.listAnio = listAnio;
	}

	public List<ArchivoPagoDia> getPagosDate() {
		return pagosDate;
	}

	public void setPagosDate(List<ArchivoPagoDia> pagosDate) {
		this.pagosDate = pagosDate;
	}

	public String getListMes() {
		if (listMes != null) {
			listMes = (String) SessionManager.get(NombreObjetosSesion.MES);
		}
		return listMes;
	}

	public void setListMes(String listMes) {
		if (listMes != null) {
			SessionManager.put(NombreObjetosSesion.MES, listMes);
		}
		this.listMes = listMes;
	}

	public GenericSearchBs getGenericSearchBs() {
		return genericSearchBs;
	}

	public void setGenericSearchBs(GenericSearchBs genericSearchBs) {
		this.genericSearchBs = genericSearchBs;
	}

	public boolean isPermisos() {
		return permisos;
	}

	public void setPermisos(boolean permisos) {
		this.permisos = permisos;
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public Integer getIdPago() {
		return idPago;
	}

	public void setIdPago(Integer idPago) {
		this.idPago = idPago;
	}

	public PagoSiga getPagoSiga() {
		return pagoSiga;
	}

	public void setPagoSiga(PagoSiga pagoSiga) {
		this.pagoSiga = pagoSiga;
	}

	
	
}