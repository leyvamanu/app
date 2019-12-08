<%-- 
   Autor: Jose Luis Sánchez Escoda 
--%>

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
        <title>Formulario Usuario Recepcionista</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <!--CSS-->
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_formulario_usuario.css">
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
        <!--Contenido-->
        <div class="container area">
            <div class="col-md-12">
                <!--Titulo-->
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="page-header">
                                <h2>Crear nuevo huésped</h2>

                                <hr>
                                <br>
                            </div>
                        </div>  
                    </div>
                </div>
                <!--Formulario añadir huesped-->
                <div class="container">
                    <form action="../agregarHuesped/" method="POST" id="agregarUsuario">
                      
                        <div class="form-group row">
                            <label for="perfil" class="col-sm-2 col-form-label">Perfil:</label>
                            <div class="col-sm-10">
                                <input maxlength="100" minlength="3" type="text" class="form-control" name="perfil" value="${perfil.nombre}" readonly>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="nombre" class="col-sm-2 col-form-label">Nombre huésped:</label>
                            <div class="col-sm-10">
                                <input maxlength="100" minlength="3" type="text" class="form-control" placeholder="nombre" name="nombre" required>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="procedencia" class="col-sm-2 col-form-label">Procedencia:</label>
                            <div class="col-sm-10">
                                <input maxlength="100" type="text" class="form-control" placeholder="procedencia" name="procedencia" required>
                            </div>
                        </div>
                        <div class="form-group row">

                            <label for="foto_id" class="col-sm-2 col-form-label">Avatar:</label>
                            <div class="col-sm-10">   
                                <div class="form-check text-center d-flex flex-wrap" required>
                                    <c:forEach items="${avatares}" var="avatar">
                                        <div class="centrar text-center">
                                        <label class="form-check-label" for="inlineRadio3"><img src="<spring:url value="/resources/img/${avatar.foto}"/>" alt="${avatar.foto}" class="rounded-circle" width="100"></label>
                                        <input class="form-check-input" type="radio" name="avatar" value="${avatar.id}" required>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <label class="col-sm-2 col-form-label"></label>
                            <div class="col-sm-10">
                                <br>
                                <button  type="submit" class="btn btn-primary">Crear huesped</button>
                                <button  type="reset" class="btn btn-primary">Reiniciar formulario</button>
                            </div>
                        </div>

                    </form>
                </div>
            </div>   
        </div>   
        <!--scripts-->                            
        <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


    </body>
</html>


