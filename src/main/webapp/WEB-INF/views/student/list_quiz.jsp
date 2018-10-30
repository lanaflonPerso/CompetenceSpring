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
				<td>${ quiz.name }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>