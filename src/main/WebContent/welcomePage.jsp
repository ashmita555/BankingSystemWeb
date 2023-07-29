<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<style>
		div {
		 	position: relative;
		 	align: center;
		 }
	</style>
</head>
<body>
	<br>
	<form action="loginPage.jsp" method="post" target="mainFrame">
		<div style="position:absolute; top:40px; right:40px;"><input type="submit" value="Log Out" align="right"/></div>
	</form>
	<br><br>
	<div align="center">
		<table cellspacing="10" cellpadding="10">
			<tr>
				<td><a href="depositToAccountPage.jsp" target="mainFrame"><img alt="" src="images/ClickHere.png" height="25px" width="25px"></a></td>
				<td>Deposit Amount</td>
			</tr>
			<tr>
				<td><a href="withdrawFromAccountPage.jsp" target="mainFrame"><img alt="" src="images/ClickHere.png" height="25px" width="25px"></a></td>
				<td>Withdraw Amount</td>
			</tr>
			<tr>
				<td><a href="fundsTransferPage.jsp" target="mainFrame"><img alt="" src="images/ClickHere.png" height="25px" width="25px"></a></td>
				<td>Transfer to another account</td>
			</tr>
			<tr>
				<td><a href="SingleAssociateDetails" target="mainFrame"><img alt="" src="images/ClickHere.png" height="25px" width="25px"></a></td>
				<td>Get Account Details</td>
			</tr>
			<tr>
				<td><a href="customerCarePage.jsp" target="mainFrame"><img alt="" src="images/ClickHere.png" height="25px" width="25px" ></a></td>
				<td>Customer Window</td>
			</tr>
		</table>
	</div>
	
</body>
</html>