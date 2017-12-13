package com.ingenieria.proyecto;

public class Profesor extends Persona {
    private String departamento;

    public Profesor() {
    }

    public Profesor(String id, String departamento) {
        super(id);
    }

    public Profesor(String nombre, String tipo, Curso curso, String departamento) {
        super(nombre, tipo, curso);
        this.departamento = departamento;
    }

    public Profesor(String id, String nombre, String tipo, Curso curso, String departamento) {
        super(id, nombre, tipo, curso);
        this.departamento = departamento;
    }
}
