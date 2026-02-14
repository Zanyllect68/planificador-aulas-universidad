// src/main/java/com/universidad/planificador_aulas/repository/FacultadRepository.java
package com.universidad.planificador_aulas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.universidad.planificador_aulas.model.Facultad;

@Repository
public interface FacultadRepository extends JpaRepository<Facultad, Long> {
    // Métodos automáticos: save(), findAll(), findById(), deleteById(), etc.
}
