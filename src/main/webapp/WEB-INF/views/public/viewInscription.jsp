<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	
		<div class="row">
			<aside class="col-md-4">
			
			</aside>
			<div class="col-md-8">
				<form method="post" action="">
					<div class="form-group">
						<label for="firstname">Prénom: </label>
						<input type="text" class="form-control" id="firstname" name="firstname" value="<c:out value="${ student.firstname }" />"/> 
						<small id="firstnameHelp" class="form-text text-muted">${ ctrl.msgFirstname }</small>
					</div>
					
					<div class="form-group">
						<label for="lastname">Nom: </label>
						<input type="text" class="form-control" id="lastname" name="lastname" value="${ student.lastname }"/> 
						<small id="lastnameHelp" class="form-text text-muted text-danger">${ ctrl.msgLastname }</small>
					</div>
					
					<div class="form-group">
						<label for="email">Email: </label>
						<input type="email" class="form-control" id="email" name="email" value="${ student.email }"/> 
						<small id="emailHelp" class="form-text text-muted text-danger">${ ctrl.msgEmail }</small>
					</div>
					
					<div class="form-group">
						<label for="birthDate">Date de naissance: </label>
						<input type="date" class="form-control" id="birthDate" name="birthDate" value="${ student.birthDate }"/> 
						<small id="birthDateHelp" class="form-text text-muted text-danger">${ ctrl.msgBirthDate }</small>
					</div>
					
					<button class="btn btn-primary" type="submit">Enregistrement</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>