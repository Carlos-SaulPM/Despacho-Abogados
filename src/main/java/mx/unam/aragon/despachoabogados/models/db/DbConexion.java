package mx.unam.aragon.despachoabogados.models.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import mx.unam.aragon.despachoabogados.models.exceptions.ConexionDbException;

public class DbConexion {
  private static final String URL = "jdbc:mariadb://localhost:3306/";
  private static final String DB = "dbAbogados";
  private static final String USUARIO= "root";
  private static final String CONTRASENA = "110403";

  /**
   * @throws ConexionDbException Lanzada cuando existe algun error en la conexion a la Db
   * */
  public static Connection getConexion() throws ConexionDbException {
    try {
      return DriverManager.getConnection(URL + DB, USUARIO, CONTRASENA);
    } catch (SQLException e) {
      throw new ConexionDbException("ERROR EN LA CONEXIÓN A LA BASE DE DATOS", e);
    }
  }
}
