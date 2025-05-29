package com.microservice.paciente.service;

import com.microservice.paciente.model.Paciente;
import com.microservice.paciente.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> findAll(){
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> getPatientById(int id){
        return pacienteRepository.findById(id);
    }

    public Paciente getPatientById2(int id){
        return pacienteRepository.findById(id).get();
    }

    //La funcion save sirve tanto para guardar como para 
    public Paciente save(Paciente paciente){
        return pacienteRepository.save(paciente);
    }

    public void delete(int id){
        pacienteRepository.deleteById(id);
    }

}
