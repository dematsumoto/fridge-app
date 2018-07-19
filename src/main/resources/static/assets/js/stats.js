var expiredItems = 0;
var expireSoon = 0;
var stillGoodItems = 0;
var pieData = [];

var overviewChart;
var isChartInitialized = false;


function getFridgeOverview(){
	$.getJSON( "/fridgeStats/overview")
    .done(function(data){
	expiredItems = data.expiredItems;
	expireSoon = data.expireSoonItems;
	stillGoodItems = data.stillGoodItems;
	$('#expired-number').html(expiredItems);
	$('#exp-soon-number').html(expireSoon);
	$('#expired-number-notif').html(expiredItems);
	pieData = [expiredItems, expireSoon, stillGoodItems];
	createOrUpdateChart(pieData);
	})
}

function createOrUpdateChart(newPieData){
	if (isChartInitialized){
		updateChartData(newPieData);
	}
	else {
		initOverviewChart();
	}
}

function updateChartData(newPieData){
	overviewChart.data.datasets.forEach((dataset) => {
		dataset.data = newPieData;
	});
	overviewChart.update();
}


function initOverviewChart(){
	var ctx = document.getElementById("overviewChart");
	overviewChart = new Chart(ctx, {
	    type: 'doughnut',
	    data: retrieveDataSet(),
	    options: {
	    	legend: {
	    		position: 'bottom'
	    	}
    	}	
	});

	isChartInitialized = true;
}

function retrieveDataSet(){
	var data = {
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
	return data;
}