package com.universidad.planificador_aulas.repository;

import com.universidad.planificador_aulas.model.ClaseGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClaseGrupoRepository extends JpaRepository<ClaseGrupo, Long> {
}
