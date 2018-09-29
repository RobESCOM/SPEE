$(function() {
	var tipoContacto_ = undefined;

	if (tipoContacto_  == undefined) {
		$("#divNoEmpleado").addClass("hidden");
		$("#divBoleta").addClass("hidden");
	}
	$("#alumnoCheckId").change(function() {
		var tipoContacto_ = $("#alumnoCheckId option:selected").val();
		if (tipoContacto_ == "1") {
			$("#divNoEmpleado").addClass("hidden");
			$("#divBoleta").removeClass("hidden");
		} else if (tipoContacto_ == "2") {
			$("#divBoleta").addClass("hidden");
			$("#divNoEmpleado").removeClass("hidden");
		} else if (tipoContacto_ == "3") {
			$("#divNoEmpleado").addClass("hidden");
			$("#divBoleta").addClass("hidden");
		}
	});
});
