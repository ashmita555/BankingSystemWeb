<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
	<table cellspacing="10" cellpadding="10" align="center">
		<tr>
			<td><img alt="" src="images/ClickHere.png" height="25px" width="25px" onclick="toggleTableRowDisplayState('singleAccount')"></td>
			<td>Get Specific Account Details</td>
		</tr>
		<tr>
			<td><a href="AdminGetAllAccounts" target="mainFrame" ><img alt="" src="images/ClickHere.png" height="25px" width="25px" onclick="toggleTableRowDisplayState('allAccounts')"></a></td>
			<td>Get Account Details</td>
		</tr>
		<tr>
			<td><img alt="" src="images/ClickHere.png" height="25px" width="25px" onclick="toggleTableRowDisplayState('accountTransaction')"></td>
			<td>Get Account Transactions</td>
		</tr>
	</table>
		
	<div id="singleAccount" style="display:none" align="center">
		<br>
		<h2>Fetch Account Details</h2>
		<form action="AdminGetSingleAccountServlet" method="post" >
			<table class="table">
				<tr>
					<th>Account Number</th>
					<td><input type="text" name="accountNo" pattern="[1-9][0-9]{8}" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Fetch Account Details"></td>
				</tr>
			</table>
		</form>
		<br>
		<div class="div2">${requestScope.errorMessage}</div>
	</div>
	
	<div id="accountTransaction" style="display:none" align="center">
		<br>
		<h2>Fetch Account Transactions</h2>
		<form action="AdminGetSingleAccountTransactionsServlet" method="post" >
			<table class="table">
				<tr>
					<th>Account Number</th>
					<td><input type="text" name="accountNo" pattern="[1-9][0-9]{8}" required></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="Fetch Account Transactions"></td>
				</tr>
			</table>
		</form>
		<br>
		<div class="div2">${requestScope.errorMessage3}</div>
	</div>
	
	<div id="dataContainer" align="center">
		<c:if test="${requestScope.requestSingleAccount == 1}">
			<table align="center" cellpadding="10" class="table">
					<caption><h3>Account Detail</h3></caption>
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
		</c:if>
		
		<c:if test="${requestScope.requestAllAccounts == 1}">
			<table align="center" cellpadding="10" class="table">
					<caption><h3>Account Details</h3></caption>
					<tr>
						<th><b>Account Number</b></th>
						<th><b>First name</b></th>
						<th><b>Last Name</b></th>
						<th><b>Account Type</b></th>
						<th><b>Account Balance</b></th>
						<th><b>Account Status</b></th>
					</tr>
					<c:forEach var="account" items="${requestScope.accountList }">
						<tr>
							<td>${account.accountNo}</td>
							<td>${account.firstName}</td>
							<td>${account.lastName}</td>
							<td>${account.accountType}</td>
							<td>${account.accountBalance}</td>
							<td>${account.status}</td>
						</tr>
					</c:forEach>
				</table>
		</c:if>
		<c:if test="${requestScope.requestAccountTransactions == 1}">
			<table align="center" cellpadding="10" class="table">
					<caption><h3>Account Transactions</h3></caption>
					<tr>
						<th><b>Transaction ID</b></th>
						<th><b>Transaction Amount</b></th>
						<th><b>Transaction Type</b></th>
					</tr>
					<c:forEach var="transaction" items="${requestScope.transactions }">
						<tr>
							<td>${transaction.transactionId}</td>
							<td>${transaction.amount}</td>
							<td>${transaction.transactionType}</td>
						</tr>
					</c:forEach>
				</table>
		</c:if>
		
		<div class="div2">
			${requestScope.errorMessage}
			${requestScope.errorMessage2}
			${requestScope.errorMessage3}
		</div>
	</div>
	
	<form action="loginPage.jsp" method="post" target="mainFrame">
		<div align="center" style="position:relative;top:40px;"><input type="submit" value="Log Out" align="center" style="botttom:20px;"/></div>
	</form>
	
<script>
	function toggleTableRowDisplayState(rowID){
		var idContainer = ["singleAccount", "accountTransaction", "dataContainer"];
		idContainer.forEach(function(div){
			document.getElementById(div).style.display="none";
		});
		var rowDisplayState = document.getElementById(rowID);
		rowDisplayState.style.display="block";
	}
</script>
</body>
</html>