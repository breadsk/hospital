package com.microservice.atencion.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalTime;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AtencionDTO {
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_atencion;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora_atencion;
    private Double costo;
    private Integer id_paciente;
    private String comentario;
}
