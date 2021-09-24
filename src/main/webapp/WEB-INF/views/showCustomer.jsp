<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>showing customers</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>Customer Id</th>
				<th>Customer Name</th>
				<th>Customer Email</th>
				<th>Customer Phone</th>
				<th>Customer Address</th>
				<th>Customer Account Number</th>
				<th>Customer Account Balance</th>
			</tr>
		</thead>

		<tbody>
			<c:set var="customer" scope="session" value="${customer}" />
			<tr>
				<td>${customer.customerId}</td>
				<td>${customer.customerName}</td>
				<td>${customer.customerEmail}</td>
				<td>${customer.customerPhone}</td>
				<td>${customer.customerAddress}</td>
				<td>${customer.account.accountNo}</td>
				<td>${customer.account.accountBalance}</td>
			</tr>
		</tbody>
	</table>
	<ul>
		<li>
			<a href="addCustomer">Add new Customer</a>
		</li>
		<li>
			<a href="/bankapp/transaction/showAllTransactions">View all Transactions</a>	
		</li>
		<li>
			<a href="/bankapp/transaction/withdrawMoney">Withdraw Money</a>	
		</li>
		<li>
			<a href="/bankapp/transaction/depositMoney">Deposit Money</a>
		</li>
		<li>
			<a href="/bankapp/transaction/transferMoney">Transfer Money</a>	
		</li>
	</ul>
</body>
</html>