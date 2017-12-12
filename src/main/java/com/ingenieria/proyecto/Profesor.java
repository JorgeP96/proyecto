package com.ingenieria.proyecto;

public class Profesor extends Persona {
    public Profesor() {
    }

    public Profesor(String id) {
        super(id);
    }

    public Profesor(String nombre, String tipo, String departamento, Curso curso) {
        super(nombre, tipo, departamento, curso);
    }

    public Profesor(String id, String nombre, String tipo, String departamento, Curso curso) {
        super(id, nombre, tipo, departamento, curso);
    }
}
