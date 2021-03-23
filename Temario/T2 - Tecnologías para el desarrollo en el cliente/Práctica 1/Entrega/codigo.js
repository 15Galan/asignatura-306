function validar(){
  var nombre = document.getElementByName("nombre");
  var apellidos = document.getElementByName("apellidos");

  var mensaje = "";
  var correcto = true;

  if (nombre == 'undefined') {
    mensaje = mensaje + "Nombre vacío\n";
    correcto = false;
  }

  if (apellidos == 'undefined') {
    mensaje = mensaje + "Apellidos vacío\n";
    correcto = false;
  }

  if (correcto === false) {
    alert(mensaje);
  }

  return correcto;
}
