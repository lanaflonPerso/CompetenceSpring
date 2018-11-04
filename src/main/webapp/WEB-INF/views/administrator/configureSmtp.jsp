<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
	<meta charset="utf-8" />
	<title>Configuration du Smtp</title>
</head>
<body>
	<h1>Configuration du SMTP pour l'envoie d'email</h1>
	<form:form method="POST" action="/AutoQuiz3000/administrator/configuresmtp" modelAttribute="email-form">
		<div class="form-group">
			<form:label  path="smtpServer" class="col-md-2">serveur Smtp</form:label>
			<form:input class="col-md-5" path="smtpServer"  type="text" placeholder="Entrer l'adresse du serveur smtp"/>
			<small id="smtpServerHelp" class="form-text text-danger"><form:errors path="smtpServer" /></small>
		</div>
		<div class="form-group">
			<form:label  path="emailSender" class="col-md-2">Adresse email</form:label>
			<form:input class="col-md-5" path="emailSender"  type="text" placeholder="Entrer l'adresse email "/>
			<small id="emailHelp" class="form-text text-danger"><form:errors path="emailSender" /></small>
		</div>
		<div class="form-group">
			<form:label  path="port" class="col-md-2">Port</form:label>
			<form:input class="col-md-5" path="port"  type="text" placeholder="Entrer le port"/>
			<small id="portlHelp" class="form-text text-danger"><form:errors path="port" /></small>
		</div>
		<div class="form-group">
			<form:label  path="emailUser" class="col-md-2">Identifiant</form:label>
			<form:input class="col-md-5" path="emailUser"  type="text" placeholder="Entrer l'identifiant"/>
			<small id="userlHelp" class="form-text text-danger"><form:errors path="emailUser" /></small>
		</div>
		<div class="form-group">
			<form:label path="emailPassword" class="col-md-2">Mot de passe</form:label>
			<form:input type="password" path="emailPassword" />
			<small id="passwordlHelp" class="form-text text-danger"><form:errors path="emailPassword" /></small>
		</div>
		<input type="submit" value="Enregistrer" class="btn btn-primary"/>
		<a href="#" class="btn btn-secondary">Annuler</a>
	</form:form>
</body>
</html>