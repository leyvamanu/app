<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!--Autor: Manuel Leyva de la Morena-->


<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
        <link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width"/>
        <title>Colaborador</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.rawgit.com/Chalarangelo/bootstrap-extend/880420ae663f7c539971ded33411cdecffcc2134/css/bootstrap-extend.min.css"/>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_listado_administrador.css">
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_actividades.css">
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_barra_lateral.css">
    </head>
    <body>
        <!--Menú-->
        <nav class="navbar navbar-expand-md navbar-light bg-light fixed-top">
            <a class="nav-brand logo" href="/DiscoverMe/colaborador/">DiscoverMe</a>
            <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbar-1" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse flex-row-reverse" id="navbar-1">
                <ul class="navbar-nav p-2">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Crear</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="/DiscoverMe/colaborador/formularioExperiencia">Experiencia</a>
                            <a class="dropdown-item" href="/DiscoverMe/colaborador/formularioOferta">Oferta</a>
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
                    <a href="<spring:url value= '/colaborador/listadoHuespedes/'/>">
                        <i class="fa fa-cloud" aria-hidden="true"></i>
                        <span class="nav-text">
                            Apps Activas
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/colaborador/listadoComentarios/'/>">
                        <i class="fa fa-comment" aria-hidden="true"></i>
                        <span class="nav-text">
                            Comentarios
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/colaborador/listadoExperiencia/'/>">
                        <i class="fa fa-globe" aria-hidden="true"></i>
                        <span class="nav-text">
                            Experiencias
                        </span>
                    </a>
                </li>
                <li class="has-subnav">
                    <a href="<spring:url value= '/colaborador/listadoOfertas/'/>">
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
        <!--Informacion-->
        <div class="container">
            <c:choose>
                <c:when test="${!empty param.info}">
                    <c:choose>
                        <c:when test="${param.info == 'create'}">
                            <div class="container">
                                <div class="row">
                                    <div class="alert alert-success alert-dismissible">
                                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                        Oferta <b>creada</b> con exito.
                                    </div>
                                </div>
                            </div>                            
                        </c:when>
                        <c:when test="${param.info == 'update'}">
                            <div class="container">
                                <div class="row">
                                    <div class="alert alert-success alert-dismissible">
                                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                        Oferta <b>actualizada</b> con exito.
                                    </div>
                                </div>
                            </div>                            
                        </c:when>
                        <c:when test="${param.info == 'delete'}">
                            <div class="container">
                                <div class="row">
                                    <div class="alert alert-success alert-dismissible">
                                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                        Oferta <b>eliminada</b> con exito.
                                    </div>
                                </div>
                            </div>                            
                        </c:when>
                    </c:choose>                         
                </c:when>
            </c:choose>
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
                                <c:forEach items="${experiencia.ofertas}" var="oferta" varStatus="loop">
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <ul class="list-group">
                                                    <li class="list-group-item">
                                                        <div class="media">
                                                            <div class="info media-left">
                                                                <h3>${oferta.nombre}</h3>
                                                                <h3>Codigo: ${oferta.codigo}</h3>
                                                            </div>
                                                            <div class="caja-texto media-body">
                                                                <h5>${oferta.descripcion}</h5>
                                                                <h5>
                                                                    <c:choose>
                                                                        <c:when test="${oferta.fecha_inicio==null}">
                                                                            Todo el año.
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            Desde el ${oferta.fecha_inicio} hasta el ${oferta.fecha_fin}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </h5>
                                                                <h5>
                                                                    <c:choose>
                                                                        <c:when test="${oferta.hora_inicio==null}">
                                                                            Todo el día.
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            Desde las ${oferta.hora_inicio} hasta las ${oferta.hora_fin}
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </h5>
                                                            </div>
                                                            <!--<div class="media-right">-->
                                                            <form action="<spring:url value= '${oferta.id}/'/>">
                                                                <div class="editar media-bottom">
                                                                    <input type="submit" class="btn btn-primary" value="Editar"/>
                                                                </div>
                                                            </form>
                                                            <form action="<spring:url value= 'eliminar/${oferta.id}/'/>">
                                                                <div class="eliminar media-bottom">
                                                                    <input type="submit" class="btn  btn-danger" value="Eliminar"/>
                                                                </div>
                                                            </form>
                                                            <!--</div>-->
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
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>         
    </body>
</html>