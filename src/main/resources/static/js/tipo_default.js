/** 
 
 * @fileOverview Funciones JQuery para el tipo del formulario sea cultural por defecto
 
 * @author Jose Luis Sánchez Escoda, 03.2019
 
 * @version 0.1
 
 */

"use strict";
/**
 * @function
 * @description Funcion principal
 */
$(document).ready(function () {
    seleccionaPrimerTipo();
    botonReset();

    function seleccionaPrimerTipo() {
        $("input.form-check-input")
                .first()
                .prop("checked", true);
    }

    function botonReset() {
        $("button#reinicia").click(function () {

            location.reload();

        });
    }
});
