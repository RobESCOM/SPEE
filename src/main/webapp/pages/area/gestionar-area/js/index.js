$(function() {
	dataTableEMETH.createSortedDataTable("tblAreas", 1, "asc");
});

function myclickDialogBajaArea(idArea) {
	$("#dlgBajaArea").removeClass("hidden");
	var action = $("#hdnUrlAction").val();
	action = action + idArea;
	$("#frmBajaArea").attr("action", action);
	$.publish("showDlgBaja");
}

function closeBajaDlg() {
	$.publish("closeDlgBaja");
}

function myclickDialogReactivaArea(idArea) {
	$("#reactivaArea").removeClass("hidden");
	var action = $("#hdnUrlActionReact").val();
	action = action + idArea;
	$("#frmRectivaArea").attr("action", action);
	$.publish("showDlgReact");
}

function closeReactivaDlg() {
	$.publish("closeDlgReact");
}