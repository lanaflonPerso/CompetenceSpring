<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Création d'un questionnaire</title>

<h1>Création du Questionnaire</h1>
<div class="col-md-8">
	<form method="post" action="">
		<div class="form-group">
			<label for="stClassName">Choix de la classe</label>
			<select class="form-control" id="stClassName" name="stClassName">
				<c:forEach var="classe" items="${ classes }">
					<option value="${ classe.id }">${ classe.name }</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label for="name">Intitulé du formulaire:</label>
			<input type="text" class="form-control" id="name" name="name" value="${ ctrl.quiz.name }"/>
			<c:if test="${ ctrl.msgName != null }">
				<small id="nameHelp" class="form-text text-danger">${ ctrl.msgName }</small>
			</c:if>
			
		</div>

		<div class="form-group">
			<label for="skill">Compétence:</label>
			<input type="text" class="form-control" id="skill" name="skill" value="${ ctrl.quiz.skill.name }"/>
			<c:if test="${ ctrl.msgSkill != null }">
				<small id="skillHelp" class="form-text text-danger">${ ctrl.msgSkill }</small>
			</c:if>
		</div>

		<div class="form-group">
			<label for="startDebut">Date de début:</label>
			<input type="date" class="form-control" id="startDebut" name="startDebut" />
			<c:if test="${ ctrl.msgDateDebut != null }">
				<small id="skillHelp" class="form-text text-danger">${ ctrl.msgDateDebut }</small>
			</c:if>
		</div>

		<div class="form-group">
			<label for="endDate">Date de fin:</label>
			<input type="date" class="form-control" id="endDate" name="endDate" />
		</div>
		
		<div class="form-group">
			<label for="scoreToAcquireSkill">Score Minimum:</label>
			<input type="number" class="form-control" id="scoreToAcquireSkill" name="scoreToAcquireSkill" value="${ ctrl.quiz.scoreToAcquireSkill }"/>
			<c:if test="${ ctrl.msgScoreToAcquireSkill != null }">
				<small id="scoreToAcquireSkillHelp" class="form-text text-danger">${ ctrl.msgScoreToAcquireSkill }</small>
			</c:if>
		</div>

		<button class="btn btn-primary" type="submit">Continuer</button>
	</form>
</div>
