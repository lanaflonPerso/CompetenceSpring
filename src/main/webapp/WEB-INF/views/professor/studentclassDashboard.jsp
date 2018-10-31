<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Dashboard classes</title>
<base href="/AutoQuiz3000/professor/">
</head>
<body>

	<h1 class="text-center"><c:out value="${classe.name}"></c:out> <small class="text-muted">du 
	 <fmt:formatDate type="date" dateStyle = "short" value="${classe.startDate}" /> au 
	 <fmt:formatDate type="date" dateStyle = "short" value="${classe.endDate}" /></small></h1>
	<h2> Liste des élèves</h2>
	<table class="table table-hover table-sm">
		<thead class="thead-dark">
			<tr>
				<th>Prénom</th>
				<th>Nom</th>
				<th>Email</th>
				<th>Date de naissance</th>
				<th>Type</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${usersAssigned}" var="user">
			<tr>
				<td><c:out value="${user.firstName}"></c:out></td>
				<td><c:out value="${user.lastName}"></c:out></td>
				<td><c:out value="${user.email}"></c:out></td>
				<td><c:out value="${user.birthdate}"></c:out></td>
				<td><c:out value="${user.type}"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<h2> Liste des questionnaires</h2>
	<ul class="list-group list-group-flush"">
	<c:forEach items="${quizs}" var="quiz">
		<li class="list-group-item">quiz</li>
	</c:forEach>
	</ul>
</body>
</html>