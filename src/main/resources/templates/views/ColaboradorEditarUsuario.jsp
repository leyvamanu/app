<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!--Autor: Carlos Litwiñiuk Zarza-->


<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
        <link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width"/>
        <title>Editar Usuario</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.rawgit.com/Chalarangelo/bootstrap-extend/880420ae663f7c539971ded33411cdecffcc2134/css/bootstrap-extend.min.css"/>
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_administrador.css">
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

        <!--Contenido-->
        <div class="container area">
            <div class="col-md-12">
                <!--Titulo-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="page-header">
                                <h2>Editar usuario</h2>

                                <hr>
                                <br>
                            </div>
                        </div>  
                    </div>
                </div>
                <!--Informacion-->
                <c:choose>
                    <c:when test="${!empty param.info}">
                        <c:choose>
                            <c:when test="${param.info == 'update'}">
                                <div class="container">
                                    <div class="row">
                                        <div class="alert alert-success alert-dismissible">
                                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                            Usuario <b>actualizado</b> con exito.
                                        </div>
                                    </div>
                                </div>                            
                            </c:when>
                        </c:choose>                         
                    </c:when>
                </c:choose>
                <!--Formulario usuario-->
                <div class="container">
                    <form action="../actualizarUsuario/" method="POST" id="agregarUsuario">
                        <div class="form-group row">
                            <label for="nombre" class="col-sm-2 col-form-label">Nombre usuario:</label>
                            <div class="col-sm-10">
                                <input maxlength="100" type="text" class="form-control" placeholder="nombre" name="nombre" value="${user.nombre}" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombre" class="col-sm-2 col-form-label">Contraseña:</label>
                            <div class="col-sm-10">
                                <input maxlength="100" type="text" class="form-control" placeholder="Contraseña" name="password" value="${user.passwd}" required>
                            </div>
                        </div>
                           <div class="form-group row">
                            <label for="foto_id" class="col-sm-2 col-form-label">Avatar:</label>
                            <div class="col-sm-10">   
                                <div class="form-check text-center d-flex flex-wrap" required>
                                    <c:forEach items="${avatares}" var="avatar">
                                        <div class="centrar">
                                            <label class="form-check-label" for="inlineRadio3"><img src="<spring:url value="/resources/img/${avatar.foto}"/>" alt="${avatar.foto}" class="rounded-circle img-avatar"></label>
                                            <input class="form-check-input" type="radio" name="avatar" value="${avatar.id}" 
                                                   <c:choose>
                                                       <c:when test="${avatar.id eq usuario.foto.id}">
                                                           checked
                                                       </c:when>
                                                   </c:choose>
                                                   >
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label"></label>
                            <div class="col-sm-10">
                                <br>
                                <button  type="submit" class="btn btn-primary">Aceptar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>   
        </div>   
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>         
    </body>
</html>