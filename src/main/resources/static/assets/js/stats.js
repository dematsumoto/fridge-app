var expiredItems = 0;
var expireSoon = 0;
var stillGoodItems = 0;
var pieData = [];

function getFridgeOverview(){
	var overview;
	$.getJSON( "/fridgeStats/overview")
    .done(function(data){
	overview = data;
	expiredItems = data.expiredItems;
	expireSoon = data.expireSoonItems;
	stillGoodItems = data.stillGoodItems;
	$('#expired-number').html(expiredItems);
	$('#exp-soon-number').html(expireSoon);
	pieData = [expiredItems, expireSoon, stillGoodItems];
	initOverviewChart();
	})
}

function initOverviewChart(){
	var ctx = document.getElementById("overviewChart");
	var overviewChart = new Chart(ctx, {
    type: 'doughnut',
    data: retrieveDataSet()
	});
}

function retrieveDataSet(){
	var dataSet = {
		labels: ['Expired', 'Soon','Good'],
	    datasets: [{
	    	data: pieData,
	    	labels: 'Fridge Overview',
	    	backgroundColor: [
	    		'rgba(235, 94, 40, 0.7)',
	        	'rgba(243, 187, 69, 0.7)',
	        	'rgba(122, 194, 154, 0.7)'
	    	],
	    	borderColor: [
	        	'rgba(235, 94, 40, 1)',
	        	'rgba(243, 187, 69, 1)',
	        	'rgba(122, 194, 154, 1)'
	    	]
	    }]
	}
	return dataSet;
}




