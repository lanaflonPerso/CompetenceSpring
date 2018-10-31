<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	
		<div class="row">
		
			<aside class="col-md-4">
				
			</aside>
			
			<div class="col-md-8">
				<h1>${ quiz.name } (${ quiz.quizQuestions.size() } questions)</h1>

				<c:forEach var="question" items="${ quiz.quizQuestions }">	
					<h3>${ question.text }</h3>
					<c:forEach var="response" items="${ question.quizResponses }">
					<div class="form-check">
						<input class="form-check-input" type="checkbox" value="${ response.id }" id="Q${ question.id }R${ response.id }"/>
						<label for="Q${ question.id }R${ response.id }">${ response.text }</label>
					</div>
					</c:forEach>
				</c:forEach>
			</div>
		</div>
    </div>
</body>
</html>