/* 
 * Script para las ventanas emergentes tipo modal
 * @author Manuel Leyva
 */

var fotos = document.getElementsByClassName('columna foto');
for(var i = 0; i < fotos.length; i++){
    var modal = document.getElementById('modal-'+i);

    // Get the image and insert it inside the modal - use its "alt" text as a caption
    var img = document.getElementById('img-'+i);
    var modalImg = document.getElementById("img-modal-"+i);
    var captionText = document.getElementById("caption-"+i);
    img.onclick = function () {
        modal.style.display = "block";
        modalImg.src = this.src;
        captionText.innerHTML = this.alt;
    };

    // Get the <span> element that closes the modal
    var span = document.getElementById("close-"+i);

    // When the user clicks on <span> (x), close the modal
    span.onclick = function () {
        modal.style.display = "none";
    };
};