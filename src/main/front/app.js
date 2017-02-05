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
			data: { "ingredients" : "blah" },
			success: function(recipeList) {
				console.log(recipeList);
			}
		});
	});

	$("#location_form").submit(function(e) {
		e.preventDefault();
		var address = $('#location').val();
		$('#location').val("");
		console.log(address);
		findAddress(0, address);
	});
});	

function findAddress(index, address) {

	if (index === 0) {
		address = address.replace(/ /g, '+');

		$.ajax({
			url: "http://localhost:8080/find",
			type: 'POST',
			data: { "addr": address },
			success: function(closest) {
				closest.inventory.forEach(function(item) {
					var name = item.text.replace(/ /g, '_');
					$('#list_form').append('<input type="checkbox" name="item" class="item" id="' +
					name + '">' + item.text);
				});
				$('#provider').val("Products from" + closest.name);

                $('#list_form input').click(function() {
                    if ($(this).is(":checked")) {
                        var name = $(this).attr("id").replace(/_/g, ' ');
                        $('#produce_form').append('<input type="checkbox" name="item" class="item">'
                            + name);
                    }
                });
			}
		});

	} else {

		$.ajax({
			url: "http://localhost:8080/find",
			type: 'POST',
			data: { "index": index },
			success: function(closest) {
				closest.inventory.forEach(function(item) {
					var name = item.text.replace(/ /g, '_');
					$('#list_form').append('<input type="checkbox" name="item" class="item" id="' +
                        name + '">' + name);

				});
				$('#provider').val("Products from" + closest.name);

                $('#list_form input').click(function() {
                    if ($(this).is(":checked")) {
                        var name = $(this).attr("id").replace(/_/g, ' ');
                        $('#produce_form').append('<input type="checkbox" name="item" class="item">'
                            + name);
                    }
                });
			}
		});
	}
}