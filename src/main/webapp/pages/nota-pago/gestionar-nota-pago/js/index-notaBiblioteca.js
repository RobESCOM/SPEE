$(function() {
	var concepto = 0;
	
	$("#numLibros").change(function(){
		var numDias = $("#numDias").val();
		if($("#numLibros").val() == 1){
			concepto = numDias * 6.5;
		} else if($("#numLibros").val() == 2){
			concepto = numDias * 13;
		} else {
			concepto = numDias * 19.5;
		}
		const final = concepto + 0.001;
		document.getElementById("concepto").value = final.toFixed(2);
	});
	
	$("#numDias").change(function(){
		if($("#numLibros").val() == 1){
			concepto = $("#numDias").val() * 6.5;
		} else if($("#numLibros").val() == 2){
			concepto = $("#numDias").val() * 13;
		} else {
			concepto = $("#numDias").val() * 19.5;
		}
		const final = concepto + 0.001;
		document.getElementById("concepto").value = final.toFixed(2);
	});
});

$(function cantidad(){
	
});