$(function() {
	dataTableEMETH.createSortedDataTable("tblPagosPorAutorizar", 1, "asc");
});


function myclickDialogCancelacion(idCita) {
	$("#cancelacionServicio").removeClass("hidden");
	var action = $("#hdnUrlAction").val();
	action = action + idCita;
	$("#frmCancelacionCita").attr("action", action);
	$.publish("showDlgCancelacion");
}

function closeDlgCancelacion() {
	$.publish("closeDlgCancelacion");
}
