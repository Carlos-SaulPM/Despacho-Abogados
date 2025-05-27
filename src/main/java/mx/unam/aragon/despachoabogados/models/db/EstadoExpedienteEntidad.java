package mx.unam.aragon.despachoabogados.models.db;

import mx.unam.aragon.despachoabogados.models.Estado;
import mx.unam.aragon.despachoabogados.models.Expediente;
import mx.unam.aragon.despachoabogados.models.exceptions.ConexionDbException;
import mx.unam.aragon.despachoabogados.models.exceptions.FechasInvalidas;
import mx.unam.aragon.despachoabogados.models.exceptions.QueryErrorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EstadoExpedienteEntidad {


  public static Estado obtenerEstado(Expediente expediente) throws ConexionDbException {
    try (Connection con = DbConexion.getConexion();
         PreparedStatement stmt = con.prepareStatement("SELECT * FROM expedienteEstadoActual as eea WHERE eea.id_epe = ?")) {
      stmt.setInt(1, expediente.getId_epe());
      ResultSet rs = stmt.executeQuery();
      if(rs.next()){
        return new Estado(
                rs.getInt("id_eto"),
                rs.getString("fecha_hora"),
                rs.getString("observacion"),
                rs.getString("estado_expediente")
        );
      }
    } catch (ConexionDbException e) {
      throw new ConexionDbException("Obtener estado " + e.getMessage(), e);
    } catch (SQLException e) {
      System.err.println(e.getMessage());
    }catch (FechasInvalidas e){
      System.err.println(e);
    }

    return null;
  }


}
