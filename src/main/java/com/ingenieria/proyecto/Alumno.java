package com.ingenieria.proyecto;

public class Alumno extends Persona {
    public Alumno() {
    }

    public Alumno(String id) {
        super(id);
    }

    public Alumno(String nombre, String tipo, Curso curso) {
        super(nombre, tipo, curso);
    }

    public Alumno(String id, String nombre, String tipo, Curso curso) {
        super(id, nombre, tipo, curso);
    }
}
