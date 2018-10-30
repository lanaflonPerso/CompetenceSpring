<div class="btn-group btn-group-vertical">
	<a href="<c:url value="/professor/create_quiz" />">
		<button class="btn btn-primary">
			Créer un formulaire
		</button>
	</a>
	<c:if test="${ sessionScope.quiz.countQuestion() > 4 }">
		<a href="<c:url value="/professor/close_quiz" />">
			<button class="btn btn-primary">
				Cloture du formulaire
			</button>
		</a>
	</c:if>

</div>