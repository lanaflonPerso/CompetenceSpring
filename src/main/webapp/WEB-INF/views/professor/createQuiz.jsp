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
						<label for="classe">Example select</label>
						<select class="form-control" id="classe" name="classe">
							<c:forEach var="classe" items="${ classes }">
								<option value="${ classe.id }">${ classe.name }</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="name">Intitulé du formulaire: </label> <input
							type="text" class="form-control" id="name" name="name" />
					</div>

					<div class="form-group">
						<label for="competence">Compétence: </label> <input type="text"
							class="form-control" id="competence" name="competence" />
					</div>

					<div class="form-group">
						<label for="dateDebut">Date de début: </label> <input type="date"
							class="form-control" id="dateDebut" name="dateDebut" />
					</div>

					<div class="form-group">
						<label for="dateFin">Date de fin: </label> <input type="date"
							class="form-control" id="dateFin" name="dateFin" />
					</div>

					<button class="btn btn-primary" type="submit">Continuer</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>