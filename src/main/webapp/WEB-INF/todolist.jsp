<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" data-theme="light">
<head>
<meta charset="UTF-8">
<title>gotlisted: To Do List</title>
<script src="https://cdn.tailwindcss.com"></script>
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.10.2/dist/full.min.css" rel="stylesheet" type="text/css" />
</head>
<body>


	<div class="navbar bg-[#394053]">
	  <a class="btn btn-ghost text-xl bg-[#6E6362]" href="./">GOTLISTED</a>
	</div>
	
	<div class="">
		<h1>To Do List of <%=session.getAttribute("user_name")%></h1>
	
	<%if((int)request.getAttribute("messageType") == 1) {%>
		<p> Aggiunto con successo nuovo elemento alla lista </p>
	<%} else if ((int)request.getAttribute("messageType") == 4) {%>
		<p> Errore, qualcosa è andato storto... </p>
	<%} %>
	
	<!-- Create new todoitem -->
	<form action="./additem" method="post">
		<input class="input border border-black" type="text" name="title" id="title" required>
		<input class="input border border-black" type="text" name="descr" id="descr" required>
		<button type="submit">Invia</button>
	</form>
	
	<ul> <c:forEach items="${todolist}" var="todoitem">
		<li> 
			<c:if test="${todoitem.done==true}">
				<form class="flex" action="./checkItem" method="post">
					<p class="text flex-1">${todoitem.title}</p>
					<input type="text" name="item_check" value="${todoitem.id}" style="display:none;">
					<button class="btn btn-circle btn-outline flex-1" type="submit"><svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"><path d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z"/></svg></button>
				</form>
			</c:if>
			<c:if test="${todoitem.done==false}">
				<form class="flex" action="./checkItem" method="post">
					<p class="text flex-1">${todoitem.title}</p>
					<input type="text" name="item_check" value="${todoitem.id}" style="display:none;">
					<button class="btn btn-circle btn-outline flex-1" type="submit"><svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" /></svg></button>
				</form>
			</c:if>
		</li>
	</c:forEach> </ul>
	</div>
	
	
</body>
</html>