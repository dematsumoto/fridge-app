function getAllItems(){
	var items;
	$.getJSON( "/fridge", function(data){
		items = data;
		console.log("Fridge Items: " + JSON.stringify(data[0].name));
		//$("#all-fridge-items").append('<div>' + JSON.stringify(data) + '</div>');
        $.each(items, function(i,item) {
        	$("#table-all-items").append("<tr><td>" + item.name + "</td>" 
        		+ "<td>" + item.startDate + " </td>"
        		+ "<td>" + item.validUntilDate + "</td></tr>");
        });

	});
}

function submitNewItem(){
	$("#addNewItem").submit(function(e){
		console.log("asd123");

    e.preventDefault();

    var data = {}
    var Form = this;

    //Gathering the Data
    //and removing undefined keys(buttons)
    $.each(this.elements, function(i, v){
            var input = $(v);
        data[input.attr("name")] = input.val();
        delete data["undefined"];
    });

    //Form Validation goes here....

    //Save Form Data........
    $.ajax({
        cache: false,
        url : "fridge/addItem",
        type: "POST",
        dataType : "json",
        data : JSON.stringify(data),
        context : Form,
        success : function(callback){
            //Where $(this) => context == FORM
            console.log(JSON.parse(callback));
            $(this).html("Success!");
        },
        error : function(){
            $(this).html("Error!");
        }
    });
	})
}