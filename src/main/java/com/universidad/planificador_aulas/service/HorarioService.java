package com.universidad.planificador_aulas.service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.universidad.planificador_aulas.controller.dto.ResultadoGeneracionHorarios;
import com.universidad.planificador_aulas.model.Aula;
import com.universidad.planificador_aulas.model.ClaseGrupo;
import com.universidad.planificador_aulas.model.DiaSemana;
import com.universidad.planificador_aulas.model.FranjaHoraria;
import com.universidad.planificador_aulas.model.Horario;
import com.universidad.planificador_aulas.repository.AulaRepository;
import com.universidad.planificador_aulas.repository.ClaseGrupoRepository;
import com.universidad.planificador_aulas.repository.FacultadRepository;
import com.universidad.planificador_aulas.repository.HorarioRepository;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private final AulaRepository aulaRepository;
    private final ClaseGrupoRepository claseGrupoRepository;
    @SuppressWarnings("unused")
    private final FacultadRepository facultadRepository;

    public HorarioService(HorarioRepository horarioRepository,
                          AulaRepository aulaRepository,
                          ClaseGrupoRepository claseGrupoRepository,
                          FacultadRepository facultadRepository) {
        this.horarioRepository = horarioRepository;
        this.aulaRepository = aulaRepository;
        this.claseGrupoRepository = claseGrupoRepository;
        this.facultadRepository = facultadRepository;
    }

    public Horario asignarAula(Long claseId,
                               DiaSemana dia,
                               LocalTime horaInicio,
                               LocalTime horaFin) {

        Optional<ClaseGrupo> claseOpt = claseGrupoRepository.findById(claseId);
        if (claseOpt.isEmpty()) {
            throw new IllegalArgumentException("ClaseGrupo no encontrada con id: " + claseId);
        }
        ClaseGrupo clase = claseOpt.get();

        List<Aula> aulas = aulaRepository.findAll()
                .stream()
                .filter(a -> a.getCapacidad() != null
                        && clase.getNumeroEstudiantes() != null
                        && a.getCapacidad() >= clase.getNumeroEstudiantes())
                .toList();

        for (Aula aula : aulas) {
            List<Horario> conflictos = horarioRepository
                    .findByAulaAndDiaSemanaAndHoraInicioLessThanAndHoraFinGreaterThan(
                            aula,
                            dia,
                            horaFin,
                            horaInicio
                    );

            if (conflictos.isEmpty()) {
                Horario horario = new Horario(dia, horaInicio, horaFin, aula, clase);
                return horarioRepository.save(horario);
            }
        }

        throw new IllegalStateException("No hay aulas disponibles para ese horario y capacidad.");
    }

    public ResultadoGeneracionHorarios generarHorariosAutomaticos(List<Long> idsClases) {

        List<ClaseGrupo> clases = claseGrupoRepository.findAllById(idsClases)
                .stream()
                .sorted(Comparator.comparing(ClaseGrupo::getNumeroEstudiantes).reversed())
                .toList();

        List<Aula> aulas = aulaRepository.findAll();

        List<FranjaHoraria> franjas = List.of(
                new FranjaHoraria(LocalTime.of(7, 0), LocalTime.of(9, 0)),
                new FranjaHoraria(LocalTime.of(9, 0), LocalTime.of(11, 0)),
                new FranjaHoraria(LocalTime.of(13, 0), LocalTime.of(15, 0)),
                new FranjaHoraria(LocalTime.of(15, 0), LocalTime.of(17, 0)),
                new FranjaHoraria(LocalTime.of(17, 0), LocalTime.of(19, 0))
        );

        DiaSemana[] dias = {
                DiaSemana.LUNES,
                DiaSemana.MARTES,
                DiaSemana.MIERCOLES,
                DiaSemana.JUEVES,
                DiaSemana.VIERNES,
                DiaSemana.SABADO
        };

        List<Horario> horariosCreados = new ArrayList<>();
        List<Long> clasesNoAsignadas = new ArrayList<>();

        for (ClaseGrupo clase : clases) {
            boolean asignada = false;

            for (DiaSemana dia : dias) {
                if (asignada) break;

                for (FranjaHoraria franja : franjas) {
                    if (asignada) break;

                    for (Aula aula : aulas) {
                        if (aula.getCapacidad() == null
                                || clase.getNumeroEstudiantes() == null
                                || aula.getCapacidad() < clase.getNumeroEstudiantes()) {
                            continue;
                        }

                        List<Horario> conflictos = horarioRepository
                                .findByAulaAndDiaSemanaAndHoraInicioLessThanAndHoraFinGreaterThan(
                                        aula,
                                        dia,
                                        franja.getFin(),
                                        franja.getInicio()
                                );

                        if (conflictos.isEmpty()) {
                            Horario horario = new Horario(
                                    dia,
                                    franja.getInicio(),
                                    franja.getFin(),
                                    aula,
                                    clase
                            );
                            horariosCreados.add(horarioRepository.save(horario));
                            asignada = true;
                            break;
                        }
                    }
                }
            }

            if (!asignada) {
                clasesNoAsignadas.add(clase.getId());
            }
        }

        return new ResultadoGeneracionHorarios(horariosCreados, clasesNoAsignadas);
    }
}
