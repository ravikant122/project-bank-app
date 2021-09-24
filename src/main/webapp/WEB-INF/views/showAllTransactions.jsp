<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>transactionId</th>
				<th>fromAccountNumber</th>
				<th>toAccountNumber</th>
				<th>amount</th>
				<th>transactionType</th>
				<th>status</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${transactions}" var="transaction">
				<tr>
					<td>${transaction.transactionId}</td>
					<td>
						<c:if test="${transaction.fromAccountNumber==null}" >
							self
						</c:if>
						<c:if test="${transaction.fromAccountNumber!=null}" >
							${transaction.fromAccountNumber}
						</c:if>
					</td>
					<td>
						<c:if test="${transaction.toAccountNumber==null}" >
							self
						</c:if>
						<c:if test="${transaction.toAccountNumber!=null}" >
							${transaction.toAccountNumber}
						</c:if>
					</td>
					<td>${transaction.amount}</td>
					<td>${transaction.transactionType}</td>
					<td>${transaction.status}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul>
		<li>
			<a href="/bankapp/customer/getAllCustomers">Show all Customer</a>
		</li>
		<li>
			<a href="/bankapp/customer/addCustomer">Add new Customer</a>
		</li>
		<li>
			<a href="withdrawMoney">Withdraw Money</a>	
		</li>
		<li>
			<a href="depositMoney">Deposit Money</a>
		</li>
		<li>
			<a href="transferMoney">Transfer Money</a>	
		</li>
	</ul>
</body>
</html>