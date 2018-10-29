<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12">
		<h1>Quiz : ${sessionScope.quizName}</h1> 
	
		<p>${ sessionScope.orderNum }/${ sessionScope.qstNb } - ${ currentQst.text }</p>
		
		<form method="get" action='<c:url value="/student/quiz/next-question" />'>

			<c:forEach var="response" items="${ responses }">
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="response" value="${ response.id }" id="${ response.id }"/>
					<label for="${ response.id }">${ response.text }</label>
				</div>
			</c:forEach>
			<input type="submit" class="btn btn-primary" value="Next" />
		</form>
	</div> 
</div>