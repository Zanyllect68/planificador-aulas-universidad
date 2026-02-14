package com.universidad.planificador_aulas.controller.dto;

import java.util.List;

public class GenerarHorariosRequest {

    private List<Long> idsClases;

    public List<Long> getIdsClases() {
        return idsClases;
    }

    public void setIdsClases(List<Long> idsClases) {
        this.idsClases = idsClases;
    }
}
