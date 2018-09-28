package mx.ipn.escom.spee.pagos.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tc01_catalogo_area")
public class CatalogoArea implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	public enum CatalogoAreaEnum {

		CELEX(1, "Centro de Lenguas Extrangeras"),

		DENTALES(2, "Servicios Dentales"),

		FOTOCOPIADO(3, "Servicios Fotocopiado"),

		BIBLIOTECA(4, "Servicios Biblioteca");

		private Integer idArea;

		private String nombre;

		private CatalogoAreaEnum(Integer idEstatus, String nombre) {
			this.idArea = idEstatus;
			this.nombre = nombre;
		}

		public Integer getIdEstatus() {
			return idArea;
		}

		public String getNombre() {
			return nombre;
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_catalogo_area")
	private Integer id;

	@Column(name = "nb_area")
	private String nombreArea;

	@Column(name = "ds_descripcion")
	private String descripcion;

	public CatalogoArea() {
		super();
	}

	public CatalogoArea(Integer id, String nombreArea, String descripcion) {
		super();
		this.id = id;
		this.nombreArea = nombreArea;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
