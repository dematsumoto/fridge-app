function getAllItems(){
	$('#table-all-items tr:gt(0)').remove();
	var items;
	$.getJSON( "/fridge")
	    .done(function(data){
		items = data;
		if (!items.length > 0){
            emptyFridgeNotification();
            return;
		}
        $.each(items, function(i,item) {
        	var itemName = item.name;
        	var editIconHtml = "<td> <a href=\"#\"><i class=\"ti-pencil icon-medium icon-info\"></i></a>";
			var deleteIconHtml = "<a href=\"#\" onclick=\"deleteRequest(this)\" ><i class=\"ti-trash icon-medium icon-danger\"></i></a> </td></tr>";
        	$("#table-all-items").append("<tr><td class=\"name\">" + item.name + "</td>"
        		+ "<td>" + item.startDate + " </td>"
        		+ "<td>" + item.validUntilDate + "</td>"
        		+ editIconHtml
        		+ deleteIconHtml);
        });

	})
	.fail(function(response){
	    errorNotification(response.responseJSON.message);
	});
}

function errorNotification(message){
        $.notify({
            icon: 'ti-close', message: message}, {
            type: 'danger', timer: 4000
            });
};

function emptyFridgeNotification(){
            $.notify({
                icon: 'ti-info-alt', message: "Fridge is empty"}, {
                type: 'info', timer: 4000
                });
}

function postNewItem(newItem){
	return $.ajax({
    type: "POST",
    url: "/fridge/addItem",
    data: newItem,
    contentType: "application/json; charset=utf-8",
    dataType: "json"
	})
}

function addNewItem(newItem) {
	postNewItem(newItem)
		.done(addItemSuccess)
		.fail((function(response) {
			if (response.status == 503){
				errorNotification("Database unavailable. Try again later");
			}
			else {
			    errorNotification("Error: There is a problem with your item.");
			}
       }));
}

function addItemSuccess(){
    successNotification("Your item has been successfully added to the fridge!");
	var form = document.getElementById("addItemForm");
	form.reset();
	getAllItems();	
}

function successNotification(message){
    $.notify({icon: 'ti-check', message: message},{
                    type: 'success', timer: 4000
                });
}

function deleteRequest(trashIcon){
	var item = $(trashIcon).closest('tr').find('.name').text();
	$.ajax({
    url: '/fridge/' + item,
    type: 'DELETE',
    success: function(result) {
        successNotification(item + " removed from fridge!");
        getAllItems();
    }
});
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
			addNewItem(item);
		}, false);

	});

})();

$(function() {
	$( ".datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
});