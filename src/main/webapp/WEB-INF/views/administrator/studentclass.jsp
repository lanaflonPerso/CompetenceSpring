<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Gestion des classes</title>
</head>
<body>
	<h1 class="pt-1">Gestion des classes</h1>
	<input class="form-control my-4 " id="searchInput" type="text" placeholder="Recherche">
	<table id="studentTable" class="table table-striped">
		<thead class="thead-light">
			<tr>
				<th onclick="sortTable(0,'studentTable')">Nom<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(1,'studentTable')">Date début<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(2,'studentTable')">Date fin<i class="pl-1 fa fa-sort"></i></th>
				<th> </th>
			</tr>
		</thead>
		<tbody id="studentTableBody">
			<c:forEach items="${stclasses}" var="classe">
				<tr>
					<td class="col-4 "><c:out value="${classe.name}"></c:out></td>
					<td><fmt:formatDate type="date" value="${classe.startDate}" /></td>
					<td><fmt:formatDate type="date" value="${classe.endDate}" /></td>
					<spring:url value="/administrator/studentclass/${classe.id}/delete" var="delUrl" />
					<spring:url value="/administrator/studentclass/${classe.id}/update" var="updateUrl" />
					<spring:url value="/administrator/assignStudent/${classe.id}" var="assignUrl" />
					<td>
						<a href="${assignUrl}" class="btn btn-success" role="button">Assigner</a>
						<a href="${updateUrl}" class="btn btn-info " role="button">Modifier</a>	
						<a href="${delUrl}" class="btn btn-danger " <c:if test="${fn:length(classe.students) gt 0}">disabled</c:if>" role="button" aria-disabled="true">Supprimer</a>					
					</td>					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${maxpage gt 1}">
		<ul class="pagination justify-content-center">
			<li class="page-item <c:if test="${param.page eq 1}">disabled</c:if>">
	      		<a class="page-link" href="/AutoQuiz3000/administrator/studentclass/?page=${param.page-1}&max=${param.max}">Précédent</a>
	    	</li>
	    	<c:forEach var = "i" begin = "1" end = "${maxpage}">
	    		<li class="page-item <c:if test="${param.page eq i}">active</c:if>">
	      			<a class="page-link" href="/AutoQuiz3000/administrator/studentclass/?page=${i}&max=${param.max}">${i}</a>
	    		</li>
	    	</c:forEach>
	    	<li class="page-item <c:if test="${param.page eq maxpage}">disabled</c:if>">
		      <a class="page-link" href="/AutoQuiz3000/administrator/studentclass/?page=${param.page+1}&max=${param.max}">Suivant</a>
	    	</li>
	  	</ul>
 		</c:if>
	<c:if test="${not empty message}">
		<div class="alert alert-danger" role="alert">
			<c:out value="${message}"></c:out>
		</div>
	</c:if>
	<form:form class="border p-2 rounded border-secondary" method="POST" action="/AutoQuiz3000/administrator/studentclass" modelAttribute="studentclass-form">
		<div class="form-group ">
			<form:label  path="name" >Nom</form:label>
			<form:input path="name" type="text" placeholder="Entrer le nom de la classe" class="form-control"/>
			<small class="form-text text-danger"><form:errors path="name" /></small>
		</div>
		<div class="form-group">
			<form:label path="startDate" class="">Date de début</form:label>
			<form:input type="date" path="startDate" class="form-control"/>
			<small class="form-text text-danger"><form:errors path="startDate" /></small>
		</div>
		<div class="form-group">
			<form:label path="endDate" class="">Date de fin</form:label>
			<form:input type="date" path="endDate" class="form-control"/>
			<small class="form-text text-danger"><form:errors path="endDate" /></small>
		</div>
		<form:input type="hidden" path="id"/>
		<input type="submit" value="${actionButton}" class="btn btn-primary"/>
	</form:form>
	
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