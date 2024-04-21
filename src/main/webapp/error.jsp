<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gotlisted: Errore di sistema</title>
</head>
<body>
	<h1>Si è rotto tutto!</h1>
	<p>Log di errore:</p><br>
	<p> <%=exception.getMessage() %> </p>
	<a href="./">Ritorna alla Home Page</a>
</body>
</html>