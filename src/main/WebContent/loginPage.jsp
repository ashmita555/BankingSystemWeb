<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
	<style>
		.loginTable {
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
<body>
	<h1 align="center">Login To your Account</h1>
	<br><br><br><br>
	<form name="loginForm" method="post" action="LoginServlet">
		<table align="center" cellpadding="10" class="loginTable">
			<tr>
				<td>Account Number:</td>
				<td><input type="text" name="accountNumberText" pattern="[1-9][0-9]{8}" required/></td>
			</tr>
			
			<tr>
				<td>Unique Pin:</td>
				<td><input type="password" name="passwordText" pattern="[0-9]{4}" required/></td>
			</tr>
			
			<tr>
				<td align="center" colspan="2"><input type="submit" id="loginButton" value="Login" class="button"/></td>
			</tr>
		</table>
	</form>
	<br>
	<div class="div2" name="errorMessage">${requestScope.errorMessage}</div>
	<br>
	<div class="div1">
		<table align="center">
			<tr>
				<td><img alt="" src="images/ClickHere.png" height="25px" width="25px" class="rotate90"></td>
				<td><a href="accountOpeningPage.jsp" target="mainFrame">New User? Open An Account.</a></td>
			</tr>
		</table>
	</div>
	<br>
</body>
</html>