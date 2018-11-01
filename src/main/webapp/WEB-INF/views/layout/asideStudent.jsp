<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="btn-group btn-group-vertical mt-1">
 	<a href="<c:url value="/student/list_quiz" />">
		<button class="btn btn-primary mt-1">
			${ sessionScope.nbrQuiz } Quiz
		</button>
	</a>
	<a href="<c:url value="/student/quiz_history" />">
		<button class="btn btn-primary mt-1">
			Historique Formulaire
		</button>
	</a>
	
</div>