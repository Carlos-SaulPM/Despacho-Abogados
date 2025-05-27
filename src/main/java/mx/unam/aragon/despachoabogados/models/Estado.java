package mx.unam.aragon.despachoabogados.models;

import mx.unam.aragon.despachoabogados.models.db.EstadoExpedienteEntidad;
import mx.unam.aragon.despachoabogados.models.exceptions.ConexionDbException;
import mx.unam.aragon.despachoabogados.models.exceptions.FechasInvalidas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Estado {
  private int id_eto;
  private LocalDateTime fecha_hora;
  private String observacion;
  private String estado;

  public Estado() {
  }
  /**
   * Objeto para obtener y guardar de la DB
   */
  public Estado(int id_eto, String fecha_hora, String observacion, String estado) throws FechasInvalidas {
    try {
      this.id_eto = id_eto;
      this.observacion = observacion;
      this.estado = estado;
      this.fecha_hora = parsearFecha(fecha_hora);
    } catch (DateTimeParseException e) {
      throw new FechasInvalidas("El formato para parsear la fecha es invalido");
    }
  }

  private LocalDateTime parsearFecha(String fechaHora) throws DateTimeParseException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    return LocalDateTime.parse(fechaHora, formatter);
  }

  /** Actualiza el estado actual con la DB, pero se debe de pasar la referencia del expediente (this).
   * */
  public void actualizarEstado(Expediente expediente) throws ConexionDbException {
    Estado nuevoEstado = EstadoExpedienteEntidad.obtenerEstado(expediente);
    this.id_eto = nuevoEstado.getId_eto();
    this.fecha_hora = nuevoEstado.getFecha_hora();
    this.observacion = nuevoEstado.getObservacion();
    this.estado = nuevoEstado.getEstado();
  }

  //ToString
  @Override
  public String toString() {
    return "Estado{" +
            "id_eto=" + id_eto +
            ", fecha_hora=" + fecha_hora +
            ", observacion='" + observacion + '\'' +
            ", estado='" + estado + '\'' +
            '}';
  }

  //GETTERS Y SETTERS
  public int getId_eto() {
    return id_eto;
  }

  public void setId_eto(int id_eto) {
    this.id_eto = id_eto;
  }

  public LocalDateTime getFecha_hora() {
    return fecha_hora;
  }

  public void setFecha_hora(LocalDateTime fecha_hora) {
    this.fecha_hora = fecha_hora;
  }

  public String getObservacion() {
    return observacion;
  }

  public void setObservacion(String observacion) {
    this.observacion = observacion;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }
}
