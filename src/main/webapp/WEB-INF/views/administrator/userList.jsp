<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page session="true" isELIgnored="false"
	contentType="text/html; charset=UTF-8"%>
<!Doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Gestion des utilisateurs</title>
</head>
<body>
	<h1 class="pt-1">Gestion des utilisateurs</h1>
	<input class="form-control my-4 " id="searchInput" type="text" placeholder="Recherche">
	<table id="userTable" class="table table table-striped">
		<thead class="thead-light">
			<tr>
				<th onclick="sortTable(0,'userTable')">Prénom<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(1,'userTable')">Nom<i class="pl-1 fa fa-sort "></i></th>
				<th onclick="sortTable(2,'userTable')">Email<i class="pl-1 fa fa-sort "></i></th>
				<th onclick="sortTable(3,'userTable')">Date de naissance<i class="pl-1 fa fa-sort"></i></th>
				<th onclick="sortTable(4,'userTable')">Type<i class="pl-1 fa fa-sort"></i></th>
				<th></th>
			</tr>
		</thead>
		<tbody id="userTableBody">
			<c:forEach items="${users}" var="user">
			<tr>
				<td><c:out value="${user.firstName}"></c:out></td>
				<td><c:out value="${user.lastName}"></c:out></td>
				<td><c:out value="${user.email}"></c:out></td>
				<td><fmt:formatDate type="date" value="${user.birthdate}" /></td>
				<td><c:out value="${user.type}"></c:out></td>
				<spring:url value="user/${user.id}/delete" var="delUrl" />
				<spring:url value="user/${user.id}/update" var="updtaeUrl" />
				<td>
					<a href="${updtaeUrl}" class="btn btn-primary btn-sm" role="button">Modifier</a>
					<a href="${delUrl}" class="btn btn-danger btn-sm <c:if test="${not empty user.studentClass || (countadmin <= 1 && user.type eq 'ADMINISTRATOR')}">disabled</c:if>" role="button" aria-disabled="true">Supprimer</a> 
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${maxpage gt 1}">
		<ul class="pagination justify-content-center">
			<li class="page-item <c:if test="${param.page eq 1}">disabled</c:if>">
	      		<a class="page-link" href="/AutoQuiz3000/administrator/user/?page=${param.page-1}&max=${param.max}">Précédent</a>
	    	</li>
	    	<c:forEach var = "i" begin = "1" end = "${maxpage}">
	    		<li class="page-item <c:if test="${param.page eq i}">active</c:if>">
	      			<a class="page-link" href="/AutoQuiz3000/administrator/user/?page=${i}&max=${param.max}">${i}</a>
	    		</li>
	    	</c:forEach>
	    	<li class="page-item <c:if test="${param.page eq maxpage}">disabled</c:if>">
		      <a class="page-link" href="/AutoQuiz3000/administrator/user/?page=${param.page+1}&max=${param.max}">Suivant</a>
	    	</li>
	  	</ul>
  	</c:if>
  	
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
    $("#userTableBody tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>
</body>
</html>