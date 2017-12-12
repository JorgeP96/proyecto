package com.ingenieria.proyecto;

import java.util.Date;

public class Curso {
    private String idCurso;
    private String nombre;
    private String duracion;
    private Date fInicio;
    private Date fTermino;
    private String horarios;
    private Integer alumnos;
    private Float costo;

    public Curso() {
    }

    public Curso(String idCurso) {
        this.idCurso = idCurso;
    }

    public Curso(String nombre, String duracion, Date fInicio, Date fTermino, String horarios, Integer alumnos, Float costo) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.fInicio = fInicio;
        this.fTermino = fTermino;
        this.horarios = horarios;
        this.alumnos = alumnos;
        this.costo = costo;
    }

    public Curso(String idCurso, String nombre, String duracion, Date fInicio, Date fTermino, String horarios, Integer alumnos, Float costo) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.duracion = duracion;
        this.fInicio = fInicio;
        this.fTermino = fTermino;
        this.horarios = horarios;
        this.alumnos = alumnos;
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

    public Date getfInicio() {
        return fInicio;
    }

    public void setfInicio(Date fInicio) {
        this.fInicio = fInicio;
    }

    public Date getfTermino() {
        return fTermino;
    }

    public void setfTermino(Date fTermino) {
        this.fTermino = fTermino;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public Integer getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Integer alumnos) {
        this.alumnos = alumnos;
    }

    public Float getCosto() {
        return costo;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }
}
