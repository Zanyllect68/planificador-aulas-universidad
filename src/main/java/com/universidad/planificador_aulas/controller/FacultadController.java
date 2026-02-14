package com.universidad.planificador_aulas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.planificador_aulas.model.Facultad;
import com.universidad.planificador_aulas.repository.FacultadRepository;

@RestController
@RequestMapping("/api/facultades")
public class FacultadController {

    @Autowired
    private FacultadRepository facultadRepository;

    @PostMapping
    public ResponseEntity<Facultad> crear(@RequestBody Facultad facultad) {
        Facultad saved = facultadRepository.save(facultad);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public ResponseEntity<List<Facultad>> listar() {
        return ResponseEntity.ok(facultadRepository.findAll());
    }
}
