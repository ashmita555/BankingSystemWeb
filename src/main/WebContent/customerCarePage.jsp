<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer Care</title>
<style>
	.table {
	    border-collapse:separate;
	    border:solid black 1px;
	    border-radius:12px;
	    border-spacing:20px;
	}
	th {
		color:red;
	}
	
	.button{
		padding: 10px;
		font-size: 12px;
		width: 200px;
		border-radius: 5px;
	}
	.div1{
		color: blue;
		text-align: center;
		font-size: 14px;
	}
	.div2{
		color: red;
		text-align: center;
		font-size: 14px;
	}
</style>
</head>
<body>
	<h1 align="center">Customer Window</h1>
	<table cellspacing="10" cellpadding="10" align="center">
		<tr>
			<td><img alt="" src="images/ClickHere.png" height="25px" width="25px" onclick="toggleTableRowDisplayState('changePin')"></td>
			<td>Change Account Pin</td>
		</tr>
		<tr >
			<td ><a href="ChangeAccountStatusServlet" target="mainFrame"><img alt="" src="images/ClickHere.png" height="25px" width="25px" onclick="toggleTableRowDisplayState('"')></a></td>
			<td>Change Account Status</td>
		</tr>
		<tr>
			<td><img alt="" src="images/ClickHere.png" height="25px" width="25px" onclick="toggleTableRowDisplayState('deactivateAccount')"></td>
			<td>Deactivate Account</td>
		</tr>
	</table>
			
	<div id="changePin" style="display: none;">
		<form name="pinForm" method="post" action="PinUpdate">
		<table align="center" cellpadding="10" class="table">
			<tr>
				<td><b>Old Pin</b></td>
				<td><input type="password" name="oldPinText" pattern="[0-9]{4}" required/></td>
			</tr>
			<tr>
				<td><b>New Pin</b></td>
				<td><input type="password" name="newPinText" pattern="[0-9]{4}" required/></td>
			</tr>
			<tr>
				<td><b>Confirm Pin</b></td>
				<td><input type="password" name="confirmPinText" pattern="[0-9]{4}" required/></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" value="Change PIN" class="button"/></td>
			</tr>
		</table>
	</form>
	<br>
	<div class="div2" name="errorMessage">${requestScope.errorMessage}</div>
	</div>
	
	<div id="changeAccountStatusId" align="center" class="div2">
		${requestScope.message}	
		${requestScope.errorMessage}
	</div>
	
	
	<div id="deactivateAccount" style="display: none;">
		<form name="DeactivateAccount" method="post" action="DeactivateAccountServlet">
		<table align="center" cellpadding="10" class="table">
			<tr>
				<td><b>Confirm Your Pin</b></td>
				<td><input type="password" name="confirmPinText" pattern="[0-9]{4}" required/></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" class="button"/></td>
			</tr>
		</table>
	</form>	
	<c:if test="${requestScope.check ==1}">
		${requestScope.message}	
		${requestScope.errorMessage}
	</c:if>
	</div>
	
	<br>
	<form action="welcomePage.jsp" method="post" target="mainFrame">
		<div align="center"><input type="submit" value="Home Screen"></div>
	</form>
	
<script>
	function toggleTableRowDisplayState(rowID){
		var idContainer = ["changePin", "changeAccountStatusId", "deactivateAccount"];
		idContainer.forEach(function(div){
			document.getElementById(div).style.display="none";
		});
		var rowDisplayState = document.getElementById(rowID);
		rowDisplayState.style.display="block";
	}
</script>

</body>
</html>