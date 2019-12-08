<%-- 
    Document   : editarServicio
    Created on : 11-abr-2019, 10:02:59
    Author     : JoseLuis
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!--Autor: Carlos Litwiñiuk Zarza-->


<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
        <link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width"/>
        <title>Editar Servicio</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
              integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.rawgit.com/Chalarangelo/bootstrap-extend/880420ae663f7c539971ded33411cdecffcc2134/css/bootstrap-extend.min.css"/>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_listado_administrador.css">
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_barra_lateral.css">
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/timepicker.css">
        

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
        <!--vista inicial del servicio hotel-->
        <div class="container area">
            <div class="col-md-12">
                <!--Título-->
                <div class="container"><br>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="page-header">
                                <h2>Editar servicio hotel</h2>
                            </div>
                            <hr/>
                        </div>  
                    </div>
                </div>	
                <!--Formulario añadir servicio hotel-->
                <div class="container">
                    <form id="formServicio" action="../actualizar/${servicio.id}" method="POST" enctype="multipart/form-data">
                        
                        <div class="form-group row">    
                        <c:choose>
                           <c:when test="${servicio.disponible=='0'}">
                            <label for="stackedCheck1" class="col-sm-9 form-check-label">Disponibilidad:</label>
                            <div class="col-sm-3">
                                <div class="form-group row"> 
                                <label><input type="radio" name="disponible" value="true" ><span class="onofd"> Disponible </span></label>
                                <label><input type="radio"  name="disponible" value="false" checked><span class="onof"> No disponible</span> </label>
                                </div>
                            </div>
                            </c:when>
                            <c:otherwise>
                            <label for="stackedCheck1" class="col-sm-9 form-check-label">Disponible:</label>
                            <div class="col-sm-3">
                                <div class="form-group row"> 
                                    <label><input type="radio" name="disponible" value="true" checked><span class="onofd">Disponible</span> </label>
                                <label><input type="radio" name="disponible" value="false"><span class="onof">No disponible</span> </label>
                                </div>
                            </div>                            
                            </c:otherwise>
                        </c:choose>    
                        </div>
                        <div class="form-group row">
                            <label for="nombre" class="col-sm-2 col-form-label">Nombre servicio:</label>
                            <div class="col-sm-10">
                                <input maxlength="100" type="text" class="form-control" id="inputNombre" placeholder="Nombre del servicio..." name="nombre" value="${servicio.nombre}" required>
                            </div>
                        </div>
                        <c:set var="pattern" value="MM/dd/yyyy"/>
                        <div class="form-group row ">
                            <label for="fecha_inicio" class="col-sm-2 col-form-label">Inicio:</label>
                            <div class="col-sm-5">
                                <fmt:parseDate value="${servicio.fecha_inicio}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
                                <input type="text" class="form-control datepicker" name="fecha_inicio" value="<fmt:formatDate value="${parsedDate}" type="date" pattern="${pattern}"/>">
                            </div>
                            <label for="hora_inicio" class="col-sm-1 col-form-label">Hora:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"  id="time_element1" name="hora_inicio" value="${servicio.hora_inicio}" >
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="fecha_fin" class="col-sm-2 col-form-label">Fin:</label>
                            <div class="col-sm-5">
                                <fmt:parseDate value="${servicio.fecha_fin}" pattern="yyyy-MM-dd" var="parsedDate" type="date"/>
                                <input type="text" class="form-control datepicker" name="fecha_fin" value="<fmt:formatDate value="${parsedDate}" type="date" pattern="${pattern}"/>">
                            </div>
                            <label for="hora_fin" class="col-sm-1 col-form-label">Hora:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="time_element2" name="hora_fin" value="${servicio.hora_fin}" >
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="descripcion" class="col-sm-2 col-form-label">Descripción:</label>
                            <div class="col-sm-10">
                                <textarea maxlength="250" class="form-control" rows="4" placeholder="Información sobre el servicio..." 
                                          name="descripcion" required>${servicio.descripcion}</textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="precio" class="col-sm-2 col-form-label">Precio:</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="precio" placeholder="€" value="${servicio.precio}">
                            </div>
                        </div>
                        <c:forEach items="${servicio.fotos}" var="foto">
                            <c:choose>
                                <c:when test="${foto.logo==true}">
                                    <div class="form-group row">
                                        <div class="col-sm-2"><img src="<spring:url value="/resources/img/${foto.foto}"/>" class="imagen img-thumbnail img-responsive" alt="dibujo" width="150"></div>
                                        <label for="imagenDestacada" class="col-sm-3 col-form-label">Selecciona imagen destacada:</label>
                                        <div class="col-sm-7">
                                            <input type="file" class="form-control-file" name="imagenDestacada" id="formControlFile">
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:forEach> 
                        <c:set var="loop" value="1" scope="page" />
                        <c:forEach items="${servicio.fotos}" var="foto">
                            <c:choose>
                                <c:when test="${foto.logo!=true}">
                                    <div class="form-group row">
                                        <div class="col-sm-2"><img src="<spring:url value="/resources/img/${foto.foto}"/>" class="imagen img-thumbnail img-responsive" alt="dibujo" width="150"></div>
                                        <label for="imagen${loop}" class="col-sm-3 col-form-label">Selecciona imagen:</label>
                                        <div class="col-sm-7">
                                            <input type="file" class="form-control-file" name="imagen${loop}" id="formControlFile"  accept="image/png, .jpeg, .jpg">
                                        </div>
                                    </div>
                                    <c:set var="loop" value="${loop + 1}" scope="page"/>
                                </c:when>
                            </c:choose>
                        </c:forEach> 
                        <div class="form-group row">
                            <!--<label class="col-sm-2 col-form-label"></label>-->
                            <!--<div class="col-sm-10">-->
                                <br>
                                <button type="submit" class="btn btn-primary editar">Aceptar</button>
                            <!--</div>-->
                            <!--</div>-->
                                </form>
                                <form  action="<spring:url value= 'eliminar'/>">
                                <button type="submit" class="btn btn-danger" formaction='eliminar'>Eliminar</button>
                                <!--<input type="submit" class="btn  btn-danger" value="Eliminar"/>-->
                                </form>
                </div>
            </div>
        </div> 
        </diV

        <!--scripts-->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript" src="/DiscoverMe/resources/js/timepicker.js"></script>
        <script type="text/javascript" src="/DiscoverMe/resources/js/fecha_hora.js"></script>
        
        
    </body>
</html>