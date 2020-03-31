<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Joc daus multijugador</title>
    </head>
    <body>

        <!-- Capçalera -->
        <jsp:include page="WEB-INF/comuns/capcalera.jsp"/>   
        <h4 class="text-center">Joc de daus</h4>
        <br>
        <div class="section m-2 p-2 border text-center">
            <div class="row">
                <div class="col-sm-4 offset-sm-4">
                    <c:if test = "${llancat}">
                        <h1 class="display-1 text-primary" id="dau">${valorDau}</h1>    
                        <button onClick="location.href='${pageContext.request.contextPath}/JocControler?accio=continuar'" class="btn btn-primary" id="continuar" value="">Continuar</button>
                    </c:if> 
                    <c:if test = "${!llancat}">
                        Hola ${jugador.nom}, tira el dau<br>
                        <button onClick="location.href='${pageContext.request.contextPath}/JocControler?accio=llancar'" class="btn btn-success" id="llancar" value="">Llançar!</button>
                    </c:if> 
                </div>
            </div>
        </div>
        <script>
            function llancarDau() {
                var x = 1 + Math.floor(Math.random() * 6);
                document.getElementById("dau").innerHTML = x;
                document.getElementById("llancar").disabled = true;
                document.getElementById("continuar").disabled = false;
                continuar.value = x; 
            }
        </script>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>
