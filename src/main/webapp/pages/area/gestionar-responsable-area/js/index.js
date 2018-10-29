$(function() {
	dataTableEMETH.createSortedDataTable("tblResponsables", 1, "asc");
});

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