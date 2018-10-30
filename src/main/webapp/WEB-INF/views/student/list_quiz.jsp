<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table class="table">
	<thead>
		<tr>
			<td>Nom du quiz</td>
		</tr>
	</thead>
	
	<tbody>
		<c:forEach var="quiz" items="${ quizs }">
			<tr>
				<td><a href='<c:url value="/student/quiz/${ quiz.id }/start" />'>${ quiz.name }</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>