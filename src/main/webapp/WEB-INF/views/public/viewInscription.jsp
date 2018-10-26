<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
	<aside class="col-md-4">
	
	</aside>
	<div class="col-md-8">
		<form method="post" action="">
			<div class="form-group">
				<label for="firstName">Prénom: </label>
				<input type="text" class="form-control" id="firstName" name="firstName" value="<c:out value="${ user.firstName }" />"/> 
				<small id="firstNameHelp" class="form-text text-muted">${ ctrl.msgFirstName }</small>
			</div>
			
			<div class="form-group">
				<label for="lastName">Nom: </label>
				<input type="text" class="form-control" id="lastName" name="lastName" value="${ user.lastName }"/> 
				<small id="lastNameHelp" class="form-text text-muted text-danger">${ ctrl.msgLastName }</small>
			</div>
			
			<div class="form-group">
				<label for="email">Email: </label>
				<input type="email" class="form-control" id="email" name="email" value="${ user.email }"/> 
				<small id="emailHelp" class="form-text text-muted text-danger">${ ctrl.msgEmail }</small>
			</div>
			
			<div class="form-group">
				<label for="birthdate">Date de naissance: </label>
				<input type="date" class="form-control" id="birthdate" name="birthdate" value="${ user.birthdate }"/> 
				<small id="birthDateHelp" class="form-text text-muted text-danger">${ ctrl.msgBirthdate }</small>
			</div>
			
			<button class="btn btn-primary" type="submit">Enregistrement</button>
		</form>
	</div>
</div>
