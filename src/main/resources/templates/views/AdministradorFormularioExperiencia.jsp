<!-- 
   Autor: Jose Luis Sánchez Escoda 
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>


<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
        <link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Experiencia</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
              integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_administrador.css">
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
        <!--vista inicial formulario experiencia-->
        <div class="container area">
            <div class="col-md-12">
                <!--Titulo-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="page-header">
                                <h2>Crear experiencia</h2>

                                <hr>
                                <br>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Errores-->
                <c:choose>
                    <c:when test="${!empty param.error}">
                        <c:choose>
                            <c:when test="${param.error == 'nombre'}">
                                <div class="container">
                                    <div class="row">
                                        <div class="alert alert-danger alert-dismissible">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            El <b>nombre</b> de la experiencia ya existe.
                                        </div>
                                    </div>
                                </div>                            
                            </c:when>
                        </c:choose>                         
                    </c:when>
                </c:choose>
                <!--Formulario añadir experiencia-->
                <div class="container">
                    <form id="formExperiencia" action="agregarExperiencia/" method="POST" enctype="multipart/form-data">

                        <div class="form-group row">
                            <label for="nombre" class="col-sm-2 col-form-label">Nombre experiencia:</label>
                            <div class="col-sm-10">
                                <input maxlength="100" type="text" class="form-control" placeholder="nombre" name="nombre" required>
                            </div>
                        </div>
                        <div class="form-group row ">
                            <label for="fecha_inicio" class="col-sm-2 col-form-label">Inicio:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control datepicker" name="fecha_inicio">
                            </div>
                            <label for="hora_inicio" class="col-sm-1 col-form-label">Hora:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control"  id="time_element1" name="hora_inicio">
                            </div>

                        </div>
                        <div class="form-group row">
                            <label for="fecha_fin" class="col-sm-2 col-form-label">Fin:</label>
                            <div class="col-sm-5">
                                <input type="text" class="form-control datepicker" name="fecha_fin">
                            </div>
                            <label for="hora_fin" class="col-sm-1 col-form-label">Hora:</label>
                            <div class="col-sm-4">
                                <input type="text" class="form-control" id="time_element2" name="hora_fin">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="descripcion" class="col-sm-2 col-form-label">Descripción:</label>
                            <div class="col-sm-10">
                                <textarea maxlength="250" class="form-control" rows="4" placeholder="descripcion" name="descripcion"
                                          required></textarea>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="precio" class="col-sm-2 col-form-label">Precio:</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="precio" placeholder="€">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="distancia" class="col-sm-2 col-form-label">Distancia:</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="distancia" placeholder="minutos" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="mapa" class="col-sm-2 col-form-label">Selecciona mapa:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="mapa" id="formControlFile">
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="imagenDestacada" class="col-sm-2 col-form-label">Selecciona imagen destacada:</label>
                            <div class="col-sm-10">
                                <input type="file" class="form-control-file" name="imagenDestacada" id="formControlFile" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="imagen1" class="col-sm-2 col-form-label">Selecciona imagen:</label>
                            <div class="col-sm-10">
                                <input type="file" class="form-control-file" name="imagen1" id="formControlFile" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="imagen2" class="col-sm-2 col-form-label">Selecciona imagen:</label>
                            <div class="col-sm-10">
                                <input type="file" class="form-control-file" name="imagen2" id="formControlFile" required>
                            </div>
                        </div>

                        <div class="form-group row">
                            <label for="imagen3" class="col-sm-2 col-form-label">Selecciona imagen:</label>
                            <div class="col-sm-10">
                                <input type="file" class="form-control-file" name="imagen3" id="formControlFile" required>
                            </div>
                        </div>

                        <div class="row">
                        </div>       
                        <div class="form-group row" >

                            <label for="tipo" class="col-sm-2 col-form-label">Tipo de experiencia:</label>
                            <div class="col-sm-10">


                                <div class="form-check form-check-inline" >

                                    <c:forEach items="${tipos}" var="tipo">

                                        <div class="form-check form-check-inline">
                                            <input class="form-check-input" type="checkbox" name="tipo" id="nocturno" value="${tipo.id}">
                                            <label class="form-check-label" for="inlineRadio3">${tipo.nombre}</label>
                                        </div>

                                    </c:forEach> 
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label"></label>
                            <div class="col-sm-10">
                                <br>
                                <button type="submit" class="btn btn-primary">Crear experiencia</button>
                                <button type="button" id="reinicia" class="btn btn-primary">Reiniciar formulario</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--scripts-->
        <script src="https://code.jquery.com/jquery-3.3.1.min.js"
        integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script type="text/javascript" src="/DiscoverMe/resources/js/timepicker.js"></script>
        <script type="text/javascript" src="/DiscoverMe/resources/js/fecha_hora.js"></script>
        <script type="text/javascript" src="/DiscoverMe/resources/js/validar_tipo.js"></script>
    </body>
</html>
