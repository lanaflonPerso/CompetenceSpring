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
<title>Dashboard Administrateur</title>

</head>
<body>
<div class="row text-center">
 <ul class="list-group my-5 mx-4 col-3">
  <li class="list-group-item font-weight-bold bg-info">Utilisateurs</li>
  <li class="list-group-item"><c:out value="${countStudent}"></c:out> élève<c:if test="${countStudent gt 1 }">s</c:if></li>
  <li class="list-group-item"><c:out value="${countProfessor}"></c:out> Professeur<c:if test="${countProfessor gt 1 }">s</c:if></li>
  <li class="list-group-item"><c:out value="${countAdmin}"></c:out> Administrateur<c:if test="${countAdmin gt 1 }">s</c:if></li>
</ul>

 <ul class="list-group my-5 mx-4 col-3">
  <li class="list-group-item font-weight-bold bg-info">Classes</li>
  <li class="list-group-item"><c:out value="${countStudent}"></c:out></li>
</ul>

 <ul class="list-group my-5 mx-4 col-3">
  <li class="list-group-item font-weight-bold bg-info">Questionraires</li>
  <li class="list-group-item"><c:out value="${countStudent}"></c:out></li>
</ul>
</div>


 
</body>