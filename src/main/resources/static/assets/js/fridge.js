function getAllItems(){
	$('#table-all-items tr:gt(0)').remove();
	var items;
	$.getJSON( "/fridge", function(data){
		items = data;
        $.each(items, function(i,item) {
        	var itemName = item.name;
        	var editIconHtml = "<td> <a href=\"#\"><i class=\"ti-pencil icon-medium icon-info\"></i></a>";
			var deleteIconHtml = "<a href=\"#\" onclick=\"deleteRequest()\" ><i class=\"ti-trash icon-medium icon-danger\"></i></a> </td></tr>";
        	$("#table-all-items").append("<tr><td class=\"name\">" + item.name + "</td>" 
        		+ "<td>" + item.startDate + " </td>"
        		+ "<td>" + item.validUntilDate + "</td>"
        		+ editIconHtml
        		+ deleteIconHtml);
        });

	});
}

function postNewItem(newItem){
	console.log("posting item");
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
			if (response.status != 202){
				addItemFail();
			}   
       }));

}

function addItemSuccess(){
	$.notify({
            	icon: 'ti-check',
            	message: "Your item has been successfully added to the fridge!"

            },{
                type: 'success',
                timer: 4000
            });
	var form = document.getElementById("addItemForm");
	form.reset();
	getAllItems();	
}

function addItemFail(){
		$.notify({
            	icon: 'ti-close',
            	message: "Error: There is a problem with your item. "

            },{
                type: 'danger',
                timer: 4000
            });
}

function deleteRequest(){
	var item = $(this);
	console.log(item);
	$.ajax({
    url: '/' + item,
    type: 'DELETE',
    success: function(result) {
        console.log(item + " deleted");
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
			console.log(item);
			addNewItem(item);
		}, false);

	});

})();

$(function() {

	$( ".datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });

});