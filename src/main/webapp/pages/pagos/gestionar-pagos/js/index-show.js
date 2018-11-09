$(function() {
	dataTableEMETH.createSortedDataTable("tblPagosEnviados", 1, "asc");
});

function myClickDialog(idCuenta){
	$("#registroPago").removeClass("hidden");
 	$.publish("showRegistro");
}

function closeBajaDlg() {
 	$.publish("closeRegistro");
}