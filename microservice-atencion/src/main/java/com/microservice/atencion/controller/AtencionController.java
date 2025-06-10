package com.microservice.atencion.controller;

import com.microservice.atencion.model.Atencion;
import com.microservice.atencion.service.AtencionService;
import com.microservice.atencion.dto.AtencionDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/atenciones")
public class AtencionController {

    @Autowired
    private AtencionService atencionService;

    @GetMapping("/listar")
    public List<Atencion> getAllAtentions(){            
        return atencionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAtentiontById(@PathVariable Integer id){                               
        return atencionService.getAtentiontById(id)
            .map(atencion -> ResponseEntity.ok(atencion))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody Atencion atencion) {
        atencionService.save(atencion);
    }

    @GetMapping("/search-by-patient/{id_paciente}")
    public ResponseEntity<?> findByIdPatient(@PathVariable int id_paciente){
        return ResponseEntity.ok(atencionService.findByIdPaciente(id_paciente));
    }
    
    

}
