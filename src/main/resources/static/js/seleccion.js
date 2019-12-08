/**
 
 * @fileOverview Funciones JQuery para seleccionar y resaltar elementos de la página listadoServicios.jsp y listadoSitios.jsp
 
 * @author Jose Luis Sánchez Escoda, 04.2019
 
 * @version 0.1
 
 */

"use strict";

/**
 * @function
 * @description Funcion principal
 */
$(document).ready(function() {
  addServicioDisponible();
  cambiaFondoServicio();
  seleccionaServicio();

  /**
   * @function
   * @description Realiza un submit del formulario de la actividad seleccionada
   */
  function seleccionaServicio() {
    $("ul.lista-enable").click(function() {
      $(this)
        .find("form")
        .submit();
    });
  }
  /**
   * @function
   * @description Cambia el color del fondo de los elementos que contienen actividad para destacar el seleccionado
   *
   */
  function cambiaFondoServicio() {
    $(".actividad").mouseover(function() {
      $(this).css("background-color", "#F2F2F2");
    });
    $(".actividad").mouseout(function() {
      $(this).css("background-color", "white");
    });
  }
  /**
   * @function
   * @description añade al elemento ul.list-group la clase que activa el poder hacer submit al servicio concreto
   *
   */
  function addServicioDisponible() {
    $("input.disponible")
      .closest("ul.list-group")
      .addClass("lista-enable");
  }
});
