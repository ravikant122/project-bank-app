<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>my secure app home page</title>
</head>
<body>
	Home page
	<br>
	<br />
	<a href="/bankapp/customer/getCustomer">get a customers</a>
	<br />
	
	<br />
	<a href="/bankapp/customer/getAllCustomers">get all customers</a>
	<br />
	
	<br />
	<a href="/bankapp/customer/addCustomer">Add Customer</a>
	<br />
	
	<br />
	<a href="/bankapp/transaction/showAllTransactions">Show All Transactions</a>
	<br />
	
	<br />
	<a href="/bankapp/transaction/pendingTransaction">Pending Transaction</a>
	<br />
	
	<br />
	<a href="/bankapp/transaction/withdrawMoney">Withdraw Money</a>
	<br />
	
	<br />
	<a href="/bankapp/transaction/depositMoney">Deposit Money</a>
	<br />
	
	<br />
	<a href="/bankapp/transaction/transferMoney">Transfer Money</a>
	<br />
	
	<br />
	<a href="/bankapp/user/getAllEmployees">Get all employee</a>
	<br />
	
	<br />
	<a href="/bankapp/user/addEmployee">Add Employee</a>
	<br />
	<br />
	
	<form:form action="${pageContext.request.contextPath}/logout" method="post">
		<input type="submit" value="logout">
	</form:form>
</body>
</html>