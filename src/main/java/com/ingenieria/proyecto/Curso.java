package com.ingenieria.proyecto;

import java.util.Date;

public class Curso {
    private String idCurso;
    private String nombre;
    private String duracion;
    private String fInicio;
    private String fTermino;
    private String horarios;
    private Float costo;

    public Curso() {
    }

    public Curso(String idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(String nombre, String duracion, String fInicio, String fTermino, String horarios, Float costo) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.fInicio = fInicio;
        this.fTermino = fTermino;
        this.horarios = horarios;
        this.costo = costo;
    }

    public Curso(String idCurso, String nombre, String duracion, String fInicio, String fTermino, String horarios, Float costo) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.duracion = duracion;
        this.fInicio = fInicio;
        this.fTermino = fTermino;
        this.horarios = horarios;
        this.costo = costo;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getfInicio() {
        return fInicio;
    }

    public void setfInicio(String fInicio) {
        this.fInicio = fInicio;
    }

    public String getfTermino() {
        return fTermino;
    }

    public void setfTermino(String fTermino) {
        this.fTermino = fTermino;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }
}
