<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.table {
	border-collapse: separate;
	border: solid black 1px;
	border-radius: 12px;
	border-spacing: 20px;
}

th{
	text-align:center;
	color:red;
}

td {
	text-align:center;
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
	<h1 align="center">Account Details</h1>
	<table align="center" cellpadding="50">
		<tr>
			<td>
				<table align="center" cellpadding="10" class="table">
					<caption><h3>Account</h3></caption>
					<tr>
						<th><b>Account Number</b></th>
						<td>${requestScope.account.accountNo}</td>
					</tr>
					<tr>
						<th><b>First name</b></th>
						<td>${requestScope.account.firstName}</td>
					</tr>
					<tr>
						<th><b>Last Name</b></th>
						<td>${requestScope.account.lastName}</td>
					</tr>
					<tr>
						<th><b>Account Type</b></th>
						<td>${requestScope.account.accountType}</td>
					</tr>
					<tr>
						<th><b>Account Balance</b></th>
						<td>${requestScope.account.accountBalance}</td>
					</tr>
					<tr>
						<th><b>Account Status</b></th>
						<td>${requestScope.account.status}</td>
					</tr>
				</table>
			</td>
			
			<td>
				<table align="center" cellpadding="10" class="table">
					<caption><h3>Transactions History</h3></caption>
						<tr>
							<th>Transaction Id</th>
							<th>Transaction Amount</th>
							<th>Transaction Type</th>
						</tr>
						<c:forEach var="transaction" items="${requestScope.account.transactions}">
							<tr>
								<td>${transaction.transactionId}</td>
								<td>${transaction.amount}</td>
								<td>${transaction.transactionType}</td>
							</tr>
						</c:forEach>
				</table>
			</td>
		<tr>
	</table>
	
	<form action="welcomePage.jsp" method="post">
		<div align="center" style="bottom:20px;"><input type="submit" value="Home Screen"></div>
	</form>

</body>
</html>