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
        <title>Experiencias</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_listados.css">
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
        <!--Listado Ofertas Servicios Hotel-->
        <!--Titulo-->                  
        <div class="container"><br>
            <div class="row">
                <div class="col-md-12 center-bloc">
                    <h1>Ofertas Servicios Hotel</h1>
                    <hr/>
                </div>
            </div>
        </div>
        <div class="container" id="accordion">
            <c:forEach items="${ofertasServicios}" var="ofertaServicios" varStatus="loop">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="media">
                                        <div class="info media-left">
                                            <h3>${ofertaServicios.nombre}</h3>
                                            <h3>Codigo: ${ofertaServicios.codigo}</h3>
                                        </div>
                                        <div class="caja-texto media-body">
                                            <h5>${ofertaServicios.descripcion}</h5>
                                            <h5>
                                                <c:choose>
                                                    <c:when test="${ofertaServicios.fecha_inicio==null}">
                                                        Todo el año.
                                                    </c:when>
                                                    <c:otherwise>
                                                        Desde el ${ofertaServicios.fecha_inicio} hasta el ${ofertaServicios.fecha_fin}
                                                    </c:otherwise>
                                                </c:choose>
                                            </h5>
                                            <h5>
                                                <c:choose>
                                                    <c:when test="${ofertaServicios.hora_inicio==null}">
                                                        Todo el día.
                                                    </c:when>
                                                    <c:otherwise>
                                                        Desde las ${ofertaServicios.hora_inicio} hasta las ${ofertaServicios.hora_fin}
                                                    </c:otherwise>
                                                </c:choose>
                                            </h5>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!--Listado Ofertas Servicios Hotel-->
        <!--Titulo-->                  
        <div class="container"><br>
            <div class="row">
                <div class="col-md-12 center-bloc">
                    <h1>Ofertas Experiencias</h1>
                    <hr/>
                </div>
            </div>
        </div>
        <div class="container" id="accordion">
            <c:forEach items="${ofertasExperiencias}" var="ofertaExperiencias" varStatus="loop">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="media">
                                        <div class="info media-left">
                                            <h3>${ofertaExperiencias.nombre}</h3>
                                            <h3>Codigo: ${ofertaExperiencias.codigo}</h3>
                                        </div>
                                        <div class="caja-texto media-body">
                                            <h5>${ofertaExperiencias.descripcion}</h5>
                                            <h5>
                                                <c:choose>
                                                    <c:when test="${ofertaExperiencias.fecha_inicio==null}">
                                                        Todo el año.
                                                    </c:when>
                                                    <c:otherwise>
                                                        Desde el ${ofertaExperiencias.fecha_inicio} hasta el ${ofertaExperiencias.fecha_fin}
                                                    </c:otherwise>
                                                </c:choose>
                                            </h5>
                                            <h5>
                                                <c:choose>
                                                    <c:when test="${ofertaExperiencias.hora_inicio==null}">
                                                        Todo el día.
                                                    </c:when>
                                                    <c:otherwise>
                                                        Desde las ${ofertaExperiencias.hora_inicio} hasta las ${ofertaExperiencias.hora_fin}
                                                    </c:otherwise>
                                                </c:choose>
                                            </h5>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>       
    </body>
</html>