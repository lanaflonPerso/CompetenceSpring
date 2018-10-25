<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
						<label for="token">Token: </label>
						<input type="text" class="form-control" id="token" name="token" /> 
						<small id="tokenHelp" class="form-text text-muted text-danger">${ ctrl.msgToken }</small>
					</div>
					
					<div class="form-group">
						<label for="token">Password: </label>
						<input type="password" class="form-control" id="password" name="password" />
						<small id="passwordHelp" class="form-text text-muted text-danger">${ ctrl.msgPassword }</small>
					</div>
					
					<div class="form-group">
						<label for="confirm">Confirmation: </label>
						<input type="password" class="form-control" id="confirm" name="confirm" /> 
					</div>
										
					<button class="btn btn-primary" type="submit">Enregistrement</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>