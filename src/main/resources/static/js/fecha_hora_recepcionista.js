/**
 
 * @fileOverview Funciones JQuery para los campos fecha y hora de la página formularioExperiencia
 
 * @author Jose Luis Sánchez Escoda, 04.2019
 
 * @version 0.1
 
 */

"use strict";
$(document).ready(function() {
  $(".datepicker_recep").datepicker(); //funcion jquery-ui para agregar fechas en los formularios
  $("#time_elementrecep1").timepicker(); //https://www.jqueryscript.net/time-clock/Time-Selection-Popover-jQuery-Timepicker.html
  $("#time_elementrecep2").timepicker();
});