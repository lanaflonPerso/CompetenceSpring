<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>			
			<h1>${ quiz.name } (${ quiz.quizQuestions.size() } questions)</h1>

			<c:forEach var="question" items="${ quiz.quizQuestions }">	
				<ul class="list-group">
	  				<li class="list-group-item active">${question.text}</li>
					<c:forEach var="response" items="${ question.quizResponses }">
						
						<li class="list-group-item <c:if test="${response.correct}">text-success</c:if>">${ response.text }</li>
					</c:forEach>
				</ul>
			</c:forEach>
</body>
</html>