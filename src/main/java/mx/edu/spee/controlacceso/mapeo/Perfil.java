package mx.edu.spee.controlacceso.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Catalogo;
import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tau01_perfil")
public class Perfil implements Modelo, Catalogo, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1261786304467427786L;

	public enum PerfilEnum {
		SUBDIRECTOR(1, ""),

		ADMINISTRADOR_CELEX(2, ""),

		ADMINISTRADOR_DENTALES(3, ""),

		ADMINISTRADOR_BIBLIOTECA(4, ""),

		ADMINISTRADOR_FOTOCOPIADO(5, ""),

		ENCARGADO_CAJA(6, ""),

		CONTADOR(7, ""),

		ALUMNO(8, "Alumno"),

		TRABAJADOR(9, ""),

		EXTERNO(10, ""),

		DEFAULT(11, "Perfil Default"),
		
		BAJA(12, "Perfil Baja");

		private Integer valor;

		private String nombre;

		private PerfilEnum(Integer valor, String nombre) {
			this.valor = valor;
			this.nombre = nombre;
		}

		public Integer getValor() {
			return valor;
		}

		public String getNombre() {
			return nombre;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_perfil")
	private Integer id;

	@Column(name = "nb_perfil")
	private String nombre;

	@Column(name = "ds_perfil")
	private String descripcion;

	@Column(name = "st_activo")
	private Boolean activo;

	public Perfil() {
		super();
	}

	public Perfil(Integer id, String nombre, String descripcion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

}