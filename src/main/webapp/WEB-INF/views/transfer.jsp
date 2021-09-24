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
	<form:form action="transferMoney" method="post" modelAttribute="transactionObject">
		<table>
			<tr>
				<td>Enter from AccountNumber</td>
				<td>
					<form:input path="fromAccountNumber" />
				 	<form:errors path="fromAccountNumber" class="error" />
				</td>
			</tr>
			<tr>
				<td>Enter to AccountNumber</td>
				<td>
					<form:input path="toAccountNumber" />
				 	<form:errors path="toAccountNumber" class="error" />
				</td>
			</tr>
			<tr>
				<td>Enter transfer amount</td>
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