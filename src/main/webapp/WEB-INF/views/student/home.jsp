<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF_8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	
		<div class="row">
			<aside class="col-md-4">
				<p>${ sessionScope.user.lastName }</p>
				<p>${ sessionScope.isAdmin }</p>
				<p>${ sessionScope.isProfessor }</p>
				<a href='<c:url value="/professor/quiz" />'>Ajouter un formulaire</a>
			</aside>
			<div class="col-md-8">
			</div>
		</div>
	</div>
</body>
</html>