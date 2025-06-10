package com.microservice.paciente.http.response;

import com.microservice.paciente.dto.AtencionDTO;
import java.util.List;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AtentionsByPatientResponse {
    private String rut;
    private String nombres;
    private String apellidos;
    private Date fechaNacimiento;
    private String correo;
    private List<AtencionDTO> atentionDTOList;
    
}