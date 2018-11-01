<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>Inscription</title>

<form method="post" action="">
	<h1>Inscription</h1>
	<div class="form-group">
		<label for="firstName">Prénom: </label>
		<input type="text" class="form-control" id="firstName" name="firstName" value="<c:out value="${ user.firstName }" />"/> 
		<c:if test="${ ctrl.msgFirstname != null }">
			<small id="firstNameHelp" class="form-text text-danger">${ ctrl.msgFirstname }</small>
		</c:if>
	</div>

	<div class="form-group">
		<label for="lastName">Nom: </label>
		<input type="text" class="form-control" id="lastName" name="lastName" value="${ user.lastName }"/>
		<c:if test="${ ctrl.msgLastname != null }">
			<small id="lastNameHelp" class="form-text text-danger">${ ctrl.msgLastname }</small>
		</c:if>
	</div>
	
	<div class="form-group">
		<label for="email">Email: </label>
		<input type="email" class="form-control" id="email" name="email" value="${ user.email }"/>
		<c:if test="${ ctrl.msgEmail != null }"> 
			<small id="emailHelp" class="form-text text-danger">${ ctrl.msgEmail }</small>
		</c:if>
	</div>
	
	<div class="form-group">
		<label for="birthdate">Date de naissance: </label>
		<input type="date" class="form-control" id="birthdate" name="birthdate" value="${ user.birthdate }"/>
		<c:if test="${ ctrl.msgBirthDate != null }"> 
			<small id="birthDateHelp" class="form-text text-danger">${ ctrl.msgBirthDate }</small>
		</c:if>
	</div>
	
	<button class="btn btn-primary" type="submit">Enregistrement</button>
</form>