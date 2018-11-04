<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Tittle</title>

</head>
<body>
<h1>Gestion de l'effectif de la classse <c:out value="${classe.name}"></c:out></h1>
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
	<c:forEach items="${assignedusers}" var="user">
		<tr>
			<td><c:out value="${user.firstName}"></c:out></td>
			<td><c:out value="${user.lastName}"></c:out></td>
			<td><c:out value="${user.email}"></c:out></td>
			<td><fmt:formatDate type="date" value="${user.birthdate}"/></td>
			<td><c:out value="${user.type}"></c:out></td>
				<spring:url value="/administrator/assignStudent/${user.id}/delete/${classe.id}" var="delUrl" />
			<td><a href="${delUrl}" class="btn btn-danger <c:if test="${fn:length(classe.quizzes) gt 0}"> disabled</c:if>" role="button">Enlever</a></td>
		</tr>
	</c:forEach>
			<c:forEach items="${unasssignedusers}" var="useru">
		<tr  class="table-warning">
			<td><c:out value="${useru.firstName}"></c:out></td>
			<td><c:out value="${useru.lastName}"></c:out></td>
			<td><c:out value="${useru.email}"></c:out></td>
			<td><fmt:formatDate type="date" value="${useru.birthdate}"/></td>
			<td><c:out value="${useru.type}"></c:out></td>
			 <spring:url value="/administrator/assignStudent/${useru.id}/add/${classe.id}" var="addUrl" />
			<td><a href="${addUrl}" class="btn btn-success<c:if test="${fn:length(classe.quizzes) gt 0}"> disabled</c:if>" role="button">Ajouter</a></td>
		</tr>
	</c:forEach>
</tbody>
</table>
<spring:url value="/administrator/studentclass?page=1&max=20" var="backUrl" />
<a href="${backUrl}"  class="btn btn-secondary justify-content-center" role="button" >Retour</a>

</body>
</html>