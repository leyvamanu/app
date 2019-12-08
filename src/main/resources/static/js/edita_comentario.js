/** 
 
 * @fileOverview Funciones JQuery para puntuar comentarios con estrellas, hacer scroll dentro de la página actividad.jsp (experiencia.jsp)
 
 * @author Jose Luis Sánchez Escoda, 06-05-2019
 
 * @version 0.1
 
 */

"use strict";
/**
 * @function
 * @description Funcion principal
 */
$(document).ready(function() {
  
  var puntuacion = $("#puntuacionNumerica").val();
  pintaAmarilloEstrellas(puntuacion);
  puntuaEstrellas(); 
    
    
    
  function puntuaEstrellas() {
    $("i.fa-2x").click(function() {
      $(".alert").hide(250);
      var index = $("i.fa-2x").index(this);
      index = index + 1;
      puntuacion = index;
      $("#puntuacionNumerica").val(puntuacion);
      pintaBlancoEstrellas();
      pintaAmarilloEstrellas(puntuacion);
    });
  }

  /**
   * @function
   * @description Cambia las estrellas a blanco
   *
   */
  function pintaBlancoEstrellas() {
    $("i.fa-2x").each(function(index, el) {
      if ($(el).hasClass("fas")) {
        $(el).removeClass("fas");
        $(el).addClass("far");
        $(el).css("color", "black");
      }
    });
  }  
    
    /**
   * @function
   * @description Cambia las estrellas seleccionadas a amarillo
   *
   */
  function pintaAmarilloEstrellas(puntuacion) {
    $("i.fa-2x").each(function(value, el) {
      $(el).removeClass("far");
      $(el).addClass("fas");
      $(el).css("color", "#FFD700");
      return value !== puntuacion - 1;
    });
  }
  
  /**
   * @function
   * @description Realiza un scroll del boton 'ver comentarios' hacia el listado de comentarios
   *
   */
  function scrollAComentarios() {
    $("button#ir_a_comentarios").click(function() {
      /*NOTA: para que funcione animate hay que usar la version https://code.jquery.com/jquery-3.3.1.min.js 
               la version slim no tiene todas las funciones*/
      $("html, body").animate(
        {
          scrollTop: $("h1#lista_comentarios").offset().top
        },
        "slow"
      );
    });
  }

  /**
   * @function
   * @description Realiza un submit del formulario, en caso que no esten puntuadas las estrellas salta aviso
   *
   */
  function enviaComent() {
    $("button#enviaComent").click(function() {
      console.log("has hecho click en enviar comentario!");
      console.log(puntuacion);
      if (puntuacion == 0) {
        $(".alert").show(250);
      }
    });
  }
  
 });

