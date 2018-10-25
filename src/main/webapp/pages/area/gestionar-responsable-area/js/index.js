$(function() {
	dataTableEMETH.createSortedDataTable("tblResponsables", 1, "asc");
});

/*
 * el modulo que se obtiene de las iteraciones se guarda en el label que se pasa
 * como parametro al mensaje de eliminar
 */
function myclickDialog(idCuenta) {
	$("#bajaResponsable").removeClass("hidden");
	var action = $("#hdnUrlAction").val();
	action = action + idCuenta;
	$("#frmBajaResponsable").attr("action", action);
	$.publish("showDlgBaja");
}

function closeBajaDlg() {
	$.publish("closeDlgBaja");
}