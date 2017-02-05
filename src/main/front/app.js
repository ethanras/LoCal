$(document).ready(function() {
	var index = 1;

	$('#next').click(function() {
		index++;
		findAddress(index, "");
	});

	$('#previous').click(function() {
		index--;
		findAddress(index, "");
	});

	$('#submit_ingredient').click(function() {
		$.ajax({
			url: "http://localhost:8080/submit",
			type: 'POST',
			data: { "ingredients" : "blah" }
			success: function(recipeList) {
				console.log(recipeList);
			}
		});
	};
*/
	$("#location_form").submit(function(e) {
		e.preventDefault();
		var address = $('#location').val();
		$('#location').val("");
		console.log(address);
		findAdress(0, address);
	});
});	

function findAddress(index, address) {
	if (index === 0) {
		$.ajax({
			url: "http://localhost:8080/find",
			type: 'POST',
			data: { "addr": address },
			success: function(closest) {
				console.log(closest);
			}
		});
	} else {
		$.ajax({
			url: "http://localhost:8080/find",
			type: 'POST',
			data: { "index": index },
			success: function(closest) {
				console.log(closest);
			}
		});
	}
}
