<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="menu.jsp"></jsp:include>
<div class="w3-container w3-mobile">
	<form id="dateForm" method="post"
		action="/URLServelet"
		class="w3-container w3-mobile">
		<label>Actual URL</label> <input class="w3-input w3-border w3-round"
			type="url" id="actualURL" name="actualURL" required="required">
		<input type="hidden" name="operation" value="DELETE"> <input
			type="submit" value="Delete"
			class="w3-btn-block w3-ripple w3-hover-teal" id="formButton" />
	</form>
</div>
<jsp:include page="footer.jsp"></jsp:include>