package com.microservice.atencion.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class AtencionDTO {
    @NotNull
    @FutureOrPresent
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_atencion;

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime hora_atencion;

    @NotNull
    @Positive
    private Double costo;

    @NotNull
    @Positive
    private Integer id_paciente;

    @NotBlank
    @Size(max = 500)
    private String comentario;
}
