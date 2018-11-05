<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Ajout d'utilisateurs</title>
<h1 class="my-4">Ajout d'utilisateurs en masse</h1>
<p>le format est de type:</p>
<ul>
	<li>prénom;nom;email;dateDeNaissance;classe;type</li>
</ul>

<p>pour le type:</p>
<ul>
	<li>0= étudiant</li>
	<li>1= professeur</li>
	<li>2= administrateur</li>
</ul>

<form method="post" action="">
	<div class="form-group">
		<label for="listStudent">Liste d'utilisateurs: </label>
		<textarea class="form-control" id="listStudent" rows="8"
			name="listStudent"></textarea>
	</div>
	
	<button id="btnSubmit" class="btn btn-primary" type="submit">Enregistrement</button>
	<a href="<c:url value="/administrator/user?page=1&max=10" />" class="btn btn-secondary ml-2 "  role="button">Annuler</a>
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