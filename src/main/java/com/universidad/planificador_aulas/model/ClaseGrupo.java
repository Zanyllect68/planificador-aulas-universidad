package com.universidad.planificador_aulas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;  // ← AÑADIDO AQUÍ
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clases_grupo")
public class ClaseGrupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreMateria;
    private String codigoMateria;
    private String programa;
    private Integer numeroEstudiantes;
    private String docente;

    // ← AÑADIDO CAMPO AQUÍ (después de numeroEstudiantes)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facultad_id")
    private Facultad facultad;

    public ClaseGrupo() {
    }

    public ClaseGrupo(String nombreMateria, String codigoMateria, String programa,
                      Integer numeroEstudiantes, String docente) {
        this.nombreMateria = nombreMateria;
        this.codigoMateria = codigoMateria;
        this.programa = programa;
        this.numeroEstudiantes = numeroEstudiantes;
        this.docente = docente;
    }

    public Long getId() {
        return id;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(String codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Integer getNumeroEstudiantes() {
        return numeroEstudiantes;
    }

    public void setNumeroEstudiantes(Integer numeroEstudiantes) {
        this.numeroEstudiantes = numeroEstudiantes;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    // ← AÑADIDOS MÉTODOS
    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }
}
