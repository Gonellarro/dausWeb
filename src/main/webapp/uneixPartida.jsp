<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

        <title>Unir-se a partida de daus multijugador</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/JocControler" method="post">
        <jsp:include page="WEB-INF/comuns/capcalera.jsp"/>   
        <h4 class="text-center">3.Uni� a partida nova</h4>
                <br>
        <div class="section m-2 p-2 border text-center">
                <div class="row">
                    <div class="col-12">
                        <div class="form-group">
                            <label>Codi de partida</label>
                            <input type="text" class="form-control" id="partida" name ="partida" placeholder="Introdueix el codi de la partida">
                            <input type="hidden" id="crear" name="crear" value="false">
                            <button type="submit" class="btn btn-primary">Enviar</button>
                        </div>
                    </div>		
                </div>	                        
            </div>
        </form>

        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    </body>
</html>