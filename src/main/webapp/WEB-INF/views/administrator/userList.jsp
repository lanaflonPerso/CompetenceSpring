<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8" />
<title></title>
</head>
<body>
	<h1>Gestion des utilisateurs</h1>
	<table class="table table-hover table-sm table-responsive">
		<thead class="thead-light">
			<tr>
				<th>Prénom</th>
				<th>Nom</th>
				<th>Email</th>
				<th>Date de naissance</th>
				<th>Type</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.firstName}"></c:out></td>
				<td><c:out value="${user.lastName}"></c:out></td>
				<td><c:out value="${user.email}"></c:out></td>
				<td><fmt:formatDate type="date" value="${user.birthdate}" /></td>
				<td><c:out value="${user.type}"></c:out></td>
				<spring:url value="user/${user.id}/delete" var="delUrl" />
				<spring:url value="user/${user.id}/update" var="updtaeUrl" />
				<td>
					<a href="${updtaeUrl}" class="btn btn-primary btn-sm" role="button">Modifier</a>
					<a href="${delUrl}" class="btn btn-danger btn-sm <c:if test="${not empty user.studentClass || (countadmin <= 1 && user.type eq 'ADMINISTRATOR')}">disabled</c:if>" role="button" aria-disabled="true">Supprimer</a> 
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${maxpage gt 1}">
		<ul class="pagination justify-content-center">
			<li class="page-item <c:if test="${param.page eq 1}">disabled</c:if>">
	      		<a class="page-link" href="/AutoQuiz3000/administrator/user/?page=${param.page-1}&max=${param.max}">Précédent</a>
	    	</li>
	    	<c:forEach var = "i" begin = "1" end = "${maxpage}">
	    		<li class="page-item <c:if test="${param.page eq i}">active</c:if>">
	      			<a class="page-link" href="/AutoQuiz3000/administrator/user/?page=${i}&max=${param.max}">${i}</a>
	    		</li>
	    	</c:forEach>
	    	<li class="page-item <c:if test="${param.page eq maxpage}">disabled</c:if>">
		      <a class="page-link" href="/AutoQuiz3000/administrator/user/?page=${param.page+1}&max=${param.max}">Suivant</a>
	    	</li>
	  	</ul>
  	</c:if>
</body>
</html>