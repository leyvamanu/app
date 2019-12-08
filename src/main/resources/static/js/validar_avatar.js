$('form').submit(function(e){
    // si la cantidad de checkboxes "chequeados" es cero,
    // entonces se evita que se envíe el formulario y se
    // muestra una alerta al usuario
    if (!$('input[name="avatar"]').is(':checked')) {
        e.preventDefault();
        alert('Debe seleccionar al menos un avatar');
    }
});