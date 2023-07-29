<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Pin</title>
<style>
	.pinTable {
	    border-collapse:separate;
	    border:solid black 1px;
	    border-radius:12px;
	    border-spacing:20px;
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
	<h1 align="center">Update your Pin</h1>
	<br><br><br><br>
	<form name="pinForm" method="post" action="PinUpdate">
		<table align="center" cellpadding="10" class="pinTable">
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
				<td align="center" colspan="2"><input type="submit" id="loginButton" value="Change PIN" class="button"/></td>
			</tr>
		</table>
	</form>
	<br>
	<div class="div2" name="errorMessage">${requestScope.errorMessage}</div>
</body>
</html>