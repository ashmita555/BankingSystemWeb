<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
	<style>
		.openAccountTable {
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
		select:invalid { 
			color: gray; 
		}
	</style>
</head>
<body>
	<h1 align="center">Account Opening</h1>
	<br><br>
	<form name="OpenAccountForm" method="post" action="OpenAccountServlet">
		<table align="center" cellpadding="10" class="openAccountTable">
			<tr>
				<td><b>First Name</b></td>
				<td><input type="text" name="firstName" id="firstName"  placeholder="Enter first name" pattern="[a-zA-Z]{1,}" oninvalid="setCustomValidity('The First Name must contain only letters eg. John')" onchange="try{setCustomValidity('')}catch(e){}" required></td>
			</tr>
			<tr>
				<td><b>Last Name</b></td>
				<td><input type="text" name="lastName"  placeholder="Enter last name" pattern="[a-zA-Z]{1,}" oninvalid="setCustomValidity('The Last Name must contain only letters eg. William')"  onchange="try{setCustomValidity('')}catch(e){}" required></td>
			</tr>
			<tr>
				<td><b>Account Type</b></td>
				<td>
					<select name="accountType" required>
						<option value="" disabled selected hidden>Select from dropdown</option>
						<option value="Savings">Savings</option>
						<option value="Current">Current</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><b>Opening Balance</b></td>
				<td><input type="text" name="openingBalance"  placeholder="Enter Account Opening Balance" oninvalid="setCustomValidity('Minimum Initial Account balance must be Rs. 1000')" pattern="[1-9][0-9]{3,}"  onchange="try{setCustomValidity('')}catch(e){}" required></td>
			</tr>
			<tr>
				<td align="center" colspan="2"><input type="submit" name="openAccountButton" value="Open Account" class="button"/></td>
			</tr>
		</table>
	</form>
	<div class="div2">${requestScope.errorMessage}</div>
	</body>
</html>