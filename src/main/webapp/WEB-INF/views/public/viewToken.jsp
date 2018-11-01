<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<h1>Inscription</h1>
<div class="row">
	<aside class="col-md-4">
	
	</aside>
	
	<p>${ ctrl }
	<div class="col-md-8">
		<form method="post" action="">
			<div class="form-group">
				<label for="token">Token: </label>
				<input type="text" class="form-control" id="token" name="token" value="${ user.token }" />
				<c:if test="${ ctrl.msgToken != null }">
					<small id="tokenHelp" class="form-text text-danger">${ ctrl.msgToken }</small>
				</c:if>
			</div>
			
			<div class="form-group">
				<label for="token">Password: </label>
				<input type="password" class="form-control" id="password" name="password" />
				<c:if test="${ ctrl.msgPassword != null }"> 
					<small id="passwordHelp" class="form-text text-danger">${ ctrl.msgPassword }</small>
				</c:if>
			</div>
			
			<div class="form-group">
				<label for="confirm">Confirmation: </label>
				<input type="password" class="form-control" id="confirm" name="confirm" /> 
			</div>
								
			<button class="btn btn-primary" type="submit">Enregistrement</button>
		</form>
	</div>
</div>