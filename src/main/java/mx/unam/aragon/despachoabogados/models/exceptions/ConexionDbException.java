package mx.unam.aragon.despachoabogados.models.exceptions;

import java.sql.SQLException;

public class ConexionDbException extends SQLException {

  public ConexionDbException(String mensaje) {
    super(mensaje);
  }
  public ConexionDbException(String mensaje, Throwable causa) {
    super(mensaje, causa);
  }
}
