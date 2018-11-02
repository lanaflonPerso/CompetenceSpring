<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<title>Vue du questionnaire</title>

<h1>${ quiz.name } (${ quiz.quizQuestions.size() } questions)</h1>

<c:forEach var="question" items="${ quiz.quizQuestions }">	
	<h3>${ question.text }</h3>
	<c:forEach var="response" items="${ question.quizResponses }">
	<div class="form-check">
		<input class="form-check-input" type="checkbox" value="${ response.id }" id="Q${ question.id }R${ response.id }" <c:if test="${ response.correct }">checked</c:if> />
		<label for="Q${ question.id }R${ response.id }">${ response.text }</label>
	</div>
	</c:forEach>
</c:forEach>
