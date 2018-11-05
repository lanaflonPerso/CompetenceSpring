<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>Résultat</title>

<c:choose>
	<c:when test="${ quizTotal < 2 } ">
		<c:set value="questionnaire" var="varQuestionnaire"/>
	</c:when>
	<c:otherwise>
		<c:set value="questionnaires" var="varQuestionnaire"/>
	</c:otherwise>
</c:choose>


<div class="col-md-12 alert alert-light">
	<h2>Statistique (${ sessionScope.user.studentClass.name })</h2>
	<p>Vous avez répondu a ${ quizTotal } ${ varQuestionnaire } </p>
	<p>vous avez une note moyenne de ${ average }%</p>	
</div>

<div class="col-md-12">
	<table class="table">
		<thead>
			<tr>
				<td>Nom du quiz</td>
				<td>% obtenu</td>
				<td>nb correct</td>
				<td>nb incorrect</td>
				<td>Compétence testé</td>
				<td>recu</td>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach var="quiz" items="${ hQuizs }">
				<tr>
					<td><a href="<c:url value="/student/quiz/history/${ quiz.quiz.id }" />">${ quiz.quiz.name }</a></td>
					<td>${ quiz.score }% (${ quiz.quiz.scoreToAcquireSkill }%)</td>
					<td>${ quiz.correctResponse }</td>
					<td>${ quiz.errorResponse }</td>
					<td>${ quiz.quiz.skill.name }</td>
					<c:choose>
						<c:when test="${ quiz.score >= quiz.quiz.scoreToAcquireSkill }">
							<td class="table-success">Obtenu</td>
						</c:when>
						<c:otherwise>
							<td class="table-danger">Echoué</td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>

<div class="col-md-12">
	<h2>listes de compétences acquises</h2>
	
	<table class="table">
		<tr>
			<c:forEach var="skill" items="${ sessionScope.user.skills }" varStatus="status">
				<td>${ skill.name }</td>
				<c:if test="${ status.count%4 == 0 }">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
</div>