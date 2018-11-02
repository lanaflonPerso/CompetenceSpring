<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>Mes questionnaire a faire</title>

<table class="table">
	<thead>
		<tr>
			<td>Nom du quiz</td>
			<td>Date Limite</td>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="quiz" items="${ quizs }">
			<tr>
				<td><a href='<c:url value="/student/quiz/${ quiz.id }/start" />'>${ quiz.name }</a></td>
				<td><fmt:formatDate value="${ quiz.endDate }" pattern="dd/MM/yyyy" /></td>
			</tr>
		</c:forEach>
	</tbody>
</table>