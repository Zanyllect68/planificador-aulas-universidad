package com.universidad.planificador_aulas.repository;

import com.universidad.planificador_aulas.model.Aula;
import com.universidad.planificador_aulas.model.DiaSemana;
import com.universidad.planificador_aulas.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByAulaAndDiaSemanaAndHoraInicioLessThanAndHoraFinGreaterThan(
            Aula aula,
            DiaSemana diaSemana,
            LocalTime horaFin,
            LocalTime horaInicio
    );
}
