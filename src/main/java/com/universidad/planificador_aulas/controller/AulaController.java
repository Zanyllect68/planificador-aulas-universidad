package com.universidad.planificador_aulas.controller;

import com.universidad.planificador_aulas.model.Aula;
import com.universidad.planificador_aulas.repository.AulaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    private final AulaRepository aulaRepository;

    public AulaController(AulaRepository aulaRepository) {
        this.aulaRepository = aulaRepository;
    }

    @PostMapping
    public ResponseEntity<Aula> crearAula(@RequestBody Aula aula) {
        Aula guardada = aulaRepository.save(aula);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Aula>> listarAulas() {
        List<Aula> aulas = aulaRepository.findAll();
        return ResponseEntity.ok(aulas);
    }
}
