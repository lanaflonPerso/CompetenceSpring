<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h1>titre = ${ sessionScope.quiz.name }</h1>

<h2>reponse correcte  = ${ sessionScope.quizTest.correctResponse }</h2>
<h2>reponse incorrect = ${ sessionScope.quizTest.errorResponse }</h2>
<h2>taux de réussite  = ${ sessionScope.quizTest.score }%</h2>

<c:forEach var="question" items="${ sessionScope.quiz.quizQuestions }">
	<h3>${ question.text }</h3>
	
	<c:forEach var="response" items="${ question.quizResponses }">
		<p class="<c:if test="${ response.correct }">alert alert-success</c:if>">

		<c:forEach var="StResponse" items="${ sessionScope.quizTest.stResponse }">
							
			<c:if test="${ StResponse.idResponse == response.id }">
				<c:choose>
					<c:when test="${ StResponse.answered != response.correct }">
						<c:set value="<span class='text-danger'>Erreur</span>" var="error" scope="request" />
					</c:when>
					<c:otherwise>
						<c:set value="" var="error" scope="request" />
					</c:otherwise>
				</c:choose>
			</c:if>
		</c:forEach>		
			
			
			${ error } ${ response.text }</p>
	</c:forEach>
</c:forEach>