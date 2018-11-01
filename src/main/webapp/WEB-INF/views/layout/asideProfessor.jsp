<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="btn-group btn-group-vertical mt-1">
	<a href="<c:url value="/professor/create_quiz" />">
		<button class="btn btn-primary mt-1">
			Créer un formulaire
		</button>
	</a>
	<c:if test="${ sessionScope.quiz.countQuestion() > 2 }">
		<a href="<c:url value="/professor/close_quiz" />">
			<button class="btn btn-primary mt-1">
				Cloture du formulaire
			</button>
		</a>
	</c:if>

</div>