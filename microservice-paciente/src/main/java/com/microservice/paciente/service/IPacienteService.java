package com.microservice.paciente.service;

import com.microservice.paciente.model.Paciente;
import com.microservice.paciente.http.response.AtentionsByPatientResponse;

import java.util.List;

public interface IPacienteService {
    AtentionsByPatientResponse findAtentionsByIdPatient(int id_paciente);
}
