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
	<h1>Les classes</h1>
	<table class="table table-hover table-sm">
		<thead class="thead-light">
			<tr>
				<th>Nom</th>
				<th></th>
				<th>Date de début</th>
				<th>Date de fin</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${classes}" var="classe">
				<tr>
					<td><c:out value="${classe.name}"></c:out></td>
					<td><c:out value="${classe.students.size()}"></c:out></td>
					<td><fmt:formatDate type="date" value="${classe.startDate}" /></td>
					<td><fmt:formatDate type="date" value="${classe.endDate}" /></td>
					<spring:url value="studentClassDashboard/${classe.id}" var="viewUrl" />
					<td><a href="${viewUrl}">Visualiser</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h1>Les questionnaires</h1>
	<table class="table table-hover table-sm">
		<thead class="thead-light">
			<tr>
				<th>Nom</th>
				<th>Compétance</th>
				<th>Score minimal</th>
				<th>Date de début</th>
				<th>Date de fin</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${quizzes}" var="quiz">
				<tr>
					<td><c:out value="${quiz.name}"></c:out></td>
					<td><c:out value="${quiz.skill.name}"></c:out></td>
					<td><c:out value="${quiz.scoreToAcquireSkill}"></c:out></td>
					<td><fmt:formatDate type="date" value="${quiz.startDate}" /></td>
					<td><fmt:formatDate type="date" value="${quiz.endDate}" /></td>
					<spring:url value="viewQuizCs/${quiz.id}" var="viewUrl" />
					<td><a href="${viewUrl}">Visualiser</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>