$(function() {
	dataTableEMETH.createSortedDataTable("tblImpresiones", 1, "asc");
});

function myClickDialog(){
	$("#registroPago").removeClass("hidden");
	var action = $("#hdnUrlAction").val();
	$("#frmRegistroImpresiones").attr("action", action);
	$.publish("showRegistro");
}

function closeBajaDlg() {
	$.publish("closeRegistro");
}