package com.universidad.planificador_aulas.controller.dto;

import java.util.List;

import com.universidad.planificador_aulas.model.Horario;

public class ResultadoGeneracionHorarios {

    private List<Horario> horariosAsignados;
    private List<Long> clasesNoAsignadas;

    public ResultadoGeneracionHorarios(List<Horario> horariosAsignados, List<Long> clasesNoAsignadas) {
        this.horariosAsignados = horariosAsignados;
        this.clasesNoAsignadas = clasesNoAsignadas;
    }

    public List<Horario> getHorariosAsignados() {
        return horariosAsignados;
    }

    public void setHorariosAsignados(List<Horario> horariosAsignados) {
        this.horariosAsignados = horariosAsignados;
    }

    public List<Long> getClasesNoAsignadas() {
        return clasesNoAsignadas;
    }

    public void setClasesNoAsignadas(List<Long> clasesNoAsignadas) {
        this.clasesNoAsignadas = clasesNoAsignadas;
    }
}
