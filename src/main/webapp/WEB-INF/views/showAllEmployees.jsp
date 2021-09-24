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
				<th>employeeId</th>
				<th>employeeUsername</th>
				<th>employeeEmail</th>
				<th>employeePhone</th>
				<th>salary</th>
				<th>update</th>
				<th>delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${employees}" var="employee">
				<tr>
					<td>${employee.userId}</td>
					<td>${employee.username}</td>
					<td>${employee.email}</td>
					<td>${employee.phone}</td>
					<td>${employee.salary}</td>
					<td><a href="updateEmployee?id=<c:out value="${employee.userId}"/>">update</a></td>
					<td><a href="deleteEmployee?id=<c:out value="${employee.userId}"/>">delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="addEmployee">Add new Employee</a>
</body>
</html>