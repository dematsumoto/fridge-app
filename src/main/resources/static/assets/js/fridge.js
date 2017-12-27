function getAllItems(){
	$("#table-all-items").empty();
	var items;
	$.getJSON( "/fridge", function(data){
		items = data;
		//console.log("Fridge Items: " + JSON.stringify(data[0].name));
		//$("#all-fridge-items").append('<div>' + JSON.stringify(data) + '</div>');
        $.each(items, function(i,item) {
        	$("#table-all-items").append("<tr><td>" + item.name + "</td>" 
        		+ "<td>" + item.startDate + " </td>"
        		+ "<td>" + item.validUntilDate + "</td></tr>");
        });

	});
}

function postNewItem(newItem){
	console.log("posting item");
	$.ajax({
    type: "POST",
    url: "/fridge/addItem",
    data: newItem,
    contentType: "application/json; charset=utf-8",
    dataType: "json"
});
	location.reload();
}

(function() {
	function toJSONString( form ) {
		var obj = {};
		var elements = form.querySelectorAll( "input" );
		for( var i = 0; i < elements.length; ++i ) {
			var element = elements[i];
			var name = element.name;
			var value = element.value;

			if( name ) {
				obj[ name ] = value;
			}
		}
		obj.active = true;

		return JSON.stringify( obj );
	}

	document.addEventListener( "DOMContentLoaded", function() {
		var form = document.getElementById( "addItemForm" );
		form.addEventListener( "submit", function( e ) {
			e.preventDefault();
			var item = toJSONString( this );
			console.log(item);
			postNewItem(item);
			//postNewItem(JSON.stringify(item));

		}, false);

	});

})();