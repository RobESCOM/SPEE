$(function() {
	dataTableEMETH.createSortedDataTable("tblServicios", 1, "desc");
});

function myclickDialogBaja(idServicio) {
	$("#bajaServicio").removeClass("hidden");
	var action = $("#hdnUrlAction").val();
	action = action + idServicio;
	$("#frmBajaServicio").attr("action", action);
	$.publish("showDlgBaja");
}

function closeBajaDlgBaja() {
	$.publish("closeDlgBaja");
}

function myclickDialogReact(idServicio) {
	$("#reactivaServicio").removeClass("hidden");
	var action = $("#hdnUrlActionReact").val();
	action = action + idServicio;
	$("#frmRectivaServicio").attr("action", action);
	$.publish("showDlgReact");
}

function closeBajaDlgReact() {
	$.publish("closeDlgReact");
}