<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	.error{
		color:#EE1313;
	}
</style>
</head>
<body>
	<form:form action="withdrawMoney" method="post" modelAttribute="withdrawObject">
		<table>
			<tr>
				<td>Enter customer accountNumber</td>
				<td>
					<form:input path="accountNumber" />
				 	<form:errors path="accountNumber" class="error" />
				</td>
			</tr>
			<tr>
				<td>Enter withdraw amount</td>
				<td>
					<form:input path="amount" />
				 	<form:errors path="amount" class="error" />
				</td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>		
</body>
</html>