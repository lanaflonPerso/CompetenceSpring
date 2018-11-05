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
	<h1 class="text-center pt-1"><c:out value="${classe.name}"></c:out> <small class="text-muted">du 
	 <fmt:formatDate type="date" dateStyle = "short" value="${classe.startDate}" /> au 
	 <fmt:formatDate type="date" dateStyle = "short" value="${classe.endDate}" /></small></h1>
	<table class="table table-striped table-sm my-5 shadow-sm p-3 mb-5 bg-white rounded">
		<thead class="thead-light">
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
				<td><fmt:formatDate type="date" value="${user.birthdate}" /></td>
				<td><c:out value="${user.type}"></c:out></td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<hr>
	<h2 class="my-4"> Questionnaires</h2>
	<c:forEach items="${StatQuiz}" var="stat">
		<div class="card my-3 shadow p-3 mb-5 bg-white rounded">
		  <div class="card-header">
		  		<h4>${stat.quiz.name}</h4> 
		  </div>
  			<div class="card-body">
				<c:if test="${ stat.countQuizDone gt 0}">
		 			<div class="alert alert-info " role="alert">
		 				Il reste ${ stat.countQuizDone} questionaire<c:if test="${ stat.countQuizDone gt 1}">s</c:if> à faire.
					</div>
				</c:if> 
			<ul class="list-group list-group-flush">
  				<li class="list-group-item">
		    		<h5>Score moyen</h5>
					<div class="progress">
		  				<div class="progress-bar" role="progressbar" style="width: ${stat.averangeScore}%;" aria-valuenow="${stat.averangeScore}" aria-valuemin="0" aria-valuemax="100">${stat.averangeScore}%</div>
					</div>
					<div class="progress" style="height: 4px;">
		  				<div class="progress-bar bg-danger" role="progressbar" style="width: ${stat.minScore}%;" aria-valuenow="${stat.minScore}" aria-valuemin="0" aria-valuemax="100"></div>
					</div>
					<div class="progress" style="height: 4px;">
		  				<div class="progress-bar bg-success" role="progressbar" style="width: ${stat.maxScore}%;" aria-valuenow="${stat.maxScore}" aria-valuemin="0" aria-valuemax="100"></div>
					</div>
					<small>Minimun: ${stat.minScore}%</small>,<small> Maximum: ${stat.maxScore}%</small>
				</li>
				<li class="list-group-item">
					<h5>Compétence <span class="badge badge-secondary float-right">${stat.quiz.skill.name}</span></h5>
					
					<div class="progress">
  						<div class="progress-bar bg-success" role="progressbar" style="width: <c:out value="${stat.countSkill*100/nbStudent }"></c:out>%" aria-valuenow="<c:out value="${stat.countSkill*100/nbStudent }"></c:out>" aria-valuemin="0" aria-valuemax="100">${stat.countSkill}</div>
  						<div class="progress-bar bg-danger" role="progressbar" style="width: <c:out value="${stat.countFailSkill*100/nbStudent }"></c:out>%" aria-valuenow="<c:out value="${stat.countFailSkill*100/nbStudent }"></c:out>" aria-valuemin="0" aria-valuemax="100"><c:out value="${stat.countFailSkill}"></c:out></div>
					</div>
					<small class="float-right">Score minimal requis: ${stat.quiz.scoreToAcquireSkill}%</small>
				</li>
					</ul>
	  		</div>
		</div>
	</c:forEach>
</body>
</html>