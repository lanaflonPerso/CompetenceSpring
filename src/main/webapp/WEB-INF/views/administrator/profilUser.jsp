<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Profil de <c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName}"></c:out></title>
</head>
<body>
	<h1>Profil de <c:out value="${user.firstName}"></c:out> <c:out value="${user.lastName}"></c:out></h1>

	<form:form method="POST" action="/AutoQuiz3000/administrator/user" modelAttribute="user-form">
		<div class="form-group">
			<form:label  path="firstName" class="col-md-2">Prénom</form:label>
			<form:input class="col-md-5" path="firstName"  type="text" placeholder="Entrer le prénom "  value="${user.firstName}"/>
			<small id="emailHelp" class="form-text text-danger"><form:errors path="firstName" /></small>
		</div>
		<div class="form-group">
			<form:label  path="lastName" class="col-md-2">Nom</form:label>
			<form:input class="col-md-5" path="lastName"  type="text" placeholder="Entrer le nom" value="${user.lastName}"/>
			<small id="emailHelp" class="form-text text-danger"><form:errors path="lastName" /></small>
		</div>
		<div class="form-group">
			<form:label  path="email" class="col-md-2">Email</form:label>
			<form:input class="col-md-5" path="email"  type="text" placeholder="Entrer l'email" value="${user.email}"/>
			<small id="emailHelp" class="form-text text-danger"><form:errors path="email" /></small>
		</div>
		<div class="form-group">
			<form:label  path="type" class="col-md-2">Type</form:label>
	 		<form:select  path="type">
    			<form:options items="${lstTypeUser}"></form:options>
  			</form:select>
  		</div>	
  		<form:input type="hidden" path="id"/>
		<input type="submit" value="Modifier" class="btn btn-primary"/>
		<a href="/AutoQuiz3000/administrator/user?page=1&max=20" class="btn btn-secondary">Annuler</a>
	</form:form>
		
</body>
</html>