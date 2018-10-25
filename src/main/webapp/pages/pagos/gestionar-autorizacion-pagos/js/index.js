$(function() {
	dataTableEMETH.createSortedDataTable("tblPagosPorAutorizar", 1, "desc");
});

function corteCaja() {
	var action = $("#hdnUrlAction").val();
	action = action;
	$("#frmCorteCaja").attr("action", action);
	$.publish("showDlgCorteCaja");
}

function closeDlgCorte() {
	$.publish("closeDlgCorte");
}