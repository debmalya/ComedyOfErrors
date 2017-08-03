<html>
<head>
<script src="/js/w3.js"></script>
<script src="/js/w3data.js"></script>
<!--   <script src="/js/jquery-3.2.1.min.js"></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<link rel="stylesheet" href="/css/w3.css">
<script type="text/javascript">
			google.charts.load('current', {
				'packages' : [ 'table' ]
			});
			google.charts.setOnLoadCallback(drawTable);

			function drawTable() {
				/*
				var data = new google.visualization.DataTable();
				data.addColumn('string', 'Name');
				data.addColumn('number', 'Salary');
				data.addColumn('boolean', 'Full Time Employee');
				data.addRows([ [ 'Mike', {
					v : 10000,
					f : '$10,000'
				}, true ], [ 'Jim', {
					v : 8000,
					f : '$8,000'
				}, false ], [ 'Alice', {
					v : 12500,
					f : '$12,500'
				}, true ], [ 'Bob', {
					v : 7000,
					f : '$7,000'
				}, true ] ]);
				*/
				var table = new google.visualization.Table(document
						.getElementById('table_div'));
				
				var JSONObject = $.ajax({
		            url: 'http://localhost:8112/api/v1/day', 
		            xhrFields: {
		                // The 'xhrFields' property sets additional fields on the XMLHttpRequest.
		                // This can be used to set the 'withCredentials' property.
		                // Set the value to 'true' if you'd like to pass cookies to the server.
		                // If this is enabled, your server must respond with the header
		                // 'Access-Control-Allow-Credentials: true'.
		                withCredentials: false
		              },
		            dataType: 'json',
		                async: false,
		            type: 'GET',
		            }).responseText;
				
				

				  console.log("JSONObject :" , JSONObject);
				  var data = new google.visualization.DataTable();
				  data.addColumn('string', 'Date');
				  data.addColumn('string', 'Activity'); 
				  for (var i = 0; i < JSONObject.length; i++){
					  
				  }
				  data.addRows(JSONObject);
				  
		          //var data = new google.visualization.DataTable(JSONObject, 0.5);
		          console.log("data :" , data);

				table.draw(data, {
					showRowNumber : true,
					width : '50%',
					height : '50%'
				});
				
				
			}
		</script>
</head>
<body>
	<div
		class="w3-container w3-teal w3-mobile w3-center w3-margin-top w3-margin-left w3-margin-right">
		<h1>Playing with dates</h1>
	</div>
	<div class="w3-container w3-half">
		<form id="dateForm" method="post"
			action="http://localhost:8112/api/v1/day"
			class="w3-container w3-mobile ">
			<label>Date</label> <input class="w3-input w3-border w3-round"
				type="date" id="dayStamp" name="dayStamp" required="required">
			<label>Activity</label>
			<textarea class="w3-input w3-border w3-round" id="activity"
				name="activity" required="required" rows="5"></textarea>
			<input type="submit" value="Store"
				class="w3-btn-block w3-ripple w3-hover-teal" />
		</form>
	</div>
	<div class="w3-container" id="table_div">
		
	</div>
	<footer>
		<div
			class="w3-container w3-teal w3-margin-left w3-margin-right">
			<p align="center">debmalya.jash@gmail.com</p>
		</div>
	</footer>
</body>
</html>
