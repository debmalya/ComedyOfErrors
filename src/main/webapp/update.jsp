<jsp:include page="header.jsp"></jsp:include>
<body>

	<div class="w3-container w3-teal w3-mobile w3-center">
		<h1>Playing with dates</h1>
	</div>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="w3-container w3-mobile">
		<form id="dateForm" method="post"
			action="http://localhost:8112/api/v1/day"
			class="w3-container w3-mobile">
			<label>Date</label> <input class="w3-input w3-border w3-round"
				type="date" id="dayStamp" name="dayStamp" required="required">
			<label>Activity</label>
			<textarea class="w3-input w3-border w3-round" id="activity"
				name="activity" required="required" rows="5"></textarea>
			<input type="submit" value="Store"
				class="w3-btn-block w3-ripple w3-hover-teal" id="formButton" />
		</form>
		<script>
			$('#formButton').click(submitMultipleForms());
			function submitMultipleForms() {
				$("#dateForm").submit(function() { //Handler for form1 
				});
				$("#returnForm").submit(function() { //Handler for form2
				});
			}
		</script>
		<form id="returnForm" action="index.jsp"></form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>