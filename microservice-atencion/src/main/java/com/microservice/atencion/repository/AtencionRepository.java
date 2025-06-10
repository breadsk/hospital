package com.microservice.atencion.repository;

import com.microservice.atencion.model.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion,Integer>{
    @Query("SELECT a FROM Atencion a WHERE a.id_paciente = :id_paciente")
    List<Atencion> findAllAtention(int id_paciente);
}
