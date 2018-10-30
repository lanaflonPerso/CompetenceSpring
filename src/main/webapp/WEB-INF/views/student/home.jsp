<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<div class="container">
	
		<div class="row">
			<aside class="col-md-4">
				<p>type= ${ sessionScope.user.type }</p>
				<p>classe= ${ sessionScope.user.studentClass.name }
				<p>${ sessionScope.user.lastName }</p>
				<p>${ sessionScope.isAdmin }</p>
				<p>${ sessionScope.isProfessor }</p>
				<a href='<c:url value="/professor/create_quiz" />'>Ajouter un formulaire</a>
			</aside>
			<div class="col-md-8">
			</div>
		</div>
	</div>
