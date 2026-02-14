package com.universidad.planificador_aulas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.planificador_aulas.model.ClaseGrupo;
import com.universidad.planificador_aulas.repository.ClaseGrupoRepository;
import com.universidad.planificador_aulas.repository.FacultadRepository;

@RestController
@RequestMapping("/api/clases")
public class ClaseGrupoController {

    private final ClaseGrupoRepository claseGrupoRepository;
    @SuppressWarnings("unused")  // ← AÑADE ESTO
    private final FacultadRepository facultadRepository;

    public ClaseGrupoController(ClaseGrupoRepository claseGrupoRepository,
                                FacultadRepository facultadRepository) {
        this.claseGrupoRepository = claseGrupoRepository;
        this.facultadRepository = facultadRepository;
    }

    @PostMapping
    public ResponseEntity<ClaseGrupo> crearClase(@RequestBody ClaseGrupo claseGrupo) {
        claseGrupo.setFacultad(null);  // Evita error Hibernate
        ClaseGrupo guardada = claseGrupoRepository.save(claseGrupo);
        return new ResponseEntity<>(guardada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ClaseGrupo>> listarClases() {
        return ResponseEntity.ok(claseGrupoRepository.findAll());
    }
}

