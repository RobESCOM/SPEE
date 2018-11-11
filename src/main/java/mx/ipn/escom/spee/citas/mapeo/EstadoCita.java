package mx.ipn.escom.spee.citas.mapeo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tcd02_estado_cita")
public class EstadoCita implements Modelo, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 491272058585178085L;
	
	public enum EstadoCitaEnum {
		
		PENDIENTE(1, "En espera"),
		
		CONCLUIDA(2, "Cita concluida"),
		
		CANCELADA(3, "Dentista cancela"),
		
		INASISTENCIA(4, "Inasistencia");
		
		private Integer id;
		
		private String nombre;
		
		private EstadoCitaEnum(Integer id, String nombre) {
			this.id = id;
			this.nombre = nombre;
		}
		
		public Integer getId() {
			return id;
		}
		
		public String getNombre() {
			return nombre;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_estado_cita")
	private Integer id;
	
	@Column(name = "nb_estado")
	private String nombreEstado;
	
	@Column(name = "ds_descripcion")
	private String descripcion;
	
	@Column(name = "st_activo")
	private Boolean estatus;
	
	@OneToMany(mappedBy= "estadoCita")
	private List<Cita> listCitas;
	
	public EstadoCita() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(Boolean estatus) {
		this.estatus = estatus;
	}

	public List<Cita> getListCitas() {
		return listCitas;
	}

	public void setListCitas(List<Cita> listCitas) {
		this.listCitas = listCitas;
	}
}
