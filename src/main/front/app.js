$(document).ready(function() {
/*
	$.ajax("http://localhost:8080/", {
		datatype: 'json',
		success: function(dogshitFromBryan) {
			console.log(dogshitFromBryan);
		}
	});

	$.ajax({
		url: "http://localhost:8080/",
		type: 'PUT',
		data: { "ingredients" : "blah" }
	});
*/


/*
		var googleAPIKey = "AIzaSyBn9CD8FowWeOi_mf2BfL1eh7_09G9H9bU";
		var source = "256+Philip+Street+Waterloo+ON";
		var destination = "18+Foreht+Crescent+Aurora+ON";
		var url = "https://maps.googleapis.com" + 
					"/maps/api/distancematrix/json?units=metric&origins=" +
					source + "&destinations=" + destination +
					"&key=" + googleAPIKey + "&callback=?";
*/
	$("#location_form").submit(function(e) {
		e.preventDefault();
		var address = $('#location').val();
		$('#location').val("");
		console.log(address);
/*
		$.ajax({
			url: "http://localhost:8080/",
			type: 'PUT',
			data: { "googleURL" : address }
		});
*/
	});
});	
