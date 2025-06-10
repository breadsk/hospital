package com.microservice.paciente.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AtencionDTO {
    private LocalDate fecha_atencion;    
    private LocalTime hora_atencion;
    private Double costo;
    private Integer id_paciente;
    private String comentario;
}
