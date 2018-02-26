var expiredItems = 0;
var expireSoon = 0;

function getFridgeOverview(){
	var overview;
	$.getJSON( "/fridgeStats/overview")
    .done(function(data){
	overview = data;
	expiredItems = data.expiredItems;
	expireSoon = data.expireSoonItems;
	$('#expired-number').html(expiredItems);
	$('#exp-soon-number').html(expireSoon);
	})
}