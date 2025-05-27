package mx.unam.aragon.despachoabogados.models.db;

import mx.unam.aragon.despachoabogados.models.Expediente;
import mx.unam.aragon.despachoabogados.models.exceptions.ConexionDbException;
import mx.unam.aragon.despachoabogados.models.exceptions.FechasInvalidas;
import mx.unam.aragon.despachoabogados.models.exceptions.QueryErrorException;

import java.sql.*;

public class ExpedienteEntidad {

  /**
   * Obtiene el expediente de la base de datos por medio del id.
   *
   * @param id_epe Id del expediente a obtener.
   **/
  public static Expediente obtenerExpediente(int id_epe) throws QueryErrorException {
    try (Connection con = DbConexion.getConexion();
         PreparedStatement stmt = con.prepareStatement("SELECT * FROM expediente WHERE id_epe = ?");) {
      stmt.setInt(1, id_epe);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Expediente(
                rs.getInt("id_epe"),
                rs.getString("asunto"),
                rs.getString("tipo_participacion_cliente"),
                rs.getTimestamp("fecha_inicio"),
                rs.getTimestamp("fecha_fin"),
                rs.getInt("id_cte"),
                rs.getInt("id_tco"));
      } else {
        throw new QueryErrorException("No se encontró el expediente con ID: " + id_epe);
      }

    } catch (ConexionDbException e) {
      throw new QueryErrorException("Error de conexión al obtener expediente", e);
    } catch (SQLException e) {
      throw new QueryErrorException("Error en la consulta del expediente", e);
    } catch (FechasInvalidas e) {
      throw new QueryErrorException("Fecha inválida en el expediente", e);
    }
  }

  /**
   * Obtiene los datos actuales del expediente en la DB.
   *
   * @param expediente Debe tener un id_epe definido.
   */
  public static Expediente obtenerExpediente(Expediente expediente) throws QueryErrorException {
    try (Connection con = DbConexion.getConexion();
         PreparedStatement stmt = con.prepareStatement("SELECT * FROM expediente WHERE id_epe = ?");) {
      stmt.setInt(1, expediente.getId_epe());
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        return new Expediente(
                rs.getInt("id_epe"),
                rs.getString("asunto"),
                rs.getString("tipo_participacion_cliente"),
                rs.getTimestamp("fecha_inicio"),
                rs.getTimestamp("fecha_fin"),
                rs.getInt("id_cte"),
                rs.getInt("id_tco"));
      } else {
        throw new QueryErrorException("No se encontró el expediente con ID: " + expediente.getId_epe());
      }
    } catch (FechasInvalidas e) {
      throw new QueryErrorException("Fechas invalidas en obtenerExpediente");
    } catch (SQLException e) {
      throw new QueryErrorException("Error en la consulta obtenerExpediente", e);
    }
  }

  /**
   * Actualiza los datos modificados del expediente en la base de datos
   *
   * @param expediente Objeto de la clase Expediente, debe de tener los cambios a realizar.
   * @return boolean - Retorna true o false, si la operacion fue exitosa.
   */
  public static boolean actualizarExpediente(Expediente expediente) throws QueryErrorException {
    boolean seActualizoExpediente = false;
    try (Connection con = DbConexion.getConexion();
         CallableStatement sp = con.prepareCall("{CALL sp_actualizar_expediente(?,?,?,?,?,?,?,?)}");) {
      sp.setInt(1, expediente.getId_epe());
      sp.setString(2, expediente.getAsunto());
      sp.setString(3, expediente.getTipo_participacion_cliente());
      sp.setTimestamp(4, Timestamp.valueOf(expediente.getFecha_inicio()));
      sp.setTimestamp(5, Timestamp.valueOf(expediente.getFecha_fin()));
      sp.setInt(6, expediente.getId_cte());
      sp.setInt(7, expediente.getId_tco());
      sp.registerOutParameter(8, Types.BOOLEAN);
      sp.execute();
      seActualizoExpediente = sp.getBoolean(8);
      if (seActualizoExpediente) return seActualizoExpediente;
    } catch (SQLException e) {
      throw new QueryErrorException("Error en actualizarExpediente "+expediente.getId_epe(), e);
    }
    return seActualizoExpediente;
  }

}
