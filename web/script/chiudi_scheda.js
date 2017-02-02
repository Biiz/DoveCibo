/* Author: Giacomo Barbieri */

function allerta(){
    if(document.getElementById('input_form').value !== '' && document.getElementById('input_form').value.length >=3){
        document.getElementById('formCercaRisto').submit();
        setTimeout(function() {
            //così dopo 250ms la finestra viene chiusa
            parent.window.close();
        }, 250);
    }
}