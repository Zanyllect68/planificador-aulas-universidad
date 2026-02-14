package com.universidad.planificador_aulas.model;

import java.time.LocalTime;

public class FranjaHoraria {

    private final LocalTime inicio;
    private final LocalTime fin;

    public FranjaHoraria(LocalTime inicio, LocalTime fin) {
        this.inicio = inicio;
        this.fin = fin;
    }

    public LocalTime getInicio() {
        return inicio;
    }

    public LocalTime getFin() {
        return fin;
    }
}
