<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Amount Deposit Page</title>
<style>
table {
	border-collapse: separate;
	border: solid black 1px;
	border-radius: 12px;
	border-spacing: 20px;
}

.button {
	padding: 10px;
	font-size: 12px;
	width: 200px;
	border-radius: 5px;
}

.div1 {
	color: blue;
	text-align: center;
	font-size: 14px;
}

.div2 {
	color: red;
	text-align: center;
	font-size: 14px;
}
</style>
</head>
<body>
	<h1 align="center">Deposit Page</h1>
	<form name="DepositPage" action="DepositToAccountServlet" method="post">
		<table cellspacing="10" cellpadding="10" align="center">
			<tr>
				<td><b>Amount to be deposit:</b></td>
				<td><input type="text" name="amount" pattern="[1-9][0-9]{0,}" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Deposit To Account" class="button"></td>
			</tr>
		</table>
	</form>
	<br>
	<div class="div2"> ${requestScope.errorMessage} </div>
	<div class="div1"> ${requestScope.resultMessage} </div>
	<br>
	<form action="welcomePage.jsp" method="post" target="mainFrame">
		<div align="center" style="float:bottom; margin-bottom:20px;"><input type="submit" value="Home Screen" align="center"/></div>
	</form>
</body>
</html>