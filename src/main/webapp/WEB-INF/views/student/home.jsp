<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Page d'acceuil</title>

<div class="row">
	<div class="col-md-8">
		<c:choose>
			<c:when test="${ user != null && user.type == 'PROFESSOR' }">
				<p>salut le prof</p>
			</c:when>
			<c:when test="${ user != null && user.type == 'ADMINISTRATOR' }">
				<p>salut l'administrateur</p>
			</c:when>
			<c:when test="${ user != null && user.type == 'STUDENT' }">
				<p>salut l'etudiant</p>
			</c:when>
			<c:otherwise>
				<p>salut l'inconnue</p>
			</c:otherwise>
		</c:choose>
	</div>
</div>

