<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Customer</title>
</head>
<body>
	<h5>Update Customer</h5>
	<form:form action="addCustomerController" method="post" modelAttribute="customer">
	<form:hidden path="customerId"/>
		<table>
			<tr>
				<td>Enter customer name</td>
				<td>
					<form:input path="customerName" />
				 	<form:errors path="customerName" class="error" />
				</td>
			</tr>
			
			<tr>
				<td>Enter customer email</td>
				<td>
					<form:input path="customerEmail" />
					<form:errors path="customerEmail" class="error" />
				</td>
			</tr>
			
			<tr>
				<td>Enter customer phone number</td>
				<td>
					<form:input path="customerPhone" />
				 	<form:errors path="customerPhone" class="error" />
				 </td>
			</tr>
			
			<tr>
				<td>Enter customer address</td>
				<td>
					<form:input path="customerAddress" />
				 	<form:errors path="customerAddress" class="error" />
				 </td>
			</tr>
			
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>