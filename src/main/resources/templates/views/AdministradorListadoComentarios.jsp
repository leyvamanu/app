<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!--Autor HTML: Manuel Leyva-->


<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
        <link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width"/>
        <title>Listado Comentarios</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.rawgit.com/Chalarangelo/bootstrap-extend/880420ae663f7c539971ded33411cdecffcc2134/css/bootstrap-extend.min.css"/>
        <!--CSS-->
        <!--<link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_listado_colaborador_admin.css">-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_administrador.css">
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_barra_lateral.css">
    </head>
    <body>
        <!--Menú-->
        <nav class="navbar navbar-expand-md navbar-light bg-light fixed-top">
            <a class="nav-brand logo" href="/DiscoverMe/administrador/">DiscoverMe</a>
            <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbar-1" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse flex-row-reverse" id="navbar-1">
                <ul class="navbar-nav p-2">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Crear</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/DiscoverMe/administrador/formularioExperiencia">Experiencia</a>
                            <a class="dropdown-item" href="/DiscoverMe/administrador/formularioUsuario">Usuario</a>
                            <a class="dropdown-item" href="/DiscoverMe/administrador/formularioServicioHotel">Servicio</a>
                            <a class="dropdown-item" href="/DiscoverMe/administrador/formularioOferta">Oferta</a>
                        </div>
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
        <!--Menu lateral-->
        <nav class="main-menu">
            <ul class="menu-lateral">
                <li>
                    <a href="<spring:url value= '/administrador/listadoHuespedes/'/>">
                        <i class="fa fa-cloud" aria-hidden="true"></i>
                        <span class="nav-text">
                            Apps Activas
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/administrador/listadoComentarios/'/>">
                        <i class="fa fa-comment" aria-hidden="true"></i>
                        <span class="nav-text">
                            Comentarios
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/administrador/listadoExperiencia/'/>">
                        <i class="fa fa-globe" aria-hidden="true"></i>
                        <span class="nav-text">
                            Experiencias
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/administrador/listadoColaboradores/'/>">
                        <i class="fa fa-users" aria-hidden="true"></i>
                        <span class="nav-text">
                            Colaboradores
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/administrador/listadoServicio/'/>">
                        <i class="fa fa-h-square" aria-hidden="true"></i>
                        <span class="nav-text">
                            Servicios del Hotel
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/administrador/listadoOfertas/'/>">
                        <i class="fa fa-eur" aria-hidden="true"></i>
                        <span class="nav-text">
                            Ofertas
                        </span>
                    </a>
                </li>
            </ul>   
            <ul class="logout">
                <li>
                    <a href="/DiscoverMe/logout/">
                        <i class="fa fa-power-off fa-2x"></i>
                        <span class="nav-text">
                            Cerrar sesión
                        </span>
                    </a>
                </li>  
            </ul>
        </nav>
        <!--lista acordeón-->
        <div class="container" id="accordion">
            <c:forEach items="${experiencias}" var="experiencia" varStatus="loop">
                <br>
                <div class="card">
                    <div class="card-header" id="heading${loop.index}">
                        <h5 class="mb-0">
                            <button class="btn btn-link" data-toggle="collapse" data-target="#collapse${loop.index}" aria-expanded="true" aria-controls="collapse${loop.index}">
                                <h3>${experiencia.nombre}</h3>
                            </button>
                        </h5>
                        <c:choose>
                            <c:when test="${experiencia.puntuacion=='0'}">
                                <span>Sin Puntuar</span>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var = "i" begin = "1" end = "${experiencia.puntuacion}">
                                    <i class="fas fa-star" style="color:#FFD700;"></i>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div id="collapse${loop.index}" class="collapse" aria-labelledby="heading${loop.index}" data-parent="#accordion">
                        <div class="card-body">
                            <c:forEach items="${experiencia.comentarios}" var="comentario" varStatus="loop">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <ul class="list-group">
                                                <li class="list-group-item">
                                                    <div class="media">
                                                        <div class="info media-left">
                                                            <img src="<spring:url value="/resources/img/${comentario.usuario.foto.foto}"/>" alt="Imagen" class="rounded-circle" width="100">
                                                            <h3>${comentario.usuario.nombre}</h3>
                                                            <h4>${comentario.usuario.procedencia}</h4>
                                                            <span>${comentario.fecha}</span>
                                                            <br>
                                                            <c:forEach var = "i" begin = "1" end = "${comentario.puntos}">
                                                                <i class="fas fa-star" style="color:#FFD700;"></i>
                                                            </c:forEach>    
                                                        </div>
                                                        <div class="media-body caja-texto">
                                                            <h4>${comentario.titulo}</h4>
                                                            <p>${comentario.comentario}</p>
                                                        </div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>    
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>         

    </body>
</html>