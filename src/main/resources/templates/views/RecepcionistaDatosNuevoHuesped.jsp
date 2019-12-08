<%-- 
    Document   : RecepcionistaDatosNuevoHuesped
    Created on : 18-abr-2019, 12:47:07
    Author     : JoseLuis
--%>
<!--Autor HTML: Jose Luis Sanchez Escoda-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>

<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
        <link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width"/>
        <title>Datos del nuevo Huesped</title>
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
            <a class="nav-brand logo" href="/DiscoverMe/recepcionista/">DiscoverMe</a>
            <button class="navbar-toggler " type="button" data-toggle="collapse" data-target="#navbar-1" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse flex-row-reverse" id="navbar-1">
                <ul class="navbar-nav p-2">
                    <li class="nav-item">
                        <a class="nav-link" href="/DiscoverMe/recepcionista/formularioExperiencia">Crear experiencia</a>
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
        <!-- datos del nuevo huesped-->
        <div class="container area">
            <div class="col-md-12">
                <!--Titulo-->                  
                <div class="container"><br>
                    <div class="row">
                        <div class="col-md-12 center-bloc">
                            <h1>Datos del nuevo Huésped</h1>
                            <hr/>
                        </div>
                    </div>
                </div>
                <form action="generarPDF/" method="POST" id="agregarUsuario">
                    <div class="form-group row">
                        <label for="nombre" class="col-sm-2 col-form-label">Nombre huésped:</label>
                        <div class="col-sm-10">
                            <input maxlength="100" type="text" class="form-control" name="nombre" value="${huesped.nombre}" readonly>
                        </div>
                    </div>                  
                    <div class="form-group row">
                        <label for="nombre" class="col-sm-2 col-form-label">Identificador huésped:</label>
                        <div class="col-sm-10">
                            <input maxlength="100" type="text" class="form-control" name="id" value="${huesped.id}" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="nombre" class="col-sm-2 col-form-label">Contraseña huésped:</label>
                        <div class="col-sm-10">
                            <input maxlength="100" type="text" class="form-control" name="passwd" value="${huesped.passwd}" readonly>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="col-sm-2 col-form-label"></label>
                        <div class="col-sm-10">
                            <br>
                            <!--<button  type="submit" class="btn btn-primary">Generar PDF</button>-->
                            <button class="btn btn-primary hidden-print" onclick="myFunction()"><span class="glyphicon glyphicon-print" aria-hidden="true"></span> Imprimir</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>         
        <script type="text/javascript" src="/DiscoverMe/resources/js/imprimir.js"></script>
    </body>
</html>


