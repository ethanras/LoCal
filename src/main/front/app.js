$(document).ready(function() {
	$.ajax("http://localhost:8080/", {
		datatype: 'json',
		success: function(input) {
			console.log(input);
		}
	});
});	
