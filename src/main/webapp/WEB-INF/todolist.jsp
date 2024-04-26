<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it" data-theme="dim">
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-type" content="text/html;charset=utf-8" />
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.10.2/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<title>gotlisted: ToDo List</title>
</head>
<body>
	<div class="navbar bg-base-300">
	  <div class="flex-1">
	    <a href="/gotlisted" class="btn btn-ghost text-xl">gotlisted</a>
	  </div>
	  <div class="flex-none"><a href="logoff">
	    <button class="btn btn-square btn-ghost"> 
	    <!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
	    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512" class="inline-block w-5 h-5 stroke-current fill-slate-300"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M377.9 105.9L500.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L377.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1-128 0c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM160 96L96 96c-17.7 0-32 14.3-32 32l0 256c0 17.7 14.3 32 32 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32l-64 0c-53 0-96-43-96-96L0 128C0 75 43 32 96 32l64 0c17.7 0 32 14.3 32 32s-14.3 32-32 32z"/></svg>
	    </button></a>
	  </div>
	</div>
	
<div class="m-8 flex">
	<div class="w-2/5 mr-4">
		<h1 class="text-5xl font-bold mb-8">ToDo List di <span class="text-accent font-extrabold"><%=session.getAttribute("user_name")%></span>.</h1>
		<h1 class="text-2xl mb-2 text-primary">Aggiungi un nuovo elemento alla todo list.</h1>
	<!-- Create new todoitem -->
	<form action="./additem" method="post">
	<label class="form-control w-full my-1">
	  <div class="label">
	    <span class="label-text">Titolo:</span>
	  </div>
	  <input type="text" name="title" id="title" placeholder="Cosa devo fare?" class="input input-bordered w-full" required/>
	</label>
	
	<label class="form-control w-full my-1">
	  <div class="label">
	    <span class="label-text">Descrizione:</span>
	  </div>
	  <textarea class="textarea textarea-bordered" placeholder="Scrivo i dettagli..." name="descr" id="descr" required></textarea>
	</label>
		<button class="btn btn-primary my-4" type="submit">Salva</button>		
	</form>
	</div>
	
	<ul class="w-3/5 ml-4">
		<%if((int)request.getAttribute("messageType") == 1) {%>
			<p class="text-success mb-1"> Aggiunto con successo nuovo elemento alla lista. </p>
		<%}else if((int)request.getAttribute("messageType") == 2) {%>
			<p class="text-success mb-1"> Elemento marcato come completato. </p>
		<%}else if((int)request.getAttribute("messageType") == 3) {%>
			<p class="text-success mb-1"> Elemento marcato come non completato. </p>
		<%}%>
	<c:forEach items="${todolist}" var="todoitem">
		<li> 
			<c:if test="${todoitem.done==true}">
				<form class="flex" action="./checkItem" method="post">
				<div role="alert" class="alert bg-base-300 shadow-lg my-1">
					<input type="text" name="item_check" value="${todoitem.id}" style="display:none;">
					<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
					<button class="btn btn-circle btn-info flex-1" type="submit">
						<svg xmlns="http://www.w3.org/2000/svg" class="h-6 w-6" stroke="currentColor" viewBox="0 0 448 512"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M438.6 105.4c12.5 12.5 12.5 32.8 0 45.3l-256 256c-12.5 12.5-32.8 12.5-45.3 0l-128-128c-12.5-12.5-12.5-32.8 0-45.3s32.8-12.5 45.3 0L160 338.7 393.4 105.4c12.5-12.5 32.8-12.5 45.3 0z"/></svg>
					</button>
					<div>
				    <h3 class="font-bold">${todoitem.title}</h3>
				    <div class="text-xs">${todoitem.descr}</div>
				  </div>
				</div>
				</form>
			</c:if>
			<c:if test="${todoitem.done==false}">
				<form class="flex" action="./checkItem" method="post">
				<div role="alert" class="alert bg-base-300 shadow-lg my-1">
					<input type="text" name="item_check" value="${todoitem.id}" style="display:none;">
					<!--!Font Awesome Free 6.5.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.-->
					<button class="btn btn-circle btn-info btn-outline flex-1" type="submit"></button>
					<div>
				    <h3 class="font-bold">${todoitem.title}</h3>
				    <div class="text-xs">${todoitem.descr}</div>
				  </div>
				</div>
				</form>
			</c:if>
		</li>
	</c:forEach>
	</ul>
</div>
</body>
</html>