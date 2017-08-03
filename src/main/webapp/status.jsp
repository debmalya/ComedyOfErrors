
<%
	String lastActionStatus = (String) request.getAttribute("status");
%>
<div class="w3-container w3-mobile  w3-teal w3-center">
	<%=lastActionStatus == null ? "" : lastActionStatus%>
</div>