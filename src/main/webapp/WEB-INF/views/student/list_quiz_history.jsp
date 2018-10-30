<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
				<td>${ quiz.quiz.name }</td>
				<td>${ quiz.score }</td>
				<td>${ quiz.correctResponse }</td>
				<td>${ quiz.errorResponse }</td>
				<td>${ quiz.quiz.skill.name }</td>
				<c:choose>
					<c:when test="${ quiz.score > quiz.quiz.scoreToAcquireSkill }">
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