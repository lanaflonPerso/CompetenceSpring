<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <div class="container">
	
		<div class="row">
		
			<aside class="col-md-4">
			
			</aside>
			<div class="col-md-8">
			
				<h1>${ sessionScope.quiz.name }</h1>
				
				<form id="myForm" method="post" action="">

                    <input id="nbrChoix" type="hidden" name="nbrChoix" value="2" />

					<div class="form-group">
						<label for="titleQuestion">Titre de la question: </label>
						<textarea class="form-control" id="titleQuestion" rows="4" name="titleQuestion"></textarea> 
					</div>
					
					<div class="form-inline">
						<label for="choix1">Choix 1: </label>
                        <input type="text" class="form-control m-3" id="choix1" name="choix1" />
                        <input type="checkbox" id="choix1" name="checkChoix1" class= "m-3" />
                        <label for="checkChoix1">Est une reponse?</label>
					</div>
					
					<div class="form-inline">
						<label for="choix2">Choix 2: </label>
                        <input type="text" class="form-control m-3" id="choix2" name="choix2" />
                        <input type="checkbox" id="choix2" name="checkChoix2" class= "m-3" />
                        <label for="checkChoix2">Est une reponse?</label>
                    </div>
                    
                    <div id="newQuestionPack"></div>
					<button id="addChoice" type="button" class="btn btn-primary btn-sm">Ajouter une réponse</button>
					
					<button id="btnSubmit"class="btn btn-primary" type="submit">Enregistrement</button>
				</form>
			</div>
		</div>
    </div>
</body>
<script>
    var divContainer= document.getElementById("newQuestionPack");
    var nbrChoixInput= document.getElementById("nbrChoix");
    let i= 3
    document.getElementById("addChoice").addEventListener("click", function(){

        var newDiv= document.createElement('div');
        newDiv.className= "form-inline";
        // LABEL
        var newLabel= document.createElement('label');
        newLabel.className= "";
        newLabel.setAttribute('for', 'choix'+i);
        var textLabel= document.createTextNode('Choix '+i+': ');
        newLabel.appendChild(textLabel);

        // INPUT
        var newInput= document.createElement('input');
        newInput.className= "form-control m-3";
        newInput.setAttribute('name', 'choix'+i);
        newInput.setAttribute('type', 'text');
        newInput.setAttribute('id', 'choix'+i)

        // CHECKBOX
        var newCheckbox= document.createElement('input');
        newCheckbox.className= "m-3";
        newCheckbox.setAttribute('name', 'checkChoix'+i);
        newCheckbox.setAttribute('type', 'checkbox');

        // LABEL CHECKBOX
        var newLabel2= document.createElement('label');
        newLabel2.setAttribute('for', 'checkChoix'+i);
        var textLabel2= document.createTextNode('Est une reponse?');
        newLabel2.appendChild(textLabel2);


        newDiv.appendChild(newLabel);
        newDiv.appendChild(newInput);
        newDiv.appendChild(newCheckbox);
        newDiv.appendChild(newLabel2);


        nbrChoixInput.setAttribute("value", i);
        i++;
        var par= document.querySelector('form-group');
        divContainer.insertBefore(newDiv, par);
    });
</script>

</html>