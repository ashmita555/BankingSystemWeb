<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Opened!</title>
<style>
	div {
	 	background-color: #ffc107;
        text-align: center;
	 	font-size: 36px;
	 	color: white;
	 }
	p {
	 	text-align:center;
	 	font-size: 22px;
	 }
    .button{
		padding: 5px;
		font-size: 12px;
		width: 125px;
		border-radius: 5px;
	}
</style>
</head>
<body>
	<div>Welcome ${sessionScope.account.firstName} ${sessionScope.account.lastName}</div>
		<p> Your details have been successfully registered!</p>
		<p> Your Account Number is:  ${sessionScope.account.accountNo}</p>
		<form action="pinUpdationPage.jsp" method="post" target="mainFrame">
			<table width="100%" >
				<tr>
					<td align="center"><input type="submit" value="Change your PIN" class="button"></td>
				</tr>
			</table>
		</form>
</body>
</html>