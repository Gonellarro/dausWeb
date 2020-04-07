<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="es">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!--<meta http-equiv="refresh" content="5"> -->


        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Estadístiques partida de daus multijugador</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/JocControler" method="post">
            <jsp:include page="WEB-INF/comuns/capcalera.jsp"/>   
            <h4 class="text-center">5.Resultats de la partida</h4>
            <br>
            <div class="section m-2 p-2 border text-center">
                <div class="row">
                    <div class="col-12 col-md-4">
                        Partida: <p class="bg-dark text-light">${partida.hashPartida} - Ronda: ${ronda}</p>

                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#<E/th>
                                <th scope="col">Avatar</th>
                                <th scope="col">Nom</th>
                                <th scope="col">Punts</th>
                                <th scope="col">Creador</th>
                                <th scope="col">Guanyador</th>
                                </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items = "${jugadors}" var="jugador" varStatus="loopCounter">   
                                        <tr>
                                            <th scope="row">
                                                <c:out value="${loopCounter.count}"/>
                                            </th> 
                                            <td>
                                                <img src="resources/img/hippiicons/<c:out value="${jugador.avatar}"/>.png" width="42" height="42">
                                            </td><td>
                                                ${jugador.nom}  
                                            </td><td>
                                                ${jugador.valorDau}
                                            </td><td>
                                                <c:if test = "${jugador.creador}">
                                                    <img src="resources/img/ledVerd.png" width="42">
                                                </c:if>
                                                <c:if test = "${!jugador.creador}">
                                                    <img src="resources/img/ledGris.png" width="42">
                                                </c:if>
                                            </td><td>
                                                <c:if test = "${jugador.guanyador}">
                                                    <img src="resources/img/corona.png" width="42">
                                                </c:if>
                                           
                                            
                                        </tr>
                                    </c:forEach>
                                </tbody>
                        </table>  
                        <br>
                        <a href="${pageContext.request.contextPath}/JocControler?accio=refrescar2" class="btn btn-info">Refrescar</a>
                        <c:if test = "${jugador.creador}">
                            <a href="${pageContext.request.contextPath}/JocControler?accio=nova" class="btn btn-primary">Nova ronda</a>
                        </c:if> 
                    </div>			
                </div>		
            </div> 


            <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>