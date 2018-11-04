<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Profil utilisateur</title>
</head>
<body>
<h1>Profil utilisateur</h1>
<div  class="col-md-8">
	<form:form method="POST" action="/AutoQuiz3000/administrator/user" modelAttribute="user-form">
		<div class="form-group">
			<form:label path="firstName">Prénom</form:label>
			<form:input class="form-control" path="firstName"  type="text" placeholder="Entrer le prénom "/>
			<small id="firstNameHelp" class="form-text text-danger"><form:errors path="firstName" /></small>
		</div>
		<div class="form-group">
			<form:label path="lastName" >Nom</form:label>
			<form:input class="form-control" path="lastName" type="text" placeholder="Entrer le nom"/>
			<small id="lastNameHelp" class="form-text text-danger"><form:errors path="lastName" /></small>
		</div>
		<div class="form-group">
			<form:label path="email" >Email</form:label>
			<form:input class="form-control" path="email"  type="text" placeholder="Entrer l'email"/>
			<small id="emailHelp" class="form-text text-danger"><form:errors path="email" /></small>
		</div>
		<div class="form-group">
			<form:label path="type" >Type</form:label>
	 		<form:select class="form-control" path="type" >
    			<form:options items="${lstTypeUser}"></form:options>
  			</form:select>
  		</div>
  		<div class="form-group">
			<form:label path="birthdate" >Date de naissance</form:label>
			<form:input type="date" path="birthdate" class="form-control"/>
			<small id="birthdateHelp" class="form-text text-danger"><form:errors path="birthdate" /></small>
		</div>
		<div class="form-group">
			<form:label path="password" >Réinitialisation du mot de passe</form:label>
			<form:input type="password" path="password" class="form-control" />
			<small id="passwordHelp" class="form-text text-danger"><form:errors path="password" /></small>
		</div>
		<div class="form-group">
			<form:input type="password" path="confirmPassword" class="form-control" />
			<small id="comfirmPasswordHelp" class="form-text text-danger"><form:errors path="confirmPassword" /></small>
		</div>
		<c:if test="${not empty message}">
			<div class="alert alert-danger" role="alert">
				<c:out value="${message}"></c:out>
			</div>
		</c:if>
		<form:input type="hidden" path="id"/>
		<input type="submit" value="Modifier" class="btn btn-primary"/>
		<a href="/AutoQuiz3000/administrator/user?page=1&max=20" class="btn btn-secondary">Annuler</a>
	</form:form>
	</div>
</body>
</html>