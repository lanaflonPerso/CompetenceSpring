<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title>Resultat du quiz: ${ sessionScope.quiz.name }</title>

<h1>Questionnaire: ${ sessionScope.quiz.name }</h1>

<div class="alert alert-warning">
	<p>reponse correcte  = <span class="h3">${ sessionScope.quizTest.correctResponse }</span></p>
	<p>reponse incorrect = <span class="h3">${ sessionScope.quizTest.errorResponse }</span></p>
	<p>taux de réussite  = <span class="h3">${ sessionScope.quizTest.score }%</span></p>
</div>

<c:forEach var="question" items="${ sessionScope.quiz.quizQuestions }">
	<h4>${ question.text }</h4>
	
	<table class="table">
	
		<c:forEach var="response" items="${ question.quizResponses }">	
			<c:forEach var="StResponse" items="${ sessionScope.quizTest.stResponse }">
				
				<c:set value="" var="error" scope="request" />
				<c:set var="classeResponseStudent" scope="request" value=""/>
				
				<c:if test="${ StResponse.idResponse == response.id }">
					<c:choose>
						<c:when test="${ StResponse.answered == true && response.correct == false }">
							<c:set var="error" scope="request" value="<i class='fas fa-thumbs-down'></i>"/>
							<c:set var="classeResponseStudent" scope="request" value="alert alert-danger"/>
						</c:when>
						<c:when test="${ StResponse.answered == false && response.correct == true }">
							<c:set var="classeResponseStudent" scope="request" value="alert alert-danger"/>
						</c:when>
						<c:when test="${ StResponse.answered == true && response.correct == true }">
							<c:set var="error" scope="request" value="<i class='fas fa-thumbs-up'></i>"/>
							<c:set var="classeResponseStudent" scope="request" value="alert alert-success"/>
						</c:when>
						<c:otherwise>
							<c:set value="" var="error" scope="request" />
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${ response.correct }">
							<c:set var="classeResponse" scope="request" value="alert alert-success"/>
						</c:when>
						<c:otherwise>
							<c:set var="classeResponse" scope="request" value=""/>
						</c:otherwise>
					</c:choose>
				
					<tr>
						<td class="${ classeResponseStudent }">${ error }</td>
						<td  class="${ classeResponse }">${ response.text }</td>
					</tr>
				
				</c:if> 
			
			</c:forEach>				
		</c:forEach>
	</table>
</c:forEach>