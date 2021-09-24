<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Get Customer</title>
</head>
<body>
	<h5>Enter Customer Id and get Customer</h5>
	<form:form action="getCustomerController" modelAttribute="customerId" method="post">
		<label>Enter Customer Id:</label>
		<input type="text" name="customerId" id="customerId" />
		<input type="submit" />
	</form:form>
</body>
</html>




