<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!--Autor HTML: Carlos Litwiñiuk-->


<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
	<link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${experiencia.nombre}</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_actividades.css">
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/modal.css">
    </head>
    <body>
        <!--Menú-->
        <nav class="navbar navbar-expand-md navbar-light bg-light fixed-top">
            <a class="nav-brand logo" href="/DiscoverMe/huesped/listadoExperiencia/">DiscoverMe</a>
            <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbar-1" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse flex-row-reverse" id="navbar-1">
                <ul class="navbar-nav p-2">
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value= '/huesped/listadoServicios/'/>">Servicios</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value= '/huesped/listadoOfertas/'/>">Ofertas</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<spring:url value= '/huesped/'/>">Preferencias</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">${usuario.nombre}</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="<spring:url value= '/editar/'/>">Editar</a>
                            <a class="dropdown-item" href="<spring:url value= '/logout/'/>">Salir</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>

            <!--Titulo-->
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="page-header">
                            <h2>Edita tu comentario</h2>
                        </div>
                    </div>  
                </div>
            </div>
            <!--Formulario añadir comentario-->
            <div class="container">
                <form action="<spring:url value= 'updateComentario/'/>" method="POST">
                    <div class="form-group row">
                        <label for="titulo" class="col-sm-2 col-form-label">Titulo:</label>
                        <div class="col-sm-10">
                            <input maxlength="100" type="text" class="form-control" id="inputNombre" placeholder="Titulo del comentario..." name="titulo" value="${comentario.titulo}" required>
                        </div>
                    </div>
                    <!--Aquí van las estrellas animadas-->
                    <div class="form-group row">
                        <label for="inputPuntuación" class="col-sm-2 col-form-label">Puntuacion:</label>
                        <div class="col-sm-10">
                            <i class="far fa-star fa-2x"></i>
                            <i class="far fa-star fa-2x" ></i>
                            <i class="far fa-star fa-2x" ></i>
                            <i class="far fa-star fa-2x" ></i>
                            <i class="far fa-star fa-2x" ></i>
                        </div>
                        <!--mensaje de advertencia -->
                        <div class="alert alert-warning alert-dismissible fade show" style="display:none;" >
                            <strong>info:</strong> seleccione el numero de estrellas, por favor
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <input type="number" class="form-control" name ="puntuacion" id="puntuacionNumerica" required min="1" max="5" title="1-5" value="${comentario.puntos}" hidden>
                        </div>
                    </div>

                    <div class="form-group row">
                        <label for="formControlTextarea" class="col-sm-2 col-form-label">Comentario:</label>
                        <div class="col-sm-10">
                            <textarea maxlength="250" class="form-control" id="formControlTextarea" rows="4" placeholder="Explique su experiencia..." name ="comentario" required >${comentario.comentario}</textarea>
                        </div>
                    </div>
                    <br>
                    <div class="form-group row">
                        <div class="col-sm-10">
                            <button id="enviaComent" type="submit" class="btn btn-primary">Aceptar</button>
                        </div>
                    </div>
                </form>
            </div>
                        
        <!--scripts-->                            
        <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script type="text/javascript" src="/DiscoverMe/resources/js/edita_comentario.js"></script>
    </body>
</html>

