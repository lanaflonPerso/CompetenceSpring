<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page session="false" contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<meta charset="utf-8" />
	<title>Home</title>
	<link rel="stylesheet" href='<spring:theme code="css" />' />
	<script type="text/javascript" src="resources/jquery-ui/external/jquery/jquery.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//Au click sur le lien
			$("a#ajaxLink").click(function(e){
				
				//req AJAX vers le serveur
				$.ajax({
					type:"GET",
					contentType:"application/json",
					url : "trainings/1",
					dataType : "json",
					success : function(data){
						//alert(data.id + " " + data.title);
						$("div#resultat").html("<h3>"+data.id + " " + data.title+"</h3>");
					},
					error : function(xhr, ajaxOptions, thrownError){
						alert("Erreur : "+ xhr.status + " : " + thrownError);
					}
				});
				
				
				
				//$("div#resultat").html("toto");
				e.preventDefault();//stopper la propagation de l'evt
			});
			
			//Evénement sur le lien #ajaxListLink
			$("a#ajaxListLink").click(function(e){
				
				//req AJAX vers le serveur
				$.ajax({
					type:"GET",
					contentType:"application/json",
					url : "trainings/json",
					dataType : "json",
					success : function(data){
						//alert(data);
						var res = '<table border="1">';
						res+="<tr><th>Id</th><th>Titre</th></tr>";
						$(data).each(function(i){
							res+="<tr>";
							res+="<td>"+data[i].id+"</td>";
							res+="<td>"+data[i].title+"</td>";
							res+="</tr>";	
						});
						res += '</table>';
						$("div#resultat").html(res);
						
					},
					error : function(xhr, ajaxOptions, thrownError){
						alert("Erreur : "+ xhr.status + " : " + thrownError);
					}
				});
				
				
				
				//$("div#resultat").html("toto");
				e.preventDefault();//stopper la propagation de l'evt
			});
		});
	
	</script>
</head>
<body>
<h1>
	Formation Spring MVC
</h1>

	<a href="authenticate">Formulaire (authentification)</a>
	<br />
	<a href="contacts">Gestion des contacts</a>
	<br />
	<a href="achats">Panier d'achats</a>
	<br />
	<a href="inscription">TP (SpringMVC+ORM)</a>
<br />
	<a href="trainings/json">WS REST formations (json)</a>
	<br />
	
	<a href="trainings/xml">WS REST formations (XML)</a>
	<br />
	
	<a href="trainings/1">WS REST Formation avec l'id=1</a>
	<br />
	
	<h4>AJAX</h4>
	<a href="#" id="ajaxLink">WS Rest Ajax</a>
	<br />
	<a href="#" id="ajaxListLink">WS Rest Ajax list</a>
	
	<div id="resultat">
	
	</div>
	
	
</body>
</html>
