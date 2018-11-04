<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Configuration SMTP</title>
</head>
<body>
	<h1>Configuration du SMTP</h1>
	<form:form method="POST" action="/AutoQuiz3000/administrator/configuresmtp" modelAttribute="email-form">
		<div class="form-group">
			<form:label  path="smtpServer" >Serveur SMTP</form:label>
			<form:input path="smtpServer" class="form-control" type="text" placeholder="Entrer l'adresse du serveur smtp"/>
			<small id="smtpServerHelp" class="form-text text-danger"><form:errors path="smtpServer" /></small>
		</div>
		<div class="form-group">
			<form:label  path="emailSender" >Email</form:label>
			<form:input  path="emailSender" class="form-control" type="text" placeholder="Entrer l'adresse email "/>
			<small id="emailHelp" class="form-text text-danger"><form:errors path="emailSender" /></small>
		</div>
		<div class="form-group">
			<form:label  path="port" >Port</form:label>
			<form:input  path="port" class="form-control" type="text" placeholder="Entrer le port"/>
			<small id="portlHelp" class="form-text text-danger"><form:errors path="port" /></small>
		</div>
		<div class="form-group">
			<form:label  path="emailUser" >Identifiant</form:label>
			<form:input  path="emailUser" class="form-control" type="text" placeholder="Entrer l'identifiant"/>
			<small id="userlHelp" class="form-text text-danger"><form:errors path="emailUser" /></small>
		</div>
		<div class="form-group">
			<form:label path="emailPassword" >Mot de passe</form:label>
			<form:input type="password" class="form-control" path="emailPassword" />
			<small id="passwordlHelp" class="form-text text-danger"><form:errors path="emailPassword" /></small>
		</div>
		<input type="submit" value="Enregistrer" class="btn btn-primary"/>
		<a href="/AutoQuiz3000/student/" class="btn btn-secondary">Annuler</a>
	</form:form>
</body>
</html>