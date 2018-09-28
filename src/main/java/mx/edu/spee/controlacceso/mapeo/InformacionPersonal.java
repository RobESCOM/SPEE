package mx.edu.spee.controlacceso.mapeo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.opensymphony.xwork2.validator.annotations.RegexFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;

import mx.ipn.escom.spee.util.mapeo.Modelo;

@Entity
@Table(name = "tau04_informacion_personal")
public class InformacionPersonal implements Modelo, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_informacion_personal")
	private Integer id;

	@Column(name = "tx_nombre")
	private String nombre;

	@Column(name = "tx_primer_apellido")
	private String primerApellido;

	@Column(name = "tx_segundo_apellido")
	private String segundoApellido;

	@Column(name = "tx_curp")
	private String curp;

	@Column(name = "tx_numero_boleta")
	private String boleta;

	@Column(name = "tx_numero_empleado")
	private String noEmpleado;

	@Column(name = "tx_correo")
	private String correo;

	@Column(name = "tx_celular")
	private String celular;

	@Column(name = "id_cuenta")
	private Integer idCuenta;

	public InformacionPersonal() {
		super();
	}

	@Validations(requiredStrings = {
			@RequiredStringValidator(type = ValidatorType.FIELD, message = "Campo obligatorio") })
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Validations(regexFields = {
			@RegexFieldValidator(regexExpression = "${getText('mx.ipn.escom.spee.regex.nombre')}", type = ValidatorType.FIELD, message = "Caracteres no permitidos") }, requiredStrings = {
					@RequiredStringValidator(type = ValidatorType.FIELD, message = "Campo obligatorio") })
	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	@Validations(regexFields = {
			@RegexFieldValidator(regexExpression = "${getText('mx.ipn.escom.spee.regex.nombre')}", type = ValidatorType.FIELD, message = "Caracteres no permitidos") }, requiredStrings = {
					@RequiredStringValidator(type = ValidatorType.FIELD, message = "Campo obligatorio") })
	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	@Validations(regexFields = {
			@RegexFieldValidator(regexExpression = "${getText('mx.ipn.escom.spee.regex.nombre')}", type = ValidatorType.FIELD, message = "Caracteres no permitidos") }, requiredStrings = {
					@RequiredStringValidator(type = ValidatorType.FIELD, message = "Campo obligatorio") })
	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	@Validations(regexFields = {
			@RegexFieldValidator(regexExpression = "${getText('mx.ipn.escom.spee.regex.mail')}", type = ValidatorType.FIELD, message = "Caracteres no permitidos") }, requiredStrings = {
					@RequiredStringValidator(type = ValidatorType.FIELD, message = "Campo obligatorio") })
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Validations(regexFields = {
			@RegexFieldValidator(regexExpression = "${getText('mx.ipn.escom.spee.telefono')}", type = ValidatorType.FIELD, message = "Caracteres no permitidos") }, requiredStrings = {
					@RequiredStringValidator(type = ValidatorType.FIELD, message = "Campo obligatorio") })
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBoleta() {
		return boleta;
	}

	public void setBoleta(String boleta) {
		this.boleta = boleta;
	}

	public String getNoEmpleado() {
		return noEmpleado;
	}

	public void setNoEmpleado(String noEmpleado) {
		this.noEmpleado = noEmpleado;
	}

	public Integer getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(Integer idCuenta) {
		this.idCuenta = idCuenta;
	}

}
