<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">

		<div class="row">
			<h1>Crréation du quizz</h1>
			<aside class="col-md-4"></aside>
			<div class="col-md-8">
				<form method="post" action="">
					<div class="form-group">
						<label for="stClassName">Example select</label>
						<select class="form-control" id="stClassName" name="stClassName">
							<c:forEach var="classe" items="${ classes }">
								<option value="${ classe.id }">${ classe.name }</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="name">Intitulé du formulaire:</label>
						<input type="text" class="form-control" id="name" name="name" />
					</div>

					<div class="form-group">
						<label for="skill">Compétence:</label>
						<input type="text" class="form-control" id="skill" name="skill" />
					</div>

					<div class="form-group">
						<label for="startDebut">Date de début:</label>
						<input type="date" class="form-control" id="startDebut" name="startDebut" />
					</div>

					<div class="form-group">
						<label for="endDate">Date de fin:</label>
						<input type="date" class="form-control" id="endDate" name="endDate" />
					</div>
					
					<div class="form-group">
						<label for="scoreToAcquireSkill">Score Minimum:</label>
						<input type="number" class="form-control" id="scoreToAcquireSkill" name="scoreToAcquireSkill" />
					</div>

					<button class="btn btn-primary" type="submit">Continuer</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>