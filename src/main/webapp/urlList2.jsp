<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>
<div class="w3-container w3-mobile" id="table_div">
		<input oninput="w3.filterHTML('#id01', '.item', this.value)" placeholder="Search for URL...">
		<table id="id01">
			
			<tr>
				<th>ActualURL</th>
				<th>ShortURl</th>
				<th>Alias</th>
				<th>CreationDate</th>
			</tr>
			<tr w3-repeat="DayList" class="item">
				<td>{{0}}</td>
				<td><a href="/wz/{{1}}">{{1}}</a></td>
				<td>{{2}}</td>
				<td>{{3}}</td>
			</tr>
			
		</table>
		<script>
			w3.getHttpObject("/DayList", myFunction);

			function myFunction(myObject) {
				w3.displayObject("id01", myObject);
			}
		</script>
	</div>
<jsp:include page="footer.jsp"></jsp:include>