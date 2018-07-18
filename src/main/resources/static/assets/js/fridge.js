var itemList = null;
var currentItem = null;

function getAllItems(){
	$('#table-all-items tr:gt(0)').remove();
	var items;
	$.getJSON( "/fridge")
	    .done(function(data){
		items = data;
		itemList = data;
		if (!items.length > 0){
            emptyFridgeNotification();
            return;
		}
        $.each(items, function(i,item) {
        	var itemName = item.name;
        	$("#table-all-items").append("<tr bgcolor=" + bgColorByStatus(item.status) + ">" 
        		+ "<td class=\"name\">" + item.name + "</td>"
        		+ "<td>" + item.startDate + " </td>"
        		+ "<td>" + item.validUntilDate + "</td>"
        		+ "<td>"
        		+ generateEditIconTableCell()
        		+ generateDeleteIconTableCell())
        		+ "</td></tr>";
        });
	})
	.fail(function(response){
	    errorNotification(response.responseJSON.message);
	});

	function generateEditIconTableCell(){
		var editIconHtml = [];
		editIconHtml.push(
			"<a href=\"#\" onclick=\"editItemModal(this)\">",
			"<i class=\"ti-pencil icon-medium icon-black\">",
			"</i></a>"
			);
		return editIconHtml.join("");
	}

	function generateDeleteIconTableCell(){
	var DeleteIconHtml = [];
	DeleteIconHtml.push(
			"<a href=\"#\" onclick=\"deleteRequest(this)\">",
			"<i class=\"ti-trash icon-medium icon-black\">",
			"</i></a>"
		);
	return DeleteIconHtml.join("");
	}
}

function bgColorByStatus(status){
	var color;
	if (status == "Good"){
		color = "#AAD8BE";
	}
	else if (status == "Expired"){
		color = "#F29876";
	}
	else {
		color = "#F8D999";
	}

	return color;
}

function errorNotification(message){
        $.notify({
            icon: 'ti-close', message: message}, {
            type: 'danger', timer: 2000
            });
};

function emptyFridgeNotification(){
            $.notify({
                icon: 'ti-info-alt', message: "Fridge is empty"}, {
                type: 'info', timer: 2000
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
	getFridgeOverview();	
}

function successNotification(message){
    $.notify({icon: 'ti-check', message: message},{
                    type: 'success', timer: 2000
                });
}

function deleteRequest(trashIcon){
	var tableIndex = $(trashIcon).closest('tr').index();
	item = itemList[tableIndex];
	$.ajax({
    url: '/fridge/item/' + item.id + '/delete',
    type: 'DELETE',
    success: function(result) {
        successNotification(item.name + " removed from fridge!");
        getAllItems();
        getFridgeOverview();
    }
});
}

function postItemForUpdate(item){
	return $.ajax({
    type: "POST",
    url: "/fridge/update",
    data: item,
    contentType: "application/json; charset=utf-8",
    dataType: "json"
	})
}

function updateItem(item) {
	postItemForUpdate(item)
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


//Form submition (New Item)
(function() {
	document.addEventListener( "DOMContentLoaded", function() {
		var form = document.getElementById( "addItemForm" );
		form.addEventListener("submit", function(e) {
			e.preventDefault();
			var item = toJSONString(this);
			addNewItem(item);
		}, false);
	});
})();


//Form submition (Edit Item)
(function() {
	document.addEventListener( "DOMContentLoaded", function() {
		var form = document.getElementById("editItemForm");
		form.addEventListener("submit", function(e) {
			e.preventDefault();
			var item = toJSONString(this);

			//workaround to append id to payload
			let itemObj = JSON.parse(item);
			itemObj.id = currentItem.id;
			let itemStr = JSON.stringify(itemObj);

			updateItem(itemStr);
			$('#itemEditModal').modal('hide');
		}, false);
	});
})();

function toJSONString(form) {
	var obj = {};
	var elements = form.querySelectorAll( "input" );
	for( var i = 0; i < elements.length; ++i ) {
		var element = elements[i];
		var name = element.name;
		var value = element.value;

		if (name) {
			obj[name] = value;
		}
	}
	obj.active = true;
	return JSON.stringify(obj);
}

function editItemModal(item){
	var rowIndex = $(item).closest('tr').index();
	currentItem = itemList[rowIndex];
	itemTableRow = document.getElementById("table-all-items").rows[rowIndex+1];


	var nameCell = itemTableRow.cells[0].innerText;
	var startDateCell = itemTableRow.cells[1].innerText;
	var expiryDateCell = itemTableRow.cells[2].innerText;

	//set input fileds from table values
	document.getElementById("editName").value = nameCell;
	document.getElementById("editSD").value = startDateCell;
	document.getElementById("editED").value = expiryDateCell;

	$('#itemEditModal').modal();
}


$(function() {
	$( ".datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
});

function howToModal(item){
	$('#howToModal').modal();
}