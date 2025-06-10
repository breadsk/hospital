package com.microservice.paciente.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.paciente.dto.AtencionDTO;

import java.util.List;

@FeignClient(name = "msvc-atencion", url = "localhost:9090")
public interface AtencionClient {

    @GetMapping("/api/v1/student/search-by-course/{courseId}")    
    List<AtencionDTO> findAllStudentByCourse(@PathVariable Long courseId);

}
