package mx.unam.aragon.despachoabogados.models;

import mx.unam.aragon.despachoabogados.models.exceptions.FechasInvalidas;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Expediente {
  private int id_epe;
  private String asunto;
  private int id_cte;
  private int id_tco;
  private String tipo_participacion_cliente;
  private LocalDateTime fecha_inicio;
  private LocalDateTime fecha_fin;

  public Expediente(int id_epe, String asunto, int id_cte, int id_tco, String tipo_participacion_cliente, Timestamp fecha_inicio, Timestamp fecha_fin) throws FechasInvalidas {
    this.id_epe = id_epe;
    this.asunto = asunto;
    this.id_cte = id_cte;
    this.id_tco = id_tco;
    this.tipo_participacion_cliente = tipo_participacion_cliente;
    if(!verificarFechas(fecha_inicio.toLocalDateTime(),fecha_fin.toLocalDateTime()))
      throw new FechasInvalidas("La fecha de inicio esta después de la fecha de fin");
    this.fecha_inicio = fecha_inicio.toLocalDateTime();
    this.fecha_fin = fecha_fin.toLocalDateTime();
  }


  private boolean verificarFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin){
    return fechaInicio.isBefore(fechaFin);
  }


}
