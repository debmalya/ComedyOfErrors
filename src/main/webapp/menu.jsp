<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Sidebar -->
<div class="w3-sidebar w3-bar-block w3-border-right"
	style="display: none" id="mySidebar">
	<button onclick="w3_close()" class="w3-bar-item w3-large">Close
		&times;</button>
	<a href="/index.jsp" class="w3-bar-item w3-button">Add</a> 
	<!--  
	<a href="/delete.jsp" class="w3-bar-item w3-button">Delete</a>
	<a href="/update.jsp" class="w3-bar-item w3-button">Update
	-->
	</a> <a href="/urlList.jsp" class="w3-bar-item w3-button">List</a>
</div>
<div class="w3-teal">
	<button class="w3-button w3-teal w3-xlarge" onclick="w3_open()">☰</button>
	<div class="w3-container"></div>
</div>
<script>
	function w3_open() {
		document.getElementById("mySidebar").style.display = "block";
	}
	function w3_close() {
		document.getElementById("mySidebar").style.display = "none";
	}
</script>