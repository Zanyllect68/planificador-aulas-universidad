package com.universidad.planificador_aulas.controller;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universidad.planificador_aulas.controller.dto.AsignarHorarioRequest;
import com.universidad.planificador_aulas.controller.dto.GenerarHorariosRequest;
import com.universidad.planificador_aulas.controller.dto.ResultadoGeneracionHorarios;
import com.universidad.planificador_aulas.model.Horario;
import com.universidad.planificador_aulas.service.HorarioService;

@RestController
@RequestMapping("/api/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @PostMapping("/asignar")
    public ResponseEntity<Horario> asignarHorario(@RequestBody AsignarHorarioRequest request) {
        Horario horario = horarioService.asignarAula(
                request.getClaseId(),
                request.getDiaSemana(),
                LocalTime.parse(request.getHoraInicio()),
                LocalTime.parse(request.getHoraFin())
        );
        return new ResponseEntity<>(horario, HttpStatus.CREATED);
    }

    @PostMapping("/generar")
    public ResponseEntity<ResultadoGeneracionHorarios> generarHorarios(@RequestBody GenerarHorariosRequest request) {
        ResultadoGeneracionHorarios resultado = horarioService.generarHorariosAutomaticos(request.getIdsClases());
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
}

}
