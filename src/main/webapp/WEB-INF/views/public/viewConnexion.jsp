<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>Authentication</title>

<h1>Authentication</h1>
<div class="col-md-8">
	<form method="post" action='<c:url value="/public/connection" />'>
		<div class="form-group">
			<label for="email">Email: </label>
			<input type="email" class="form-control" id="email" name="email" value="${ user.email }"/> 
			<small id="emailHelp" class="form-text text-muted text-danger">${ ctrl.msgConnection }</small>
		</div>
		
		<div class="form-group">
			<label for="password">Password: </label>
			<input type="password" class="form-control" id="password" name="password" /> 
		</div>
		
		<button class="btn btn-primary" type="submit">Connexion</button>
	</form>
</div>