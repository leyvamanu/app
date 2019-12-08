<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--Autor HTML: Carlos Litwiñiuk-->


<!DOCTYPE html>
<html>
    <head>
        <!--Favicon-->
        <link rel="icon" href="/DiscoverMe/resources/iconos/favicon.png" type="image/png" sizes="32x32">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width"/>
        <title>Acceso usuarios</title>
        <!--CDN-->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <!--CSS-->
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link rel="stylesheet" type="text/css" href="/DiscoverMe/resources/css/estilos_login.css">
    </head>
    <body>
        <!--Login-->
        <div class="container">
            <div class="logo">
                <h1>
                    DiscoverMe
                </h1>
            </div>
            <div class="d-flex justify-content-center">
                <div class="card h-100">
                    <div class="card-header text-center">
                        <h3>Acceso clientes</h3>
                    </div>
                    <div class="card-body">
                        <form action="<spring:url value= '/login/'/>" method="POST">
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                                </div>
                                <input type="text" class="form-control" placeholder="Usuario" name="nombre" value="${usuarioDemo.id}">
                            </div>
                            <div class="input-group form-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text"><i class="fas fa-key"></i></span>
                                </div>
                                <input type="password" class="form-control" placeholder="Contraseña" name="password" value="${usuarioDemo.passwd}">
                            </div>
                            <br>
                            <c:choose>
                                <c:when test="${!empty error}">
                                    <p class="alert alert-danger">${error}</p>
                                </c:when>
                            </c:choose>
                            <div class="form-group text-center">
                                <input type="submit" value="Acceder" class="btn login_boton btn-block">
                            </div>
                        </form>
                    </div>
                    <div class="card-footer">
                        <div class="d-flex">
                            <a href="#" data-toggle="modal" data-target="#modal-olvidoPassword">Olvidaste tu password?</a>
                        </div>
                        <div role="dialog" tabindex="-1" class="modal fade" id="modal-olvidoPassword" 
                             style="max-width:600px;margin-right:auto;margin-left:auto;">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header"> <!-- CABECERA -->
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                                        <h4 class="text-center modal-title">Generar contraseña nueva</h4>
                                    </div>
                                    <div class="modal-body"> <!-- CUERPO DEL MENSAJE -->
                                        <p>Para recuperar la contraseña, debe de dirigirse a recepción y pedir que le faciliten una nueva.</p>
                                    </div>
                                    <div class="modal-footer"> <!-- PIE -->
                                        <button class="btn btn-default btn btn-primary btn-lg" type="button" data-dismiss="modal">Cerrar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>       
    </body>
</html>