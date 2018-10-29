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
<title>Gestion des classes</title>

</head>
<body>

		<h1>Gestion des classes</h1>
		<table class="table table-hover table-sm">
			<thead class="thead-dark">
				<tr>
					<th>Nom</th>
					<th>Date début</th>
					<th>date fin</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${stclasses}" var="classe">
					<tr>
						<td class="col-4 "><c:out value="${classe.name}"></c:out></td>
						<td><fmt:formatDate type="date" value="${classe.startDate}" /></td>
						<td><fmt:formatDate type="date" value="${classe.endDate}" /></td>
						<spring:url value="studentclass/${classe.id}/delete" var="delUrl" />
						<spring:url value="studentclass/${classe.id}/update" var="updateUrl" />
						<spring:url value="/professor/studentclass/${classe.id}" var="viewUrl" />
						<td class="col-4">
							<a href="${viewUrl}" class="btn btn-secondary" role="button">Visualiser</a>
							<a href="${updateUrl}" class="btn btn-success" role="button">Modifier</a>
							<a href="${delUrl}" class="btn btn-danger" role="button">Supprimer</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	
		<c:if test="${param.page>1}">
			<a href="studentclass?page=${param.page-1}&max=${param.max}" class="btn btn-secondary" role="button">Précédent</a>
		</c:if>
 		<span> ${param.page}</span> 
		<c:if test="${suivExist}">
			<a href="studentclass?page=${param.page+1}&max=${param.max}" class="btn btn-secondary" role="button">Suivant</a>
		</c:if>
		<c:if test="${not empty message}">
		<div class="alert alert-danger" role="alert">
			<c:out value="${message}"></c:out>
		</div>
		</c:if>
		<form:form method="POST" action="/AutoQuiz3000/administrator/studentclass" modelAttribute="studentclass-form">
			<div class="form-group">
				<form:label  path="name" class="col-md-2">
					Nom
				</form:label>
				<form:input class="col-md-5" path="name"  type="text" placeholder="Entrer le nom de la classe"/>
				<small id="emailHelp" class="form-text text-danger"><form:errors path="name" /></small>
				
			</div>
			<div class="form-group">
				<form:label path="startDate" class="col-md-2">Date de début</form:label>
				<form:input type="date" path="startDate" class="col-md-2" />
				<small id="emailHelp" class="form-text text-danger"><form:errors path="startDate" /></small>
			</div>
			<div class="form-group">
				<form:label path="endDate" class="col-2">Date de fin</form:label>
				<form:input type="date" path="endDate" class="col-md-2" />
					<small id="emailHelp" class="form-text text-danger"><form:errors path="endDate" /></small>
			</div>
			<form:input type="hidden" path="id"/>
			<input type="submit" value="Ajouter" class="btn btn-primary"/>
		</form:form>
</body>
</html>