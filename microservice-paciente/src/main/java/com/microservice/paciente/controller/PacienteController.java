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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.net.URI;
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
    public ResponseEntity<?> getPatientById(@PathVariable("id_paciente") Integer id){
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
    

    @PostMapping("path")
    public ResponseEntity<?> save(@Valid @RequestBody Paciente paciente){
        try{
            Paciente pacienteGuardado = pacienteService.save(paciente);

            //Uri del nuevo recurso creado ( ej: http://localhost:8080/paciente/5)
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(pacienteGuardado.getId_paciente())
                    .toUri();

            //Respuesta exitosa con cabeceras y cuerpo
            return ResponseEntity
                    .created(location)//Código 201 Created
                    .body(pacienteGuardado);
        } catch(DataIntegrityViolationException e){
            //Ejemplo: Error si hay un campo único duplicado (ej: email repetido)
            Map<String,String> error = new HashMap<>();
            error.put("message","El email ya está registrado");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);//Error 409
        }
    }
 
    @PutMapping("/{id_paciente}")
    public ResponseEntity<Paciente> update(@PathVariable int id,@RequestBody Paciente paciente){
        try{

            Paciente pac = pacienteService.getPatientById2(id);
            pac.setId_paciente(id);
            pac.setRut(paciente.getRut());
            pac.setNombres(paciente.getNombres());
            pac.setApellidos(paciente.getApellidos());
            pac.setFechaNacimiento(paciente.getFechaNacimiento());
            pac.setCorreo(paciente.getCorreo());

            pacienteService.save(paciente);
            return ResponseEntity.ok(paciente);

        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id){
        try{

            pacienteService.delete(id);
            return ResponseEntity.noContent().build();//Operacion exitosa pero no hay
            //contenido para devolver

        }catch(Exception ex){
            return ResponseEntity.notFound().build();
        }
    }

    // @GetMapping("/search-by-patient/{id_paciente}")
    // public ResponseEntity<?> findByIdPatient(@PathVariable int id_paciente) {
    //     System.out.println("El ic del paciente es: -------------------------------" + id_paciente);
    //     return ResponseEntity.ok(pacienteService.)
    // }

}
