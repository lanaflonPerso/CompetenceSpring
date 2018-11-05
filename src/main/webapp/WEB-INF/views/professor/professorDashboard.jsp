<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Dashboard Professeur</title>
<base href="/AutoQuiz3000/professor/">
</head>
<body>
	<h1 class="pt-1">Les classes</h1>
	<table id="classTable" class="table table-striped table-sm my-4 ">
		<thead class="thead-light">
			<tr>
				<th onclick="sortTable(0,'classTable')">Nom<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(1,'classTable')">Effectif<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(2,'classTable')">Date de début<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(3,'classTable')">Date de fin<i class="pl-1 fa fa-sort"></i></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${classes}" var="classe">
				<tr>
					<td><c:out value="${classe.name}"></c:out></td>
					<td><c:out value="${classe.students.size()}"></c:out></td>
					<td><fmt:formatDate type="date" value="${classe.startDate}" /></td>
					<td><fmt:formatDate type="date" value="${classe.endDate}" /></td>
					<spring:url value="studentClassDashboard/${classe.id}" var="viewUrl" />
					<td><a href="${viewUrl}" class="btn btn-primary" >Visualiser</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr>
	<h1 class="pt-1">Les questionnaires</h1>
	<table id="questionTable" class="table table-striped table-sm  my-4">
		<thead class="thead-light">
			<tr>
				<th onclick="sortTable(0,'questionTable')">Nom<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(1,'questionTable')">Compétance<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(2,'questionTable')">Score minimal<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(3,'questionTable')">Date de début<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(4,'questionTable')">Date de fin<i class="pl-1 fa fa-sort"></i></th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${quizzes}" var="quiz">
				<tr>
					<td><c:out value="${quiz.name}"></c:out></td>
					<td><c:out value="${quiz.skill.name}"></c:out></td>
					<td><c:out value="${quiz.scoreToAcquireSkill}"></c:out></td>
					<td><fmt:formatDate type="date" value="${quiz.startDate}" /></td>
					<td><fmt:formatDate type="date" value="${quiz.endDate}" /></td>
					<spring:url value="viewQuizCs/${quiz.id}" var="viewUrl" />
					<td><a href="${viewUrl}" class="btn btn-primary" >Visualiser</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
	function sortTable(n,iid) {
		  var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
		  table = document.getElementById(iid);
		  switching = true;
		  dir = "asc"; 
		  while (switching) {
		    switching = false;
		    rows = table.rows;
		    for (i = 1; i < (rows.length - 1); i++) {
		      shouldSwitch = false;
		      x = rows[i].getElementsByTagName("TD")[n];
		      y = rows[i + 1].getElementsByTagName("TD")[n];
		      if (dir == "asc") {
		        if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
		          shouldSwitch= true;
		          break;
		        }
		      } else if (dir == "desc") {
		        if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
		          shouldSwitch = true;
		          break;
		        }
		      }
		    }
		    if (shouldSwitch) {
		      rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
		      switching = true;
		      switchcount ++;      
		    } else {
		      if (switchcount == 0 && dir == "asc") {
		        dir = "desc";
		        switching = true;
		      }
		    }
		  }
		}
	</script>
</body>
</html>