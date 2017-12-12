package com.ingenieria.proyecto;

public abstract class Persona {
    private String id;
    private String nombre;
    private String tipo;
    private String departamento;
    private Curso curso;

    public Persona() {
    }

    public Persona(String id) {
        this.id = id;
    }

    public Persona(String nombre, String tipo, String departamento, Curso curso) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.departamento = departamento;
        this.curso = curso;
    }

    public Persona(String id, String nombre, String tipo, String departamento, Curso curso) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.departamento = departamento;
        this.curso = curso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
