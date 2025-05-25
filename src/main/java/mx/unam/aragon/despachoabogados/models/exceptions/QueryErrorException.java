package mx.unam.aragon.despachoabogados.models.exceptions;

import java.sql.SQLException;

public class QueryErrorException extends SQLException {
  public QueryErrorException(String message) {
    super(message);
  }
  public QueryErrorException(String message, Throwable causa) {
    super(message, causa);
    System.err.println(causa);
  }
}
