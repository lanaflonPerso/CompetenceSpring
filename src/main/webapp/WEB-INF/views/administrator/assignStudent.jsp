<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Gestion de l'effectif</title>

</head>
<body>
<h1 class="pt-1">Gestion de l'effectif de : <c:out value="${classe.name}"></c:out></h1>
	<input class="form-control my-4 " id="searchInput" type="text" placeholder="Recherche">
	<table id="studentTable" class="table table-striped">
	<thead class="thead-light">
		<tr>
			<th onclick="sortTable(0,'studentTable')">Prénom<i class="pl-1 fa fa-sort"></i></th>
			<th onclick="sortTable(1,'studentTable')">Nom<i class="pl-1 fa fa-sort"></i></th>
			<th onclick="sortTable(2,'studentTable')">Email<i class="pl-1 fa fa-sort"></i></th>
			<th onclick="sortTable(3,'studentTable')">Date de naissance<i class="pl-1 fa fa-sort"></i></th>
			<th onclick="sortTable(4,'studentTable')">Type<i class="pl-1 fa fa-sort"></i></th>
			<th></th>
		</tr>
	</thead>
	<tbody id="studentTableBody">
	<c:forEach items="${assignedusers}" var="user">
		<tr>
			<td><c:out value="${user.firstName}"></c:out></td>
			<td><c:out value="${user.lastName}"></c:out></td>
			<td><c:out value="${user.email}"></c:out></td>
			<td><fmt:formatDate type="date" value="${user.birthdate}"/></td>
			<td><c:out value="${user.type}"></c:out></td>
				<spring:url value="/administrator/assignStudent/${user.id}/delete/${classe.id}" var="delUrl" />
			<td><a href="${delUrl}" class="btn btn-danger btn-sm <c:if test="${fn:length(classe.quizzes) gt 0}"> disabled</c:if>" role="button">Enlever</a></td>
		</tr>
	</c:forEach>
			<c:forEach items="${unasssignedusers}" var="useru">
		<tr  class="table-warning">
			<td><c:out value="${useru.firstName}"></c:out></td>
			<td><c:out value="${useru.lastName}"></c:out></td>
			<td><c:out value="${useru.email}"></c:out></td>
			<td><fmt:formatDate type="date" value="${useru.birthdate}"/></td>
			<td><c:out value="${useru.type}"></c:out></td>
			 <spring:url value="/administrator/assignStudent/${useru.id}/add/${classe.id}" var="addUrl" />
			<td><a href="${addUrl}" class="btn btn-success btn-sm <c:if test="${fn:length(classe.quizzes) gt 0}"> disabled</c:if>" role="button">Ajouter</a></td>
		</tr>
	</c:forEach>
</tbody>
</table>
<spring:url value="/administrator/studentclass?page=1&max=20" var="backUrl" />
<a href="${backUrl}"  class="btn btn-secondary justify-content-center" role="button" >Retour</a>
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

	<script>
		$(document).ready(function(){
		  $("#searchInput").on("keyup", function() {
		    var value = $(this).val().toLowerCase();
		    $("#studentTableBody tr").filter(function() {
		      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
		    });
		  });
		});
	</script>
</body>
</html>