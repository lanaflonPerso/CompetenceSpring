<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="true" isELIgnored="false" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>Espace Admin</title>
</head>
<body>
<h1>
	Espace Admin<br />
	${sessionScope.username}
</h1>
	

</body>
</html>
