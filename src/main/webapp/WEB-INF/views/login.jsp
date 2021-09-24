<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<h5>Please login with your username and password</h5>

	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
		<font color="red"> <c:out
				value="${SPRING_SECURITY_LAST_EXCEPTION.message }"></c:out>
		</font>
	</c:if>
	<c:url value="/myloginprocessor" var="login" />
	<form:form action="${login}" method="post">
		<label>Username:</label>
		<input type="text" name="username" />
		<br><br>
		<label>Password:</label>
		<input type="text" name="password" />
		<br><br>
		<input type="submit" />
	</form:form>
</body>
</html>


