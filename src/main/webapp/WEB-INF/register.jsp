<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>gotlisted: Register</title>
</head>
<body>
<!-- redirect se loggati -->
	<%if((boolean)request.getAttribute("registerError")) {%>
		<p> Errore di creazione account </p>
	<%} %>


	<form action="./register" method="post">
	<input type="text" name="username" id="username" required>
	<input type="password" name="password" id="password" required>
	<input type="password" name="confirm_password" id="confirm_password" required>
	<button type="submit">Register</button>
	</form>
	
	<a href="login">Login</a>
	
	
</body>
</html>