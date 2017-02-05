var ingredients = [];
var ownedIngredients =[];
var map;

$(document).ready(function() {
	var index = 0;
	update();
	$('#list_form').empty();
    $('#recipe_list').empty();
	initMap();

	$('#next').click(function(e) {
        e.preventDefault();
		if (index < 2) {
			index++;
		}
        findAddress(index, "");
	});

	$('#previous').click(function(e) {
        e.preventDefault();
        if (index > 0) {
            index--;
    	}
        findAddress(index, "");
	});

	$('#submit_recipe').click(function(e) {
		e.preventDefault();
		var arr = [];
        $('#recipe_ingredients input:checked').each(function(){
			var elem = $(this).attr("id").replace(/_/g, ' ');
			elem = elem.slice(0, -1);
            arr.push(elem);
        });
		getRecipes(arr);
	});

	$('#submit_ingredient').click(function(e) {
		e.preventDefault();
        var prod = $('#ingredient').val();
        $('#ingredient').val("");
		ownedIngredients.push(prod);
		update();
	});

	$("#location_form").submit(function(e) {
		e.preventDefault();
		var address = $('#location').val();
		$('#location').val("");
		findAddress(0, address);
	});
});

function initMap() {

    var latlng = new google.maps.LatLng(43.4631, -80.5202);
    var options = {
        zoom: 10,
        center: latlng,
        mapTypeControl: true
    };
    map = new google.maps.Map(document.getElementById("map"), options);
}

function addMarker(addr) {
    var geocoder = new google.maps.Geocoder();
	if (geocoder) {
		geocoder.geocode({ 'address': addr}, function(results, status) {

			var marker = new google.maps.Marker({
				position: results[0].geometry.location,
				map: map
			});
		});
	}
}

function update() {

	$('#produce_form').empty();
	$('#recipe_ingredients').empty();
	for (var i = 0; i < ingredients.length; i++) {
        $('#produce_form').append('<input type="checkbox" name="item" class="item">'
            + ingredients[i]);
        var id = ingredients[i].replace(/ /g, '_');
        $('#recipe_ingredients').append('<input type="checkbox" name="item" class="item" id="' +
            id + '2">' + ingredients[i]);
	}
	for (var i = 0; i < ownedIngredients.length; i++) {
        $('#recipe_ingredients').append('<input type="checkbox" name="item" class="item" id="' +
            id + '2">' + ownedIngredients[i]);
	}
}

function checkProduct(closest) {

    $('#list_form').empty();

    addMarker(closest.location);

    closest.inventory.forEach(function(item) {
        var id = item.text.replace(/ /g, '_');
        $('#list_form').append('<input type="checkbox" name="item" class="item" id="' +
            id + '1">' + item.text + '<br>');
        for (var i = 0; i < ingredients.length; i++) {
            if (ingredients[i] === item.text) {
            	$('#' + id + '1')[0].checked = true;
			}
        }
    });


    $('#provider').html("Products from " + closest.name);

    $('#list_form input').click(function() {
        var id = $(this).attr("id");
        id = id.slice(0, -1);
        var name = id.replace(/_/g, ' ');
        if ($(this).is(":checked")) {
            ingredients.push(name);
            update();
        } else {
            var index = ingredients.indexOf(name);
            if (index >= 0) {
                ingredients.splice(index, 1);
            }
            update();
        }
    });
}

function findAddress(index, address) {

	if (address !== "") {
		address = address.replace(/ /g, '+');

		$.ajax({
			url: "http://localhost:8080/find",
			type: 'POST',
			data: { "addr": address },
			success: checkProduct
		});

	} else {

		$.ajax({
			url: "http://localhost:8080/find",
			type: 'POST',
			data: { "index": index },
			success: checkProduct
		});
	}
}

function getRecipes(arr) {
	var request = "";
	for (var i = 0; i < arr.length; i++) {
		request = request + arr[i] + ',';
    }

	$('#recipe_list').empty();
    $.ajax({
        url: "http://localhost:8080/recipes",
        type: 'GET',
        data: { "ingredients" : request },
        success: function(recipeList) {
        	recipeList.forEach(function(item) {
        		$('#recipe_list').append('<li class="recipe"><a target="_blank" href="' +
					item.url + '">' + item.label + '</a></li>');

			});
        }
    });
}