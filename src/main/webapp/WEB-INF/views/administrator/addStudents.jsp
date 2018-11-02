<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Ajout d'utilisateurs</title>

<form method="post" action="">
	<div class="form-group">
		<label for="listStudent">Liste d'étudiant: </label>
		<textarea class="form-control" id="listStudent" rows="8"
			name="listStudent"></textarea>
	</div>
	
	<button id="btnSubmit" class="btn btn-primary" type="submit">Enregistrement</button>
</form>

<c:if test="${ usersOK.size() > 0}">
	<h4>Utilisateur(s) Ajouté</h4>
	<table class="table m-2">
		<thead>
			<tr>
				<td>Nom</td>
				<td>Prénom</td>
				<td>Email</td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="user" items="${ usersOK }">
				<tr class="alert alert-success">
					<td>${ user.firstName }</td>
					<td>${ user.lastName }</td>
					<td>${ user.email }</td>
				</tr>
			</c:forEach>	
		</tbody>
	</table>
</c:if>

<c:if test="${ usersNoOK.size() > 0}">
	<div class="alert alert-danger m-1">
		<h4>Erreur dans l'ajout</h4>
		<c:forEach var="user" items="${ usersNoOK }">
			<p>${ user.firstName } ${ user.lastName }</p>
		</c:forEach>
	</div>
</c:if>