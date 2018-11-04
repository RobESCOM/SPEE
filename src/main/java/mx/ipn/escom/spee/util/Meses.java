package mx.ipn.escom.spee.util;

public enum Meses {
	ENE(0,"Enero"),FEB(1,"Febrero"),MAR(2,"Marzo"),ABR(3,"Abril"),MAY(4,"Mayo"),JUN(5,"Junio"),
	JUL(6,"Julio"),AGO(7,"Agosto"),SEP(8,"Septiembre"),OCT(9,"Octubre"),NOV(10,"Noviembre"),DIC(11,"Diciembre");
	
	private Integer idValor;
	
	private String nombre;
	
	private Meses(Integer idValor, String nombre) {
		this.idValor=idValor;
		this.nombre=nombre;
	}

	public Integer getIdValor() {
		return idValor;
	}

	public String getNombre() {
		return nombre;
	}
}
