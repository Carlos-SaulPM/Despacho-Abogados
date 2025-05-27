package mx.unam.aragon.despachoabogados.models;

import mx.unam.aragon.despachoabogados.models.db.ExpedienteEntidad;
import mx.unam.aragon.despachoabogados.models.exceptions.ConexionDbException;
import mx.unam.aragon.despachoabogados.models.exceptions.FechasInvalidas;
import mx.unam.aragon.despachoabogados.models.exceptions.QueryErrorException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Expediente {
  private int id_epe;
  private String asunto;
  private int id_cte;
  private int id_tco;
  private String tipo_participacion_cliente;
  private LocalDateTime fecha_inicio;
  private LocalDateTime fecha_fin;
  private Estado estado;

  /**
   * Objeto para guardar los datos que se obtienen de la base de datos
   * @param id_epe es el id del expediente.
   * @param id_cte es el id del cliente.
   * @param id_tco es el id del tipo de caso
   * @throws FechasInvalidas Se produce cuando la fecha de inicio esta después de la fecha fin. No son coherentes las fechas
   */
  public Expediente(int id_epe, String asunto, String tipo_participacion_cliente, Timestamp fecha_inicio, Timestamp fecha_fin, int id_cte, int id_tco) throws FechasInvalidas {
    this.id_epe = id_epe;
    this.asunto = asunto;
    this.id_cte = id_cte;
    this.id_tco = id_tco;
    this.tipo_participacion_cliente = tipo_participacion_cliente;
    if (!verificarFechas(fecha_inicio.toLocalDateTime(), fecha_fin.toLocalDateTime()))
      throw new FechasInvalidas("Fechas inválidas, fecha inicio esta después de fecha fin");
    this.fecha_inicio = fecha_inicio.toLocalDateTime();
    this.fecha_fin = fecha_fin.toLocalDateTime();
    this.estado = new Estado();
  }
  /**
   * Objeto para recibir datos de las vistas
   * @param id_epe es el id del expediente.
   * @param id_cte es el id del cliente.
   * @param id_tco es el id del tipo de caso
   * @throws FechasInvalidas Se produce cuando la fecha de inicio esta después de la fecha fin. No son coherentes las fechas
  */
  public Expediente(int id_epe, String asunto,String tipo_participacion_cliente, LocalDate fechaInicio, LocalDate fechaFin, int id_cte, int id_tco) throws FechasInvalidas {
    this.id_epe = id_epe;
    this.asunto = asunto;
    this.id_cte = id_cte;
    this.id_tco = id_tco;
    this.tipo_participacion_cliente = tipo_participacion_cliente;
    LocalDateTime fechaInicioCompleta = convertirAFechaYHora(fechaInicio);
    LocalDateTime fechaFinCompleta = convertirAFechaYHora(fechaFin);
    if (!verificarFechas(fechaInicioCompleta, fechaFinCompleta))
      throw new FechasInvalidas("Fechas inválidas, fecha inicio esta después de fecha fin");
    this.fecha_inicio = fechaInicioCompleta;
    this.fecha_fin = fechaFinCompleta;
    this.estado = new Estado();
  }

  //Funciones Auxiliares
  private boolean verificarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
    return fechaInicio.isBefore(fechaFin);
  }
  private LocalDateTime convertirAFechaYHora(LocalDate fecha) {
    return LocalDateTime.of(fecha, LocalTime.now());
  }

  public void actualizarExpediente() throws QueryErrorException {
    ExpedienteEntidad.actualizarExpediente(this);
  }

  @Override
  public String toString() {
    return "Expediente{" +
            "id_epe=" + id_epe +
            ", asunto='" + asunto + '\'' +
            ", id_cte=" + id_cte +
            ", id_tco=" + id_tco +
            ", tipo_participacion_cliente='" + tipo_participacion_cliente + '\'' +
            ", fecha_inicio=" + fecha_inicio +
            ", fecha_fin=" + fecha_fin +
            '}';
  }

  //GETTERS
  public int getId_epe() {
    return id_epe;
  }

  public String getAsunto() {
    return asunto;
  }

  public int getId_cte() {
    return id_cte;
  }

  public int getId_tco() {
    return id_tco;
  }

  public String getTipo_participacion_cliente() {
    return tipo_participacion_cliente;
  }

  public LocalDateTime getFecha_inicio() {
    return fecha_inicio;
  }

  public LocalDateTime getFecha_fin() {
    return fecha_fin;
  }
  // SETTERS
  public void setId_epe(int id_epe) {
    this.id_epe = id_epe;
  }

  public void setAsunto(String asunto) {
    this.asunto = asunto;
  }

  public void setId_cte(int id_cte) {
    this.id_cte = id_cte;
  }

  public void setId_tco(int id_tco) {
    this.id_tco = id_tco;
  }

  public void setTipo_participacion_cliente(String tipo_participacion_cliente) {
    this.tipo_participacion_cliente = tipo_participacion_cliente;
  }

  public void setFecha_inicio(LocalDateTime fecha_inicio) {
    this.fecha_inicio = fecha_inicio;
  }

  public void setFecha_fin(LocalDateTime fecha_fin) {
    this.fecha_fin = fecha_fin;
  }

  public Estado getEstado() throws ConexionDbException {
    if(this.estado.getId_eto() == 0){this.estado.actualizarEstado(this);}
    return estado;
  }
}
