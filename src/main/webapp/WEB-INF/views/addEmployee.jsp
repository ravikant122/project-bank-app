<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<form:form action="addEmployee" method="post" modelAttribute="user">
	<form:hidden path="userId"/>
		<table>
			<tr>
				<td>Enter employee username</td>
				<td>
					<form:input path="username" />
				 	<form:errors path="username" class="error" />
				</td>
			</tr>
			
			<tr>
				<td>Enter employee email</td>
				<td>
					<form:input path="email" />
					<form:errors path="email" class="error" />
				</td>
			</tr>
	
			<tr>
				<td>Enter employee password</td>
				<td>
					<form:input type="password" path="password" />
					<form:errors path="password" class="error" />
				</td>
			</tr>
	
			
			<tr>
				<td>Enter employee phone number</td>
				<td>
					<form:input path="phone" />
				 	<form:errors path="phone" class="error" />
				 </td>
			</tr>
			
			
			<tr>
				<td>Enter employee salary</td>
				<td>
					<form:input type="number" path="salary"/>
					<form:errors path="salary" class="error" />
				 </td>
			</tr>
			
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
</body>
</html>