<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vue du questionnaire</title>
</head>
<body>			
	<h1 class="my-4">${ quiz.name } (${ quiz.quizQuestions.size() } question<c:if test="${ quiz.quizQuestions.size() gt 1 }">s</c:if>)</h1>
	<c:forEach var="question" items="${ quiz.quizQuestions }">	
		<ul class="list-group shadow p-3 mb-4 bg-white rounded">
 				<li class="list-group-item active">${question.text}</li>
			<c:forEach var="response" items="${ question.quizResponses }">
				<li class="list-group-item <c:if test="${response.correct}">bg-info</c:if>">${ response.text }</li>
			</c:forEach>
		</ul>
	</c:forEach>
</body>
</html>