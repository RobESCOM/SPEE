$(function() {
	var tipoImpresion_ = undefined;
	
	$("#ImpresionCheckId").change(function(){
		var tipoImpresion_ = $("#ImpresionCheckId option:selected").val();
		var numImpresion_ = $("#numeroImpresion").val();
		var nuevoValor;
		if (tipoImpresion_ == "1") {
			$("#divNumero").addClass("hidden");
		} else if (tipoImpresion_ == "2") {
			nuevoValor = numImpresion_ * 5;
		} else if(tipoImpresion_ == "3"){
			nuevoValor = numImpresion_ * 15;
		} else if(tipoImpresion_ == "4"){
			nuevoValor = numImpresion_ * 15;
		} else if(tipoImpresion_ == "5"){
			nuevoValor = numImpresion_ * 30;
		} else if(tipoImpresion_ == "6"){
			nuevoValor = numImpresion_ * 60;
		}
		
		if (tipoImpresion_ != "1") {
			$("#divNumero").removeClass("hidden");
			document.getElementById("texto").innerHTML = "Equivalente a impresiones en negro";
			document.getElementById("nuevoValor").innerHTML = nuevoValor;
		}
	});
	
	$("#numeroImpresion").change(function(){
		var nuevoValor;
		var numeroImpresion_ = $("#numeroImpresion").val();
		if($("#ImpresionCheckId option:selected").val() == 2){
			nuevoValor = numeroImpresion_ * 5;
		} else if($("#ImpresionCheckId option:selected").val() == 3){
			nuevoValor = numeroImpresion_ * 15; 
		} else if($("#ImpresionCheckId option:selected").val() == 4){
			nuevoValor = numeroImpresion_ * 15; 
		} else if($("#ImpresionCheckId option:selected").val() == 5){
			nuevoValor = numeroImpresion_ * 30; 
		} else if($("#ImpresionCheckId option:selected").val() == 6){
			nuevoValor = numeroImpresion_ * 60; 
		}
		
		if($("#ImpresionCheckId option:selected").val() != 1){
			document.getElementById("nuevoValor").innerHTML = nuevoValor;
		}
	});
});

$(function inicio(){
	$("#divNumero").addClass("hidden");
});

/*$(document).ready(function()
		{
		    $("#ms_num").attr('maxlength','6');
		});*/
