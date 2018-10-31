<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>

<html>
<head>
	<title>Mon titre HOME</title>
</head>
<body>

<h1>
	AutoQuiz
</h1>

	<a href="authenticate">Formulaire (authentification)</a><br />
	<a href="public/connection">Connection</a><br />
	<a href="public/inscription">Inscription</a><br />
	<a href="public/token">Inscription</a><br />
	<a href="administrator/studentclass?page=1&max=20">Gestion des classes</a><br />
	<a href="administrator/user?page=1&max=20">Gestion des utilisateurs</a><br />
	<a href="professor/studentClassDashboard">test dasboard</a><br />
	
	<p>type= ${ user.type }</p>
	<c:choose>
		<c:when test="${ user != null && user.type= '' }">
			<p>salut l'inconnue</p>
		</c:when>
	</c:choose>
	
	
</body>
</html>