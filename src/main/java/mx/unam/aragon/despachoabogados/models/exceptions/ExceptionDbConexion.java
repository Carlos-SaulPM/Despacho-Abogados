package mx.unam.aragon.despachoabogados.models.exceptions;

import java.sql.SQLException;

public class ExceptionDbConexion extends SQLException {
  public ExceptionDbConexion(String mensaje, Throwable causa) {
    super(mensaje, causa);
  }
}
