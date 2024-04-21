<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>gotlisted: Login</title>
</head>
<body>
<!-- redirect se loggati -->
	<%if((boolean)request.getAttribute("loginError")) {%>
		<p> Errore di accesso </p>
	<%} %>


	<form action="./login" method="post">
	<input type="text" name="username" id="username" required>
	<input type="password" name="password" id="password" required>
	<button type="submit">Login</button>
	</form>
	
	<a href="register">registrati</a>
	
	
</body>
</html>