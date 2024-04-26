<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it" data-theme="dim">
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/daisyui@4.10.2/dist/full.min.css" rel="stylesheet" type="text/css" />
<script src="https://cdn.tailwindcss.com"></script>
<title>gotlisted: Login</title>
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
	
	<form action="./login" method="post">
	<div class="flex justify-center mt-32">
		<div class="card card-side bg-base-100 shadow-xl w-2/4">
	  	<figure><img src="https://i.imgur.com/cQGM7Zh.jpeg" alt="ToDoList Image"/></figure>
	  		<div class="card-body">
		    <h2 class="card-title">Accedi a gotlisted</h2>    
				<input type="text" placeholder="username" class="input input-bordered w-full max-w-xs my-2" name="username" id="username" required>
				<input type="password" placeholder="password" class="input input-bordered w-full max-w-xs my-2" name="password" id="password" required>
				<p>Devi ancora registrarti? Fallo <a href="register" class="text-accent">qui</a>.</p>
		    <div class="card-actions justify-end">
		      <button class="btn btn-primary">Accedi</button>
		    </div>
		  </div>
		</div>
	</div>
	</form>	
</body>
</html>