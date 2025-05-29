package com.microservice.paciente.controller;

import com.microservice.paciente.model.Paciente;
import com.microservice.paciente.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/listar")
    public List<Paciente> getAllPatients(){
        return pacienteService.findAll();
    }
    
    @GetMapping("/{id_paciente}")
    public ResponseEntity<?> getPatientById(@PathVariable Integer id){
        Optional<Paciente> paciente = pacienteService.getPatientById(id);

        if(paciente.isPresent()){
            //Respuesta exitosa con cabeceras personalizadas (opcional)
            return ResponseEntity.ok()
                    .header("mi-encabezado", "valor")
                    .body(paciente.get());
        } else{
            //Respuesta de error con cuerpo personalizado ( ej: JSON con mensaje)
            Map<String,String> errorBody = new HashMap<>();
            errorBody.put("message","No se encontró el paciente con ID: " + id);
            errorBody.put("status","404");
            errorBody.put("timestamp",LocalDateTime.now().toString());

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorBody);
        }

        //Se usa <?> para permitir que el método retorne:
        //1. Un objeto Paciente ( en caso de éxito, código 200)
        //2. Un objeto de error personalizado(en caso de fallo, código 404)

        //Al usar <?>, no estas limitando el cuerpo de la respuesta a un único tipo (como Paciente)
        //Si no que permites múltiples tipos en la respuesta

    }
    

}
