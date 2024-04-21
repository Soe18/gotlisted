<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gotlisted: To Do List</title>
</head>
<body>
	<h1>To Do List of <%=session.getAttribute("user_name")%></h1>
	
	<%if((int)request.getAttribute("messageType") == 1) {%>
		<p> Aggiunto con successo nuovo elemento alla lista </p>
	<%} else if ((int)request.getAttribute("messageType") == 4) {%>
		<p> Errore, qualcosa è andato storto... </p>
	<%} %>
	
	
	<!-- Create new todoitem -->
	<form action="./additem" method="post">
		<input type="text" name="title" id="title" required>
		<input type="text" name="descr" id="descr" required>
		<button type="submit">Invia</button>
	</form>
	
	<ul> <c:forEach items="${todolist}" var="todoitem">
		<li> ${todoitem.title} </li>	
	</c:forEach> </ul>
</body>
</html>