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
	<form:form method="POST" action="/AutoQuiz3000/public/userprofil" modelAttribute="user-form">
		<div class="form-group">
			<form:label  path="firstName" class="col-md-2">Prénom</form:label>
			<form:input class="col-md-5" path="firstName"  type="text" placeholder="Entrer le prénom "/>
			<small id="firstNameHelp" class="form-text text-danger"><form:errors path="firstName" /></small>
		</div>
		<div class="form-group">
			<form:label  path="lastName" class="col-md-2">Nom</form:label>
			<form:input class="col-md-5" path="lastName"  type="text" placeholder="Entrer le nom"/>
			<small id="lastNameHelp" class="form-text text-danger"><form:errors path="lastName" /></small>
		</div>
		<div class="form-group">
			<form:label  path="email" class="col-md-2">Email</form:label>
			<form:input class="col-md-5" path="email"  type="text" placeholder="Entrer l'email"/>
			<small id="emailHelp" class="form-text text-danger"><form:errors path="email" /></small>
		</div>
  		<div class="form-group">
			<form:label path="birthdate" class="col-md-2">Date de naissance</form:label>
			<form:input type="date" path="birthdate" />
			<small id="birthdateHelp" class="form-text text-danger"><form:errors path="birthdate" /></small>
		</div>
		<div class="form-group">
			<form:label path="password" class="col-md-2">Réinitialisation du mot de passe</form:label>
			<form:input type="password" path="password" />
			<small id="passwordHelp" class="form-text text-danger"><form:errors path="password" /></small>
		</div>
		<div class="form-group">
			<form:input type="password" path="confirmPassword" class="offset-md-2" />
			<small id="comfirmPasswordHelp" class="form-text text-danger"><form:errors path="confirmPassword" /></small>
		</div>
		<c:if test="${not empty message}">
			<div class="alert alert-danger" role="alert">
				<c:out value="${message}"></c:out>
			</div>
		</c:if>
		<form:input type="hidden" path="id"/>
		<input type="submit" value="Modifier" class="btn btn-primary"/>
		<a href="/AutoQuiz3000/student/" class="btn btn-secondary">Annuler</a>
	</form:form>
</body>
</html>