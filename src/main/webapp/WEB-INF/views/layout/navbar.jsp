<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<a class="navbar-brand" href='<c:url value="/" />'>AutoQuiz</a>

	<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
		<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
		
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'ADMINISTRATOR' }">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/administrator/administatorDashboard" />">Dashboard</a>
				</li>
			</c:if>
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'ADMINISTRATOR' }">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/administrator/user?page=1&max=10" />">Gérer les utilisateurs</a>
				</li>
			</c:if>
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'ADMINISTRATOR' }">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/administrator/studentclass?page=1&max=10" />">Gérer les classes</a>
				</li>
			</c:if>
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'ADMINISTRATOR' }">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/administrator/configuresmtp" />">Configurer Smtp</a>
				</li>
			</c:if>
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'PROFESSOR' }">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/professor/professorDashboard" />">Dashboard</a>
				</li>
			</c:if>	
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'PROFESSOR' }">
				<li class="nav-item">
					<a class="nav-link" href="<c:url value="/professor/create_quiz" />">Créer un formulaire</a>
				</li>
			</c:if>
			
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'STUDENT' }">
				<li class="nav-item">
					<a class="nav-link<c:if test="${sessionScope.nbrQuiz eq 0 }"> disabled</c:if>" href='<c:url value="/student/list_quiz" />' role="button" aria-disabled="true"> Quiz <span class="badge badge-primary">${ sessionScope.nbrQuiz }</span></a>
				</li>
			</c:if>
			
			<c:if test="${ sessionScope.user != null && sessionScope.user.type == 'STUDENT' }">
				<li class="nav-item">
					<a class="nav-link" href='<c:url value="/student/quiz_history" />'>Historique Formulaire</a>
				</li>
			</c:if>
		</ul>
		<ul class="navbar-nav mr-lg-5 mt-2 mt-lg-0 ">
			<c:choose>
				<c:when test="${ sessionScope.user != null }">
					<li class="nav-item dropdown">
					     	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					       		<c:out value="${ sessionScope.user.firstName} ${sessionScope.user.lastName }"></c:out>
							</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					  			<a class="dropdown-item" href="<c:url value="/public/userprofil" />">Profil</a>
					 			<a class="dropdown-item" href="<c:url value="/public/deconnection" />">Déconnection</a>
							</div>
					</li>
				</c:when>
				<c:otherwise>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/public/connection" />" >Connexion</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="<c:url value="/public/inscription" />" >Inscription</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</nav>