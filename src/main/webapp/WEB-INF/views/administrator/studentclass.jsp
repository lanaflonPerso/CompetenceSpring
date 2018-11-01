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
			<thead class="thead-light">
				<tr>
					<th>Nom</th>
					<th>Date début</th>
					<th>Date fin</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${stclasses}" var="classe">
					<tr>
						<td class="col-4 "><c:out value="${classe.name}"></c:out></td>
						<td><fmt:formatDate type="date" value="${classe.startDate}" /></td>
						<td><fmt:formatDate type="date" value="${classe.endDate}" /></td>
						<spring:url value="/administrator/studentclass/${classe.id}/delete" var="delUrl" />
						<spring:url value="/administrator/studentclass/${classe.id}/update" var="updateUrl" />
						<spring:url value="/administrator/assignStudent/${classe.id}" var="assignUrl" />
						<td>
							<a href="${assignUrl}" class="btn btn-success btn-sm" role="button">Assigner</a>
							<a href="${updateUrl}" class="btn btn-info btn-sm" role="button">Modifier</a>
							<a href="${delUrl}" class="btn btn-danger btn-sm" role="button" disabled>Supprimer</a>
						</td>					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<c:if test="${maxpage gt 1}">
			<ul class="pagination justify-content-center">
				<li class="page-item <c:if test="${param.page eq 1}">disabled</c:if>">
		      		<a class="page-link" href="/AutoQuiz3000/administrator/studentclass/?page=${param.page-1}&max=${param.max}">Précédent</a>
		    	</li>
		    	<c:forEach var = "i" begin = "1" end = "${maxpage}">
		    		<li class="page-item <c:if test="${param.page eq i}">active</c:if>">
		      			<a class="page-link" href="/AutoQuiz3000/administrator/studentclass/?page=${i}&max=${param.max}">${i}</a>
		    		</li>
		    	</c:forEach>
		    	<li class="page-item <c:if test="${param.page eq maxpage}">disabled</c:if>">
			      <a class="page-link" href="/AutoQuiz3000/administrator/studentclass/?page=${param.page+1}&max=${param.max}">Suivant</a>
		    	</li>
		  	</ul>
  		</c:if>
		<c:if test="${not empty message}">
			<div class="alert alert-danger" role="alert">
				<c:out value="${message}"></c:out>
			</div>
		</c:if>
		<form:form method="POST" action="/AutoQuiz3000/administrator/studentclass" modelAttribute="studentclass-form">
			<div class="form-group ">
				<form:label  path="name" class="col-md-3">Nom</form:label>
				<form:input path="name"  type="text" placeholder="Entrer le nom de la classe" class="col-md-6"/>
				<small class="form-text text-danger offset-3"><form:errors path="name" /></small>
			</div>
			<div class="form-group">
				<form:label path="startDate" class="col-md-3">Date de début</form:label>
				<form:input type="date" path="startDate" />
				<small class="form-text text-danger offset-3"><form:errors path="startDate" /></small>
			</div>
			<div class="form-group">
				<form:label path="endDate" class="col-md-3">Date de fin</form:label>
				<form:input type="date" path="endDate" />
				<small class="form-text text-danger offset-3"><form:errors path="endDate" /></small>
			</div>
			<form:input type="hidden" path="id"/>
			<input type="submit" value="${actionButton}" class="btn btn-primary"/>
			<a href="#" class="btn btn-secondary" role="button">Retour</a>
		</form:form>
</body>
</html>