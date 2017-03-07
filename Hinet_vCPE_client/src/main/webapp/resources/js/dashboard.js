$(document).ready(function() {
	bindEvents();
	showStatus();
	chartInfo();
	
	setInterval(showStatus,5000);// 注意函数名没有引号和括弧！
	
})
	
function bindEvents() {
	
	
}


function showStatus() {
	$('#tableDashboard').bootstrapTable({
		url : 'showStatus',
		silent: true,
		columns : [
		           
		           	{
		           		field : 'portName',
		           		title : 'Port名稱:',
		           		halign : 'left'
		           	},
		           	{
		           		field : 'onlineStatus',
		           		title : '連線',
		           		halign :'left'
		           	},
		           	{
		           		field : 'downloadRate',
		           		title : '下載速率:',
		           		halign : 'left'
		           	},
		           	{
		           		field : 'uploadRate',
		           		title : '上傳速率:',
		           		halign : 'left'
		           	}
		           	
	           	  ]
	
	});
	
	$('#tableDashboard').bootstrapTable('refresh');
}

function chartInfo() {
	
	$.get('showStatus').success(function(data){
		var ctx = document.getElementById('downloadRateChart').getContext('2d');
		var downloadRateChart = new Chart(ctx, {
		  type: 'line',
		  data: {
		    labels: [(new Date()).toLocaleString()],
		    datasets: [{
			      label: data[0].portName,
			      data: [data[0].downloadRate],
			      backgroundColor: "rgba(151,187,205,0.0)",
			      borderColor : "rgba(258,188,38,1)", 
		    }, {
			      label: data[1].portName,
			      data: [data[1].downloadRate],
			      backgroundColor: "rgba(38,198,238,0.0)",
			      borderColor : "rgba(38,198,238,1)"
		    }, {
			      label: data[2].portName,
				  data: [data[2].downloadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(224, 37, 0)" 	
		    }, {
			      label: data[3].portName,
				  data: [data[3].downloadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(94, 158, 31)" 		
		    }, {
			      label: data[4].portName,
				  data: [data[4].downloadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(215, 9, 212)" 	
		    }, {
			      label: data[5].portName,
				  data: [data[5].downloadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(8, 0, 168)" 	
		    }]
		  }
		});
	
		setInterval(function() {
			$.get('showStatus').success(function(data){
				if (downloadRateChart.data.datasets[0].data.length < 5){
					downloadRateChart.data.labels.push((new Date()).toLocaleString());
					downloadRateChart.data.datasets[0].data.push(data[0].downloadRate);
					downloadRateChart.data.datasets[1].data.push(data[1].downloadRate);
					downloadRateChart.data.datasets[2].data.push(data[2].downloadRate);
					downloadRateChart.data.datasets[3].data.push(data[3].downloadRate);
					downloadRateChart.data.datasets[4].data.push(data[4].downloadRate);
					downloadRateChart.data.datasets[5].data.push(data[5].downloadRate);
				
				}else{
					downloadRateChart.data.labels.push((new Date()).toLocaleString());
					downloadRateChart.data.datasets[0].data.push(data[0].downloadRate);
					downloadRateChart.data.datasets[1].data.push(data[1].downloadRate);
					downloadRateChart.data.datasets[2].data.push(data[2].downloadRate);
					downloadRateChart.data.datasets[3].data.push(data[3].downloadRate);
					downloadRateChart.data.datasets[4].data.push(data[4].downloadRate);
					downloadRateChart.data.datasets[5].data.push(data[5].downloadRate);			
					
					downloadRateChart.data.labels.splice(0, 1);
					downloadRateChart.data.datasets[0].data.splice(0, 1);
					downloadRateChart.data.datasets[1].data.splice(0, 1);
					downloadRateChart.data.datasets[2].data.splice(0, 1);
					downloadRateChart.data.datasets[3].data.splice(0, 1);
					downloadRateChart.data.datasets[4].data.splice(0, 1);
					downloadRateChart.data.datasets[5].data.splice(0, 1);
				}
				downloadRateChart.update();
				
			})
			
		}, 5000);
		
		
		var ctx = document.getElementById('uploadRateChart').getContext('2d');
		var uploadRateChart = new Chart(ctx, {
		  type: 'line',
		  data: {
		    labels: [(new Date()).toLocaleString()],
		    datasets: [{
			      label: data[0].portName,
			      data: [data[0].uploadRate],
			      backgroundColor: "rgba(151,187,205,0.0)",
			      borderColor : "rgba(258,188,38,1)", 
		    }, {
			      label: data[1].portName,
			      data: [data[1].uploadRate],
			      backgroundColor: "rgba(38,198,238,0.0)",
			      borderColor : "rgba(38,198,238,1)"
		    }, {
			      label: data[2].portName,
				  data: [data[2].uploadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(224, 37, 0)" 	
		    }, {
			      label: data[3].portName,
				  data: [data[3].uploadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(94, 158, 31)" 		
		    }, {
			      label: data[4].portName,
				  data: [data[4].uploadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(215, 9, 212)" 	
		    }, {
			      label: data[5].portName,
				  data: [data[5].uploadRate],
				  backgroundColor: "rgba(38,198,238,0.0)",
				  borderColor : "rgb(8, 0, 168)" 	
		    }]
		  }
		});
	
		setInterval(function() {
			$.get('showStatus').success(function(data){
				if (uploadRateChart.data.datasets[0].data.length < 5){
					uploadRateChart.data.labels.push((new Date()).toLocaleString());
					uploadRateChart.data.datasets[0].data.push(data[0].uploadRate);
					uploadRateChart.data.datasets[1].data.push(data[1].uploadRate);
					uploadRateChart.data.datasets[2].data.push(data[2].uploadRate);
					uploadRateChart.data.datasets[3].data.push(data[3].uploadRate);
					uploadRateChart.data.datasets[4].data.push(data[4].uploadRate);
					uploadRateChart.data.datasets[5].data.push(data[5].uploadRate);
				
				}else{
					uploadRateChart.data.labels.push((new Date()).toLocaleString());
					uploadRateChart.data.datasets[0].data.push(data[0].uploadRate);
					uploadRateChart.data.datasets[1].data.push(data[1].uploadRate);
					uploadRateChart.data.datasets[2].data.push(data[2].uploadRate);
					uploadRateChart.data.datasets[3].data.push(data[3].uploadRate);
					uploadRateChart.data.datasets[4].data.push(data[4].uploadRate);
					uploadRateChart.data.datasets[5].data.push(data[5].uploadRate);			
					
					uploadRateChart.data.labels.splice(0, 1);
					uploadRateChart.data.datasets[0].data.splice(0, 1);
					uploadRateChart.data.datasets[1].data.splice(0, 1);
					uploadRateChart.data.datasets[2].data.splice(0, 1);
					uploadRateChart.data.datasets[3].data.splice(0, 1);
					uploadRateChart.data.datasets[4].data.splice(0, 1);
					uploadRateChart.data.datasets[5].data.splice(0, 1);
				}
				uploadRateChart.update();
				
			})
			
		}, 5000);
	
	})
	
	
	
}


